/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regionaltrade.service.impl;

import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.repository.PreventiveRepository;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import com.regionaltrade.repository.impl.PreventiveRepositoryImpl;
import com.regionaltrade.service.PreventiveService;
import com.regionaltrade.service.exception.ServiceException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Cristian
 */
public class PreventiveServiceImpl implements PreventiveService {
    
    private final PreventiveRepository preventive;

    public PreventiveServiceImpl() {
        
        this.preventive = new PreventiveRepositoryImpl();
    }
    
    

    @Override
    public PreventiveHistory savePreventive(PreventiveHistory preventive) throws ServiceException {
        
        try {
            return this.preventive.insert(preventive);
        } catch (DuplicateException ex) {
           throw new ServiceException("Preventivo duplicado" + ex.getMessage(), ex);
        } catch (GenericException ex) {
            throw new ServiceException("No se ha podido guardar el preventivo", ex);
        }
    }

    @Override
    public PreventiveHistory getPreventiveByCodeMachine(String code)throws GenericException, NullPointerException {
        
       
            
        try {
            return this.preventive.getByCodeMachine(code);
        } catch (GenericException ex) {
            throw new GenericException("No se pudo obtener el equipo");
        } catch (NullPointerException ex) {
            throw new NullPointerException("El equipo con código " + code + " no existe");
        }
       
    }

    @Override
    public List<PreventiveHistory> findPreventivesByCodeMachine(String code) throws ServiceException {
        
        try {
            return this.preventive.findAllByCodeMachine(code);
        } catch (GenericException ex) {
           throw new ServiceException("No se ha podido obtener el listado de preventivos con código=" + code, ex);
        }
    }
    
}
