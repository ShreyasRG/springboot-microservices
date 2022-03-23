package com.microservices.currencyexchangeservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment envi;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchange(
			@PathVariable String from,
			@PathVariable String to)
	{
		logger.info("retriveExchange called with {} to {}", from, to);
		//hard-coded values
		//CurrencyExchange ce = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		CurrencyExchange ce = repository.findByFromAndTo(from, to);
		if(ce == null)
		{
			throw new RuntimeException("data not found for : " + from + " to " + to);
		}
		String port = envi.getProperty("local.server.port");
		ce.setEnvironment(port);
		return ce;
	}
}
