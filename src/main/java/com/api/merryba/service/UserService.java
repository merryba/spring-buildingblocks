package com.api.merryba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.merryba.Model.User;
import com.api.merryba.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//Get All Users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	//Create User
	public User createUser(User user) {
		return userRepository.save(user);
		
	}
	
	//Get user by Id
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	//find by username
	public Optional<User> getUserByUsername(String username){
		Optional<User> user =userRepository.findByUsername(username);
		return user;
	}
	
	//Update user by ID
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by id
	
	public void  deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent())
			userRepository.deleteById(id);
	}


}
