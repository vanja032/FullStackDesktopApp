package projectPackage;

import java.util.ArrayList;

public class Stock { 
	
	private ArrayList<Item> stock; 
	private int stockSize;

	public ArrayList<Item> getStock() {
		return this.stock;
	}

	public int getStockSize() {
		return this.stockSize;
	}

	public void setStock(Item i) {
		this.stock.add(i); 
		this.stockSize++;
	}

	public Stock() {
		super(); 
		this.stock = new ArrayList<Item>();
		this.stockSize = 0;
	}
	
	public void RemoveElement(Item i)
	{
		this.stock.remove(i);
		this.stockSize--;
	}

}
