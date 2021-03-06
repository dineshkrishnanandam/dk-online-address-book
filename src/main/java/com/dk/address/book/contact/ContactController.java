package com.dk.address.book.contact;

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
public class ContactController {


	@Value("${view.add.contact}")
	String addContactViewName;

	@Autowired
	ContactRepository contactRepository;

	@GetMapping("/add-contact")
	public String addContact() {
		return addContactViewName;
	}

	@GetMapping("/update-contact")
	public String updateContact(@RequestParam Integer id , Model model) {

		Optional<Contact> checking = contactRepository.findById(id);
		Contact a = checking.orElse(new Contact());
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
		Contact contact = new Contact();
		contact.setName(name);
		contact.setAge(age);
		contact.setEmail(email);
		contact.setPhNumber(phNumber);
		contact.setAddress(address);
		contact.setId(id);
		

		contactRepository.save(contact);

		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("phNumber", phNumber);
		model.addAttribute("email", email);
		model.addAttribute("address", address);
		model.addAttribute("id", id);
		
		return "contact_modify_success";

	}

	
	
	
	
	@GetMapping("/list-contact")
	public String listContact(Model model) {
		Iterable<Contact> contactList = contactRepository.findAll();
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
		
		
		
		Contact contact = new Contact();
		contact.setName(name);
		contact.setAge(age);
		contact.setEmail(email);
		contact.setPhNumber(phNumber);
		contact.setAddress(address);

		contactRepository.save(contact);

		model.addAttribute("name", name);
		model.addAttribute("age", age);
		model.addAttribute("phNumber", phNumber);
		model.addAttribute("email", email);
		model.addAttribute("address", address);

		return "contact_add_success";

	}

}
