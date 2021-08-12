package fr.spaceShop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.spaceShop.controler.ProductManagerListener;
import fr.spaceShop.modele.products.Product;
import fr.spaceShop.modele.products.Stock;
import fr.spaceShop.modele.products.WholeSealer;

public class ProductManager extends JDialog{
	private JButton exit, send ;
	private JLabel[] productsStocks = new JLabel[10] ;
	private JLabel[] wholeSealerPrices = new JLabel[10] ;
	private JLabel concludeQuantity[] = new JLabel[10];
	private JTextField[] purchasePrices = new JTextField[10];
	private JButton[] addToStocks = new JButton[10];
	private JButton[] removeToStocks = new JButton[10];
	private int[] purchase = {0,0,0,0,0,0,0,0,0,0};
	private int moneyToBuy = 0;
	private JLabel moneyToBuyLabel;
	
	  public ProductManager(JFrame parent, Stock[] stocks, WholeSealer[] wholeSealers){
		  super(parent, "Interface des produits", true);
		  this.setSize(1280, 845);
		  this.setLayout(new BorderLayout());
		  
		  
		  JPanel content = new JPanel();
		  content.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
		  
		  JPanel titlePanel = new JPanel();
		  titlePanel.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
		  JLabel TitleProductsNames = new JLabel("Nom");
		  formateJlabelColor1(TitleProductsNames);
		  titlePanel.add(TitleProductsNames);
		  
		  JLabel TitleProductsMark = new JLabel("Marque");
		  formateJlabelColor1(TitleProductsMark);
		  titlePanel.add(TitleProductsMark);
		  
		  JLabel titleProductsType = new JLabel("Type du produit");
		  formateJlabelColor1(titleProductsType);
		  titlePanel.add(titleProductsType);
		  
		  JLabel TitleProductsStock = new JLabel("Stock");
		  formateJlabelColor1(TitleProductsStock);
		  titlePanel.add(TitleProductsStock);
		  
		  JLabel TitleAddtoStock = new JLabel("Ajout d'un produit");
		  formateJlabelColor1(TitleAddtoStock);
		  titlePanel.add(TitleAddtoStock);
		  
		  JLabel TitleRemovetoStock = new JLabel("<html>Supression<br> d'un produit</html>");
		  formateJlabelColor1(TitleRemovetoStock);
		  titlePanel.add(TitleRemovetoStock);
		  
		  JLabel titleWholeSealerPrices = new JLabel("Prix du grossite");
		  formateJlabelColor1(titleWholeSealerPrices);
		  titlePanel.add(titleWholeSealerPrices);
		  
		  JLabel titlePurchasePrices  = new JLabel("<html>Prix du magasin<br>(en spaceuros)</html>") ;
		  formateJlabelColor1(titlePurchasePrices);
		  titlePanel.add(titlePurchasePrices);
		  titlePanel.setBackground(Color.black);
		  titlePanel.setPreferredSize(new Dimension(1280, 60));
		  content.add(titlePanel);
		  
		  JPanel concludePanel = new JPanel();
		  concludePanel.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
		  concludePanel.setPreferredSize(new Dimension(1280, 60));
		  concludePanel.setBackground(Color.LIGHT_GRAY);
		  for(int i = 0 ; i < Product.PRODUCT_NB; i++){
			  JPanel productPanelConclude = new JPanel();
			  productPanelConclude.setPreferredSize(new Dimension(116, 60));
			  productPanelConclude.setBackground(Color.LIGHT_GRAY);
			  productPanelConclude.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
			  JLabel productNameConclude = new JLabel("" + stocks[i].getProduct().getName());
			  concludeQuantity[i] = new JLabel(purchase[i] + "");
			  formateLabelConclude(productNameConclude);
			  productPanelConclude.add(productNameConclude);
			  formateLabelConclude(concludeQuantity[i]);
			  productPanelConclude.add(concludeQuantity[i]);
			  concludePanel.add(productPanelConclude);
			  
			  JPanel productPanel = new JPanel();
			  productPanel.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
			  JLabel productsNames = new JLabel(stocks[i].getProduct().getName());
			  productPanel.add(productsNames);
			  JLabel productsMark = new JLabel(stocks[i].getProduct().getMark());
			  productPanel.add(productsMark);
			  JLabel productsType = new JLabel(stocks[i].getProduct().getType()); //addToStocks
			  productPanel.add(productsType);
			  productsStocks[i] = new JLabel((stocks[i].getQuantity())+ "");
			  productPanel.add(productsStocks[i]);
			  addToStocks[i] = new JButton("<html>Ajouter un produit<br>au stock</html>");
			  productPanel.add(addToStocks[i]);
			  removeToStocks[i] = new JButton("<html>Supression un produit<br>au stock</html>");
			  removeToStocks[i].setEnabled(false);
			  productPanel.add(removeToStocks[i]);
			  wholeSealerPrices[i] = new JLabel(wholeSealers[i].getPrice() + "");
			  productPanel.add(wholeSealerPrices[i]);
			  purchasePrices[i]  = new JTextField(stocks[i].getPrice() + "");
			  productPanel.add(purchasePrices[i]);
			  
			  if(i%2 == 0 ){
				  formateJlabelColor2(productsNames);
				  formateJlabelColor2(productsMark);
				  formateJlabelColor2(productsType);
				  formateJlabelColor2(productsStocks[i]);
				  formateJlabelColor2(wholeSealerPrices[i]);
				  formateJTextFieldColor2(purchasePrices[i]);
				  formateJButtonColor2(addToStocks[i]);
				  formateJButtonColor2(removeToStocks[i]);
			  }
			  else{
				  formateJlabelColor1(productsNames);
				  formateJlabelColor1(productsMark);
				  formateJlabelColor1(productsType);
				  formateJlabelColor1(productsStocks[i]);
				  formateJlabelColor1(wholeSealerPrices[i]);
				  formateJTextFieldColor1(purchasePrices[i]);
				  formateJButtonColor1(addToStocks[i]);
				  formateJButtonColor1(removeToStocks[i]);
			  }
			  
			  productPanel.setPreferredSize(new Dimension(1280, 60));
			  content.add(productPanel);
			  
		  }
		  
		  
		  JLabel priceToBuyLabel = new JLabel("Prix total :");
		  JPanel pricePanelConclude = new JPanel();
		  pricePanelConclude.setPreferredSize(new Dimension(116, 60));
		  pricePanelConclude.setBackground(Color.LIGHT_GRAY);
		  pricePanelConclude.setLayout(new FlowLayout(0,0,FlowLayout.CENTER));
		  priceToBuyLabel = new JLabel("Prix total :");
		  moneyToBuyLabel = new JLabel(moneyToBuy+ "");
		  formateLabelConclude(moneyToBuyLabel);
		  pricePanelConclude.add(priceToBuyLabel);
		  formateLabelConclude(priceToBuyLabel);
		  pricePanelConclude.add(moneyToBuyLabel);
		  concludePanel.add(pricePanelConclude);
		  content.add(concludePanel);
		  
		  JPanel buttonsPanel = new JPanel();
		  buttonsPanel.setPreferredSize(new Dimension(1280, 75));
		  buttonsPanel.setBackground(Color.DARK_GRAY);
		  exit = new JButton("Sortir");
		  formateJButtonColor2(exit);
		  send = new JButton("Envoyer");
		  formateJButtonColor2(send);
		  buttonsPanel.add(exit);
		  buttonsPanel.add(send);
		  content.add(buttonsPanel);
		  

		  ProductManagerListener buttonsListener = new ProductManagerListener(exit, send, this, addToStocks, removeToStocks, moneyToBuyLabel);
		  send.addActionListener(buttonsListener);
		  exit.addActionListener(buttonsListener);
		  for(int i = 0 ; i < Product.PRODUCT_NB; i++){
			  removeToStocks[i].addActionListener(buttonsListener);
			  addToStocks[i].addActionListener(buttonsListener);
		  }
		  
		  this.add(content, BorderLayout.CENTER);
		  this.setLocationRelativeTo(null);
		  this.setResizable(false);

		  }
	  
