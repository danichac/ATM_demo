package com.danielc.repositories;

import java.util.Optional;

public interface Repository<T, ID> {
	void save(T entity);
	Iterable<T> findAll();
	Optional<T> findById(ID id);
	long count();
	void update(T entity);
	void delete(T entity);
}
