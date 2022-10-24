package projectPackage;

import java.util.ArrayList;

public class Cart { 
	
	private ArrayList<Item> cart; 
	private int cartSize;

	public ArrayList<Item> getCart() {
		return this.cart;
	}

	public int getCartSize() {
		return this.cartSize;
	}

	public void setStock(Item i) {
		this.cart.add(i); 
		this.cartSize++;
	}

	public Cart() {
		super(); 
		this.cart = new ArrayList<Item>();
		this.cartSize = 0;
	}
	
	public void RemoveElement(Item i)
	{
		this.cart.remove(i);
		this.cartSize--;
	}

}
