package com.regionaltrade.repository;

import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface PreventiveRepository {
    
    public PreventiveHistory insert(PreventiveHistory preventive) throws DuplicateException, GenericException;
    
    public PreventiveHistory getByCodeMachine(String code) throws GenericException, NullPointerException;
    
    public List<PreventiveHistory> findAllByCodeMachine(String code) throws GenericException;
        
    
}
