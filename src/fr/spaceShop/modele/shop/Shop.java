package fr.spaceShop.modele.shop;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.spaceShop.controler.Controler;
import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.humans.actions.Customer;
import fr.spaceShop.modele.humans.actions.FaithFulCustomer;
import fr.spaceShop.modele.humans.actions.Guard;
import fr.spaceShop.modele.humans.actions.PDG;
import fr.spaceShop.modele.humans.actions.Seller;
import fr.spaceShop.modele.humans.actions.Worker;
import fr.spaceShop.modele.products.Purchase;
import fr.spaceShop.modele.products.PurchaseShop;
import fr.spaceShop.modele.products.Stock;

public class Shop implements Serializable { 
	private String name;
	private List<Customer> customers = new ArrayList();
	private List<Worker> guards = new ArrayList();
	private List<Worker> sellers = new ArrayList();
	private Worker pdg;
	private int nbGuard, nbSeller;
	private int money;
	private List<Integer> statMoneyEarn = new ArrayList();
	private List<Purchase> statCustomer = new ArrayList();
	private List<PurchaseShop> statPurchaseShop = new ArrayList();
	private Stock stocks[] = new Stock[10];


	public Shop(String name) {
		nbGuard = 0;
		nbSeller = 0;
		money = 1000;
		this.name = name;
		addToStatCurrentMoney();
	}

	public boolean thereGuard() {
		return (nbGuard > 0);
	}

	public boolean thereSeller() {
		return (nbSeller > 0);
	}


	public int getMoney() {
		return this.money;
	}

	public int getNbGuards() {
		return nbGuard;
	}

	public int getShopNbGuards() {
		return getNbGuards();
	}

	public int getNbSellers() {
		return nbSeller;
	}
	
	public PDG getPDG(){
		return (PDG) pdg;
	}

	public void setNbGuards(int i) {
		nbGuard = i;
	}

	public  void addToMoney(int x) {
		money += x;
	}
	
	public Stock[] getStocks(){
		return stocks;
	}
	
	public Stock getStock(int i){
		return stocks[i];
	}
	
	public void setStocks(Stock[] stocks){
		this.stocks = stocks;
	}
	
	public void actualiseShopStocks(int i, int quantity){
		stocks[i].setQuantity(quantity);
	}
	
	public void actualiseShopPrice(int price, int i) {
		stocks[i].setPrice(price);
	}

	public  Guard getRandomGuard() {
		return (Guard) guards.get((int) Math.random() * (nbGuard));
	}

	public  Worker getRandomWorker() {
		int random = (int) Math.random() * (nbGuard + nbSeller + 1);
		if (random < nbGuard)
			return guards.get(random);
		else if (random < nbSeller)
			return sellers.get(random);
		else
			return pdg;
	}

	public  Seller getRandomSeller() {
		return (Seller) sellers.get((int) Math.random()
				* (sellers.size()));
	}

	public  Seller getSeller(int i) {
		return (Seller) sellers.get(i);
	}

	public  Guard getGuard(int i) {
		return (Guard) guards.get(i);
	}

	public  void putToDeathCustomer(int id) {
		for (int i = 0; i < customers.size(); i++) {
			if (customers.get(i).getId() == id) {
				customers.remove(i);
				break;
			}
			
		}
	}

	public  void fire(int id) {
		for (int i = 0; i < sellers.size(); i++) {
			if (sellers.get(i).getId() == id) {
				sellers.remove(i);
				nbSeller--;
				break;
			}
		}
		for (int i = 0; i < guards.size(); i++) {
			if (guards.get(i).getId() == id) {
				guards.remove(i);
				nbGuard--;
				break;
			}
		}
	}

	public  void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public  void addWorker(PDG pdg) {
		this.pdg = pdg;
	}
	
	public  void addWorker(Guard guard) {
		guards.add(guard);
		nbGuard++;
	}

	public  void addWorker(Seller seller) {
		sellers.add(seller);
		nbSeller++;
	}

	public  int getCustomersSize() {
		return customers.size();
	}

	public  Customer getCustomer(int i) {
		return customers.get(i);
	}

	public  String describe() {
		String str = "<html>";
		str += name + " : <br>";
		str += "Dirigé par : " + pdg;
		str += "<br>Embauche " + nbGuard + " garde(s)<br>";
		str += "Embauche " + nbSeller + " vendeur(s)<br>";
		str += "Et possède " + money + " spaceuros</html>";
		return str;

	}

	public  void paySalries() {
		for (int i = 0; i < sellers.size(); i++) {
			if (money >= sellers.get(i).getSalary())
				money -= sellers.get(i).getSalary();
			else
				Controler.gameOver();
		}
		for (int i = 0; i < guards.size(); i++) {
			if (money >= guards.get(i).getSalary())
				money -= guards.get(i).getSalary();
			else
				Controler.gameOver();
		}
		if (money >= pdg.getSalary()) {
			int pdgSalary = pdg.getSalary();
			((PDG) pdg).addToMoney(pdgSalary);
			money -= pdgSalary;
		} else
			{
				((PDG) pdg).addToMoney(money);
				money = 0;
			}
	}

	public  void payTax() {
		addToStatCurrentMoney();
		int earn = statMoneyEarn.get(statMoneyEarn.size() - 1)
				- statMoneyEarn.get(statMoneyEarn.size() - 2);
		if (earn > 0)
			money -= earn / 4;

	}

	public  void addToStatCurrentMoney() {
		statMoneyEarn.add(money);
	}


	public Worker getWorker(int id) {
		for (int i = 0; i < guards.size(); i++) if(guards.get(i).getId() == id) return guards.get(i);
		for (int i = 0; i < sellers.size(); i++) if(sellers.get(i).getId() == id) return sellers.get(i);
		return pdg;
	}

	public FaithFulCustomer getFaithFulCustomer(int i) {
		for(int j =0; j< customers.size(); j++) if(i == customers.get(j).getId()) return (FaithFulCustomer) customers.get(j);
		return null;
	}

	public boolean haveInStock(int i) {
		return stocks[i].getQuantity()>0;
	}

	public void addToStatCustomer(Purchase purchase) {
		statCustomer.add(purchase);
	}
	

	public void addToStatPurchaseShop(PurchaseShop purchaseShop) {
		statPurchaseShop.add(purchaseShop);
		
	}

	public List<Integer> getStatMoneyEarn() {
		return statMoneyEarn;
	}

	public int getNbCustomers() {
		return customers.size();
	}

	public int getScore(int scenario) {
		Date startDate;
		int days = 0;
		if (scenario == 1){
			startDate = new Date(9056);
		}
		else if (scenario == 2){
			startDate = new Date(8056);
		}
		else {
			startDate = new Date(9812);
		}
		while(! startDate.isBefore(Date.currentDate)){
			days ++;
			Date.nextDay(startDate);
		}
		return customers.size()*50 + guards.size() * 200 + sellers.size() * 200 + statCustomer.size() + ((PDG) pdg).getMoney() - days*3;
	}

	public String getName() {
		return name;
	}

}
