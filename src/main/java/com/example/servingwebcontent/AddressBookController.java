package com.example.servingwebcontent;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressBookController {

//	@Value("${value.from.file}")
	@Value("${view.add.contact}")
	String addContactViewName;

	@Autowired
	AddressRepository addressRepository;

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}

	@GetMapping("/add-contact")
	public String addContact() {
		return addContactViewName;
	}

	@GetMapping("/update-contact")
	public String updateContact(@RequestParam Integer id , Model model) {

		Optional<Address> checking = addressRepository.findById(id);
		Address a = checking.orElse(new Address());
		model.addAttribute("contactRecord", a);
		return "update_contact";
	}
	
	
	@PostMapping("/update-contact")
	public String updatingTheContact(@RequestParam String name, 
			@RequestParam Integer id,
			@RequestParam String age,
			@RequestParam String phNumber,
			@RequestParam String email, 
			@RequestParam String address, 
			Model model) {
		Address contact = new Address();
		contact.setName(name);
		contact.setAge(age);
		contact.setEmail(email);
		contact.setPhNumber(phNumber);
		contact.setAddress(address);
		contact.setId(id);
		

		addressRepository.save(contact);

		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("phNumber", phNumber);
		model.addAttribute("email", email);
		model.addAttribute("address", address);
		model.addAttribute("id", id);
		
		return "contact_add_success";

	}

	
	
	
	
	@GetMapping("/list-contact")
	public String listContact(Model model) {
		Iterable<Address> contactList = addressRepository.findAll();
		model.addAttribute("contacts", contactList);
		return "list_contact";
	}

//	@GetMapping("/add-contact")
//	public String addContact() {
//		return "add_contact";
//	}

	@PostMapping("/add-contact")
	public String addContact(@RequestParam String name, 
			@RequestParam String age,
			@RequestParam String phNumber,
			@RequestParam String email, 
			@RequestParam String address, 
			Model model) {
		
		
		
		Address contact = new Address();
		contact.setName(name);
		contact.setAge(age);
		contact.setEmail(email);
		contact.setPhNumber(phNumber);
		contact.setAddress(address);

		addressRepository.save(contact);

		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("phNumber", phNumber);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

		return "contact_add_success";

	}

}
