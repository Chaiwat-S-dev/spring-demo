package com.spring.restful.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.spring.restful.model.User;
import com.spring.restful.repository.UserRepository;

@Service
public class UserService {
    private UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.UserRepository = repository;
    }

    public List<User> retrieveUser() {
        return UserRepository.findAll();
    }
    
    // @Cacheable(value = "User", key = "#id", unless = "#result==null")
    public Optional<User> retrieveUser(Long id) {
        System.out.printf("retrive user by id: %lf%n", id);
        return UserRepository.findById(id);
    }

    public List<User> retrieveUser(String name) {
        return UserRepository.findByName(name);
    }

    public User createUser(User User) {
        return UserRepository.save(User);
    }
    
    @CachePut(value = "user", key = "#id")
    public Optional<User> updateUser(Long id, User User) {
        Optional<User> UserOptional = UserRepository.findById(id);
        if (!UserOptional.isPresent()) {
            return UserOptional;
        }
        User.setId(id);
        return Optional.of(UserRepository.save(User));
    }
    
    @CacheEvict(value = "user", key = "#id")
    public boolean deleteUser(Long id) {
        try {
            UserRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
