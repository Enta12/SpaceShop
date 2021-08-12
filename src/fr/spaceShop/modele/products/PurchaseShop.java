package fr.spaceShop.modele.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.spaceShop.modele.globalInformations.Date;

public class PurchaseShop implements Serializable{
	private Date date;
	private List<Stock> stockBuy = new ArrayList();
	
	
	public PurchaseShop(Date date, Stock[] stocksBuy){
		 this.date = date;
		 for(int i = 0; i<Product.PRODUCT_NB; i++){
			 if(stocksBuy[i].getQuantity() != 0){
				 stockBuy.add(stocksBuy[i]);
			 }
		 }
	}
	
	public String toString(){
		String str = date + "\n";
		for(int i = 0; i<stockBuy.size(); i++){
			str += stockBuy.get(i).getProduct().getName() + " " + stockBuy.get(i).getQuantity() + "\n"; 
		}
		str += "\n\n";
		return str;
	}
	
	public List<Stock> getStockBuy(){
		return stockBuy;
	}
	
	public Date getDate(){
		return date;
	}
}
