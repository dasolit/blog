package com.ds.blog.auth;

import com.ds.blog.model.User;
import com.ds.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User principal = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없음" + username));
    return new PrincipalDetail(principal); // 스프링 시큐리티 세션에 유저 정보가 저장됨
  }
}
