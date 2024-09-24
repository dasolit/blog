package com.ds.blog.controller;

import com.ds.blog.model.KakaoProfile;
import com.ds.blog.model.OAuthToken;
import com.ds.blog.model.User;
import com.ds.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
public class UserController {

  @Value("${rest-api}")
  private String restApi;

  @Value("${blog.key}")
  private String blogKey;

  private final UserService userService;

  private final AuthenticationConfiguration authenticationConfiguration;

  @GetMapping("/auth/joinForm")
  public String joinForm(){
    return "user/joinForm";
  }

  @GetMapping("/auth/loginForm")
  public String loginForm(Model model){
    model.addAttribute("restApi", restApi);
    return "user/loginForm";
  }

  @GetMapping("/user/updateForm")
  public String updateForm(){
    return "user/updateForm";
  }

  @GetMapping("/auth/kakao/callback")
  public String kakaoCallback(
      String code,
      HttpSession session
  ) throws Exception {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", restApi);
    params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
    params.add("code", code);

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
    ResponseEntity<String> response = restTemplate
        .exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, request, String.class);

    ObjectMapper objectMapper = new ObjectMapper();
    OAuthToken oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

    RestTemplate restTemplate1 = new RestTemplate();
    HttpHeaders headers1 = new HttpHeaders();
    headers1.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
    headers1.add("Authorization", "Bearer " + oauthToken.getAccess_token());
    MultiValueMap<String, String> params1 = new LinkedMultiValueMap<>();

    HttpEntity<MultiValueMap<String, String>> request1 = new HttpEntity<>(params1, headers1);
    ResponseEntity<String> response1 = restTemplate1
        .exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, request1, String.class);

    ObjectMapper objectMapper1= new ObjectMapper();
    KakaoProfile kakaoProfile = objectMapper1.readValue(response1.getBody(), KakaoProfile.class);

    User user = User.builder()
        .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
        .password(blogKey)
        .oauth("kakao")
        .email(kakaoProfile.getKakao_account().getEmail())
        .build();

    User isUser = userService.회원찾기(user.getUsername());
    if (isUser.getUsername() == null){
      userService.회원가입(user);
    }

    Authentication authentication =
        authenticationConfiguration
            .getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), blogKey));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    return "redirect:/";
  }
}
