package fr.spaceShop.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.spaceShop.controler.StatsListener;
import fr.spaceShop.modele.products.Purchase;
import fr.spaceShop.modele.products.PurchaseShop;

public class Stats extends JDialog{
	private JButton exit, statCustomer, statProductsBuy, statMoneyEarn;
	private JPanel content;
	private CardLayout cl = new CardLayout();
	private CustomerStat customerStat = new CustomerStat();
	private ShopPurchaseStat shopPurchaseStat = new ShopPurchaseStat();
	private MoneyEarnStat moneyEarnStat = new MoneyEarnStat();
	private String[] listContent = new String[3];
	
	  public Stats(JFrame parent){
		  	super(parent, "Statistiques", true);
		  	this.setSize(650,680);
		  	this.setLayout(new BorderLayout());
		  	JPanel buttons = new JPanel();
		  	buttons.setBackground(Color.LIGHT_GRAY);
		  	buttons.setPreferredSize(new Dimension(650,80));
		  	exit = new JButton("Sortir");
			formateButton(exit);  
			statCustomer = new JButton("<html>Statistiques<br>des ventes</html>");
			formateButton(statCustomer);
			buttons.add(statCustomer);
		
			statProductsBuy = new JButton("<html>Statistiques<br>  des achats</html>");
			formateButton(statProductsBuy);
			buttons.add(statProductsBuy);
			
			statMoneyEarn = new JButton("<html>Statistiques<br>  des gains</html>");
			formateButton(statMoneyEarn);
			buttons.add(statMoneyEarn);
			buttons.add(exit);
		  	
			
			
		  	JScrollPane scrollCustomer = new JScrollPane(customerStat);
		  	scrollCustomer.setSize(600,650);
		  	scrollCustomer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		  	scrollCustomer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  	
		  	JScrollPane scrollProductsBuy = new JScrollPane(shopPurchaseStat);
		  	scrollProductsBuy.setSize(600,650);
		  	scrollProductsBuy.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		  	scrollProductsBuy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  	
		  	content = new JPanel();
			listContent[0] = "statCustomer";
			listContent[1] = "statProductsBuy";
			listContent[2] =  "statMoneyEarn";
			content.setLayout(cl);
			content.add(scrollCustomer, listContent[0]);
			content.add(scrollProductsBuy, listContent[1]);
			content.add(moneyEarnStat, listContent[2]);
			
			
			StatsListener buttonsListener = new StatsListener(statCustomer, statProductsBuy, statMoneyEarn, this, cl, listContent, content);
			statMoneyEarn.addActionListener(buttonsListener);
			statCustomer.addActionListener(buttonsListener);
			statProductsBuy.addActionListener(buttonsListener);
			exit.addActionListener(buttonsListener);
			
		  	this.add(content, BorderLayout.CENTER);
		  	this.add(buttons, BorderLayout.SOUTH);
		  	this.setLocationRelativeTo(null);
			this.setResizable(false);
		  	
		  }
	  	public void addElement(Purchase purchase){
	  		customerStat.addElement(purchase);
	  	}
	  	
		public void addElement(PurchaseShop purchaseShop) {
			shopPurchaseStat.addElement(purchaseShop);
		}
		
		public void actualiseStatMoneyEarn(List<Integer> statMoneyEarn){
			moneyEarnStat.actualiseList(statMoneyEarn);
			moneyEarnStat.repaint();
		}
		
		private void formateButton(JButton button){
			  button.setPreferredSize(new Dimension(150, 60));
			  button.setBackground(Color.DARK_GRAY);
			  button.setForeground(Color.white);
			  button.setFont(new Font("Courier New", Font.BOLD, 12));
			  button.setHorizontalAlignment(JLabel.CENTER);
		  }
		
		private void design1(JLabel label, int height){
			  label.setPreferredSize(new Dimension(130, height));
			  label.setBackground(Color.DARK_GRAY);
			  label.setForeground(Color.white);
			  label.setFont(new Font("Courier New", Font.BOLD, 12));
			  label.setHorizontalAlignment(JLabel.CENTER);
			  label.setVerticalAlignment(JLabel.CENTER);
			  label.setOpaque(true);
		  }
		
