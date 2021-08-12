package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class MenuListener implements ActionListener{
	private JButton loadGame;
	private JButton newGame;
	
	public MenuListener( JButton newGame, JButton loadGame){
		this.loadGame = loadGame;
		this.newGame = newGame;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == loadGame){
			try {
				Controler.load();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == newGame) {
			Controler.newGame();
		}
		else{
			System.exit(0);
		}
	}
}
