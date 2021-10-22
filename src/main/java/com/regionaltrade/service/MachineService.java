package com.regionaltrade.service;

import com.regionaltrade.domain.Machine;
import com.regionaltrade.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface MachineService {
    
    public Machine saveMachine(Machine machine) throws ServiceException;
    
    public List<Machine> findMachines() throws ServiceException;
    
    public Machine getMachineByCode(String code) throws ServiceException;
    
    public Machine updateMachine(Machine machine) throws ServiceException;
    
    public Machine deleteMachine(String code) throws ServiceException;
    
    
}
