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

public class Event extends JDialog{
	private JButton menu;
	private JLabel textLabel;
	
	  public Event(JFrame parent, String title, String text, int eventNb){
		  super(parent, title, true);
		  
		  this.setSize(600, 440);
		  this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		  JLabel imgEnd =new JLabel(new ImageIcon("files/img/event/event" + eventNb + ".png"));
		  imgEnd.setPreferredSize(new Dimension(600, 200));
		  this.add(imgEnd);
		  textLabel = new JLabel("<html>" + text + "</html>");
		  formateLabel(textLabel);
		  textLabel.setFont(new Font("Courier New", Font.BOLD, 15));
		  textLabel.setPreferredSize(new Dimension(590, 100));
		  JPanel textField = new JPanel();
		  textField.setBackground(Color.LIGHT_GRAY);
		  textField.setPreferredSize(new Dimension(600, 110));
		  textField.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		  textField.add(textLabel);
		  this.add(textField);
		  
		  
		  JPanel buttonField = new JPanel();
		  buttonField.setBackground(Color.LIGHT_GRAY);
		  buttonField.setPreferredSize(new Dimension(800, 100));
		  buttonField.setLayout(new FlowLayout(FlowLayout.CENTER,0, 15));
		  this.add(buttonField);
		  menu = new JButton("Fermé");
		  
		  menu.setOpaque(true);
		  menu.setForeground(Color.white);
		  menu.setFont(new Font("Courier New", Font.BOLD, 20));
		  menu.setHorizontalAlignment(JLabel.CENTER);
		  menu.setVerticalAlignment(JLabel.CENTER);
		  menu.setBackground(Color.DARK_GRAY);
		  menu.addActionListener(new EndListener(this));
		  menu.setPreferredSize(new Dimension(200, 70));
		  buttonField.add(menu);
		  
          
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);

		  }
	
	  
	  private void formateLabel(JLabel label){
		  label.setOpaque(true);
		  label.setForeground(Color.BLACK);
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  label.setBackground(Color.LIGHT_GRAY);
	  }
	  
	  
}