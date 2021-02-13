package com.neeraj;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class + "-----------");

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("---------------------------load user by user name--------------------------------");
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User Not found");
		}
		log.info("----------> " + user.toString());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());

	}

}
