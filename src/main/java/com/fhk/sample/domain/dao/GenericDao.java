package com.fhk.sample.domain.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<P,Q extends Serializable> 
{
	P getOne();
	
	List<P> findAll();

	P getById(final Q id);
	P loadById(final Q id);
	
	void add(final P entity); 
	void update(final P entity);
	void delete(final P entity);
	void deleteById(final Q id);
	void deleteById(final Q [] id);
}
