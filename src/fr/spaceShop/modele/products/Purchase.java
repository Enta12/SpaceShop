package fr.spaceShop.modele.products;

import java.io.Serializable;

import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.humans.actions.Customer;
import fr.spaceShop.modele.humans.actions.Worker;

public class Purchase implements Serializable{
	private Date date ;
	private int price;
	private Product product;
	private Customer customer = null;
	private Worker seller;
	
	public Purchase(Date date, int price, Product product, Customer customer, Worker seller){
		 this.date = date;
		 this.price = price;
		 this.product = product;
		 this.customer = customer;
		 this.seller = seller;
	}
	
	public Purchase(Date date, int price, Product product, Worker seller){
		this.date = date;
		 this.price = price;
		 this.product = product;
		 this.seller = seller;
	}
	
	
	public Date getDate(){
		return this.date ;
	}
	
	void setDate(Date date){
		this.date = date ; 
		
	}
	
	public String toString(){
		return date + "  " + product.getName();
	}
	
	public Customer getCustomer(){
		return customer;
	}

	public Product getProduct() {
		return product;
	}

	public int getPrice() {
		return price;
	}

	public Worker getSeller() {
		return seller;
	}

}
