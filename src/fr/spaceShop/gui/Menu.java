package fr.spaceShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.spaceShop.controler.MenuListener;

public class Menu extends JPanel{
	
	private JLabel logo = new JLabel(new ImageIcon( "files/img/logo.png"));
	private Background background = new Background();
	private JPanel content = new JPanel();
	private JButton newGame = new JButton(new ImageIcon("files/img/newGame.png"));
	private JButton loadGame = new JButton(new ImageIcon("files/img/loadGame.png"));
	private JButton exit = new JButton(new ImageIcon("files/img/exit.png"));
	
	
	public Menu(int width, int height){ 
		MenuListener menuListener = new MenuListener(newGame, loadGame);
		this.setBackground(Color.black); 
		this.add(background);
	    //there is a game in files ?
		ObjectInputStream ois;
		boolean isAlreadyGameFile = false;
		try {
    		ois = new ObjectInputStream(
    	              new BufferedInputStream(
    	                new FileInputStream(
    	                  new File("files/isGame"))));
    	            

    	      isAlreadyGameFile =(Boolean) ois.readBoolean();
    	      ois.close();
    	    } catch (FileNotFoundException e) {
    	      e.printStackTrace();
    	} catch (IOException e) {
	      e.printStackTrace();
	    }
		
		
	    //logo
	    logo.setPreferredSize(new Dimension(800,200));
	    
	    //LogoBottomMarge
	    JPanel logoBottomMarge = new JPanel();
	    logoBottomMarge.setPreferredSize(new Dimension(800, (height-600)/4));
	    logoBottomMarge.setOpaque(false);
	    
	    //newGame
	    newGame.addActionListener(menuListener);
	    newGame.setPreferredSize(new Dimension(800,200));
	    
	    //newGameBottomMarge
	    JPanel newGameBottomMarge = new JPanel();
	    newGameBottomMarge.setPreferredSize(new Dimension(800, (height-600)/4)); //whey /4 and not /2 ?
	    newGameBottomMarge.setOpaque(false);
	    
	    //loadGame
	    loadGame.addActionListener(menuListener);
	    loadGame.setEnabled(isAlreadyGameFile);
	    loadGame.setPreferredSize(new Dimension(800,200));
	    
	    //bottomMarge
	    JPanel bottomLeft = new JPanel();
	    bottomLeft.setPreferredSize(new Dimension((width-800)/2, 200)); //whey /4 and not /2 ?
	    bottomLeft.setOpaque(false);
	    exit.setPreferredSize(new Dimension(200, 198));
	    exit.addActionListener(menuListener);
	    bottomLeft.add(exit);
	    
	    JPanel bottomRight = new JPanel();
	    bottomRight.setPreferredSize(new Dimension((width-800)/2, (height-600)/4)); //whey /4 and not /2 ?
	    bottomRight.setOpaque(false);
	   
	    
	    //content
	    content.setPreferredSize(new Dimension(width, height));
	    content.setOpaque(false);
	    content.setLayout(new GridBagLayout());

	    
	    GridBagConstraints gbc = new GridBagConstraints();

	        
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    
	    //logo
	    gbc.gridy = 0;
	    gbc.gridx = 1;
	    content.add(logo, gbc);
	    
	    //logoBottomMargeWest
	    gbc.gridy = 1;
	    gbc.gridx = 1;
	    content.add(logoBottomMarge, gbc);
	    
	    //newGame
	    gbc.gridy = 2;
	    gbc.gridx = 1;
	    content.add(newGame, gbc);
	    
	    //newGameBottomMargeWest
	    gbc.gridy = 3;
	    gbc.gridx = 1;
	    content.add(newGameBottomMarge, gbc);
	    
	    //loadGame
	    gbc.gridy = 4;
	    gbc.gridx = 1;
	    content.add(loadGame, gbc);
	    
	    //button exit
	    gbc.gridy = 4;
	    gbc.gridx = 0;
	    content.add(bottomLeft, gbc);
	    
	    //border
	    gbc.gridy = 4;
	    gbc.gridx = 2;
	    content.add(bottomRight, gbc);
	   
	    background.add(content);
	}	
	
}
