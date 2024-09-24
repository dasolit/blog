package com.ds.blog.controller.api;

import com.ds.blog.dto.ResponseDto;
import com.ds.blog.model.User;
import com.ds.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

  private final UserService userService;

  private final AuthenticationConfiguration authenticationConfiguration;

  @PostMapping("/auth/joinProc")
  public ResponseDto<Integer> save(@RequestBody User user) {
    userService.회원가입(user);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }

  @PutMapping("/user")
  public ResponseDto<Integer> update(@RequestBody User user) throws Exception {
    userService.회원수정(user);
    Authentication authentication =
        authenticationConfiguration
            .getAuthenticationManager()
            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
  }
}