	  private void formateJlabelColor1(JLabel label){
		  label.setPreferredSize(new Dimension(160, 60));
		  label.setBackground(Color.DARK_GRAY);
		  label.setForeground(Color.white);
		  label.setFont(new Font("Courier New", Font.BOLD, 12));
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  label.setOpaque(true);
	  }
	  
	  private void formateLabelConclude(JLabel label){
		  label.setPreferredSize(new Dimension(160, 30));
		  label.setForeground(Color.BLACK);
		  label.setFont(new Font("Courier New", Font.BOLD, 12));
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
	  }
	  
	  private void formateJlabelColor2(JLabel label){
		  label.setPreferredSize(new Dimension(160, 60));
		  label.setBackground(Color.LIGHT_GRAY);
		  label.setForeground(Color.BLACK);
		  label.setFont(new Font("Courier New", Font.BOLD, 12));
		  label.setHorizontalAlignment(JLabel.CENTER);
		  label.setVerticalAlignment(JLabel.CENTER);
		  label.setOpaque(true);
	  }
	  
	  private void formateJTextFieldColor2(JTextField text){
		  text.setPreferredSize(new Dimension(160, 60));
		  text.setBackground(Color.LIGHT_GRAY);
		  text.setForeground(Color.BLACK);
		  text.setFont(new Font("Courier New", Font.BOLD, 12));
		  text.setHorizontalAlignment(JLabel.CENTER);
	  }
	  
