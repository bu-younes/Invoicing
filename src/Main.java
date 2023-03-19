
import java.util.Scanner;

public class Main {
	
	
	
	
	

	public static void main(String[] args) throws Exception {
		
		

		InvoiceItem init = new InvoiceItem();
		boolean i = true;
		Scanner sr = new Scanner(System.in);
		
		
		 String user = "sa";
		 String pass = "root";
		 boolean passwordCondation = true ;
		 while (passwordCondation)
		 {
		 System.out.println("Enter username");
		 String username = sr.next();
		 System.out.println("Enter password");
		 String password = sr.next();

		 if (username.equals(user) && password.equals(pass))
		 {
			 passwordCondation = false ;
		 }
		 else if (username != (user) && password != (pass)) 
		 {
		 System.out.println("worng username and password ");
		 
		 }


		 }
		 boolean hasPerformedAction = false;


		System.out.println("====================  Invoicing System  =========================");
		while (i) {
			System.out.println("1- Shop Settings");
			System.out.println("2- Manage Shop Items");
			System.out.println("3- creating the table in the dataBase");
			System.out.println("4. Get all the dataBase information ");
			System.out.println("5. Find the Invoice ");
			System.out.println("6- EXIT");
			int switch1 = sr.nextInt();

			if (switch1 == 1) {
				boolean manu1 = true;
				while (manu1) {
					System.out.println("1. Load Data");
					System.out.println("2. Set Shop Name");
					System.out.println("3. add invoice header");
					System.out.println("4. Back");
					int choose = sr.nextInt();
					switch (choose) {
					case 1:

						init.loadDataFormInvoiceItems();
						init.loadDAataFromInvoice();
						break;

					case 2:
						init.AddshopDetails();
						break;
					case 3:
						init.invoiceheardr();
						break;
					case 4:
						manu1 = false;
					}


				}
			}

			else if (switch1 == 2) {
				boolean manu2 = true;
				while (manu2) {
					System.out.println("1. Add Items & customer");
					System.out.println("2. Delete Items or customer");
					System.out.println("3. update Item Price  or Customer Phone number");
					System.out.println("4. Report: All Invoices");
					System.out.println("5. Report: Statistics)");
					System.out.println("6. addInvoiceItems");
					System.out.println("7. Go Back");
					int choose1 = sr.nextInt();
					switch (choose1) {
					case 1:
						System.out.println("=====FIRST Enter the customer details=====");
						init.ADDcustomer();
						init.addInvoice();
						break;
					case 2:
						boolean deleteCondation = true ;
						while(deleteCondation)
						{
						System.out.println("Select what you want to delete");
						System.out.println("1. Delete Items");
						System.out.println("2. Delete Customer");
						System.out.println("3. Back");
						int chosee = sr.nextInt();
						if(chosee == 1)
						{
							init.deletDataBaseItems();
						}
						else if (chosee == 2)
						{
							init.deletDataBaseCustomer();
						}
						else if (chosee == 3)
						{
							deleteCondation = false ;
						}
						}
					break;
					
					
					case 3:
						boolean updateCondation = true ;
						while (updateCondation)
						{
						System.out.println("Select what you want to update");
						System.out.println("1. update the price");
						System.out.println("2. update the customer phone number");
						System.out.println("3. Back");
						int newUpdate = sr.nextInt();
						if (newUpdate == 1)
						{
							init.updateDataBsePrice();
						}
						else if (newUpdate == 2)
						{
							init.updateDataBseCustomerPhone();
						}
						else if (newUpdate == 3)
						{
							updateCondation = false ;
						}
						}
						break;

					case 4:
						init.countItems1();
						init.countInvoice1();

						
						break;

					case 5:
						init.ReportStatistics();
					
						break;
	
					case 6:
						init.addInvoiceItems();
						break;

					case 7:
						manu2 = false;
						break;

					}
				}
			}
			
			else if(switch1 == 3)
			{
				
		          if (switch1 == 3 && !hasPerformedAction) { // perform action only if user input is 1 and action has not been performed already
		        	  init.createTables();
			          System.out.println("Performing action...");
			          hasPerformedAction = true; // set flag to true to indicate that action has been performed
			        } 
		          else 
			        {
			            System.out.println("Action cannot be performed again.");
			        }	
				
				break;
			}
			
			else if (switch1 == 4)
			{
                System.out.println("==================== Customer =========================================");				
                init.printCustomerDataBase();
				System.out.println("==================== Invoice =========================================");	
				init.printinvoiceDataBase();
				System.out.println("==================== Items =========================================");	
				init.printInvoiceItems();
				System.out.println("==================== Invoice Header =========================================");
				init.printInvoiceHeader();
				break;
			}
			
			else if (switch1 == 5)
			{
				init.findInvoice();
				break;
			}

		else if (switch1 == 6)
		{
		System.out.println("Are you sure you want to exit?  yes / no");
		String exitt = sr.next();
		if (exitt.equals("yes")) {
			i = false;
			System.out.println("thanks!!!!!!! ");
		} else {
			
		}

	}
		}
	}
}

	


