package fr.spaceShop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.spaceShop.controler.CandidateListener;
import fr.spaceShop.modele.humans.actions.Worker;

public class Candidate extends JDialog{
	private JButton acceptGuard, decline, acceptSeller;
	
	  public Candidate(JFrame parent, Worker candidate, Worker pdg){
		  super(parent, "Candidature", true);
		  boolean criminal = (candidate.getCriminal()>15);
		  ///this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		  this.setSize(600, 600);
		  this.setLayout(new BorderLayout());
		  
		  
		  JPanel title = new JPanel();
		  title.setLayout(new BorderLayout());
		  Font titleFont = new Font("Courier New", Font.BOLD, 15);
          JLabel object = new JLabel("   Candidature de " + candidate.getName());
          object.setForeground(Color.white);
          object.setFont(titleFont);
          title.add(object, BorderLayout.CENTER);
          JLabel mail = new JLabel("<" + candidate.getMail() +">   ");
          mail.setForeground(Color.white);
          mail.setFont(titleFont);
          title.add(mail, BorderLayout.EAST);
          title.setBackground(Color.DARK_GRAY);
          title.setPreferredSize(new Dimension(600, 35));
          this.add(title, BorderLayout.NORTH);
          
          JPanel content = new JPanel();
          content.setLayout(new BorderLayout());
          JLabel picture  = new JLabel( new ImageIcon( "files/img/caractere/caractere" + candidate.getPicture() +".png"));
          //picture.setBorder(border);
          picture.setPreferredSize(new Dimension(600, 150));
          picture.setBackground(new Color(3, 60, 76));
          content.add(picture, BorderLayout.NORTH);
          JLabel mailContent = new JLabel();
          content.setBackground(Color.LIGHT_GRAY);
          String mailContentStr = "<html>Bonjour " + pdg.getName() + ",<br><br>" + "J'ai pu voir votre magnifique magasin et j'aimerais y travailler.<br><br>";
          if(criminal || candidate.getAgility()>10) mailContentStr+= "Je suis très agile. <br>";
          if(criminal || candidate.getStrenght()>10) mailContentStr+= "Je suis très fort. <br>";
          if(criminal || candidate.getCharism()>10) mailContentStr+= "Je suis très charismatique. <br>";
          if(criminal || candidate.getVision()>10) mailContentStr+= "J'ai une trés bonne vision. <br>";
         
          
          mailContentStr += "<br>Ces talents me font vous demandez un humble salaire de " + candidate.getSalary() + " spaceuros<br><br>en espérant que ma candidature retienne votre attention<br>Cordialement,<br>" + candidate.getName() + "<br>Bonne journée</html>";
          mailContent.setText(mailContentStr);
          mailContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
          content.add(mailContent, BorderLayout.CENTER);
          this.add(content, BorderLayout.CENTER);
          
          JPanel reponse = new JPanel();
          acceptGuard = new JButton("Bonjour " + candidate.getName() + " je serais ravis de vous accueillir en tant que garde.");
          acceptGuard.setBackground(Color.LIGHT_GRAY);
          acceptSeller = new JButton("Bonjour " + candidate.getName() + " je serais ravis de vous accueillir en tant que vendeur.");
          acceptSeller.setBackground(Color.LIGHT_GRAY);
          decline = new JButton("Bonjour " + candidate.getName() + " je suis dans le regret de déclinez votre demande.");
          decline.setBackground(Color.LIGHT_GRAY);
          reponse.add(acceptGuard);
          reponse.add(acceptSeller);
          reponse.add(decline);
          reponse.setPreferredSize(new Dimension(600, 100));
          reponse.setBackground(Color.DARK_GRAY);
          
          CandidateListener reponseListener = new CandidateListener(acceptGuard, acceptSeller, candidate, this);
          acceptGuard.addActionListener(reponseListener);
          acceptSeller.addActionListener(reponseListener);
          decline.addActionListener(reponseListener);
          
          this.add(reponse, BorderLayout.SOUTH);
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);
		  this.setVisible(true);

		  }
}
