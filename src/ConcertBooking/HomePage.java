package ConcertBooking;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomePage {
	public void displayHomePage(UserInfo userInfo) {
		
		// loading interface after login successful or back to home
		System.out.print("\nLoading");
		for (int load = 0; load < 6; load++) {
			System.out.print("%");
			try {
				Thread.sleep(1000);
			}catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		System.out.println();
		System.out.println();
		
		// Home Page interface
		System.out.println("===========================");
		System.out.println("=========||Home||==========");
		System.out.println("===========================");
		System.out.println("|| (1)View Concert       ||");
		System.out.println("|| (2)Booking            ||");
		System.out.println("|| (3)Booking history    ||");
		System.out.println("|| (4)View user profile  ||");
		System.out.println("|| (5)Contact us         ||");
		System.out.println("|| (6)Sign out           ||");
		System.out.println("===========================");
		System.out.print("Enter a number: ");
		
		inputValue(userInfo);
	}
	
	//Let user to choose the option from home page
	public void inputValue(UserInfo userInfo){
			Scanner scanner = new Scanner(System.in);
			String number = scanner.nextLine();
			
			switch(number) {
			case "1":
				// View concert details
				ViewAllConcert.viewConcertDetail(userInfo);
				break;
				
			case "2":
				// Book Ticket
				ViewAllConcert.bookConcert(userInfo);
				break;
				
			case "3":
				// Booking history
				BookingHistory.historyMenu(userInfo);
				break;
				
			case "4":
				// User profile
				userInfo.displayUserInfo(userInfo);
				break;
				
			case "5":
				// Contact info
				contactInfo(userInfo);
				break;
				
			case "6":
				System.out.println("Sign Out Successful!" + "\n");
			    LoginSignUp.main(new String[0]); 
			    break;

				
			default:
					System.out.println("Wrong input, please enter 1 - 6 integer number.");
					System.out.print("\nEnter a number: ");
					inputValue(userInfo);
			}
			scanner.close();
		}
	
	public void contactInfo(UserInfo userInfo) {
		System.out.println("\n*********************Contact us****************************\t");
		System.out.println("\nDrop us a line and we'll get back to you as soon as possible.\n");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = sc.nextLine();
		
		String email;
		do {
			System.out.print("Enter your email: ");
			email = sc.nextLine();
			
  	  		if (!(email.contains("@") && email.endsWith(".com"))) {
  	  			System.out.println("Invalid email format. Please enter a valid email address.");
  	  		} 
  	  		
  	  	} while (!(email.contains("@") && email.endsWith(".com")));
		
		
		String phoneNumber;
		do {
  			System.out.print("Enter your phone number (10-11 digits starting with '01'): ");
  			phoneNumber = sc.nextLine();
  			
            if (!(phoneNumber.matches("^01\\d{8,9}$"))) {
                System.out.println("Invalid phone number format. Please enter a valid phone number.");
            } 
        } while (!(phoneNumber.matches("^01\\d{8,9}$")));
		
		int choice;
        String category;
        while (true) {
            try {
                System.out.println("\nSelect a category:");
                System.out.println("1. General Inquiries");
                System.out.println("2. Volunteer Opportunities");
                System.out.println("3. Technical Support");
                System.out.println("4. Feedback and Suggestions");

                System.out.print("\nEnter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please enter again.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        category = "General Inquiries";
                        break;
                    case 2:
                        category = "Volunteer Opportunities";
                        break;
                    case 3:
                        category = "Technical Support";
                        break;
                    case 4:
                        category = "Feedback and Suggestions";
                        break;
                    default:
                        category = "Unknown";
                        break;
                }
                break; // Exit loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

		
		System.out.println("Tell us what's your mind: ");
		String probDescription = sc.nextLine();

		ContactUs contact = new ContactUs(name, email, phoneNumber, category, probDescription);
        
        System.out.println("Submit successful!");
		System.out.println("Name: " + contact.getName());
		System.out.println("Email: " + contact.getEmail());
		System.out.println("Phonenumber: " + contact.getPhoneNumber());
		System.out.println("Category: " + contact.getCategory());
		System.out.println("Problem Description: " + contact.getProbDescription());
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Thank you for your feedback!");
		
		//write to file
		try (FileWriter fw = new FileWriter("feedback.txt",true))
		{
			fw.write("Name: " + contact.getName() + "\n");
			fw.write("Email: " + contact.getEmail() + "\n");
			fw.write("Phone Number: " + contact.getPhoneNumber() + "\n");
			fw.write("Category: " + contact.getCategory() + "\n");
			fw.write("Problem Description: " + contact.getProbDescription() + "\n");
		} 
		catch (IOException e) 
		{
			System.out.println("Error writing to file: " + e.getMessage());
		}
		
		displayHomePage(userInfo);
	}

}
