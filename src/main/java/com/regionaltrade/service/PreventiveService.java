/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regionaltrade.service;

import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.repository.exception.GenericException;
import com.regionaltrade.service.exception.ServiceException;
import java.util.List;

/**
 *
 * @author Cristian
 */
public interface PreventiveService {
    
    public PreventiveHistory savePreventive(PreventiveHistory preventive) throws ServiceException;
    
    public PreventiveHistory getPreventiveByCodeMachine(String code) throws GenericException, NullPointerException;
    
     public List<PreventiveHistory> findPreventivesByCodeMachine(String code) throws ServiceException;
}
