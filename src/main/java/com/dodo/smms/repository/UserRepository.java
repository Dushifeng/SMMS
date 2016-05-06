package com.dodo.smms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dodo.smms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsernameAndPasswordAndKind(String username,String password,int kind);
}
