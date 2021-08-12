package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

public class AdmnistratorCustomerListener implements ActionListener{
	private List<JButton> customersButtons;
	private List<JDialog> customerAdmnistrator;
	public AdmnistratorCustomerListener( List<JButton> customersButtons, List<JDialog> customerAdmnistrator){
		this.customersButtons = customersButtons;
		this.customerAdmnistrator = customerAdmnistrator;
	}
	public void actionPerformed(ActionEvent e) { 
		for(int i = 0; i<customersButtons.size(); i++){
			if(e.getSource() == customersButtons.get(i)){
				customerAdmnistrator.get(i).setVisible(true);
			}
		}
	}	
}