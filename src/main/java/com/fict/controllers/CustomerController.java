package com.fict.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fict.entities.Customer;
import com.fict.entities.Order;
import com.fict.services.CreditorService;
import com.fict.services.CustomerService;
import com.fict.services.OrderService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	@Autowired
   	private CreditorService creditorService;
	
	@RequestMapping("/admin/customer/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerService.findCustomerById(id);
	}
	
	@RequestMapping("/customer")
    public Customer getAuthenticatedCustomer(Principal principal){
	    return customerService.findCustomerByEmail(principal.getName());
    }
	
	@RequestMapping("/user")
	public Principal test(Principal principal){
		return principal;
	}
	
	@RequestMapping(value = "/customer/register", method = RequestMethod.POST)
	public Customer registerCustomer(@RequestBody Customer customer){
		return customerService.registerCustomer(customer);
	}
	
	
}