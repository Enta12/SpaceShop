package fr.spaceShop.modele.humans.actions;

import fr.spaceShop.controler.Controler;

public class Seller extends Worker{
	
	public Seller(){
		super();
		job = "Seller";
		jobFR = "Vendeur";
	}
	
	public Seller(Worker worker){
		super(worker);
		job = "Seller";
		jobFR = "Vendeur";
	}
	
	public int sell(Customer customer, int idStock) { 
		//get a random product from preferences
		int price = Controler.getStock(idStock).getPrice();
		Controler.actualiseShopStocks(Controler.getStock(idStock).getQuantity()-1, idStock);
		//money of customer's purchase with loss if seller is clumsy
		if(vision +2 < ((int) (Math.random() * ( 20)))) Controler.shop.addToMoney((int) (price - ((Math.random() * ( price - 0 )))));
		else Controler.shop.addToMoney(price);
		//return a bonus for fidelity if seller is hapiness
		if(hapinness +2 > ((int) (Math.random() * ( 20)))) return 1;
		else return -1;
	}
}
