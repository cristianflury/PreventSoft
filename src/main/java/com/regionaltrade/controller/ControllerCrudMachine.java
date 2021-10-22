package com.regionaltrade.controller;

import com.regionaltrade.domain.Machine;

import com.regionaltrade.enums.CriticalyLevel;
import com.regionaltrade.service.MachineService;

import com.regionaltrade.service.exception.ServiceException;
import com.regionaltrade.service.impl.MachineServiceImpl;

import com.regionaltrade.view.RegistrationForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian
 */
public class ControllerCrudMachine implements ActionListener {

    private final MachineService serviceMachine = new MachineServiceImpl();

    //private final PreventiveService servicePreventive = new PreventiveServiceImpl();
    private Machine machine;

    private final RegistrationForm register;

    public ControllerCrudMachine(RegistrationForm register) {
        this.register = register;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register.jButtonSave) {
            eventSaveMachine();
        }
        if (e.getSource() == register.jButtonSearch) {
            eventSearchMachine();
        }
        if (e.getSource() == register.jButtonEdit) {
            eventEnableEditing();
        }
        if (e.getSource() == register.jButtonUpdate) {
            eventUpdateMachine();
        }
        if (e.getSource() == register.jButtonDelete) {
            eventDeleteMachine();
        }

    }

    private void eventSaveMachine() {
        machine = new Machine();
        machine.setCode(register.getJTextFieldCode().getText());
        machine.setName(register.getJTextFieldName().getText());
        machine.setSector(register.getJTextFieldSector().getText());
        machine.setTask(register.getJTextFieldTask().getText());
        machine.setDescription(register.getJTextAreaDescription().getText());
        machine.setIs_critical(register.getJCheckBoxIsCritical().isSelected());
        String itemSelected = register.getJComboBoxCriticalyLevel().getSelectedItem().toString();
        switch (itemSelected) {
            case "Bajo":
                machine.setCriticaly_level(CriticalyLevel.BAJO);
                break;
            case "Medio":
                machine.setCriticaly_level(CriticalyLevel.MEDIO);
                break;
            case "Alto":
                machine.setCriticaly_level(CriticalyLevel.ALTO);
                break;
            default:
                machine.setCriticaly_level(null);
        }
        Integer numFreq = Integer.parseInt(register.getJSpinnerFrequency().getValue().toString());
        machine.setMaintenance_frequency(numFreq);
        machine.setNext_maintenance(LocalDate.now().plusWeeks(numFreq));

        try {

            serviceMachine.saveMachine(machine);

            refreshForm();

        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void eventSearchMachine() {

        try {
            machine = this.serviceMachine.getMachineByCode(register.getJTextFieldSearch().getText());
            register.setFieldForm(machine.getCode(),
                    machine.getName(),
                    machine.getSector(),
                    machine.getTask(),
                    machine.getDescription(),
                    machine.getIs_critical(),
                    machine.getCriticaly_level(),
                    machine.getMaintenance_frequency());

            register.getJTextFieldCode().setEditable(false);
            register.getJTextFieldName().setEditable(false);
            register.getJTextFieldSector().setEditable(false);
            register.getJTextFieldTask().setEditable(false);
            register.getJTextAreaDescription().setEditable(false);
            register.getJCheckBoxIsCritical().setEnabled(false);
            register.getJComboBoxCriticalyLevel().setEnabled(false);
            register.getJSpinnerFrequency().setEnabled(false);

        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } catch(NullPointerException e){
             JOptionPane.showMessageDialog(null, "El equipo no existe");
        }
    }

    private void eventEnableEditing() {

        register.getJTextFieldName().setEditable(true);
        register.getJTextFieldSector().setEditable(true);
        register.getJTextFieldTask().setEditable(true);
        register.getJTextAreaDescription().setEditable(true);
        register.getJCheckBoxIsCritical().setEnabled(true);
        register.getJComboBoxCriticalyLevel().setEnabled(true);
        register.getJSpinnerFrequency().setEnabled(true);
    }

    private void refreshForm() {
        register.setFieldForm("", "", "", "", "", false, null, 0);
    }

    private void eventUpdateMachine() {

        try {
            machine = this.serviceMachine.getMachineByCode(register.getJTextFieldCode().getText());
            machine.setId(machine.getId());
            machine.setCode(register.getJTextFieldCode().getText());
            machine.setName(register.getJTextFieldName().getText());
            machine.setSector(register.getJTextFieldSector().getText());
            machine.setTask(register.getJTextFieldTask().getText());
            machine.setDescription(register.getJTextAreaDescription().getText());
            machine.setIs_critical(register.getJCheckBoxIsCritical().isSelected());
            String itemSelected = register.getJComboBoxCriticalyLevel().getSelectedItem().toString();
            switch (itemSelected) {
                case "Bajo":
                    machine.setCriticaly_level(CriticalyLevel.BAJO);
                    break;
                case "Medio":
                    machine.setCriticaly_level(CriticalyLevel.MEDIO);
                    break;
                case "Alto":
                    machine.setCriticaly_level(CriticalyLevel.ALTO);
                    break;
                default:
                    machine.setCriticaly_level(null);
            }
            Integer numFreq = Integer.parseInt(register.getJSpinnerFrequency().getValue().toString());
            if (numFreq != machine.getMaintenance_frequency()) {

                machine.setMaintenance_frequency(numFreq);
                machine.setNext_maintenance(LocalDate.now().plusWeeks(numFreq));
            }

           
            serviceMachine.updateMachine(machine);
            register.getJTextFieldCode().setEditable(true);

            refreshForm();

        } catch (ServiceException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    private void eventDeleteMachine() {

        Integer response = JOptionPane.showConfirmDialog(null, "Está seguro de eliminar el equipo con código " + register.getJTextFieldCode().getText() + " ?", "Eliminación de Equipo", JOptionPane.YES_NO_OPTION);

        if (response == 0) {
            try {
                this.serviceMachine.deleteMachine(register.getJTextFieldCode().getText());
                JOptionPane.showMessageDialog(null, "Equipo eliminado");
                refreshForm();
            } catch (ServiceException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

        }
    }

}
