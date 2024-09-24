package com.ds.blog.auth;

import com.ds.blog.model.User;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class PrincipalDetail implements UserDetails {

  private User user; // 콤포지션

  public PrincipalDetail(User user){
    this.user = user;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add((GrantedAuthority) () -> {
      return "ROLE_"+user.getRole();
    });
    return authorities;
  }

  //
  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  //  계정 만료 여부
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  // 계정 잠김 여부
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  // 비밀번호 만료 여부
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  // 계정 활성화 여부
  @Override
  public boolean isEnabled() {
    return true;
  }
}
