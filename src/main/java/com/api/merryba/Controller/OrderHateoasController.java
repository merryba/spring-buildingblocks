package com.api.merryba.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.merryba.Model.Order;
import com.api.merryba.Model.User;
import com.api.merryba.exceptions.UserNotFoundException;
import com.api.merryba.repositories.OrderRepository;
import com.api.merryba.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// Get All Orders by userid
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found in Orders");
		
		List<Order>  allorders = userOptional.get().getOrders();
		CollectionModel<Order> finalResources = new CollectionModel<Order>(allorders);
		return finalResources;

	}
}
