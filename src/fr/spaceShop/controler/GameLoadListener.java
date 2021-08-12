package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.spaceShop.gui.GameLoad;

public class GameLoadListener implements ActionListener{
	private JButton menu;
	private JButton stats;
	private JButton nextDay;
	private JButton nextWeek;
	
	public GameLoadListener(JButton menu, JButton stats, JButton nextDay, JButton nextWeek, GameLoad window){
		this.menu = menu;
		this.stats = stats;
		this.nextDay = nextDay;
		this.nextWeek = nextWeek;
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == menu){
			Controler.Menu();
			
		}
		else if(e.getSource() == stats) {
			Controler.displayStats();
		}
		else if(e.getSource() == nextDay) {
			Controler.gameLoop(1);
		}
		else if(e.getSource() == nextWeek) {
			Controler.gameLoop(7);
		}
		else{ 
			Controler.reloadProductManager();
		}
	}	
}