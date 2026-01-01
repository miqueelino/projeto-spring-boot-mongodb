package com.jpwork.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpwork.workshopmongo.domain.User;
import com.jpwork.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repo;
	
	
	
	public List<User> findAll(){
		return repo.findAll();
	}

}