		private void design2(JLabel label, int height){
			  label.setPreferredSize(new Dimension(130, height));
			  label.setBackground(Color.LIGHT_GRAY);
			  label.setForeground(Color.black);
			  label.setFont(new Font("Courier New", Font.BOLD, 12));
			  label.setHorizontalAlignment(JLabel.CENTER);
			  label.setVerticalAlignment(JLabel.CENTER);
			  label.setOpaque(true);
		  }
		  
		  
	  
	
	class CustomerStat extends JPanel{
		private boolean design1 = false;
		private int lenght = 60;
		public CustomerStat(){
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			this.setPreferredSize(new Dimension(650, 600));
			
			JPanel title = new JPanel();
			title.setSize(650,60);
			title.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			JLabel date = new JLabel("Date");
			JLabel customer = new JLabel("Client");
			JLabel product = new JLabel("Produit");
			JLabel price = new JLabel("Prix");
			JLabel seller = new JLabel("Vendeur");
			
			design1(date, 60);
			design1(customer, 60);
			design1(product, 60);
			design1(price, 60);
			design1(seller, 60);
			this.setBackground(Color.LIGHT_GRAY);
			
			title.add(date);
			title.add(customer);
			title.add(product);
			title.add(price);
			title.add(seller);
			
			this.add(title);
			
			
		}
		
		public void addElement(Purchase purchase){
			JPanel purchasePanel = new JPanel();
			lenght += 60;
			if (lenght>600) this.setPreferredSize(new Dimension(650, lenght));
			purchasePanel.setSize(650,60);
			purchasePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			
			JLabel date = new JLabel();
			JLabel customer = new JLabel();
			JLabel product = new JLabel();
			JLabel price = new JLabel();
			JLabel seller = new JLabel();
			
			date.setText("" + purchase.getDate()+ "");
			if(purchase.getCustomer() != null){
				customer.setText("" + purchase.getCustomer()+ "");
			}
			product.setText("" + purchase.getProduct().getName() + "");
			price.setText("" + purchase.getPrice() + " spaceuros");
			seller.setText("" + purchase.getSeller() + "");
			
			this.add(purchasePanel);
			if(design1){
				design1(date, 60);
				design1(customer, 60);
				design1(product, 60);
				design1(price, 60);
				design1(seller, 60);
				this.setBackground(Color.LIGHT_GRAY);
				design1 = false;
			}
			else {
				design2(date,60);
				design2(customer,60);
				design2(product,60);
				design2(price,60);
				design2(seller,60);
				this.setBackground(Color.DARK_GRAY);
				design1 = true;
			}
			
			purchasePanel.add(date);
			purchasePanel.add(customer);
			purchasePanel.add(product);
			purchasePanel.add(price);
			purchasePanel.add(seller);
			
			this.add(purchasePanel);
		}
		
		
	}
	
	class ShopPurchaseStat extends JPanel {
		private boolean design1 = false;
		private int lenght = 60;
		public ShopPurchaseStat(){
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			this.setPreferredSize(new Dimension(650, 600));
			
			JPanel title = new JPanel();
			title.setSize(650,60);
			title.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			JLabel date = new JLabel("Date");
			JLabel products = new JLabel("Produits");
			JLabel quantity = new JLabel("Quantité");
			JLabel priceOne = new JLabel("Prix à  l'unité");
			JLabel priceTotal = new JLabel("Prix total");
			
			design1(date, 60);
			design1(products, 60);
			design1(quantity, 60);
			design1(priceOne, 60);
			design1(priceTotal, 60);
			this.setBackground(Color.LIGHT_GRAY);
			
			title.add(date);
			title.add(products);
			title.add(quantity);
			title.add(priceOne);
			title.add(priceTotal);
			
			this.add(title);
			
			
		}
		
