/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.regionaltrade.controller;

import com.regionaltrade.domain.Machine;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;


/**
 *
 * @author Cristian
 */
public class ControllerDate {
    
     public boolean isMaintenance(Machine machine){
        boolean response = false;
        
        int rta = machine.getNext_maintenance().compareTo(LocalDate.now());
        if(rta == 0) response = true;
        
        return response;
    }
    
    public String remainingMaintenanceWeeks(Machine main){
        
        
        String response = "";
        Long days = DAYS.between(LocalDate.now() ,main.getNext_maintenance());
        
        if(days< 7)response = "Faltan " + days + " días";   
        if(days >= 7) response = "Faltan " + days / 7 + " semanas";
        if(days <= 0) response = "ALERTA!! HACER MANTENIMIENTO";
        return response;
    }
    
    public LocalDate sorter(Machine main){
        
        
        return main.getNext_maintenance();
    }
    
}
