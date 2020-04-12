package com.api.merryba.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.merryba.Model.User;
import com.api.merryba.exceptions.UserExistsException;
import com.api.merryba.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
		//if user exist using username
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser !=null) {
			throw new UserExistsException("User already exists in repository");
		}
		//if not exists throw userexistsexception
		return userRepository.save(user);
		
	}
	
	//Get user by Id
	public Optional<User> getUserById(Long id)throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("User not found in teh repository");
		return user;
	}
	
	//find by username
	public User getUserByUsername(String username)throws UserNotFoundException{
		User user =userRepository.findByUsername(username);
		
		return user;
	}
	
	//Update user by ID
	
	public User updateUserById(Long id, User user)throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("User not found in the repository,provide the correct user id");
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete user by id
	
	public void  deleteUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if(!optionalUser.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in the repository,provide the correct user id");
		if(userRepository.findById(id).isPresent())
			userRepository.deleteById(id);
	}


}
