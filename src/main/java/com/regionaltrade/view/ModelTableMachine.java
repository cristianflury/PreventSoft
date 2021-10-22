/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regionaltrade.view;


import com.regionaltrade.controller.ControllerDate;
import com.regionaltrade.domain.Machine;

import com.regionaltrade.service.MachineService;

import com.regionaltrade.service.exception.ServiceException;
import com.regionaltrade.service.impl.MachineServiceImpl;


import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class ModelTableMachine extends AbstractTableModel {
    
    private MachineService serviceMachine = new MachineServiceImpl();
    private List<Machine> machines;
    private ControllerDate date = new ControllerDate();
 
   
   
    
    public ModelTableMachine(){
        try {
            this.machines = serviceMachine.findMachines();
        } catch (ServiceException ex) {
            Logger.getLogger(ModelTableMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        
        return machines.size();
    }

    @Override
    public int getColumnCount() {
        
        return 4;
    }

    @Override
    public Object getValueAt(int col, int row) {
                
               
               
                Object[] datos = { machines.get(col).getCode(), machines.get(col).getName(), date.remainingMaintenanceWeeks(machines.get(col)), date.sorter(machines.get(col))};
               
                return datos[row];
    }
    
    @Override
	public String getColumnName(int c) {
		String[] nombreColumnas = {"CÓDIGO EQUIPO", "NOMBRE", "PRÓXIMO MANTENIMIENTO","FECHA"};
		

		return nombreColumnas[c];
	}
     
 
  }
  
    

