package com.thecodeveal.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thecodeveal.app.model.Authority;
import com.thecodeveal.app.model.User;
import com.thecodeveal.app.repo.UserDetailsRepository;
import com.thecodeveal.app.service.CustomUserService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class AppController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	CustomUserService customUserService;

	
	@PostMapping("/generatemail/{email}/{virtusaemail}/{password}")
	public boolean insertMail(@PathVariable("email") String email ,
			@PathVariable("virtusaemail") String virtusaemail , @PathVariable("password") String password ) throws Exception
	{
		
		
		
		if((userDetailsRepository.findByUsername(email)==null)||userDetailsRepository.findByUsername(email).getEmailGeneration())
		{
			System.out.println("invalid");
			throw new Exception("invalid email");
		}
		
		
		
		
		List<Authority>authorityList=new ArrayList<>();
		
		authorityList.add(new Authority("EMP"));
		
		User user =new User();
		
		
		
		user.setUsername(virtusaemail);
		
		user.setPassword(passwordEncoder.encode(password));
		
		user.setAuthorites(authorityList);
		
		user.setEmailGeneration(true);
		
		//user.setMailGeneration(true);
		
		userDetailsRepository.save(user);
		
		user=userDetailsRepository.findByUsername(email);
		
		user.setEmailGeneration(true);
		
		customUserService.updateDetails(user);
		
		System.out.println(user.getUsername()+" "+user.getPassword());
		
		return true;
		
	}
	
	
	@PostMapping("/details/{username}")
	public boolean updateDetails(@RequestBody User user , @PathVariable("username") String email )
	{
		
		
		System.out.println(user.getAddressone()+" "+user.getAddresstwo()+" "+user.getDateofbirth());
		
		
		User u = userDetailsRepository.findByUsername(email);
		
		u.setFirstname(user.getFirstname());
		u.setMiddlename(user.getMiddlename());
		u.setLastname(user.getLastname());
		u.setFathername(user.getFathername());
		u.setMothername(user.getMothername());
		u.setMobilenumber(user.getMobilenumber());
		u.setAlternatemobilenumber(user.getAlternatemobilenumber());
		u.setFathermobilenumber(user.getFathermobilenumber());
		u.setMothermobilenumber(user.getMothermobilenumber());
		u.setPincode(user.getPincode());
		u.setDateofbirth(user.getDateofbirth());
		u.setPersonalemail(user.getPersonalemail());
     	u.setAddressone(user.getAddressone());
		u.setAddresstwo(user.getAddresstwo());
		
		customUserService.updateDetails(u);
		
		
		return true;
	}
	
	
	
	
	
	
	
}
