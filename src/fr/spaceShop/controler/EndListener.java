package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class EndListener implements ActionListener{
	private JDialog window;
	
	public EndListener(JDialog window){
		this.window = window;
	}
	
	private void end(){
		window.setVisible(false);
	  }
	  
	public void actionPerformed(ActionEvent e) {
		end();
			
	  }
}
