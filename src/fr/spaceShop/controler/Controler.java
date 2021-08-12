package fr.spaceShop.controler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;

import fr.spaceShop.gui.Window;
import fr.spaceShop.modele.globalInformations.Date;
import fr.spaceShop.modele.humans.actions.Customer;
import fr.spaceShop.modele.humans.actions.FaithFulCustomer;
import fr.spaceShop.modele.humans.actions.Guard;
import fr.spaceShop.modele.humans.actions.Human;
import fr.spaceShop.modele.humans.actions.PDG;
import fr.spaceShop.modele.humans.actions.Seller;
import fr.spaceShop.modele.humans.actions.Worker;
import fr.spaceShop.modele.products.Product;
import fr.spaceShop.modele.products.Purchase;
import fr.spaceShop.modele.products.PurchaseShop;
import fr.spaceShop.modele.products.Stock;
import fr.spaceShop.modele.products.WholeSealer;
import fr.spaceShop.modele.shop.Shop;


public class Controler {
	static Window window;
	public static Shop shop;
	
	private static int scenario = 1;

	public Controler(Window window) {
		this.window = window;
	}
	
	public static void initProduct(){
		Product.init(shop);
		window.initProduct(shop.getStocks(), WholeSealer.getWholeSealer());
	}

	public static void newGame() {
		Human.init();
		window.welcomingWindow();
		if(scenario ==3){
			initNewShop("Lucy","John Lemon", 9812);
		}
		else if (scenario ==2){
			initNewShop("Station Abandonnée","Nicolache", 8056);
		}
		else{
			initNewShop("Patisserie","Oragarn", 9056);
		}
		initProduct();
		window.actualise();
		initLg();
	}
	
	private static void initNewShop(String shopName, String pdgName, int year){
		Date.init(year);
		shop = new Shop(shopName);
		window.gameScreen(scenario, shopName);
		addWorker(new PDG(pdgName, -scenario));
		
	}


