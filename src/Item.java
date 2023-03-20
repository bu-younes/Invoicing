
import java.util.ArrayList;

public class Item {
	
	ArrayList<Invoice> invoice = new ArrayList<Invoice>();
	
	
	private int iteamId ;
	private String itemaName ;
	private double unitPrice ;
	private int numberOfItems ;
	private int itemsPrice ;
	
	
	
	public int getIteamId() {
		return iteamId;
	}
	public void setIteamId(int iteamId) {
		this.iteamId = iteamId;
	}
	public String getItemaName() {
		return itemaName;
	}
	public void setItemaName(String itemaName) {
		this.itemaName = itemaName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getNumbverOfItems() {
		return numberOfItems;
	}
	public void setNumbverOfItems(int numbverOfItems) {
		this.numberOfItems = numbverOfItems;
	}
	public int getItemsPrice() {
		return itemsPrice;
	}
	public void setItemsPrice(int itemsPrice) {
		this.itemsPrice = itemsPrice;
	}
	

}
