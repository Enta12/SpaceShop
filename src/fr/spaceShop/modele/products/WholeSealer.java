package fr.spaceShop.modele.products;

public class WholeSealer{
	private int price;
	private Product product;
	private static WholeSealer wholeSealer[] = new WholeSealer[Product.PRODUCT_NB];
	
	public WholeSealer(int price, Product product){
		this.price = price;
		this.product = product;
	}
	
	public int getPrice(){
		return price;
	}

	public static WholeSealer[] getWholeSealer() {
		return wholeSealer;
	}
	
	public static int getWholeSealerPrice(int idStock) {
		return wholeSealer[idStock].getPrice();
	}

	public static WholeSealer getWholeSealer(int id) {
		return wholeSealer[id];
	}
	
	
}
