package fr.spaceShop.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import fr.spaceShop.gui.ProductManager;
import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.products.Product;
import fr.spaceShop.modele.products.PurchaseShop;
import fr.spaceShop.modele.products.Stock;

public class ProductManagerListener implements ActionListener{
	private JButton exit;
	private JButton send;
	private boolean isNotSend = true;
	private boolean notMsgError = true;
	private ProductManager window;
	private JButton addToStocks[];
	private JButton removeToStocks[];
	private JLabel moneyToBuyLabel;
	
	public ProductManagerListener(JButton exit, JButton send, ProductManager window, JButton addToStocks[], JButton removeToStocks[], JLabel moneyToBuyLabel){
		this.exit= exit;
		this.send = send;
		this.window = window;
		this.addToStocks = addToStocks;
		this.removeToStocks = removeToStocks;
		this.moneyToBuyLabel= moneyToBuyLabel;
	}
	
	public void actionPerformed(ActionEvent e) { 
		if(e.getSource() == exit){
			end();
		}
		else if(e.getSource() == send) {
			if(Controler.getMoney() >= window.getMoneyToBuy() && isNotSend){
				Stock stockToStat[] = new Stock[Product.PRODUCT_NB];
				for(int i = 0; i< 10; i++){
					stockToStat[i] = new Stock(window.getWholeSealerPrices(i), window.getConcludeQuantity(i), Controler.getProduct(i));
					Controler.actualiseShopPrice(window.getPurchasePrices(i), i);
					Controler.actualiseShopStocks(window.getProductsStocks(i), i);
					isNotSend = false;
				}
				Controler.addToStatPurchaseShop(new PurchaseShop(new Date(Date.currentDate), stockToStat));
				Controler.addToMoney(-window.getMoneyToBuy());
				end();
			}
			else {
				if(notMsgError){
					  JOptionPane error = new JOptionPane();
						int option = error.showConfirmDialog(null, "Saisie incorrecte" , "Erreur dans l'envoie" , JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
						notMsgError = false;
				  }
				end();
			}
		}
		else {
			for(int i = 0; i< 10; i++){
				if(e.getSource() == addToStocks[i]){
					 window.setProductsStocks(i, window.getProductsStocks(i) + 1);
					 window.addToConcludeQuantity(i, 1);
					 window.addToMoneyToBuy(window.getWholeSealerPrices(i));
					removeToStocks[i].setEnabled(true);
					moneyToBuyLabel.setText(window.getMoneyToBuy() + "");
					
				}
				else if(e.getSource() == removeToStocks[i]){
					window.setProductsStocks(i, window.getProductsStocks(i) - 1);
					window.addToConcludeQuantity(i, -1);
					window.addToMoneyToBuy(-window.getWholeSealerPrices(i));
					moneyToBuyLabel.setText(window.getMoneyToBuy() + "");
					if(window.getConcludeQuantity(i) < 1) {
						removeToStocks[i].setEnabled(false);
					}
					
				}
				window.repaint();
			}
		}
	
	}
	

	private void end(){
		  Controler.actualise();
		  window.setVisible(false);
		  Controler.actualise();
	  }
}