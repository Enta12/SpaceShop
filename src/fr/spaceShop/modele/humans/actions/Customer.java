package fr.spaceShop.modele.humans.actions;

import fr.spaceShop.controler.Controler;
import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.products.Purchase;
import fr.spaceShop.modele.products.Stock;


public class Customer extends Human{
	private int fidelity;
	private double bForEquation;
	
	
	public Customer(){
		super();
		job = "Customer";
		jobFR = "Client";
		fidelity = 1;
		bForEquation  = -1 * (Math.random() * (0.02));
	}
	
	public Customer(Customer customer){
		super(customer);
		job = "Customer";
		jobFR = "Client";
		fidelity = 20;
		bForEquation = customer.getBForEquation();
		
	}
	
	public Customer(String name, int criminal, int charism, int strenght, int agility, int vision, int fidelity) {
		super(name, criminal, charism, strenght, agility, vision);
		job = "Customer";
		jobFR = "Client";
		this.fidelity = fidelity;
		bForEquation  = -1 * (Math.random() * (0.02));
	}
	
	public double getBForEquation(){
		return bForEquation;
	}
	
	
	public int getFidelity(){
		return fidelity;
	}
	
	public void buy(){
		int idStock = (int) (Math.random() * 10);
		Stock product = Controler.getStock(idStock);
		int priceWholeSealer = Controler.getWholeSealerPrice(idStock);
		if(Controler.haveInStock(idStock) && (((bForEquation * ((product.getPrice()+priceWholeSealer)*4+priceWholeSealer)+20)>(Math.random() * (20))))) {
			Worker seller;
			if(Controler.shop.thereSeller()) seller = Controler.shop.getRandomSeller();
			else seller = Controler.shop.getRandomWorker();
			if(fidelity<20){
				Controler.addToStatCustomer(new Purchase(new Date(Date.currentDate), product.getPrice(), Controler.getProduct(idStock), seller));
				if((fidelity += seller.sell(this, idStock))==20){
					Controler.shop.putToDeathCustomer(id);
					FaithFulCustomer newFaithfulCustomer = new FaithFulCustomer(this);
					Controler.shop.addCustomer(newFaithfulCustomer);
					Controler.addFaithfullCustomer(newFaithfulCustomer);
				}
			}
			else{
				seller.sell(this, idStock);
				Controler.addToStatCustomer(new Purchase(new Date(Date.currentDate), product.getPrice(), Controler.getProduct(idStock), this, seller));
			}
		}
	}
	public void trySteal(){
		//type of vigilant persone
		int idStock = (int) (Math.random() * 10);
		Stock product = Controler.getStock(idStock);
		if(Controler.haveInStock(idStock)){
			Worker vigilantPersonn;
			int guardBonus = 0;
			if(Controler.shop.thereGuard()) {
				vigilantPersonn = Controler.shop.getRandomGuard();
				guardBonus = 4;
			}
			else vigilantPersonn = Controler.shop.getRandomWorker();
			
			//if the vigilant is this personn 
			if(vigilantPersonn.getId() == id) steal(product, idStock);
			
			//confrontation
			if (confrontation(agility, vigilantPersonn.getVision()+guardBonus)) steal(product, idStock);
			else {
				if(vigilantPersonn.catchThief(this)){
					Controler.alert(this, this + " ра щtщ vu en train de voler par " + vigilantPersonn);
				}
			}
		}
	}
	
	public void steal(Stock product, int idStock) {
		Controler.actualiseShopStocks(product.getQuantity()-1, idStock);
		
	}
	

	public void addToFidelity(int i) {
		fidelity += 1;
		
	}
}
