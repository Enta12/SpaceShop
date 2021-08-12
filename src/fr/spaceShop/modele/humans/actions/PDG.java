package fr.spaceShop.modele.humans.actions;

import fr.spaceShop.controler.Controler;

public class PDG extends Worker{
	private static int money;

	public PDG(String name, int picture){
		super(name, picture);
		job = "PDG";
		jobFR = "PDG";
		money = 0;
	}
	
	
	public void addToMoney(int sum){
		money += sum;
	}
	
	
	public void steal() {
		int sumSteal;
		if(Controler.shop.getMoney()>10) sumSteal = (int) -(Math.random() * ( 10));
		else sumSteal = (int) -(Math.random() * ( Controler.shop.getMoney()));
		Controler.shop.addToMoney(sumSteal);
		money -= sumSteal;
	}
	
	public void trySteal(){
		Worker vigilantWorker = Controler.shop.getRandomWorker();
		if (confrontation(agility, vigilantWorker.getVision())) steal();
		else{
			vigilantWorker.addToCriminal(1);
		}
	}

	public int getMoney() {
		return money;
	}
	
}
