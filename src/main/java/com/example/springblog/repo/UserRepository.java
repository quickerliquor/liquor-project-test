package com.example.springblog.repo;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findById(long id);
  User findByUsername(String username);
  User findByEmail(String email);
}
