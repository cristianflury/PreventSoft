package com.regionaltrade.view;

import com.regionaltrade.domain.PreventiveHistory;
import com.regionaltrade.service.PreventiveService;
import com.regionaltrade.service.exception.ServiceException;
import com.regionaltrade.service.impl.PreventiveServiceImpl;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cristian
 */
public class ModelTablePreventive extends AbstractTableModel {
    
    private PreventiveService servicePreventive = new PreventiveServiceImpl();
    private List<PreventiveHistory> preventives;
    private  String code;

    public ModelTablePreventive()  {
        try {
             code = JOptionPane.showInputDialog("Inserte el código del equipo");
            this.preventives = servicePreventive.findPreventivesByCodeMachine(code);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }
    
    

    @Override
    public int getRowCount() {
        
        return preventives.size();
    }

    @Override
    public int getColumnCount() {
        
        return 4;
    }

    @Override
    public Object getValueAt(int col, int row) {
        
       Object[] datos = { preventives.get(col).getCodemachine(), preventives.get(col).getIntervention(), preventives.get(col).getIntervention_date(), preventives.get(col).getResponsible()};
               
                return datos[row];
    }
    
    @Override
	public String getColumnName(int c) {
		String[] nombreColumnas = {"CÓDIGO EQUIPO", "INTERVENCIÓN", "FECHA","RESPONSABLE"};
		

		return nombreColumnas[c];
	}
    
}
