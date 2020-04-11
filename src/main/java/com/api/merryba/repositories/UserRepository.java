package com.api.merryba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.merryba.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	public Optional<User> findByUsername(String username);

}
