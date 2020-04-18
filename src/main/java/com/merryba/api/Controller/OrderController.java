package com.merryba.api.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merryba.api.Model.Order;
import com.merryba.api.Model.User;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.repositories.OrderRepository;
import com.merryba.api.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// Get All Orders of all users
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		

		return orderRepository.findAll();
	}

	// Get All Orders by userid
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found in Orders");
		return userOptional.get().getOrders();

	}

	// Create Order
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found in Orders");

		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);

	}

	// Get All Orders by orderid
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getAllOrdersByOrderId(@PathVariable Long userid, @PathVariable Long orderid)
			throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("User not found");
		return orderRepository.findByorderid(orderid);

	}

}
