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

import fr.spaceShop.controler.WorkerAdmnistratorListener;
import fr.spaceShop.modele.humans.actions.Worker;

public class WorkerAdmnistrator extends JDialog{
	private JButton changeJobs, exit, fire;
	
	  public WorkerAdmnistrator(JFrame parent, Worker worker){
		  super(parent, "Interface du travailleur", true);
		  this.setSize(600, 600);
		  this.setLayout(new BorderLayout());
		  boolean criminal = (worker.getCriminal()>15);
		  String inversJob;
		  if(worker.getJob().equals("Seller")) inversJob = "garde";
		  else inversJob = "vendeur";
		  
		  
		  JPanel title = new JPanel();
		  title.setLayout(new BorderLayout());
		  Font titleFont = new Font("Courier New", Font.BOLD, 15);
          JLabel object = new JLabel("   Dossier " + worker.getName());
          object.setForeground(Color.white);
          object.setFont(titleFont);
          title.add(object, BorderLayout.CENTER);
          JLabel mail = new JLabel("<" + worker.getMail() +">   ");
          mail.setForeground(Color.white);
          mail.setFont(titleFont);
          title.add(mail, BorderLayout.EAST);
          title.setBackground(Color.DARK_GRAY);
          title.setPreferredSize(new Dimension(600, 35));
          this.add(title, BorderLayout.NORTH);
          
          JPanel content = new JPanel();
          content.setLayout(new BorderLayout());
          JLabel picture  = new JLabel( new ImageIcon( "files/img/caractere/caractere" + worker.getPicture() +".png"));
          picture.setPreferredSize(new Dimension(600, 150));
          picture.setBackground(new Color(3, 60, 76));
          content.add(picture, BorderLayout.NORTH);
          JLabel fileContent = new JLabel();
          content.setBackground(Color.LIGHT_GRAY);
          String fileContentStr = "<html>Profile de M." + worker.getName() + ", "+ worker.getJob()+ "<br><br>" + "Voici son dossier personnel :<br><br>D'abord, commençons par un répicatulatif de ses facultés :<br>";
          if(worker.getAgility()>10) fileContentStr+= "Il est très agile. <br>";
          else fileContentStr+= "Il n'est pas très agile. <br>";
          if(worker.getStrenght()>10) fileContentStr+= "Il est très fort. <br>";
          else fileContentStr+= "Il n'est très fort. <br>";
          if(worker.getCharism()>10) fileContentStr+= "Il est très charismatique. <br>";
          else fileContentStr+= "Il n'est pas très charismatique. <br>";
          if(criminal || worker.getVision()>10) fileContentStr+= "Il a une très bonne vision. <br>";
          if(worker.getHapiness()>10) fileContentStr+= "Il est très souriant <br>";
          else fileContentStr+= "Il n'est pas très souriant <br>";
          fileContentStr += "Données récupérées dans sa lettre de candidature et l'analyse de ses performances.<br>";
          fileContentStr += "<br>Son salaire est de " + worker.getSalary() + " spaceuros</html>";
          fileContent.setText(fileContentStr);
          fileContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
          content.add(fileContent, BorderLayout.CENTER);
          this.add(content, BorderLayout.CENTER);
          
          JPanel buttons = new JPanel();
          buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 7));
          changeJobs = new JButton("Affecter cette employé en tant que " + inversJob);
          changeJobs.setBackground(Color.LIGHT_GRAY);
          fire = new JButton("Virer cette employé, coût : " + worker.getSalary()*1.2);
          fire.setBackground(Color.LIGHT_GRAY);
          exit = new JButton("Sortir");
          exit.setBackground(Color.LIGHT_GRAY);
          buttons.add(changeJobs);
          buttons.add(fire);
          buttons.add(exit);
          buttons.setPreferredSize(new Dimension(600, 100));
          buttons.setBackground(Color.DARK_GRAY);
          
          WorkerAdmnistratorListener buttonsListener = new WorkerAdmnistratorListener(changeJobs,fire, worker, this);
          changeJobs.addActionListener(buttonsListener);
          fire.addActionListener(buttonsListener);
          exit.addActionListener(buttonsListener);
          
          this.add(buttons, BorderLayout.SOUTH);
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);

		  }
	  
}