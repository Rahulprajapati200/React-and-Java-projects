package com.reactAndJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactAndJava.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
