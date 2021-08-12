package fr.spaceShop.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.spaceShop.controler.EndListener;

public class Win extends JDialog{
	private JButton menu;
	private String scenario[] = new String[3];
	private JLabel text;
	
	  public Win(JFrame parent, int scenarioNb, int score){
		  super(parent, "Game Over", true);
		  
		  this.setSize(600, 750);
		  this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		  JLabel imgEnd =new JLabel(new ImageIcon("files/img/endImg/win" + scenarioNb + ".png"));
		  imgEnd.setPreferredSize(new Dimension(600, 200));
		  this.add(imgEnd);
		  scenario[0]  = "<html><h1>VICTOIRE</h1>" +
			  		"<br>Bravo, vous avez de quoi être fier ! Votre compte en banque est bien garni et voila 10 gaillards prêts à  en découdre ! " +
			  		"<br>Sans vous, la Terre aurait sûrement été condamnée... Mais heureusement vous êtes un vrai guerrier et vous voilà  prêt à  vous battre avec vos hommes pour sa libération !</html>";
			  scenario[1]  = "<html><h1>VICTOIRE</h1>" +
				  		"<br>Nicolache, Nicolache, mon bien heureux Nicolache ... Vous qui aviez fui la guerre par peur de mourrir, qui avez créer un petit magasin pour s'enfuir le plus loin et éviter les taupes carnivores, auriez vous pu vous imaginer que vous arriveriez à  accumuler autant d'argent ? " +
				  		"<br>C'est ainsi que votre ancienne vie prend fin, Vous pouvez vous acheter un super nouveau vaisseau à  générateur d'improbabilité infinie. Avec ce petit bijoux peut commencer votre nouvelle vie !  Ciao les taupes ! Bonjour la vie de riche !</html>";
			  scenario[2]  = "<html><h1>VICTOIRE</h1>" +
				  		"<br>Vous aviez raison, l'amour et la paix gagneront toujours ! Vous avez réuni une petite communauté bien sympatique, bien souriante et prête à  changer les esprits ! On ne peut même pas passer dans votre magasin sans imaginer un nouveau monde ...<br>" +
				  		"<br>Puis avec l'argent que vous avez gagné, le mouvement alterspacialiste ne va que mieux se porter ! Des nouvelles affiches partout, des pubs en hologramme 3D dans les cafés ... Une nouvelle ère commence !</html>";text = new JLabel(scenario[scenarioNb-1]);
		  formateLabel(text);
		  text.setFont(new Font("Courier New", Font.BOLD, 15));
		  text.setPreferredSize(new Dimension(590, 265));
		  JPanel textField = new JPanel();
		  textField.setBackground(Color.LIGHT_GRAY);
		  textField.setPreferredSize(new Dimension(600, 275));
		  textField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		  textField.add(text);
		  this.add(textField);
		  
		  JLabel scoreLabel = new JLabel("Votre score est de " + score);
		  formateLabel(scoreLabel);
		  scoreLabel.setPreferredSize(new Dimension(600, 60));
		  scoreLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		  this.add(scoreLabel);
		  
		  JPanel buttonField = new JPanel();
		  buttonField.setBackground(Color.LIGHT_GRAY);
		  buttonField.setPreferredSize(new Dimension(800, 200));
		  buttonField.setLayout(new FlowLayout(FlowLayout.CENTER,0, 15));
		  this.add(buttonField);
		  menu = new JButton("Revenir au menu");
		  
		  menu.setOpaque(true);
		  menu.setForeground(Color.white);
		  menu.setFont(new Font("Courier New", Font.BOLD, 30));
		  menu.setHorizontalAlignment(JLabel.CENTER);
		  menu.setVerticalAlignment(JLabel.CENTER);
		  menu.setBackground(Color.DARK_GRAY);
		  menu.addActionListener(new EndListener(this));
		  menu.setPreferredSize(new Dimension(400, 150));
		  buttonField.add(menu);
		  
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);
		  this.setVisible(true);

		  }
	
	  
	  private void formateLabel(JLabel label){
		  label.setOpaque(true);
		  label.setForeground(Color.BLACK);
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  label.setBackground(Color.LIGHT_GRAY);
	  }
	  
}