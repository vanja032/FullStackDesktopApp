package projectPackage;

public class Item {

	private String itemID;
	private String itemName;
	private String itemImage;
	private String itemPrice;
	private String itemCategory;
	private int itemAmount;
	
	
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public int getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
	
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public Item(String itemID, String itemName, String itemImage, String itemPrice, String itemCategory,
			int itemAmount) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemImage = itemImage;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemAmount = itemAmount;
	}
	public Item() {
		super();
	}
	@Override
	public String toString() {
		return "Item [itemID='" + itemID + "', itemName='" + itemName + "', itemImage='" + itemImage + "', itemPrice='"
				+ itemPrice + "', itemCategory='" + itemCategory + "', itemAmount='" + itemAmount + "']";
	}
	
	
	
}
