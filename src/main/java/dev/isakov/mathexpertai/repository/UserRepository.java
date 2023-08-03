package dev.isakov.mathexpertai.repository;

import dev.isakov.mathexpertai.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserCredentials, Long> {
}
