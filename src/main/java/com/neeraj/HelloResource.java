package com.neeraj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	
	private static final Logger log = LoggerFactory.getLogger(HelloResource.class+"----------------");


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/hello")
	public String getHello() {
		log.info("-----------------------------getHello------------------------------");
		return "Hello JWT";
	}

	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			log.info("----------------------------create token for authanication -------------------------------");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
		}

		log.info("------------------------------get User details-----------------------------");
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		log.info("-----------------------------get token from controller------------------------------");
		final String token = jwtUtil.generateToken(userDetails);
		log.info(userDetails.getPassword()+"---- "+userDetails.getPassword());
		log.info("---- "+token);

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

}
