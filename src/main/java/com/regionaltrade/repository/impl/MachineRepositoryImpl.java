package com.regionaltrade.repository.impl;

import com.regionaltrade.domain.Machine;
import com.regionaltrade.repository.MachineRepository;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import com.regionaltrade.repository.hibernate.HibernateBaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;



public class MachineRepositoryImpl extends HibernateBaseRepository implements MachineRepository {

	public MachineRepositoryImpl() {
		super();
	}
	
	@Override
	public Machine insert(Machine machine) throws DuplicateException, GenericException{
		
		Session session = factory.getCurrentSession();

		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			session.saveOrUpdate(machine);
			
			// Commit data.
			session.getTransaction().commit();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
			throw new DuplicateException(e.getCause().getMessage(),e);
		} catch (Exception e) {
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericException(e.getMessage(),e);
		}finally {
			session.close();
		}
		return machine;
	}

	@Override
	public List<Machine> findAll() throws GenericException {
		
		Session session = factory.getCurrentSession();

		List<Machine> machines = new ArrayList<>();
		
		try {

			// All the action with DB via Hibernate
			// must be located in one transaction.
			// Start Transaction.
			session.getTransaction().begin();

			// Create an HQL statement, query the object.
			String sql = "Select e from " + Machine.class.getName() + " e ";

			// Create Query object.
			Query<Machine> query = session.createQuery(sql);

			// Execute query.
			machines = query.getResultList();

			// Commit data.
			session.getTransaction().commit();

		} catch (Exception e) {
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
			throw new GenericException("Error consultando lista de equipos",e);
		}finally {
			session.close();
		}
		
		return machines;
	}

    @Override
    public Machine getByCode(String code) throws GenericException {
        
            Session session = factory.getCurrentSession();
            Machine machine = null;
            
            try {
                // All the action with DB via Hibernate
                // must be located in one transaction.
                // Start Transaction.
                session.getTransaction().begin();
                
                // Create an HQL statement, query the object.
                String sql = "Select machine from " + Machine.class.getName() + " machine where machine.code=:code";
                
                //Create Query object;
                Query<Machine> query = session.createQuery(sql);
                
                query.setParameter("code", code);
                
                // Execute query.
                Optional<Machine> employees = query.uniqueResultOptional();
                
                if(employees.isPresent()){
                    machine = employees.get();
                }
                
                //Commit data.
                session.getTransaction().commit();
                
            }catch(Exception e){
                //Rollback in case of an error ocurred.
                session.getTransaction().rollback();
                throw new GenericException(e.getMessage(), e);
            }finally {
                
                session.close();
            }
        
        return machine;
    }
    
    

    @Override
    public Machine update(Machine machine) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Machine delete(String code) throws GenericException {
        Machine machine = getByCode(code);
        
        Session session = factory.getCurrentSession();
        
        try {
            // All the action with DB via Hibernate
	    // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();
            
            session.remove(machine);
            
            //Commit Data
            session.getTransaction().commit();
            
        }catch (Exception e){
            e.printStackTrace();
            // Rollback in case of an error occurred.
             session.getTransaction().rollback();
            throw new GenericException(e.getMessage(),e);
        }finally {
            
            session.close();
        }
        
        return machine;
    }

	
}
