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

import fr.spaceShop.controler.CustomerAdministrationListener;
import fr.spaceShop.modele.humans.actions.FaithFulCustomer;

public class CustomerAdministrator extends JDialog{
	private JButton exit, kill;
	
	  public CustomerAdministrator(JFrame parent, FaithFulCustomer customer){
		  super(parent, "Interface du client", true);
		  this.setSize(600, 480);
		  this.setLayout(new BorderLayout());
		  
		  
		  JPanel title = new JPanel();
		  title.setLayout(new BorderLayout());
		  Font titleFont = new Font("Courier New", Font.BOLD, 15);
          JLabel object = new JLabel("   Dossier " + customer.getName());
          object.setForeground(Color.white);
          object.setFont(titleFont);
          title.add(object, BorderLayout.CENTER);
          JLabel mail = new JLabel("<" + customer.getMail() +">   ");
          mail.setForeground(Color.white);
          mail.setFont(titleFont);
          title.add(mail, BorderLayout.EAST);
          title.setBackground(Color.DARK_GRAY);
          title.setPreferredSize(new Dimension(600, 35));
          this.add(title, BorderLayout.NORTH);
          
          JPanel content = new JPanel();
          content.setLayout(new BorderLayout());
          JLabel picture  = new JLabel( new ImageIcon( "files/img/caractere/caractere" + customer.getPicture() +".png"));
          //picture.setBorder(border);
          picture.setPreferredSize(new Dimension(00, 150));
          picture.setBackground(new Color(3, 60, 76));
          content.add(picture, BorderLayout.NORTH);
          JLabel fileContent = new JLabel();
          content.setBackground(Color.LIGHT_GRAY);
          String fileContentStr = "<html>Profile de M." + customer.getName() + ", client fidèle depuis " + customer.getFidelizeDate() + "<br><br>" + "Voici un répicatulatif de ses facultés :<br>";
          if(customer.getStrenght()>10) fileContentStr+= "Il a l'air très fort. <br>";
          else fileContentStr+= "Il n'a pas l'air très fort. <br>";
          if(customer.getCharism()>10) fileContentStr+= "Il est très charismatique. <br>";
          else fileContentStr+= "Il n'est pas très charismatique. <br>";
          fileContentStr += "Donnée récupéré dans l'annalyse de son comportement.<br></html>";
          fileContent.setText(fileContentStr);
          fileContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
          content.add(fileContent, BorderLayout.CENTER);
          this.add(content, BorderLayout.CENTER);
          
          JPanel buttons = new JPanel();
          buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 7));
          kill = new JButton("Mettre à  mort ce client");
          kill.setBackground(Color.LIGHT_GRAY);
          exit = new JButton("Sortir");
          exit.setBackground(Color.LIGHT_GRAY);
          buttons.add(kill);
          buttons.add(exit);
          CustomerAdministrationListener buttonsListener = new CustomerAdministrationListener(kill, customer, this);
          kill.addActionListener(buttonsListener);
          exit.addActionListener(buttonsListener);
          buttons.setPreferredSize(new Dimension(600, 80));
          buttons.setBackground(Color.DARK_GRAY);
          this.add(buttons, BorderLayout.SOUTH);
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);

		  }
}