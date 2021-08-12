package fr.spaceShop.modele.products;

import java.io.Serializable;

import fr.spaceShop.modele.shop.Shop;

public class Product implements Serializable{
	public static final int PRODUCT_NB = 10;
	private String type ;
	private String name ; 
	private String mark ; 
	private int id ; 
	private static int nbProduct = 0 ;
	private static Product products[] = new Product[PRODUCT_NB];


	
	public Product(String name, String type, String mark, Stock stocks[], WholeSealer wholeSealers[], int i, int price) {
		this.name = name ; 
		id= nbProduct++; 
		this.type = type ; 
		this.mark = mark ;
		stocks[i] = new  Stock(price, this);
		wholeSealers[i] = new  WholeSealer(price, this);
		
		
		
	}
	

	public String getName(){
		return this.name ;
	}
	
	public String getType(){
		return this.type ;
	}
	
	public String getMark(){
		return this.mark ;
	}
	
	public int getID(){
		return this.id ;
	}

	public static Product getProduct(int i) {
		return products[i];
	}

	public static void init(Shop shop) {
		WholeSealer wholeSealer[] = WholeSealer.getWholeSealer();
		products[0] = new Product("Moc", "Ordinateur", "Pear", shop.getStocks(), wholeSealer, 0, 199);
		products[1] = new Product("Tom", "Telephone", "Woki", shop.getStocks(), wholeSealer, 1, 100);
		products[2] = new Product("Bugs", "Ordinateur", "Wyse", shop.getStocks(), wholeSealer, 2, 150 );
		products[3] = new Product("House", "Ordinateur", "HP", shop.getStocks(), wholeSealer, 3, 185 );
		products[4] = new Product("P-phone", "Telephone", "Pear", shop.getStocks(), wholeSealer, 4, 150 );
		products[5] = new Product("Door", "Logiciel", "Microhard", shop.getStocks(), wholeSealer, 5, 20);
		products[6] = new Product("P-picture", "Logiciel", "Pear", shop.getStocks(), wholeSealer, 6, 80);
		products[7] = new Product("SS-Studio", "Logiciel", "Microhard", shop.getStocks(), wholeSealer, 7, 60);
		products[8] = new Product("Mulot", "Goodies", "i-logitech", shop.getStocks(), wholeSealer, 8, 10);
		products[9] = new Product("Ecouchère", "Goodies", "Pear", shop.getStocks(), wholeSealer, 9, 20);
	}
	

}
