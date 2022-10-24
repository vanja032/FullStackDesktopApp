package projectPackage;

import java.util.ArrayList;

public class Orders { 
	
	private ArrayList<Item> orders; 
	private int ordersSize;

	public ArrayList<Item> getOrders() {
		return this.orders;
	}

	public int getOrdersSize() {
		return this.ordersSize;
	}

	public void setOrder(Item i) {
		this.orders.add(i); 
		this.ordersSize++;
	}

	public Orders() {
		super(); 
		this.orders = new ArrayList<Item>();
		this.ordersSize = 0;
	}
	
	public void RemoveElement(Item i)
	{
		this.orders.remove(i);
		this.ordersSize--;
	}

}
