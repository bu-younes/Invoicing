
import java.util.ArrayList;

public class Invoice {
	
	ArrayList<Item> itemsList = new ArrayList<Item>();
	
	private int invoiceId ;
	private int invoicDate ;
	private int invoice_phone ;
	private int numberOfItems ;
	private int totalAmount ;
	private int paidAmount ;
	private int totalBalanc ;
	private int fax ;
	private int tel ;
	private String email ;
	
	
	
	
	
	
	
	
	
	
	
	public int getInvoice_phone() {
		return invoice_phone;
	}
	public void setInvoice_phone(int invoice_phone) {
		this.invoice_phone = invoice_phone;
	}
	public int getFax() {
		return fax;
	}
	public void setFax(int fax) {
		this.fax = fax;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getInvoicDate() {
		return invoicDate;
	}
	public void setInvoicDate(int invoicDate) {
		this.invoicDate = invoicDate;
	}
	public int getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}
	public int getTotalBalanc() {
		return totalBalanc;
	}
	public void setTotalBalanc(int totalBalanc) {
		this.totalBalanc = totalBalanc;
	}
	

}
