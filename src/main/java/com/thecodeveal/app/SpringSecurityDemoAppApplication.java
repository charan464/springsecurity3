package com.thecodeveal.app;

import javax.annotation.PostConstruct;

import org.jboss.jandex.VoidType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.thecodeveal.app.model.Authority;
import com.thecodeveal.app.model.User;
import com.thecodeveal.app.repo.UserDetailsRepository;

import java.util.*;

@SpringBootApplication
public class SpringSecurityDemoAppApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoAppApplication.class, args);
	}
	
	@PostConstruct
	protected void init()
	{
		List<Authority>authorityList=new ArrayList<>();
		
		authorityList.add(new Authority("USER"));
		
		User user =new User();
		
		user.setUsername("saicharanpoleboina");
		
		user.setPassword(passwordEncoder.encode("charan@464"));
		
		user.setAuthorites(authorityList);
		
		userDetailsRepository.save(user);
		
		System.out.println(user.getUsername() +" "+user.getPassword());
		
	}

    private Authority createAuthority(String roleName)
    {
    	Authority authority=new Authority();
    	authority.setRoleName(roleName);
    	return authority;
    	
    	
    }
	
	
	
	
	
}
