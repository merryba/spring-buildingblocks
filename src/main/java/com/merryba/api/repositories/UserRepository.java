package com.merryba.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.merryba.api.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	public User findByUsername(String username);

}
