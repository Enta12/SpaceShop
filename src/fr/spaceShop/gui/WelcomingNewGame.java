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

import fr.spaceShop.controler.WelcomingNewGameListener;

public class WelcomingNewGame extends JDialog{
	private JButton button[] = new JButton[3], start;
	private String scenario[] = new String[3];
	private JLabel yourLogo;
	private JLabel text;
	
	  public WelcomingNewGame(JFrame parent){
		  super(parent, "Nouvelle partie", true);
		  
		  this.setSize(815, 950);
		  this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		  JLabel logo = new JLabel(new ImageIcon( "files/img/logo.png"));
		  logo.setPreferredSize(new Dimension(800, 200));
		  
		  this.add(logo);
		  scenario[0]  = "<html>Les castors de la CIA ...<br> " +
			  		"<br>En l'an 9056 L'humanit� entra dans une nouvelle �re. En effet, la r�publique dictoriale anti-taupe deuxi�me du nom d�cida, suite aux tensions de la population (provoqu�es par les machines � caf� ne rendant pas les sous), d'entrainer des super-castors.<br> Ces castors �taient cens�s espionner la population en s'infiltrant partout. Ce f�t bien le probl�me, ils furent partout... et lorsque le lieutenant Grande-Dents d�couvrit le fameux secret de l'�tat concernant l'industrie du bois il lan�a la r�bellion... " +
			  		"<br> Il ne fallut que tr�s peu de temps pour �liminer les forces arm�es de la Terre, les castors �taient bien trop puissants et l'humanit� d� battre en retraite dans l'espace... Cr�ant une societ� anarchiste-capitaliste d�sorganis�e et centralis�e sur les restes de la civisation spaciale mais restant tout de m�me capitaliste.<br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le d�nomm� Oragarn, ancien guerrier anti-castors, vous vous �tes retrouv� dans une station spatiale d�nomm�e \"Patisserie\" et avez d�cid� de cr�er un magasin afin d'accumuler assez d'argent et d'hommes fid�les pour vous lancer dans la reconqu�te de la Terre... <br>" +
			  		"<br>OBJECTIF : 10 Gardes, 20 000 spaceuros dans votre compte en banque </html>";
		  scenario[1]  = "<html>Les taupes carnivores ...<br> " +
			  		"<br>En l'an 8056 L'humanit� f�t une �norme b�tise. La formation du gouvernement mondiale venait de se faire. L'�conomie se portait pour le mieux. La famine avait �tait an�antie. Le seul probl�me f�t les �goux.. En effet ceux-ci n'�taient pas adapt�s et n'avaient pas �t� agrandis en m�me temps que l'essort de la population. Le gouvernement avait du mal � trouver des travailleurs pour les creuser, sachant que le taux de ch�mage avait atteint le seuil historique de 0%. Cependant une soci�t� de bio-informatique proposa une solution... " +
			  		"<br> Cette solution �tait la cr�ation de taupes biologiquement modifi�es, pouvant creuser jusqu'� 1km par jour. Elle en cr�a des centaines de milliers et tout semblait marcher pour le mieux... jusqu'au jour ou l'on compris qu'elles �taient carnivore, et se nourrissaient d'�tres humains... Suite � de nombreuses disparitions, l'enqu�te f�t men�e, ce terrible secret d�couvert et la guerre d�clench�e ! <br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le d�nomm� Nicolache, patron d'un petit caf�, vous avez squatt� une station spatial abandonn�e dans une zone non administr�e afin de fuir la guerre. Mais les combats s'intensifient, et vous avez peur qu'il rejoignent votre petit havre de paix. Votre solution ? Vous faire assez d'argent pour partir loiiiiiin, tr�s loiiiiiiiin <br>" +
			  		"<br>OBJECTIF : 100 000 spaceuros dans votre compte en banque </html>";
		  scenario[2]  = "<html>La paix et l'amour ... Mais surtout le fric ! <br> " +
			  		"<br>En l'an 9812 L'humanit� �tait au bord du gouffre. Suite � la guerre contre les castors de la CIA (qui s'�taient alli�s aux super-m�ga-over-pr�dateurs) la Terre avait �t� totalement irradi�e et n'�tait plus propice � la vie. Les derniers humains encore vivant se trouvaient dans l'espace, formant une soci�t� �trange sans lois, sans s�curit� mais o� �trangement, les soci�t�s et le capitalisme prosp�raient. Un mouvement alterspatial voyait le jour peu � peu, pour lutter contre la pervertion de cette espace et cette pr�occupation horrible que l'on appellait \"spaceuros\"." +
			  		"<br>Ces personnes assez peu nombreuses et impuissantes face aux g�ants industriels restaient dans l'ombre... L'avenir semblait vraiment noir et tout le monde baissait les bras et se r�siliait � vivre dans ce monde pourri... �� la fin de l'ann�e il ne restait presque plus aucun alterspacialiste et le mouvement semblait disparaitre ... <br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le d�nomm� Jhon Lemon, musicien renomm�, avait d�cid� de vous lever contre cet espace pourri ! Comment ? En faisant un magasin d'amour qui vous permettra de gagner assez d'argent pour pouvoir promouvoir ce mouvement ! <br>" +
			  		"<br>OBJECTIF : 20 vendeurs, 30 clients et  100 000 spaceuros dans votre compte en banque </html>";
		  text = new JLabel(scenario[0]);
		  formateLabel(text);
		  text.setFont(new Font("Courier New", Font.BOLD, 12));
		  text.setPreferredSize(new Dimension(790, 290));
		  JPanel textField = new JPanel();
		  textField.setBackground(Color.LIGHT_GRAY);
		  textField.setPreferredSize(new Dimension(800, 300));
		  textField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		  textField.add(text);
		  this.add(textField);
		  formateTitle("Sc�nario 1 :");
		  formateTitle("Sc�nario 2 :");
		  formateTitle("Sc�nario 3 :");
		  formateTitle("Votre Sc�nario :");
		  button[0]= new JButton(new ImageIcon("files/img/logo/logo1.png"));
		  formateLogo(button[0]);
		  button[1]= new JButton(new ImageIcon("files/img/logo/logo2.png"));
		  formateLogo(button[1]);
		  button[2]= new JButton(new ImageIcon("files/img/logo/logo3.png"));
		  formateLogo(button[2]);
		  yourLogo = new JLabel(new ImageIcon( "files/img/logo/logo1.png"));
		  this.add(yourLogo);
		  JPanel buttonField = new JPanel();
		  buttonField.setBackground(Color.LIGHT_GRAY);
		  buttonField.setPreferredSize(new Dimension(800, 200));
		  buttonField.setLayout(new FlowLayout(FlowLayout.CENTER,0, 15));
		  this.add(buttonField);
		  yourLogo.setPreferredSize(new Dimension(200, 200));
		  start = new JButton("<html>Partir � ... L'aventure</html>");
		  
		  start.setOpaque(true);
		  start.setForeground(Color.white);
		  start.setFont(new Font("Courier New", Font.BOLD, 30));
		  start.setHorizontalAlignment(JLabel.CENTER);
		  start.setVerticalAlignment(JLabel.CENTER);
		  start.setBackground(Color.DARK_GRAY);
		  WelcomingNewGameListener buttonsListener = new WelcomingNewGameListener(this, button, start, yourLogo, text, scenario);
		  button[0].addActionListener(buttonsListener);
		  button[1].addActionListener(buttonsListener);
		  button[2].addActionListener(buttonsListener);
		  start.addActionListener(buttonsListener);
		  start.setPreferredSize(new Dimension(700, 80));
		  buttonField.add(start);
		  
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);
		  this.setVisible(true);

		  }
	  
	  private void formateTitle(String titleStr){
		  JLabel title = new JLabel(titleStr);;
		  formateLabel(title);
		  title.setPreferredSize(new Dimension(200, 30));
		  title.setFont(new Font("Courier New", Font.BOLD, 20));
		  this.add(title);
	  }
	  
	  private void formateLabel(JLabel label){
		  label.setOpaque(true);
		  label.setForeground(Color.BLACK);
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  label.setBackground(Color.LIGHT_GRAY);
	  }
	  
	  private void formateLogo(JButton button){
		  button.setPreferredSize(new Dimension(200, 200));
		  this.add(button);
	  }
}
