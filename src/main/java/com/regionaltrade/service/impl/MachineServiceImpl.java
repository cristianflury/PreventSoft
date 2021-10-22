package com.regionaltrade.service.impl;

import com.regionaltrade.domain.Machine;
import com.regionaltrade.repository.MachineRepository;
import com.regionaltrade.repository.exception.DuplicateException;
import com.regionaltrade.repository.exception.GenericException;
import com.regionaltrade.repository.impl.MachineRepositoryImpl;
import com.regionaltrade.service.MachineService;
import com.regionaltrade.service.exception.ServiceException;
import java.util.List;
import javassist.NotFoundException;

/**
 *
 * @author Cristian
 */
public class MachineServiceImpl implements MachineService {
    
    private final MachineRepository machineRepository;
    
    //Constructor
    public MachineServiceImpl(){
        this.machineRepository = new MachineRepositoryImpl();
    }

    //Metodos de acceso a los repositorios
    @Override
    public Machine saveMachine(Machine machine) throws ServiceException {
        try{
            
            return this.machineRepository.insert(machine);
        }catch(DuplicateException e){
            throw new ServiceException("El equipo con código " + machine.getCode() + " ya se encuentra registrado");
        }catch(GenericException e){
            throw new ServiceException("No se podido guardar el equipo");
        }
    }

    @Override
    public List<Machine> findMachines() throws ServiceException {
        try {
            return this.machineRepository.findAll();
        }catch(GenericException e){
            throw new ServiceException("No se ha podido obtener el listado de equipos");
        }
    }

    @Override
    public Machine getMachineByCode(String code) throws ServiceException, NullPointerException {
        try { 
           
            return this.machineRepository.getByCode(code);
        } catch(GenericException e){
            throw new ServiceException("No se ha podido obtener el equipo con código=" + code);
        }
    }

    @Override
    public Machine updateMachine(Machine machine) throws ServiceException {
         try{
            
            return this.machineRepository.insert(machine);
        }catch(DuplicateException e){
            throw new ServiceException("El equipo con código " + machine.getCode() + " no se pudo actualizar");
        }catch(GenericException e){
            throw new ServiceException("No se podido actualizar el equipo");
        }
    }

    @Override
    public Machine deleteMachine(String code) throws ServiceException {
        try {
            return this.machineRepository.delete(code);
        }catch(GenericException e){
            throw new ServiceException("No se pudo eliminar el equipo con código " + code +".");
        }
    }
    
}
