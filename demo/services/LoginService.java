package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Login;
import com.example.demo.repositories.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository LoginRepository;
	

	
}
