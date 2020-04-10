package com.api.merryba.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.merryba.Model.UserDetails;

@RestController
public class HelloWorldController {
	
	//simpleMethod
	//URI - /helloworld
	//GET
	@RequestMapping(method = RequestMethod.GET,path="/helloworld")
	public String helloWorld() {
		return "Hello World";
		}
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Merryba","Selva","Cupertino");
	}
}
