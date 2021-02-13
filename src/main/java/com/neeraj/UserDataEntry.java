package com.neeraj;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataEntry implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserDataEntry.class + "-----------");

	@Autowired
	private UserRepository repo;

	@Override
	public void run(String... args) throws Exception {
		log.info("---------static data entry from class---------");
		java.util.List<User> list = new ArrayList<>();
		list.add(new User(1, "rv", "rv"));
		list.add(new User(2, "ranvijay", "ranvijay"));
		list.add(new User(3, "neeraj", "neeraj"));
		repo.saveAll(list);
		log.info("----------------data insert succes-----------------");
	}

}
