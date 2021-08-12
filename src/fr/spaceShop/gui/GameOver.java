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

public class GameOver extends JDialog{
	private JButton menu;
	private String scenario[] = new String[3];
	private JLabel text;
	
	  public GameOver(JFrame parent, int scenarioNb, int score){
		  super(parent, "Game Over", true);
		  this.setSize(600, 750);
		  this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		  JLabel imgEnd =new JLabel(new ImageIcon("files/img/endImg/gameOver" + scenarioNb + ".png"));
		  imgEnd.setPreferredSize(new Dimension(600, 200));
		  this.add(imgEnd);
		  scenario[0]  = "<html><h1>GAME OVER</h1>" +
			  		"<br>H�las pour vous, vos employ�s n'ont pas appr�ci� travailler gratuitement, ils sont venus avec des battes de baseball, vous ont tabass�, ils vous ont m�me arrach� les cheveux !" +
			  		"<br>Sans vous, la terre sera s�rement condamn�e... Triste fin pour vous et le reste de l'humanit�...</html>";
			  scenario[1]  = "<html><h1>GAME OVER</h1>" +
				  		"<br>Nicolache, Nicolache, mon pauvre Nicolache ... Vous qui aviez fui la guerre par peur de mourrir, qui avez cr�� un petit magasin pour s'enfuir plus loin et �viter les taupes carnivores, auriez vous pu vous imaginer que vous seriez tu� par vos propres employ�s ? " +
				  		"<br>C'est ainsi que votre vie prend fin, c'est dommage, enfin pour vous, apr�s on ne va pas se le cacher personne ne viendra vous pleurer... On peut donc dire que c'est triste pour vous, mais pour nous c'est n�gligeable.</html>";
			  scenario[2]  = "<html><h1>GAME OVER</h1>" +
				  		"<br>H�las pour vous, l'amour ne suffit pas pour payer vos employ�s... Enferm� dans l'arriere boutique alors qu'ils essaiyaient de d�foncer la porte, vous aviez beau chanter \"Imagine un monde sans argent !\", ils ont continu�, jusqu'� ce que la porte c�de.<br>" +
				  		"<br>Quelques secondes apr�s, vous avez rejoint le paradis des PDG. Arm� d'une chaussure, l'un de vos employ�s vous frappa avant de vous arracher votre belle chevelure. C'est ainsi qu'avec votre mort - au grand regret des alterspatialiste - l'ordre de l'espace tel qu'il est aujourd'hui gagne...</html>";
			  text = new JLabel(scenario[scenarioNb-1]);
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