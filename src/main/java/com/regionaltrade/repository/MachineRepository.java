package com.regionaltrade.repository;

import com.regionaltrade.domain.Machine;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface MachineRepository {
    
    public Machine insert(Machine machine) throws DuplicateException, GenericException;
    
    public List<Machine> findAll() throws GenericException;
    
    public Machine getByCode(String code) throws GenericException;
    
    public Machine update(Machine machine) throws GenericException;
    
    public Machine delete(String code) throws GenericException;
    
    
    
    
}