	  private void formateJTextFieldColor1(JTextField text){
		  text.setPreferredSize(new Dimension(160, 60));
		  text.setBackground(Color.DARK_GRAY);
		  text.setForeground(Color.white);
		  text.setFont(new Font("Courier New", Font.BOLD, 12));
		  text.setHorizontalAlignment(JLabel.CENTER);

	  }
	  
	  private void formateJButtonColor2(JButton button){
		  button.setPreferredSize(new Dimension(160, 60));
		  button.setBackground(Color.LIGHT_GRAY);
		  button.setForeground(Color.BLACK);
		  button.setFont(new Font("Courier New", Font.BOLD, 12));
		  button.setHorizontalAlignment(JLabel.CENTER);
	  }
	  
	  private void formateJButtonColor1(JButton button){
		  button.setPreferredSize(new Dimension(160, 60));
		  button.setBackground(Color.DARK_GRAY);
		  button.setForeground(Color.white);
		  button.setFont(new Font("Courier New", Font.BOLD, 12));
		  button.setHorizontalAlignment(JLabel.CENTER);
	  }
	  
	  
	  public int getMoneyToBuy(){
		  return moneyToBuy;
	  }
	  //Integer.parseInt((getWholeSealerPrices(i)

	public int getConcludeQuantity(int i) {
		return Integer.parseInt(concludeQuantity[i].getText());
	}

	public int getWholeSealerPrices(int i) {
		return Integer.parseInt(wholeSealerPrices[i].getText());
	}

	public int getPurchasePrices(int i) {
		return Integer.parseInt(purchasePrices[i].getText());
	}

	public int getProductsStocks(int i) {
		return Integer.parseInt(productsStocks[i].getText());
	}

	public void setProductsStocks(int i, int quantity) {
		productsStocks[i].setText(quantity + "");
		
	}

	public void addToConcludeQuantity(int i, int quantity) {
		purchase[i] += quantity;
		 concludeQuantity[i].setText(purchase[i] +"");
		
	}

	public void addToMoneyToBuy(int price) {
		moneyToBuy += price;
		
	}
	  
	   
}