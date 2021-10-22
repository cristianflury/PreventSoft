package com.regionaltrade.repository.hibernate;

import org.hibernate.SessionFactory;

public abstract class HibernateBaseRepository {

	protected SessionFactory factory;
	
	public HibernateBaseRepository() {
		factory = HibernateUtils.getSessionFactory();
	}		
	
}