package com.ta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.ta.dtos.ChargeRequest;
import com.ta.dtos.ChargeRequest.Currency;
import com.ta.services.StripeService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StripeController {

	@Autowired
	private StripeService stripeService;

	@PostMapping("/payments")
	public Charge charge(ChargeRequest chargeRequest) throws StripeException {
		chargeRequest.setCurrency(Currency.GBP);
		Charge charge = stripeService.charge(chargeRequest);
		return charge;
	}
}
