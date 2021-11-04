package com.dk.address.book.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {
	
	@Value("${view.add.user}")
	String addUserViewName;

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/add-user")
	public String addUser() {
		return addUserViewName;
	}

	

	@PostMapping("/add-user")
	public String addUser(@RequestParam String userId, @RequestParam String password, @RequestParam String firstName,
			@RequestParam String lastName, Model model) {

		User user = new User();

		user.setUserId(userId);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		userRepository.save(user);

		model.addAttribute("userId", userId);
		model.addAttribute("password", password);
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);

		return "user_add_success";

	}

}
