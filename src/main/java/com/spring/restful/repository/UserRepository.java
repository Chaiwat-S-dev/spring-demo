package com.spring.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.restful.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByName(String name);
}