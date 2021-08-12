package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fr.spaceShop.modele.humans.actions.PDG;

public class PDGAdmnistratorListener implements ActionListener{
	private JButton sendMoney;
	private JButton exit;
	private boolean notMsgError = true;
	private JTextField money;
	private PDG pdg;
	private JDialog window;
	public PDGAdmnistratorListener(JButton sendMoney, JButton exit, JTextField money , PDG pdg, JDialog window){
		this.sendMoney= sendMoney;
		this.exit = exit;
		this.money = money;
		this.pdg = pdg;
		this.window = window;
	}
	
	
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == exit){
			end();
		}
		else if(e.getSource() == sendMoney && notMsgError) {
			if(Integer.valueOf(money.getText())> pdg.getMoney()){
				JOptionPane error = new JOptionPane();
				int option = error.showConfirmDialog(null, "Saisie incorrecte" , "Erreur dans l'envoie" , JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			else {
				Controler.pdgGiveToShop(Integer.valueOf(money.getText()));
			}
			notMsgError = false;
			end();
		}
	
	}
	
	private void end(){
		window.setVisible(false);
		  Controler.actualise();
	  }
}
