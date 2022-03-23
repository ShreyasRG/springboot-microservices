package com.microservice.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.currencyconversionservice.bean.CurrencyConversion;

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retriveExchange(
			@PathVariable("from") String from,
			@PathVariable("to") String to);
}
