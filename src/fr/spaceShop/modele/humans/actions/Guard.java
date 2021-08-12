package fr.spaceShop.modele.humans.actions;

import fr.spaceShop.controler.Controler;

public class Guard extends Worker{
	
	public Guard(){
		super();
		job = "Guard";
		jobFR = "Garde";
	}
	
	public Guard(Worker worker){
		super(worker);
		job = "Guard";
		jobFR = "Garde";
	}

	
	public void trySteal(){
		Worker vigilantWorker = Controler.shop.getRandomWorker();
		if(vigilantWorker.getId() != id){
			if (confrontation(agility, vigilantWorker.getVision())) steal();
			else{
				Controler.alert(this, this + " ра щtщ vu en train de voler par " + vigilantWorker);
			}
		}
	}
	
	public boolean catchThief(Human thief) {
		return confrontation(((strenght + agility) /2) + 4, (thief.getStrenght() + thief.getAgility()) /2);
	}
}
