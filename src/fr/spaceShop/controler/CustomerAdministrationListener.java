package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.spaceShop.gui.CustomerAdministrator;
import fr.spaceShop.modele.humans.actions.Customer;

public class CustomerAdministrationListener implements ActionListener{
	private JButton kill;
	private Customer customer;
	private CustomerAdministrator window;
	
	
	public CustomerAdministrationListener(JButton kill, Customer customer, CustomerAdministrator window){
		this.kill = kill;
		this.customer = customer;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == kill) {
			Controler.putToDeath(customer);
		}
		end();
	}	
	
	private void end(){
		  window.setVisible(false);
		  Controler.actualise();
	  }

}