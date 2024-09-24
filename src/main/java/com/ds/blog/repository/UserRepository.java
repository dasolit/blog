package com.ds.blog.repository;

import com.ds.blog.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  // SELECT * FROM user WHERE username = ?1
  Optional<User> findByUsername(String username);
}
