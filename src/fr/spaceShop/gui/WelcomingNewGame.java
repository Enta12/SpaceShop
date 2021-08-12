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
			  		"<br>En l'an 9056 L'humanité entra dans une nouvelle ère. En effet, la république dictoriale anti-taupe deuxià¨me du nom décida, suite aux tensions de la population (provoquées par les machines à  café ne rendant pas les sous), d'entrainer des super-castors.<br> Ces castors étaient censés espionner la population en s'infiltrant partout. Ce fà»t bien le problà¨me, ils furent partout... et lorsque le lieutenant Grande-Dents découvrit le fameux secret de l'état concernant l'industrie du bois il lança la rébellion... " +
			  		"<br> Il ne fallut que trà¨s peu de temps pour éliminer les forces armées de la Terre, les castors étaient bien trop puissants et l'humanité dà» battre en retraite dans l'espace... Créant une societé anarchiste-capitaliste désorganisée et centralisée sur les restes de la civisation spaciale mais restant tout de màªme capitaliste.<br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le dénommé Oragarn, ancien guerrier anti-castors, vous vous àªtes retrouvé dans une station spatiale dénommée \"Patisserie\" et avez décidé de créer un magasin afin d'accumuler assez d'argent et d'hommes fidà¨les pour vous lancer dans la reconquàªte de la Terre... <br>" +
			  		"<br>OBJECTIF : 10 Gardes, 20 000 spaceuros dans votre compte en banque </html>";
		  scenario[1]  = "<html>Les taupes carnivores ...<br> " +
			  		"<br>En l'an 8056 L'humanité fît une énorme bétise. La formation du gouvernement mondiale venait de se faire. L'économie se portait pour le mieux. La famine avait était anéantie. Le seul problà¨me fà»t les égoux.. En effet ceux-ci n'étaient pas adaptés et n'avaient pas été agrandis en màªme temps que l'essort de la population. Le gouvernement avait du mal à  trouver des travailleurs pour les creuser, sachant que le taux de chà´mage avait atteint le seuil historique de 0%. Cependant une société de bio-informatique proposa une solution... " +
			  		"<br> Cette solution était la création de taupes biologiquement modifiées, pouvant creuser jusqu'à  1km par jour. Elle en créa des centaines de milliers et tout semblait marcher pour le mieux... jusqu'au jour ou l'on compris qu'elles étaient carnivore, et se nourrissaient d'àªtres humains... Suite à  de nombreuses disparitions, l'enquàªte fà»t menée, ce terrible secret découvert et la guerre déclenchée ! <br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le dénommé Nicolache, patron d'un petit café, vous avez squatté une station spatial abandonnée dans une zone non administrée afin de fuir la guerre. Mais les combats s'intensifient, et vous avez peur qu'il rejoignent votre petit havre de paix. Votre solution ? Vous faire assez d'argent pour partir loiiiiiin, trés loiiiiiiiin <br>" +
			  		"<br>OBJECTIF : 100 000 spaceuros dans votre compte en banque </html>";
		  scenario[2]  = "<html>La paix et l'amour ... Mais surtout le fric ! <br> " +
			  		"<br>En l'an 9812 L'humanité était au bord du gouffre. Suite à  la guerre contre les castors de la CIA (qui s'étaient alliés aux super-méga-over-prédateurs) la Terre avait été totalement irradiée et n'était plus propice à  la vie. Les derniers humains encore vivant se trouvaient dans l'espace, formant une société étrange sans lois, sans sécurité mais oà¹ étrangement, les sociétés et le capitalisme prospéraient. Un mouvement alterspatial voyait le jour peu à  peu, pour lutter contre la pervertion de cette espace et cette préoccupation horrible que l'on appellait \"spaceuros\"." +
			  		"<br>Ces personnes assez peu nombreuses et impuissantes face aux géants industriels restaient dans l'ombre... L'avenir semblait vraiment noirÂ et tout le monde baissait les bras et se résiliait à  vivre dans ce monde pourri... à€ la fin de l'année il ne restait presque plus aucun alterspacialiste et le mouvement semblait disparaitre ... <br>" +
			  		"<br> C'est dans ce contexte que VOUS ! Oui VOUS ! Le dénommé Jhon Lemon, musicien renommé, avait décidé de vous lever contre cet espace pourri ! Comment ? En faisant un magasin d'amour qui vous permettra de gagner assez d'argent pour pouvoir promouvoir ce mouvement ! <br>" +
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
		  formateTitle("Scénario 1 :");
		  formateTitle("Scénario 2 :");
		  formateTitle("Scénario 3 :");
		  formateTitle("Votre Scénario :");
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
		  start = new JButton("<html>Partir à  ... L'aventure</html>");
		  
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
