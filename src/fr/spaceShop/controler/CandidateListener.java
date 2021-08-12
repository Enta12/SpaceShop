package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.spaceShop.gui.Candidate;
import fr.spaceShop.modele.humans.actions.Worker;

public class CandidateListener implements ActionListener{
	private JButton acceptGuard;
	private JButton acceptSeller;
	private Worker candidate;
	private Candidate window;
	
	public CandidateListener(JButton acceptGuard, JButton acceptSeller, Worker candidate, Candidate window){
		this.acceptGuard= acceptGuard;
		this.acceptSeller = acceptSeller;
		this.candidate = candidate;
		this.window = window;
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == acceptGuard){
			Controler.addWorker(candidate.toGuard());
			
		}
		else if(e.getSource() == acceptSeller) {
			Controler.addWorker(candidate.toSeller());
		}
		end();
	
	}	
	
	private void end(){
		  window.setVisible(false);
		  Controler.actualise();
	  }

}
