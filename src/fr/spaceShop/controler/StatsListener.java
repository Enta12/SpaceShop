package fr.spaceShop.controler;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.spaceShop.gui.Stats;

public class StatsListener implements ActionListener{
	  
	private JButton statCustomer;
	private JButton statProductsBuy;
	private JButton statMoneyEarn;
	private Stats window;
	private CardLayout cl;
	private String listContent[];
	private JPanel content;
	
	public StatsListener(JButton statCustomer, JButton statProductsBuy, JButton statMoneyEarn, Stats window, CardLayout cl, String listContent[], JPanel content){
		this.statCustomer = statCustomer;
		this.statProductsBuy = statProductsBuy;
		this.statMoneyEarn = statMoneyEarn;
		this.window = window;
		this.cl = cl;
		this.listContent = listContent;
		this.content = content;
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource()==statCustomer){
			cl.show(content, listContent[0]);
		}
		else if (e.getSource()==statProductsBuy){
			cl.show(content, listContent[1]);
		}
		else if (e.getSource()==statMoneyEarn){
			cl.show(content, listContent[2]);
		}
		else{
			end();
		}
	
	}
		
	private void end(){
		window.setVisible(false);
	}
		  
	
  }