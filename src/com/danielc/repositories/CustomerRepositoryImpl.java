package com.danielc.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import com.danielc.entities.Customer;

public class CustomerRepositoryImpl implements CustomerRepository {

	private final Map<Long, Customer> custMap;
	
	public CustomerRepositoryImpl() {
		custMap = new HashMap<>();
	}

	@Override
	public void save(Customer customer) {
		custMap.put(customer.getId(), customer);
	}

	@Override
	public Iterable<Customer> findAll() {
		Iterable<Customer> customers = custMap.values();
		return customers;
	}

	@Override
	public Optional<Customer> findById(Long id) {
		Customer cus = custMap.get(id);
		return Optional.ofNullable(cus);
	}

	@Override
	public long count() {
		return custMap.size();
	}

	@Override
	public void update(Customer customer) {
		custMap.replace(customer.getId(), customer);
	}

	@Override
	public void delete(Customer customer) {
		custMap.remove(customer.getId());
	}

}
