package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fr.spaceShop.gui.WelcomingNewGame;


public class WelcomingNewGameListener implements ActionListener{
	  private int scenarioNb = 1;
	  private WelcomingNewGame window;
	  private JButton button[], start;
	  private JLabel yourLogo;
	  private JLabel text;
	  private String scenario[];
	  
	  public WelcomingNewGameListener(WelcomingNewGame window, JButton button[], JButton start,JLabel yourLogo, JLabel text, String scenario[]){
		  this.window = window;
		  this.button = button; 
		  this.start = start;
		  this.yourLogo = yourLogo;
		  this.text = text;
		  this.scenario = scenario;
	  }
	  
	  
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i< 3; i++){
				if(e.getSource()== button[i]){
					yourLogo.setIcon(new ImageIcon("files/img/logo/logo" + (i+1) +".png"));
					text.setText(scenario[i]);
					scenarioNb = i+1;
				}
			}
			if(e.getSource()==start){
				Controler.setScenario(scenarioNb);
				end();
			}
		}
		
		private void end(){
			  window.setVisible(false);
		}
}