		public void addElement(PurchaseShop purchase){
			JPanel purchasePanel = new JPanel();
			int hightOfPurchasePanel = purchase.getStockBuy().size() * 20;
			if(hightOfPurchasePanel<30 )lenght += 60;
			else lenght += hightOfPurchasePanel;
			if (lenght>600) this.setPreferredSize(new Dimension(650, lenght));
			purchasePanel.setSize(650,hightOfPurchasePanel);
			purchasePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			
			String quantityStr = "<html>";
			String productsStr = "<html>";
			String priceOneStr = "<html>";
			String priceTotalStr = "<html>";
			for(int i = 0; i< purchase.getStockBuy().size(); i++){
				quantityStr += purchase.getStockBuy().get(i).getQuantity() + "<br>";
				productsStr += purchase.getStockBuy().get(i).getProduct().getName() + "<br>";
				priceOneStr += purchase.getStockBuy().get(i).getPrice() + "<br>";
				priceTotalStr += (purchase.getStockBuy().get(i).getQuantity()* purchase.getStockBuy().get(i).getPrice()) + "<br>";
			}
			quantityStr += "</html>";
			productsStr += "</html>";
			priceOneStr += "</html>";
			priceTotalStr += "</html>";
			
			JLabel date = new JLabel("" + purchase.getDate());
			JLabel products = new JLabel(productsStr);
			JLabel quantity = new JLabel(quantityStr);
			JLabel priceOne = new JLabel(priceOneStr);
			JLabel priceTotal = new JLabel(priceTotalStr);
			
			
			this.add(purchasePanel);
			if(design1){
				design1(date, hightOfPurchasePanel);
				design1(products, hightOfPurchasePanel);
				design1(quantity, hightOfPurchasePanel);
				design1(priceOne, hightOfPurchasePanel);
				design1(priceTotal, hightOfPurchasePanel);
				this.setBackground(Color.LIGHT_GRAY);
				design1 = false;
			}
			else {
				design2(date, hightOfPurchasePanel);
				design2(products, hightOfPurchasePanel);
				design2(quantity, hightOfPurchasePanel);
				design2(priceOne, hightOfPurchasePanel);
				design2(priceTotal, hightOfPurchasePanel);
				this.setBackground(Color.DARK_GRAY);
				design1 = true;
			}
			
			purchasePanel.add(date);
			purchasePanel.add(products);
			purchasePanel.add(quantity);
			purchasePanel.add(priceOne);
			purchasePanel.add(priceTotal);
			
			this.add(purchasePanel);
		}
		
	}
	
	class MoneyEarnStat extends JPanel{
		private List<Integer> statMoneyEarn;
		public MoneyEarnStat(){
			this.setPreferredSize(new Dimension(650,600));
			this.statMoneyEarn = new ArrayList();
			this.setBackground(Color.blue);
			statMoneyEarn.add(200);
				
			}
		
		public void actualiseList(List<Integer> statMoneyEarn){
			this.statMoneyEarn = statMoneyEarn;
		}
		public void paintComponent(Graphics g){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, 650, 600);
			g.setColor(Color.LIGHT_GRAY);
			
			int width = 650;
			if(statMoneyEarn.size() != 1) width = 650/ (statMoneyEarn.size()-1);
			//get min and max
			int min = statMoneyEarn.get(0);
			int max = statMoneyEarn.get(0);
			for(int i = 0; i <statMoneyEarn.size(); i++){
				if(statMoneyEarn.get(i)<min) min = statMoneyEarn.get(i);
				else if(statMoneyEarn.get(i)>max) max = statMoneyEarn.get(i);
			}
			float gap;
			if(max == min) gap = 600;
			else gap = 600/(max - min);
			if (gap == 0) gap = 1;
			int x[] = new int[statMoneyEarn.size()+3];
			int y[] = new int[statMoneyEarn.size()+3];
			int i;
			for(i = 0; i <statMoneyEarn.size(); i++){
				x[i] = width*i;
				y[i] = getY(statMoneyEarn.get(i), min, gap);
			}
			x[i] = 650;
			y[i] = getY(statMoneyEarn.get(i-1), min, gap);
			x[++i] = 650;
			y[i++] = 0;
			x[i] = 0;
			y[i] = 0;
			g.fillPolygon(x, y, statMoneyEarn.size()+3);
		} 
		
		private int getY(int money, int min, float gap){
			if(money< 0) money *= -1;
			if(min <0 ) min *= -1;
			if (money-min ==0) money +=1;
			return (int) (600-( (money-min) * gap));
		}


	}
}