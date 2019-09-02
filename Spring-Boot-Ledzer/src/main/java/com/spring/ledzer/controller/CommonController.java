package com.spring.ledzer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ledzer.model.PaymentMode;
import com.spring.ledzer.model.State;
import com.spring.ledzer.repository.PaymentModeRepository;
import com.spring.ledzer.repository.StateRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommonController {

	@Autowired
	StateRepository repository;
	
	@Autowired
	PaymentModeRepository paymentModeRepository;

	@GetMapping("/states")
	public List<State> getAllStates() {
		System.out.println("Get all States...");

		List<State> states = new ArrayList<>();
		repository.findAll().forEach(states::add);

		return states;
	}
	
	@GetMapping("/paymentmodes")
	public List<PaymentMode> getAllPaymentModes() {
		System.out.println("Get all Payment Modes...");
		List<PaymentMode> payment_modes = new ArrayList<>();
		paymentModeRepository.findAll().forEach(payment_modes::add);

		return payment_modes;
	}
	
}
