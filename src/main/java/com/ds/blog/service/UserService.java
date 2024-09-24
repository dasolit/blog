package com.ds.blog.service;

import com.ds.blog.model.RoleType;
import com.ds.blog.model.User;
import com.ds.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public void 회원가입(User user) {
    String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encPassword);
    user.setRole(RoleType.USER);
    userRepository.save(user);
  }

  @Transactional
  public void 회원수정(User user) throws Exception {
    User persistance = userRepository.findById(user.getId())
        .orElseThrow(() -> {
          return new IllegalArgumentException("회원을 찾지 못했습니다.");
        });
    if (persistance.getOauth() == null || persistance.getOauth().equals("")){
      String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
      persistance.setPassword(encPassword);
      persistance.setEmail(user.getEmail());
    }
  }

  @Transactional(readOnly = true)
  public User 회원찾기(String username) {
    return userRepository.findByUsername(username).orElseGet(User::new);
  }
}
