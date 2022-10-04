package com.greenmart.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenmart.pojos.User;

public interface UserDao1 extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
