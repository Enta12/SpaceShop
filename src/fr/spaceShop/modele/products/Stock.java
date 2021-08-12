package fr.spaceShop.modele.products;

import java.io.Serializable;

public class Stock implements Serializable{
	private int price ; 
	private int quantity;
	private Product product;
	
	public void setPrice(int price){
		this.price = price ; 
		
	}

	
	public int getPrice(){
		return this.price ;
	}
	
	public Stock (int price, Product product){
		this.price = price ; 
		quantity = 0;
		this.product = product;
	}

	public Stock(int price, int quantity, Product product) {
		this.price = price;
		this.quantity = quantity;
		this.product = product;
	}


	public Product getProduct() {
		return product;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
		
	}

}
