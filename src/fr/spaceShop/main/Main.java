package fr.spaceShop.main;

import fr.spaceShop.controler.Controler;
import fr.spaceShop.gui.Window;


public class Main {
	public static void main(String[] args) {
		new Controler(new Window());
	}
}

