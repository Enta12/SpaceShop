package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import fr.spaceShop.gui.WorkerAdmnistrator;
import fr.spaceShop.modele.humans.actions.Worker;

public class WorkerAdmnistratorListener implements ActionListener{
	private boolean notMsgError = true;
	private JButton changeJobs, fire;
	private Worker worker;
	private WorkerAdmnistrator window; 
	
	public WorkerAdmnistratorListener(JButton changeJobs,JButton fire, Worker worker, WorkerAdmnistrator window){
		this.changeJobs = changeJobs;
		this.fire = fire;
		this.worker = worker;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == changeJobs){
			Controler.changeJob(worker);
		}
		else if(e.getSource() == fire) {
			if(notMsgError &&  Controler.shop.getMoney()< worker.getSalary()*1.2){
				JOptionPane error = new JOptionPane();
				int option = error.showConfirmDialog(null, "Argent insufisant" , "Erreur" , JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
				notMsgError = false;
			}
			else {
				Controler.fire(worker);
				Controler.shop.addToMoney((int)-(worker.getSalary()*1.2));
				
			}
			
		}
		end();
	}
	
	private void end(){
		window.setVisible(false);
		  Controler.actualise();
	}
}