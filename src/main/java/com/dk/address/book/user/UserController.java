package com.dk.address.book.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dk.address.book.contact.Contact;
import com.dk.address.book.contact.ContactRepository;

@Controller
public class UserController {
	
	

//	@Value("${value.from.file}")
	@Value("${view.add.contact}")
	String addContactViewName;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/add-contact")
	public String addContact() {
		return addContactViewName;
	}
	
	
	@PostMapping("/add-user")
	public String addUser(@RequestParam String userId, 
			@RequestParam String password,
			@RequestParam String firstName,
			@RequestParam String lastName, 
			Model model) {
		
		
		
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
	
		return "contact_add_success";

	}
	
	
	
	@GetMapping("/list-user")
	public String listUser(Model model) {
		
		Iterable<User> userList = userRepository.findAll();
		model.addAttribute("users", userList);
		return "list_contact";
	}
	
	
	
//
//	@GetMapping("/update-contact")
//	public String updateContact(@RequestParam Integer id , Model model) {
//
//		Optional<Contact> checking = contactRepository.findById(id);
//		Contact a = checking.orElse(new Contact());
//		model.addAttribute("contactRecord", a);
//		return "update_contact";
//	}
//	
//	
//	@PostMapping("/update-contact")
//	public String updatingTheContact(@RequestParam String name, 
//			@RequestParam Integer id,
//			@RequestParam String age,
//			@RequestParam String phNumber,
//			@RequestParam String email, 
//			@RequestParam String address, 
//			Model model) {
//		
//		
//		Contact contact = new Contact();
//		contact.setName(name);
//		contact.setAge(age);
//		contact.setEmail(email);
//		contact.setPhNumber(phNumber);
//		contact.setAddress(address);
//		contact.setId(id);
//		
//
//		contactRepository.save(contact);
//
//		model.addAttribute("name", name);
//		model.addAttribute("age", age);
//		model.addAttribute("phNumber", phNumber);
//		model.addAttribute("email", email);
//		model.addAttribute("address", address);
//		model.addAttribute("id", id);
//		
//		return "contact_update_success";
//
//	}

//	@GetMapping("/add-contact")
//	public String addContact() {
//		return "add_contact";
//	}


}
