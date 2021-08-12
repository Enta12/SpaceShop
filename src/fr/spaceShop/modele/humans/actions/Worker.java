package fr.spaceShop.modele.humans.actions;

import fr.spaceShop.controler.Controler;

public class Worker extends Human implements Sell, BeOnGuard{
	private int salary;
	protected int hapinness;
	private PersonalInformations personalInformations;
	private int level;
	private static Worker legendaryWorker[] = new Worker[5];
	private static boolean workerLgExist[] = new boolean[5];
	
	public Worker(){
		super();
		job = "Worker";
		jobFR = "Postulant";
		hapinness = (int) (Math.random() * 20);
		personalInformations = new PersonalInformations(name);;
		calculateLevel();
		calculateSalary();
	}
	
	public Worker(Worker worker){
		super(worker);
		job = "Worker";
		jobFR = "Postulant";
		salary = worker.getSalary();
		hapinness = worker.getHapiness();
		personalInformations = worker.getPersonalInformations();
		level = worker.getLevel();
	}
	
	public Worker(String name, int picture) {
		super(name);
		job = "Worker";
		jobFR = "Postulant";
		hapinness = (int) (Math.random() * 20);
		personalInformations = new PersonalInformations(name, picture);;
		calculateLevel();
		calculateSalary();
	}
	public Worker(String name, int criminal, int charism, int strenght, int agility, int vision, int hapiness, int picture) {
		super(name, criminal, charism, strenght, agility, vision);
		job = "Worker";
		jobFR = "Postulant";
		this.hapinness = hapiness;
		personalInformations = new PersonalInformations(name, picture);
		calculateLevel();
		calculateSalary();
	}
	
	public static void initLg(){
		legendaryWorker[0] = new Worker("El Fuerza", 0, 10, 100, 10, 10, 10, 25);
		legendaryWorker[1] = new Worker("Yasmine", 10, 10, 10, 10, 100, 10, 26);
		legendaryWorker[2] = new Worker("Vodka", 0, 10, 10, 100, 10, 10, 27);
		legendaryWorker[3] = new Worker("Calcifà¨re", 100, 10, 100, 100, 10, 10, 28);
		legendaryWorker[4] = new Worker("Alexandre", 100, 10, 10, 10, 10, -1, 29);
	}
	
	public static Worker getWorkerLg(int i){
		if (workerLgExist[i]){
			workerLgExist[i] = false;
			return legendaryWorker[i];
		}
		else return null;
	}
	
	public static void setWorkerLgExist(){
		workerLgExist[0] = true;
		workerLgExist[1] = true;
		workerLgExist[2] = true;
		workerLgExist[3] = true;
		workerLgExist[4] = true;
	}
	
	public static boolean[] getWorkerLgExist(){
		return workerLgExist;
	}
	
	public static void setWorkerLgExist(boolean arrayExistLg[]){
		workerLgExist = arrayExistLg;
	}


	public void trySteal(){
		//type of vigilant persone
		Worker vigilantPersonn;
		int guardBonus = 0;
		if(Controler.shop.thereGuard()) {
			vigilantPersonn = Controler.shop.getRandomGuard();
			guardBonus = 4;
		}
		else vigilantPersonn = Controler.shop.getRandomWorker();
		
		//if the vigilant is this personn 
		if(vigilantPersonn.getId() == id) steal();
		
		//confrontation
		else if (confrontation(agility, vigilantPersonn.getVision()+guardBonus)) steal();
		else {
			if(vigilantPersonn.catchThief(this)){
				Controler.alert(this, this + " à  été vu en train de voler par " + vigilantPersonn);
			}
		}
	}
	
	public void steal() {
		if(Controler.shop.getMoney()>10)Controler.shop.addToMoney((int) -(Math.random() * ( 10 - 0)));
		else Controler.shop.addToMoney((int) -(Math.random() * (Controler.shop.getMoney() - 0)));
	}
	
	
	public int getHapiness(){
		return hapinness;
	}
	
	public PersonalInformations getPersonalInformations(){
		return personalInformations;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void addToCriminal(int i){
		criminal += i;
	}
	
	public void calculateLevel(){
		level = agility + charism + vision + strenght;
	}
	public void calculateSalary(){
		if(level<10) salary = 50;
		else if (level<30) salary = 150;
		else if (level<40) salary = 200;
		else if (level<60) salary = 400;
		else salary = 800;
	}
	
	public int sell(Customer customer, int idStock) { //think to remove product from stocks
		//get a random product from preferences
		int price = Controler.getStock(idStock).getPrice();
		Controler.actualiseShopStocks(Controler.getStock(idStock).getQuantity()-1, idStock);
		//money of customer's purchase with loss if seller is clumsy
		if(vision < ((int) (Math.random() * (20)))) Controler.shop.addToMoney(price - ( (int) (Math.random() * (price))));
		else Controler.shop.addToMoney(price);
		//return a bonus for fidelity if seller is hapiness
		if(hapinness > ((int) (Math.random() * (20)))) return 0;
		else return -1;
	}
	

	
	public boolean catchThief(Human thief) {
		return confrontation((strenght + agility) /2, (thief.getStrenght() + thief.getAgility()) /2);
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void candidate(){ 
		Controler.candidate(this);
	}
	
	public Guard toGuard(){
		return new Guard(this);
	}
	
	public Seller toSeller(){
		return new Seller(this);
	}

	public String getMail() {
		return personalInformations.getMail();
	}

	public int getPicture() {
		return personalInformations.getPicture();
	}

	public void addToRandomStat(int i) {
		int stat = (int) Math.random() * (6);
		switch (stat){
		case 0: this.agility++; break;
		case 1: this.charism++; break;
		case 2: this.strenght++; break;
		case 3 : this.criminal++; break;
		case 4: this.hapinness++; break;
		case 5: this.vision++; break;
		}
		
	}
	
}
