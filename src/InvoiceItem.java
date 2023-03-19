
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InvoiceItem {

	transient Scanner sr = new Scanner(System.in);
	ArrayList<Shop> shopList = new ArrayList<Shop>();
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<Invoice> InvoicList = new ArrayList<Invoice>();

	/// SHOP///
	public void AddshopDetails() {
		boolean t1 = false;
		do {
			t1 = false;
			try // for handing the exception
			{
				Shop temShop = new Shop();
				System.out.println("Enter the shop ID  ");
				String shopId = sr.next();
				temShop.setShopName(shopId);
				System.out.println("Enter the shop ID ");
				int shopName = sr.nextInt();
				temShop.setShopId(shopName);

				shopList.add(temShop);
			} catch (InputMismatchException w) { // for handing the try and showing the given pritn insted of showing an
													// error excpation
				System.out.println("who comes the ID could be a String!!!!!! please focuse and repet ");
				System.out.println("========= repeat shop name & ID =================");
				t1 = true;
				sr.nextLine(); // with out the sc.nextLine() there will be an infinty loop going
			}
		} while (t1); // we have to do (do - while) to make the condation happen

	}

	public void printShopDetails() {
		for (Shop a : shopList) {
			System.out.println("the shop name is : " + a.getShopName() + "======");
		}

	}

	/// ITEMS///

	public void addInvoiceItems() {
		Invoice invoi = new Invoice();
		System.out.println("Enter the invoice_item_Id");
		int invoiceItemsId = sr.nextInt();
		System.out.println("Enter the previes invoice ID");
		int invoiceId = sr.nextInt();
		System.out.println("Enter the items ID");
		int itemsID = sr.nextInt();
		System.out.println("Enter the item Name ");
		String itemName = sr.next();
		System.out.println("Enter the unit price ");
		double unitPrice = sr.nextDouble();
		System.out.println("Enter the quantity ");
		int quantity = sr.nextInt();
		System.out.println("Enter the quantity amount ");
		int quantityAmount = sr.nextInt();
		InvoicList.add(invoi);

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "INSERT INTO InvoiceItems (InvoiceItem_Id,Invoice_Id,Item_Id,Item_Name,UnitPrice,Quantity,Qty_Amount)"
					+ "VALUES (" + invoiceItemsId + "," + invoiceId + "," + itemsID + ",'" + itemName + "'," + unitPrice
					+ "," + quantity + "," + quantityAmount + ")";
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("inserted successfully : " + sql);
			} else {
				System.out.println("insertion failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public void printCustomerName() {
		for (Customer a : customerList) {
			System.out.println("the customer name is : " + a.getCustomerFullName());
		}
	}

	public void print() {
		for (Item a : itemList) {
			System.out.println("the item price is :    " + a.getItemsPrice() + "======");
			System.out.println("the item number is :   " + a.getNumbverOfItems() + "======");
			System.out.println("the item ID is :       " + a.getIteamId() + "======");
		}
	}

	public void deletDataBaseItems() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			System.out.println("Enter the Item ID ");
			String sql = "DELETE FROM [dbo].[InvoiceItems]\r\n" + "      WHERE Item_Id= " + sr.nextInt();
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("Deleted successfully : " + sql);
			} else {
				System.out.println("Deleted failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public void updateDataBsePrice() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			System.out.println("Enter the Item ID ");
			int Item_id = sr.nextInt();
			System.out.println("Enter the new price");
			double newPrice = sr.nextDouble();
			String sql = "UPDATE [dbo].[InvoiceItems]\r\n" + "   SET[UnitPrice] = " + newPrice + " WHERE Item_Id = "
					+ Item_id;
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("Updated successfully : " + sql);
			} else {
				System.out.println("Update failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void changeitemprice() {
		System.out.println("Enter the ID number to iteam you want to change ");
		int induxtnumber = sr.nextInt();
		Item tempItem = new Item();
		tempItem = itemList.get(induxtnumber);
		System.out.println("Enter the new price ");
		tempItem.setItemsPrice(sr.nextInt());
		itemList.set(induxtnumber, tempItem);
		System.out.println("the item have been chanced ");
	}

	public void countItems() {
		int totalItems = 0;
		for (Item a : itemList) {
			totalItems = totalItems + a.getNumbverOfItems();
              
		}
		System.out.println("The total Items is : " + totalItems);
	}

	public void totalsales() {
		double totalPrice = 0;
		for (Item a : itemList) {
			totalPrice = totalPrice + a.getItemsPrice();

		}
		System.out.println("The total price is : " + totalPrice);
	}


	
	
	public void countItems1()
	{
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			int Item_id = 0 ;
			String sql = "SELECT COUNT(Item_Id) as ItemsNumber FROM InvoiceItems;";
            ResultSet countItems = st.executeQuery(sql);
            while(countItems.next())
            {
            	System.out.println("the number of Items is " + countItems.getInt("ItemsNumber"));
            }
			

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	
	
	
	public void countInvoice1()
	{
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			int Invoice = 0 ;
			String sql = "SELECT COUNT(invoice_Id) as ItemsNumber FROM Invoices;";
            ResultSet countInvoice = st.executeQuery(sql);
            while(countInvoice.next())
            {
            	System.out.println("the number of Invoice is " + countInvoice.getInt("ItemsNumber"));
            }
			

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	/// CUSTOMER///

	public void updateDataBseCustomerPhone() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			System.out.println("Enter the Customer ID ");
			int Item_id = sr.nextInt();
			System.out.println("Enter the new phone number");
			int customerPhoneNumber = sr.nextInt();
			String sql = "UPDATE [dbo].[Customers]\r\n" + " SET[Customer_Phone_Number] = " + customerPhoneNumber
					+ " WHERE Customer_Id = " + Item_id;
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("Updated successfully : " + sql);
			} else {
				System.out.println("Update failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void deletDataBaseCustomer() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			System.out.println("Enter the Customer ID you want to delete ");
			String sql = "DELETE FROM [dbo].[Customers]\r\n" + "      WHERE customer_ID = " + sr.nextInt();
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("Deleted successfully : " + sql);
			} else {
				System.out.println("Deleted failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public void ADDcustomer() {
		Customer customer = new Customer();
		System.out.println("Enter the customer name");
		String customerName = sr.next();
		customer.setCustomerFullName(customerName);
		System.out.println("Enter the custoemr Phone number  ");
		int customerPhomeNumber = sr.nextInt();
		customer.setCustomerPhoneNumber(customerPhomeNumber);
		customerList.add(customer);

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "INSERT INTO Customers (Customer_Full_Name,Customer_Phone_Number)"

					+ "VALUES ('" + customerName + "','" + customerPhomeNumber + "')";
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("inserted successfully : " + sql);
			} else {
				System.out.println("insertion failed");
			}
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

///INVOICE///
	public void addInvoice() {
		System.out.println("Enter the invoice id ");
		int invoiceId = sr.nextInt();
		System.out.println("Enter the customer ID ");
		int customerId = sr.nextInt();
		System.out.println("enter the invoice Date");
		String invoiceDate = sr.next();
		System.out.println("Enter number of Items");
		int itemsNumber = sr.nextInt();
		System.out.println("Enter the total amount");
		double totalAmount = sr.nextDouble();
		System.out.println("Enter the paid amount ");
		double paidAmount = sr.nextDouble();
		System.out.println("Enter the total balance");
		double totalbalance = sr.nextDouble();

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "INSERT INTO Invoices (Invoice_Id,Customer_Id,Invoice_Date,Number_Of_Items,total_Amount,paid_Amount,totkal_Balance)"

					+ "VALUES (" + invoiceId + "," + customerId + ",'" + invoiceDate + "'," + itemsNumber + ","
					+ totalAmount + "," + paidAmount + "," + totalbalance + ")";
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("inserted successfully : " + sql);
			} else {
				System.out.println("insertion failed");
			}
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public void invoiceheardr() {
		Invoice temInvoic = new Invoice();
		System.out.println("please Enter the Invoice_header_ID ");
		int invoice_id_header = sr.nextInt();

		System.out.println("Enter the invoice_ID");
		int Invoice_id = sr.nextInt();

		System.out.println("Enter the invoice_fax");
		int invoice_fax = sr.nextInt();

		System.out.println(" Enter the invoice phone");
		int invoice_phone = sr.nextInt();

		System.out.println("Enter the invoice Email");
		String invoice_Email = sr.next();

		System.out.println("Enter the todays date '__'/1/2023");
		int date = sr.nextInt();

		InvoicList.add(temInvoic);

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "INSERT INTO Invoice_header (Invoice_header_ID,Invoice_Id,Invoice_Fax,Invoice_phone,Invoice_Email,Invoice_Data)"

					+ "VALUES (" + invoice_id_header + "," + Invoice_id + "," + invoice_fax + "," + invoice_phone + ",'"
					+ invoice_Email + "','" + date + "');";
			System.out.println(sql);

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("inserted successfully : " + sql);
			} else {
				System.out.println("insertion failed");
			}
			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public void printInvoicingHeader() {
		for (Invoice a : InvoicList) {
			System.out.println("Fax is : " + a.getFax());
			System.out.println("tel number is : " + a.getTel());
			System.out.println("Fax is : " + a.getEmail());

		}

	}

	public void printcustomer() {
		for (Customer a : customerList) {
			System.out.println("customer full name is : " + a.getCustomerFullName());
			System.out.println("customer phone is     : " + a.getCustomerPhoneNumber());

		}
	}

	public static void findInvoice()

			throws IOException, InterruptedException {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Scanner sr = new Scanner(System.in);
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Please Enter the Invoice ID:");
			int invoiceID = sr.nextInt();
			String sql = "SELECT * FROM Invoices WHERE Invoice_Id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, invoiceID);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("Invoice Not Found");
			} else {
				System.out.println("Invoice Details:");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"Invoice ID\tCustomer ID\tInvoice Date\tNumber of Items\tTotal Amount\tPaid Amount\ttotal Balance");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				// Print the details of the invoice
				do {
					int invoiceId = rs.getInt("Invoice_Id");
					int customerId = rs.getInt("Customer_Id");
					String invoiceDate = rs.getString("Invoice_Date");
					int itemsNumber = rs.getInt("Number_Of_Items");
					double total_Amount = rs.getInt("total_Amount");
					double paidAmount = rs.getFloat("paid_Amount");
					double totkal_Balance = rs.getFloat("totkal_Balance");
					System.out.println(invoiceId + "\t\t\t" + customerId + "\t\t\t" + invoiceDate + "\t\t\t" + itemsNumber
							+ "\t\t\t" + total_Amount + "\t\t\t" + paidAmount + "\t\t\t" + totkal_Balance);
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
				} while (rs.next());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}
	}

	/// InvoicingSystem///
	public void invoiceDate() {
		for (Invoice a : InvoicList) {
			System.out.println("invoice date is " + a.getInvoicDate() + "/1/2023");
		}

	}

	public void ReportStatistics() {
		countItems();
	}

	public void allInvoices() {
		invoiceDate();
		for (Customer a : customerList) {
			System.out.println("Custoemr name is : " + a.getCustomerFullName());
		}
		countItems();
		totalsales();
	}
	/// show ///
	/// dataBase///

	public void printInvoiceHeader() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "select * from Invoice_header";

			ResultSet resultSet = st.executeQuery(sql);
			int count = 1;
			while (resultSet.next()) {
				System.out.println("=============================== " + count + " ============================");
				System.out.println("Invoice_header_ID = " + resultSet.getString("Invoice_header_ID"));
				System.out.println("Invoice_Id = " + resultSet.getString("Invoice_Id"));
				System.out.println("Invoice_Fax = " + resultSet.getString("Invoice_Fax"));
				System.out.println("Invoice_phone = " + resultSet.getString("Invoice_phone"));
				System.out.println("Invoice_Email = " + resultSet.getString("Invoice_Email"));
				System.out.println("Invoice_Data = " + resultSet.getString("Invoice_Data"));
				count++;
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public static void loadDataFormInvoiceItems() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM InvoiceItems ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("Items Not Found");
			} else {
				System.out.println("Items Details:");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"Invoice_Items_ID\tinvoice ID\tItem_Id\tItem_Name\tUnitPrice\tQuantity\tQty_Amount");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				do {
					int InvoiceItem_Id = rs.getInt("InvoiceItem_Id");
					int Invoice_Id = rs.getInt("Invoice_Id");
					int Item_Id = rs.getInt("Item_Id");
					String Item_Name = rs.getString("Item_Name");
					double UnitPrice = rs.getInt("UnitPrice");
					int Quantity = rs.getInt("Quantity");
					double Qty_Amount = rs.getFloat("Qty_Amount");
					System.out.println(InvoiceItem_Id + "\t\t\t\t" + Invoice_Id + "\t\t" + Item_Id + "\t\t" + Item_Name
							+ "\t\t" + UnitPrice + "\t\t" + Quantity + "\t\t" + Qty_Amount);
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
				} while (rs.next());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}
	}

	public static void loadDAataFromInvoice() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM Invoices ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.println("Invoice Not Found");
			} else {
				System.out.println("Invoice Details:");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				System.out.println(
						"Invoice ID\tCustomer ID\tInvoice Date\tNumber of Items\tTotal Amount\tPaid Amount\ttotal Balance");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------");
				do {
					int invoice_Id = rs.getInt("Invoice_Id");
					int Customer_Id = rs.getInt("Customer_Id");
					String invoice_Date = rs.getString("Invoice_Date");
					String Number_Of_Items = rs.getString("Number_Of_Items");
					double total_Amount = rs.getInt("total_Amount");
					double paid_Amount = rs.getFloat("paid_Amount");
					double totkal_Balance = rs.getFloat("totkal_Balance");
					System.out.println(invoice_Id + "\t\t" + Customer_Id + "\t\t" + invoice_Date + "\t\t"
							+ Number_Of_Items + "\t\t" + total_Amount + "\t\t" + paid_Amount + "\t\t" + totkal_Balance);
					System.out.println(
							"--------------------------------------------------------------------------------------------------------------------------------");
				} while (rs.next());
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e);
				}
			}
		}
	}

	public void printCustomerDataBase() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "select * from customers";
			int count = 1;
			ResultSet resultSet = st.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println("=============================== " + count + " ============================");
				System.out.println("Id = " + resultSet.getString("Customer_Id"));
				System.out.println("customer name = " + resultSet.getString("Customer_Full_Name"));
				System.out.println("phone number = " + resultSet.getString("Customer_Phone_Number"));
				count++;

			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void printinvoiceDataBase() {

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "select * from Invoices";

			ResultSet resultSet = st.executeQuery(sql);
			int count = 1;
			while (resultSet.next()) {
				System.out.println("=============================== " + count + " ============================");
				System.out.println("Id = " + resultSet.getString("Invoice_Id"));
				System.out.println("Customer_Id = " + resultSet.getString("Customer_Id"));
				System.out.println("Invoice_Date = " + resultSet.getString("Invoice_Date"));
				System.out.println("Number_Of_Items = " + resultSet.getString("Number_Of_Items"));
				System.out.println("total_Amount = " + resultSet.getString("total_Amount"));
				System.out.println("total_Balancer = " + resultSet.getString("totkal_Balance"));
				count++;
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void printInvoiceItems() {

		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=invoice;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";

		Connection con = null;

		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "select * from InvoiceItems";

			ResultSet resultSet = st.executeQuery(sql);
			int count = 1;
			while (resultSet.next()) {
				System.out.println("=============================== " + count + " ============================");
				System.out.println("Id = " + resultSet.getString("InvoiceItem_Id"));
				System.out.println("Invoice_Id = " + resultSet.getString("Invoice_Id"));
				System.out.println("Item_Id = " + resultSet.getString("Item_Id"));
				System.out.println("Item_Name = " + resultSet.getString("Item_Name"));
				System.out.println("UnitPrice = " + resultSet.getString("UnitPrice"));
				System.out.println("Quantity = " + resultSet.getString("Quantity"));
				System.out.println("Qty_Amount = " + resultSet.getString("Qty_Amount"));
				count++;
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

///creating table in the DataBase///
	public void createTables() {
		String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=test1;" + "encrypt=true;"
				+ "trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		Connection con = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();

			String sql = "CREATE TABLE Customers (\r\n" + "  Customer_Id INT PRIMARY KEY IDENTITY,\r\n"
					+ "  Customer_Full_Name VARCHAR(50),\r\n" + "  Customer_Phone_Number INT\r\n" + ");\r\n" + "\r\n"
					+ "CREATE TABLE Invoices (\r\n" + "  Invoice_Id INT PRIMARY KEY ,\r\n" + "  Customer_Id INT,\r\n"
					+ "  Invoice_Date DATE,\r\n" + "  Number_Of_Items INT,\r\n" + "  total_Amount DECIMAL(10,2),\r\n"
					+ "  paid_Amount DECIMAL(10,2),\r\n" + "  totkal_Balance DECIMAL(10,2),\r\n"
					+ "  FOREIGN KEY (Customer_Id) REFERENCES Customers(Customer_Id)\r\n" + ");\r\n" + "\r\n"
					+ "CREATE TABLE InvoiceItems (\r\n" + "  InvoiceItem_Id INT PRIMARY KEY ,\r\n"
					+ "  Invoice_Id INT,\r\n" + "  Item_Id INT,\r\n" + "  Item_Name VARCHAR(50),\r\n"
					+ "  UnitPrice DECIMAL(10,2),\r\n" + "  Quantity INT,\r\n" + "  Qty_Amount DECIMAL(10,2),\r\n"
					+ "  FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id)\r\n" + ");\r\n" + "\r\n"
					+ "create table Invoice_header (\r\n" + "Invoice_header_ID int primary key ,\r\n"
					+ "  Invoice_Id INT ,\r\n" + "Invoice_Fax int ,\r\n" + "Invoice_phone int ,\r\n"
					+ "Invoice_Email varchar(50) ,\r\n" + "Invoice_Data varchar(20),\r\n"
					+ "  FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id)\r\n" + ")\r\n"
					+ "create table shop(\r\n" + "shop_ID int primary key,\r\n" + "shop_name varchar(50),\r\n" + ")";

			Integer m = st.executeUpdate(sql);
			if (m >= 1) {
				System.out.println("inserted successfully : " + sql);
			} else {
				System.out.println("insertion failed");
			}

			con.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
