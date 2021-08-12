package fr.spaceShop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.spaceShop.controler.PDGAdmnistratorListener;
import fr.spaceShop.modele.humans.actions.PDG;

public class PdgAdmnistrator extends JDialog{
	
	  public PdgAdmnistrator(JFrame parent, PDG pdg){
		  super(parent, "Interface du PDG", true);
		  this.setSize(600, 600);
		  this.setLayout(new BorderLayout());

		  JButton exit = null, sendMoney = null;
		  JTextField money = null;
		  JPanel title = new JPanel();
		  title.setLayout(new BorderLayout());
		  Font titleFont = new Font("Courier New", Font.BOLD, 15);
          JLabel object = new JLabel("   Bienvenue M." + pdg.getName());
          object.setForeground(Color.white);
          object.setFont(titleFont);
          title.add(object, BorderLayout.CENTER);
          JLabel mail = new JLabel("<" + pdg.getMail() +">   ");
          mail.setForeground(Color.white);
          mail.setFont(titleFont);
          title.add(mail, BorderLayout.EAST);
          title.setBackground(Color.DARK_GRAY);
          title.setPreferredSize(new Dimension(600, 35));
          this.add(title, BorderLayout.NORTH);
          
          JPanel content = new JPanel();
          content.setLayout(new BorderLayout());
          JLabel picture  = new JLabel( new ImageIcon( "files/img/caractere/caractere" + pdg.getPicture() +".png"));
          picture.setPreferredSize(new Dimension(600, 150));
          picture.setBackground(new Color(3, 60, 76));
          content.add(picture, BorderLayout.NORTH);
          JLabel fileContent = new JLabel();
          content.setBackground(Color.LIGHT_GRAY);
          String fileContentStr = "<html>Bonjour M." + pdg.getName() + ",<br><br>" + "Voici votre dossier personnel :<br><br>D'abord, commenà§ons par un répicatulatif de vos facultés :<br>Votre force est évalué à  " + pdg.getStrenght() + " sur 20<br>Votre Agilité est évalué à  " + pdg.getAgility() + " sur 20<br>Votre Charisme est évalué à  " + pdg.getCharism() + " sur 20<br>Votre vision est évalué à  " + pdg.getVision() + " sur 20<br>Votre cleptomanie est évalué à  " + pdg.getCriminal() + " sur 20<br>Votre bonheur est évalué à  " + pdg.getHapiness() + " sur 20<br>";
          fileContentStr += "Données récupérées par l'analyse de vos dernières recherches, de votre prise de sang et de vos différents achats.<br>";
          fileContentStr += "<br>Votre salaire est de " + pdg.getSalary() + " spaceuros et vous possédez actuellement " + pdg.getMoney() +" spaceuros </html>";
          fileContent.setText(fileContentStr);
          fileContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
          content.add(fileContent, BorderLayout.CENTER);
          this.add(content, BorderLayout.CENTER);
          
          JPanel buttons = new JPanel();
          exit = new JButton("Sortir");
          exit.setBackground(Color.LIGHT_GRAY);
          
          sendMoney = new JButton("Envoyé l'argent au magasin");
          sendMoney.setBackground(Color.LIGHT_GRAY);
          JPanel panMoney = new JPanel();
          panMoney.setBackground(Color.DARK_GRAY);
          panMoney.setPreferredSize(new Dimension(220, 100));
          JLabel moneyTitle = new JLabel("Argent à  envoyer : ");
          moneyTitle.setForeground(Color.white);
          JLabel spaceurosLabel = new JLabel(" spaceuros");
          spaceurosLabel.setForeground(Color.white);
          money = new JTextField("0");
          money.setForeground(Color.black);
          money.setPreferredSize(new Dimension(90, 25));
          panMoney.add(moneyTitle);
          panMoney.add(money);
          panMoney.add(spaceurosLabel);
          panMoney.add(sendMoney);
          
    
          buttons.add(panMoney);
          buttons.add(exit);
          buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
          buttons.setPreferredSize(new Dimension(600, 100));
          buttons.setBackground(Color.DARK_GRAY);
          PDGAdmnistratorListener buttonsListener = new PDGAdmnistratorListener(sendMoney, exit, money, pdg, this);
          exit.addActionListener(buttonsListener);
          sendMoney.addActionListener(buttonsListener);
          sendMoney.addActionListener(buttonsListener);
          this.add(buttons, BorderLayout.SOUTH);
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);

		  } 
}