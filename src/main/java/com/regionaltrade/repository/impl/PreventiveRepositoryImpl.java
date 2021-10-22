/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regionaltrade.repository.impl;

import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.repository.PreventiveRepository;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import com.regionaltrade.repository.hibernate.HibernateBaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

/**
 *
 * @author Cristian
 */
public class PreventiveRepositoryImpl extends HibernateBaseRepository implements PreventiveRepository {

    public PreventiveRepositoryImpl() {

        super();
    }

    @Override
    public PreventiveHistory insert(PreventiveHistory preventive) throws DuplicateException, GenericException {
        Session session = factory.getCurrentSession();

        try {

            // All the action with DB via Hibernate
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            session.saveOrUpdate(preventive);

            // Commit data.
            session.getTransaction().commit();
        } catch (ConstraintViolationException e) {
            session.getTransaction().rollback();
            throw new DuplicateException(e.getCause().getMessage(), e);
        } catch (Exception e) {
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
            throw new GenericException(e.getMessage(), e);
        } finally {
            session.close();
        }
        return preventive;

    }

    @Override
    public PreventiveHistory getByCodeMachine(String code) throws GenericException {

        Session session = factory.getCurrentSession();
        PreventiveHistory preventive = null;

        try {
            // All the action with DB via Hibernate
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.
            String sql = "Select preventive_history from " + PreventiveHistory.class.getName() + " preventive_history where preventive_history.codemachine=:code";
            //Create Query object;
            Query<PreventiveHistory> query = session.createQuery(sql);

            query.setParameter("code", code);

            //Excecute query
            Optional<PreventiveHistory> employees = query.uniqueResultOptional();

            if (employees.isPresent()) {
                preventive = employees.get();
            }

            //Commit data.
            session.getTransaction().commit();

        } catch (Exception e) {
            //Rollback in case of an error ocurred.
            session.getTransaction().rollback();
            throw new GenericException(e.getMessage(), e);
        } finally {

            session.close();
        }

        return preventive;
    }

    @Override
    public List<PreventiveHistory> findAllByCodeMachine(String code) throws GenericException {

        Session session = factory.getCurrentSession();
        List<PreventiveHistory> preventives = null;

        try {
            // All the action with DB via Hibernate
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.
            String sql = "Select preventive_history from " + PreventiveHistory.class.getName() + " preventive_history where preventive_history.codemachine=:code";
            //Create Query object;
            Query<PreventiveHistory> query = session.createQuery(sql);

            query.setParameter("code", code);

            //Execute query.
            preventives = query.getResultList();

            // Commit data.
            session.getTransaction().commit();

        } catch (Exception e) {
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
            throw new GenericException("Error consultando lista de preventivos", e);
        } finally {
            session.close();
        }

        return preventives;

    }

}
