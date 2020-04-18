package com.merryba.api.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.merryba.api.Model.Order;
import com.merryba.api.Model.User;
import com.merryba.api.exceptions.UserNotFoundException;
import com.merryba.api.repositories.UserRepository;
import com.merryba.api.service.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		List<User> allusers = userService.getAllUsers();
		for (User user : allusers) {
			// self Link
			Long userid = user.getId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selfLink);
			
			//Relationship Link with getAllOrders
			CollectionModel<Order> orders =ControllerLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(userid);
			Link ordersLink = ControllerLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(ordersLink);
			
		}
		CollectionModel<User> finalResources = new CollectionModel<User>(allusers);
		return finalResources;

	}

	// Get User by id
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();
			Long userid = user.getId();
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			// Link selfLink =
			// linkTo(methodOn(UserHateoasController.class).one(userid)).withSelfRel();
			// Link selfLink =
			// linkTo(UserHateoasController.class).slash(userid).withSelfRel();
			user.add(selfLink);
			EntityModel<User> finalResource = new EntityModel<User>(user);
			return finalResource;
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}

}
