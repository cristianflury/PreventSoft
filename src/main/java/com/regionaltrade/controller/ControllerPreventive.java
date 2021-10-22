package com.regionaltrade.controller;

import com.regionaltrade.domain.Machine;
import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.service.MachineService;
import com.regionaltrade.service.PreventiveService;
import com.regionaltrade.service.exception.ServiceException;
import com.regionaltrade.service.impl.MachineServiceImpl;
import com.regionaltrade.service.impl.PreventiveServiceImpl;
import com.regionaltrade.view.PreventiveWindow;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Cristian
 */
public class ControllerPreventive implements ActionListener {

    private final PreventiveService servicePreventive = new PreventiveServiceImpl();
    private final PreventiveWindow preventive;
    private PreventiveHistory preventiveHistory;
    private final MachineService serviceMachine = new MachineServiceImpl();
    private Machine machine;

    public ControllerPreventive(PreventiveWindow preventiveWindow) {
        this.preventive = preventiveWindow;
    }

    private void eventSavePreventive() {

        preventiveHistory = new PreventiveHistory();
        preventiveHistory.setCodemachine(preventive.getTextFieldCodeMachine().getText());
        preventiveHistory.setResponsible(preventive.getTextFieldResponsible().getText());
        preventiveHistory.setIntervention(preventive.getJTextAreaIntervention().getText());
        preventiveHistory.setIntervention_date(LocalDate.now());

        try {
            
            machine = this.serviceMachine.getMachineByCode(preventive.getTextFieldCodeMachine().getText());
            machine.setId(machine.getId());
            machine.setNext_maintenance(LocalDate.now().plusWeeks(machine.getMaintenance_frequency()));
            this.servicePreventive.savePreventive(preventiveHistory);
            serviceMachine.saveMachine(machine);

            preventive.refresh();

            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "El equipo con código " + preventive.getTextFieldCodeMachine().getText() + " no existe");
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        eventSavePreventive();
    }

}
