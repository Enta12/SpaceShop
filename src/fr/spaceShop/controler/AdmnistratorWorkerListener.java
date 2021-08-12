package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;


public class AdmnistratorWorkerListener implements ActionListener{
	private List<JButton> workersButtons;
	private List<JDialog> workerAdmnistrator;
	private List<Integer>  workerID;
	public AdmnistratorWorkerListener( List<JButton> workersButtons, List<JDialog> workerAdmnistrator, List<Integer>  workerID){
		this.workersButtons = workersButtons;
		this.workerAdmnistrator = workerAdmnistrator;
		this.workerID = workerID;
	}
	public void actionPerformed(ActionEvent e) { 
		for(int i = 0; i<workersButtons.size(); i++){
			if(e.getSource() == workersButtons.get(i)){
				if(workerID.get(i) == Controler.getPDG().getId()) Controler.updatePDGAdministrator();
				else workerAdmnistrator.get(i).setVisible(true);
			}
		}
	}	
}