	public static void load() throws ClassNotFoundException {
		Human.init();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("files/scenario"))));

			scenario = ois.readInt();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//date
		Date.load();
		//shop
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("files/shop"))));

			shop = (Shop) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initProduct();
		//Stock
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("files/stocks"))));

			shop.setStocks((Stock[]) ois.readObject());
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//legendary
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("files/customerLg"))));

			FaithFulCustomer.setCustomerLgExist((boolean[]) ois.readObject());
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(new File("files/workerLg"))));

			Worker.setWorkerLgExist((boolean[]) ois.readObject());
			ois.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		window.gameScreen(scenario, shop.getName());
		initLg();
		window.addWorker(shop.getPDG());
		for (int i = 0; i < shop.getNbSellers(); i++){
			window.addWorker(shop.getSeller(i));
		}
		for (int i = 0; i < shop.getNbGuards(); i++){
			window.addWorker(shop.getGuard(i));
		}
		for (int i = 0; i < shop.getNbCustomers(); i++){
			if(shop.getCustomer(i).getJob().equals("FaithFulCustomer"))window.addFaithfullCustomer(shop.getCustomer(i));
		}
	}

	private static void initLg() {
		FaithFulCustomer.setCustomerLgExist();
		Worker.setWorkerLgExist();
		FaithFulCustomer.initLg();
		Worker.initLg();
		
	}

	public static void gameLoop(int nbDay) {
		// random things
		for(int i = 0; i < nbDay; i++){
			for (int j = 0; j < shop.getCustomersSize(); j++) {
				if (shop.getCustomer(j).getFidelity() > (int) (Math.random() * 20)) {
					shop.getCustomer(j).buy();
					if (shop.getCustomer(j).wantSteal())
						shop.getCustomer(j).trySteal();
				}
				else if (shop.getCustomer(j).getFidelity() == 0){
					shop.getCustomer(j).addToFidelity(1);
				}
			}
			for (int j = 0; j < shop.getShopNbGuards(); j++) {
				if (shop.getGuard(j).wantSteal())
					shop.getGuard(j).trySteal();
			}
			for (int j = 0; j < shop.getNbSellers(); j++) {
				if (shop.getSeller(j).wantSteal())
					shop.getSeller(j).trySteal();
			}
			if (shop.getPDG().wantSteal())
				shop.getPDG().trySteal();
			if (Date.nextDay(Date.currentDate)) {
				endMonth();
				window.actualiseStatMoneyEarn(shop.getStatMoneyEarn());
			}
			if (((int) (Math.random() * (20))) == 0)
				randomEvent();
			if(haveWin()) win() ;
			window.actualise();
		}
	}
	private static void win(){
		window.win(scenario, shop.getScore(scenario));
		save("isGame", false);
	}
	

	private static boolean haveWin() {
		boolean win;
		if(scenario ==3){
			win =(shop.getNbSellers() == 20 && shop.getNbCustomers() == 30 && shop.getMoney() == 100000);
		}
		else if (scenario ==2){
			win =(shop.getMoney() == 100000);
		}
		else{
			win =(shop.getNbGuards() == 10 && shop.getMoney() == 20000);
		}
		return win;
	}

	private static void endMonth() {
		shop.addToStatCurrentMoney();
		shop.payTax();
		shop.paySalries();
	}

	public static void addWorker(PDG pdg) {
		shop.addWorker(pdg);
		window.addWorker(pdg);
	}
	
	public static void addWorker(Seller seller) {
		shop.addWorker(seller);
		window.addWorker(seller);
	}
	
	public static void addWorker(Guard guard) {
		shop.addWorker(guard);
		window.addWorker(guard);
	}

	public static void addFaithfullCustomer(FaithFulCustomer customer) {
		window.addFaithfullCustomer(customer);

	}

	public static void alert(Human steal, String message) {
		window.alertSteal(steal, message);
	}

	public static void fire(Human worker) {
		shop.fire(worker.getId());
		window.removeWorkers(worker);
	}

	public static void putToDeath(Customer customer) {
		shop.putToDeathCustomer(customer.getId());
		if(customer.getFidelity() == 20){
			window.removeFaithFullCustomer(customer);
		}
		
	}
	
	public static void candidate(Worker workerCandidate){
		window.candidate(workerCandidate, shop.getPDG());
	}

	public static void actualise() {
		window.actualise();
		window.repaint();
		
	}

	public static JDialog adminWorker(int i) {
		if(i == shop.getPDG().getId()) return window.adminWorker(shop.getPDG());
		else{
			Worker worker = shop.getWorker(i);
			return window.adminWorker(worker);
		}
	}
	

	public static JDialog adminCustomer(int i) {
		return window.adminCustomer(shop.getFaithFulCustomer(i));
		
	}

	public static void pdgGiveToShop(int money) {
		shop.addToMoney(money);
		shop.getPDG().addToMoney(-money);
		
	}

	public static void changeJob(Worker worker) {
		Controler.fire(worker);
		if(worker.getJob().equals("Seller")){ 
			addWorker(worker.toGuard());
		}
		else addWorker(worker.toSeller());
		
	}

	public static void actualiseShopPrice(int price, int i) {
		shop.actualiseShopPrice(price, i);
	}

	public static void actualiseShopStocks(int quantity, int i) {
		shop.actualiseShopStocks(i, quantity);
	}
	
	public static int getMoney(){
		return shop.getMoney();
	}
	
	public static void addToMoney(int money){
		shop.addToMoney(money);
	}
	
	public static void reloadProductManager(){
		window.reloadProductManager(shop.getStocks(), WholeSealer.getWholeSealer());
	}

	public static Stock getStock(int i) {
		return shop.getStock(i);
	}

	public static boolean haveInStock(int i) {
		return shop.haveInStock(i);
	}

	public static void updatePDGAdministrator() {
		window.updatePDGAdministrator(shop.getPDG());
	}

	

	public static PDG getPDG() {
		return shop.getPDG();
	}

	public static void addToStatCustomer(Purchase purchase) {
		shop.addToStatCustomer(purchase);
		window.addElement(purchase);
		
	}


	public static void addToStatPurchaseShop(PurchaseShop purchaseShop) {
		window.addElement(purchaseShop);
		shop.addToStatPurchaseShop(purchaseShop);
		
	}

	public static void displayStats() {
		window.displayStats();
		
	}

	public static void gameOver() {
		save("isGame", false);
		window.gameOver(scenario, shop.getScore(scenario));
	}

	public static void setScenario(int scenarioNb) {
		scenario = scenarioNb;
		
	}

	public static int getGuardsNb() {
		return shop.getNbGuards();
	}
	
	public static int getSellersNb() {
		return shop.getNbSellers();
	}
	
	public static void Menu(){
		save("shop", shop);
		save("gameDate", Date.currentDate);
		save("customerLg", FaithFulCustomer.getCustomerLgExist());
		save("workerLg", Worker.getWorkerLgExist());
		save("scenario", scenario);
		save("isGame", true);
		save("stocks", shop.getStocks());
		window.backToMenu();
	}
	
	static void save(String files, boolean bool){
		ObjectOutputStream oos;
		try {
	        oos = new ObjectOutputStream(
	                new BufferedOutputStream(
	                  new FileOutputStream(
	                    new File("files/" + files))));
	        oos.writeBoolean(bool);
	        oos.close();
	   	} catch (FileNotFoundException e) {
		      e.printStackTrace();
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	
	private static void save(String files, int interger){
		ObjectOutputStream oos;
		try {
	        oos = new ObjectOutputStream(
	                new BufferedOutputStream(
	                  new FileOutputStream(
	                    new File("files/" + files))));
	        oos.writeInt(interger);
	        oos.close();
	   	} catch (FileNotFoundException e) {
		      e.printStackTrace();
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	private static void save(String files, Object objectToSave){
		ObjectOutputStream oos;
		try {
	        oos = new ObjectOutputStream(
	                new BufferedOutputStream(
	                  new FileOutputStream(
	                    new File("files/" + files))));
	        oos.writeObject(objectToSave);
	        oos.close();
	   	} catch (FileNotFoundException e) {
		      e.printStackTrace();
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}
	
	/////////////////////////////////EVENTS////////////////////////////////
	
	
	private static void randomEvent() {
		int randomThings = (int) (Math.random() * (100));
		if (randomThings < 10){
			Worker worker = new Worker();
			worker.candidate();
		}
		else if (randomThings < 20){
			Customer customer = new Customer();
			shop.addCustomer(customer);
		}
		else if (randomThings < 25){
			legendaryCustomer(randomThings);
		}
		else if (randomThings < 30){
			legendaryWorker(randomThings);
		}
		else{
			switch(randomThings){
			case 30 : moleEvent(); break;
			case 31 : ninjaEvent(); break;
			case 32 : deasesEvent(); break;
			case 33 : explosionEvent(); break;
			case 34 : breakEvent(); break; 
			case 35 : virusEvent(); break;
			case 36 : hamsterEvent(); break;
			case 37 : crashEvent(); break;
			case 38 : appleEvent(); break;
			case 39 : fireEvent(); break;
			}
		}
	}

	private static void fireEvent() {
		shop.addToMoney(-50);
		window.showEvent(8);
		window.msg("Vous avez perdu 50 spaceuros " , "Au feu !");
		if (shop.getMoney()<0) gameOver();
		
	}

	private static void appleEvent() {
		Worker worker = shop.getRandomWorker();
		worker.addToRandomStat(1);
		window.showEvent(9);
		window.msg(worker + " se sent mieux !", "Une pomme étrange !");
		
	}

	private static void crashEvent() {
		shop.addToMoney(500);
		window.showEvent(0);
		window.msg("Vous avez gagné 500 spaceuros", "Un avion s'est crashé !");
		
	}

	private static void hamsterEvent() {
		window.showEvent(1);
		boolean catchHamster = false;
		for(int i = 0; i < Product.PRODUCT_NB && !catchHamster; i++){
			if (Human.confrontation(shop.getRandomWorker().getStrenght(), 15)) catchHamster = true;
			else {
				shop.getStock(i).setQuantity(0);
				window.msg("Vous avez perdu vos stocks de " + shop.getStock(i).getProduct().getName(), "Des hamsters voleurs !");
			}
		}
	}

	private static void breakEvent() {
		window.showEvent(2);
		shop.addToMoney(-500);
		window.msg("Vous avez perdu 500 spaceuros " , "Oulala le panneau droit est parti !");
		if (shop.getMoney()<0) gameOver();
	}

	private static void virusEvent() {
		window.showEvent(3);
		for(int i = 0; i< Product.PRODUCT_NB; i++){
			if(shop.getStock(i).getProduct().getType().equals("Logiciel")){
				shop.getStock(i).setQuantity(0);
				window.msg("Vous avez perdu vos stocks de " +  shop.getStock(i).getProduct().getName(), "Virus, virus ...");
			}
		}
		
	}

	private static void explosionEvent() {
		window.showEvent(4);
		for(int i = 0; i< Product.PRODUCT_NB; i++){
			if(shop.getStock(i).getProduct().getType().equals("Teléphone")){
				shop.getStock(i).setQuantity(0);
				window.msg("Vous avez perdu vos stocks de " +  shop.getStock(i).getProduct().getName(), "BOOOUM !");
			}
		}
		
	}

	private static void ninjaEvent() {
		window.showEvent(5);
		fight(shop.getRandomWorker(), 15);
	}

	private static void fight(Worker worker, int strenght){
		int bonusGuard = 0;
		if (worker.getJob() == "Guard") bonusGuard = 5;
		if (!Human.confrontation(worker.getStrenght() + bonusGuard, strenght)){
			window.msg(worker + " est mort" , "Resultat de combat");
			if(worker.getJob().equals("PDG")) gameOver();
			else fire(worker);
		}
		else{
			window.msg(worker + " a bravé la mort" , "Resultat de combat");
		}
	}
	
	private static void deasesEvent() {
		if(shop.getNbSellers() >0)eject(shop.getRandomSeller());
		else if(shop.getNbGuards() >0) eject(shop.getRandomGuard());
	}
	
	private static void eject(Worker worker){
		window.showEvent(6);
		window.msg(worker + " est mort" , "Resultat de l'ejection");
		fire(worker);
	}

	private static void moleEvent() {
		window.showEvent(7);
		fight(shop.getRandomWorker(), 20);
		
	}

	private static void legendaryWorker(int randomThings) {
		Worker workerLg = Worker.getWorkerLg(randomThings - 25);
		if (workerLg != null) {
			workerLg.candidate();
		}
	}

	private static void legendaryCustomer(int randomThings) {
		FaithFulCustomer customerrLg = FaithFulCustomer.getCustomerLg(randomThings - 20);
		if (customerrLg != null) {
			shop.addCustomer(customerrLg);
			addFaithfullCustomer(customerrLg);
		}
		
	}

	public static Product getProduct(int idStock) {
		return Product.getProduct(idStock);
	}

	public static int getWholeSealerPrice(int idStock) {
		return WholeSealer.getWholeSealerPrice(idStock);
	}
}
