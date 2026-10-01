package ConcertBooking;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingHistory 
{
	public static void historyMenu(UserInfo user) 
	{
		// Display menu of BookingHistory
		System.out.println("\n\n=======================================");
		System.out.println("            Booking History");
		System.out.println("========================================");
		System.out.println(" (1) View All Booking History");
		System.out.println(" (2) Search for Booking by Booking ID");
		System.out.println(" (3) Modify Ticket Holder Name ");
		System.out.println(" (4) Cancel Booking ");
		System.out.println(" (5) Back To Home Page ");
		System.out.println("========================================");
		
		System.out.print("Enter a number: ");
		inputValue(user);
	}
	
		//Let user to choose the option from home page
		public static void inputValue(UserInfo user){
				Scanner scanner = new Scanner(System.in);
				String number = scanner.nextLine();
					
				switch(number) {
				case "1":
					// View all booking history
					viewAllHistory(user);
					break;
						
						
				case "2":
					// Search for booking 
					searchHistory(user);
					break;
						
				case "3":
					// Modify ticket holder name
					modifyName(user);
					break;
						
				case "4":
					// Cancel booking
					cancelBooking(user);
					break;
						
				case "5":
					// Back to home page
					HomePage homepage = new HomePage();
					homepage.displayHomePage(user);
					break;
						
				default:
					System.out.println("Wrong input, please enter 1 - 5 integer number.");
					System.out.print("\nEnter a number: ");
					inputValue(user);
				}
				scanner.close();
			}
	
		public static void displayAllHistory(UserInfo user) {
	        try {
	        	boolean found = false;
	            FileReader fr = new FileReader("booking.txt");
	            Scanner input = new Scanner(fr);
	            
	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                
	                // Separate each record by line, by following the format start with Booking ID
	                if (line.startsWith("Booking ID: ")) {
	                    
	                    // Extract the booking details
	                    String[] parts = line.split(", ");
	                    String bookingID = parts[0].split(": ")[1].trim();
	                    String name = parts[1].split(": ")[1].trim();
	                    String IC = parts[2].split(": ")[1].trim();
	                    String concertName = parts[3].split(": ")[1].trim();
	                    String date = parts[4].split(": ")[1].trim();
	                    String time = parts[5].split(": ")[1].trim();
	                    String venue = parts[6].split(": ")[1].trim();
	                    String seatingArea = parts[7].split(": ")[1].trim();
	                    String seatNumbersStr = parts[8].split(": ")[1].trim();
	                    List<String> seatNumbers = new ArrayList<>(Arrays.asList(seatNumbersStr.split(" ")));
	                    String ticketPrice = parts[9].split(": ")[1].trim();
	                    String numOfTicket = parts[10].split(": ")[1].trim();
	                    String totalAmount = parts[11].split(": ")[1].trim();
	                    String totalAmountOfFee = parts[12].split(": ")[1].trim();
	                    String userID = parts[13].split(": ")[1].trim();
	                    
	                    
	                    if(user.getUserID().equals(userID)) {
	                    	
	                    	found = true;
	                    	
	                    	System.out.println("\n\n---------------------------------------------------------------");
	                        System.out.println(" Booking Summary ");
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println("Booking ID: \t " + bookingID);
	                        System.out.println("Name: \t\t " + name);
	                        System.out.println("IC: \t\t " + IC);
	                        
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println(" Concert Detail ");
	                        System.out.println("---------------------------------------------------------------"); 
	                        System.out.println("Concert Name: \t " + concertName);
	                        System.out.println("Date: \t\t " + date);
	                        System.out.println("Time: \t\t " + time);
	                        System.out.println("Venue: \t\t " + venue);
	                        System.out.println("Category: \t " + seatingArea);
	                        System.out.println("Seat Numbers: " + String.join(" ", seatNumbers));
	                        
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println(" Transaction Summary");
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println("Price: \t\t\t " + ticketPrice);
	                        System.out.println("Ticket(s) Quantity: \t " + numOfTicket);
	                        System.out.println("Total Amount: \t\t " + totalAmount);
	                        System.out.println("Operation Fee: \t\t RM 10.00");
	                        System.out.println("Booking Fee: \t\t RM 4.00");
	                        System.out.println("Total Paid: \t\t " + totalAmountOfFee);
	                    }
	                }
	            }
	            // Display error message when user input does not match the bookingID 
                if (!found) {
                	System.out.println("No record found");
	            	
	            	try (Scanner scanner = new Scanner(System.in)) {
						String exit;
						do {
							System.out.println("\nPress Y to exit to history menu (Y) ");
							exit = scanner.nextLine().toUpperCase();
							
							if(!(exit.charAt(0) == 'Y')) {
								System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
							}
							
							if(exit.charAt(0) == 'Y') {
								 historyMenu(user);
							}
							
						}while(!(exit.charAt(0) == 'Y'));
	            	}
                }
	        } catch (Exception exc) {
	            System.out.println("File Error: " + exc.getMessage());
	        }
	    }
	    
	    public static void displaySearchedHistory(String searchID, UserInfo user) {
	        try {
	        	Scanner scanner = new Scanner (System.in);
	        	boolean found = false;
	        	String cont;
	        	
	            FileReader fr = new FileReader("booking.txt");
	            Scanner input = new Scanner(fr);
	            
	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                
	                // Separate each record by line, by following the format start with Booking ID
	                if (line.startsWith("Booking ID: ")) {
	                    
	                    // Extract the booking details
	                    String[] parts = line.split(", ");
	                    String bookingID = parts[0].split(": ")[1].trim();
	                    String name = parts[1].split(": ")[1].trim();
	                    String IC = parts[2].split(": ")[1].trim();
	                    String concertName = parts[3].split(": ")[1].trim();
	                    String date = parts[4].split(": ")[1].trim();
	                    String time = parts[5].split(": ")[1].trim();
	                    String venue = parts[6].split(": ")[1].trim();
	                    String seatingArea = parts[7].split(": ")[1].trim();
	                    String seatNumbersStr = parts[8].split(": ")[1].trim();
	                    List<String> seatNumbers = new ArrayList<>(Arrays.asList(seatNumbersStr.split(" ")));
	                    String ticketPrice = parts[9].split(": ")[1].trim();
	                    String numOfTicket = parts[10].split(": ")[1].trim();
	                    String totalAmount = parts[11].split(": ")[1].trim();
	                    String totalAmountOfFee = parts[12].split(": ")[1].trim();
	                    
	                    if(searchID.equals(bookingID)) {
	                    	found = true;      // the record that match search ID is found in booking.txt
	                    	System.out.println("\n\n---------------------------------------------------------------");
	                        System.out.println(" Booking Summary ");
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println("Booking ID: \t " + bookingID);
	                        System.out.println("Name: \t\t " + name);
	                        System.out.println("IC: \t\t " + IC);
	                        
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println(" Concert Detail ");
	                        System.out.println("---------------------------------------------------------------"); 
	                        System.out.println("Concert Name: \t " + concertName);
	                        System.out.println("Date: \t\t " + date);
	                        System.out.println("Time: \t\t " + time);
	                        System.out.println("Venue: \t\t " + venue);
	                        System.out.println("Category: \t " + seatingArea);
	                        System.out.println("Seat Numbers: \t " + String.join(" ", seatNumbers));
	                        
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println(" Transaction Summary");
	                        System.out.println("---------------------------------------------------------------");
	                        System.out.println("Price: \t\t\t RM " + ticketPrice);
	                        System.out.println("Ticket(s) Quantity: \t " + numOfTicket);
	                        System.out.println("Total Amount: \t\t " + totalAmount);
	                        System.out.println("Operation Fee: \t\t RM 10.00");
	                        System.out.println("Booking Fee: \t\t RM 4.00");
	                        System.out.println("Total Paid: \t\t " + totalAmountOfFee);
	                    }
	                }
	            }
	            
	            
	            // Display error message when user input does not match the bookingID 
                if(!found) {
		        	 System.out.println("No record found. ");
		        	 System.out.println("Please enter complete Booking ID. ");
		        	 
		        	 do {
		        		 // Ask if the user want to continue searching 
		        		 System.out.println("\nDo you want to continue searching? (Y/N)");
		        		 cont = scanner.nextLine().toUpperCase();
		        		 
		        		 if (cont.charAt(0)== 'Y') 
		        			 searchHistory(user);
		        		 else if (cont.charAt(0)== 'N')
		        			 historyMenu(user);
		        		 else
		        			 System.out.println("Error: Invalid choice. Please enter Yes or No.");
		        		 
		        	 }while(!((cont.charAt(0) == 'Y') || (cont.charAt(0) == 'N')));
                }
                
	        } catch (Exception exc) {
	            System.out.println("File Error: " + exc.getMessage());
	        }
	        
		}
		
		public static void viewAllHistory(UserInfo user) {
			try {
		        
		        displayAllHistory(user);
		        
		        try (Scanner scanner = new Scanner(System.in)) {
					String exit;
					do {
						System.out.println("\nPress Y to exit to history menu (Y) ");
						exit = scanner.nextLine().toUpperCase();
						
						while(!(exit.charAt(0) == 'Y')) {
							System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
							System.out.println("\nPress Y to exit to history menu (Y) ");
							exit = scanner.nextLine().toUpperCase();
						}
						
						if(exit.charAt(0) == 'Y') {
							 historyMenu(user);
						}
						
					}while(!(exit.charAt(0) == 'Y'));
		        }
		       
			}catch (Exception exc) {
				System.out.println("File Error: " + exc.getMessage());
			}
		}

		
		
		public static void searchHistory(UserInfo user) {
	        Scanner scanner = new Scanner(System.in);
	        String searchID;
	        String exit;
	        
	        // Ask user to enter booking ID to be searched
	        System.out.println("Enter Booking ID: ");
        	searchID = scanner.nextLine();
        	
        	// Display the booking history that matched the searchID
        	displaySearchedHistory(searchID, user);
	        
	        do {
	        	System.out.println("\nPress Y to exit to history menu (Y) ");
	        	exit = scanner.nextLine().toUpperCase();
				
	        	if(exit.charAt(0) == 'Y') {
	        		 historyMenu(user);   // exit to history page if user enter Y
	        	}
	        	else
	        		// Display error message if user does not enter Y to exit
	        		System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
	        		
	        }while(!(exit.charAt(0) == 'Y'));
	         
			scanner.close();
	        
	    }
		
		// Modify ticket Holder Name
	    public static void modifyName(UserInfo user) {
	    	
	    	// Ask user to enter booking ID to be modified
	    	Scanner scanner = new Scanner(System.in);
			System.out.println("\nEnter Booking ID to modify: ");
	        String searchID = scanner.nextLine();
	        
	        // Display the booking history that matched the searchID
	        displaySearchedHistory(searchID, user);
	        
	        // Ask user to enter a new modified ticket holder name
	        System.out.print("\nEnter new name: ");
            String newName = scanner.nextLine();
	        
	        
	        try {
	            // Read booking.txt file
	        	File inputFile = new File("booking.txt");
	            FileReader fr = new FileReader("booking.txt");
	            Scanner input = new Scanner(fr);
	            
	            while (input.hasNextLine()) {
	                String thisLine = input.nextLine();

	                // Separate each record by line, by following the format start with Booking ID
	                if (thisLine.startsWith("Booking ID: ")) {
	                	String[] parts = thisLine.split(", ");
				        String bookingID = parts[0].split(": ")[1].trim();
				        
				        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

                        // Create a temporary list to store modified lines
                        List<String> modifiedLines = new ArrayList<>();
                        String currentLine;
                        
                        // Read each line from the original file
                        while ((currentLine = reader.readLine()) != null) {
                            // If the line starts with the specified booking ID, modify the name
                            if (currentLine.startsWith("Booking ID: " + bookingID)) {
                                // Replace the existing name with the new name
                                currentLine = currentLine.replaceFirst("Ticket Holder: [a-zA-Z]+", "Ticket Holder: " + newName);
                            }
                            // Add the modified or unmodified line to the temporary list
                            modifiedLines.add(currentLine);
                        }
                        
                        reader.close();  // Close the reader
                        
                        // Write the modified lines back to the original file
                        FileWriter writer = new FileWriter(inputFile);
                        for (String line : modifiedLines) {
                            writer.write(line + System.getProperty("line.separator"));
                        }
                        
                        writer.close();  // Close the writer
                       
                        System.out.println("Name updated successfully.");
                         
                        
	        	         String exit;
	        	         do {
	        	        	 System.out.println("\nPress Y to exit to history menu (Y) ");
	        	        	 exit = scanner.nextLine().toUpperCase();
   						
	        	        	 if(exit.charAt(0) == 'Y') {
	        	        		 historyMenu(user);   // exit to history page if user enter Y
	        	        	 }
	        	        	 else
	        	        		 // Display error message if user does not enter Y to exit
	        	        		 System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
	        	        		
	        	         }while(!(exit.charAt(0) == 'Y'));
	                }
	            }
	            input.close();
	        }catch (IOException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }

	    public static void cancelBooking(UserInfo user) {
	    	
	    	// Ask user to enter booking ID to be cancelled
	    	Scanner scanner = new Scanner(System.in);
			System.out.println("\nEnter Booking ID to cancel: ");
	        String searchID = scanner.nextLine();
	        
	        // Display the booking history that matched the searchID
	        displaySearchedHistory(searchID, user);
	        
	        try {
	            // Read booking.txt file
	        	File inputFile = new File("booking.txt");
	            FileReader fr = new FileReader("booking.txt");
	            Scanner input = new Scanner(fr);
	            
	            
	            while (input.hasNextLine()) {
				    String thisLine = input.nextLine();

				    // Separate each record by line, by following the format start with Booking ID
				    if (thisLine.startsWith("Booking ID: ")) {
				    	 String[] parts = thisLine.split(", ");
				         String bookingID = parts[0].split(": ")[1].trim();
				         
				         String ans;
			        	 do
			        	 {
			        		 System.out.print("\nAre you sure you want to cancel the booking? (Y/N)");
			        		 ans = scanner.nextLine().toUpperCase();
			        		 
			        		 // Display error message
			        		 if(!((ans.charAt(0) == 'Y') || (ans.charAt(0) == 'N'))) {
			        			 System.out.println("Error: Invalid choice. Please enter Yes if you sure to cancle.");
			        		 }
			        		 
			        		 if(ans.charAt(0) == 'Y') {
			        			 
			        			 BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			        			// Create an ArrayList to store lines
			        	         List<String> lines = new ArrayList<>();
			        	         String currentLine;
			        	         // Read each line from the original file
			        	         while ((currentLine = reader.readLine()) != null) {
			        	             // Check if the line contains the specified booking ID
			        	             if (!currentLine.startsWith("Booking ID: " + bookingID)) {
			        	                 // If not, add the line to the list
			        	                 lines.add(currentLine);
			        	             }
			        	         }
			        	         // Close the reader
			        	         reader.close();     
			        	         	
			        	         // Write the filtered lines back to the original file
			        	            
			        	         FileWriter writer = new FileWriter(inputFile);
			        	            
			        	         for (String line : lines) {
			        	        	 writer.write(line + System.getProperty("line.separator"));
			        	         }
			        	            
			        	         // Close the writer
			        	         writer.close();
			        	            
			        	         // Print a success message
			        	         System.out.println("Booking cancelled successfully.");
			        	         
			        	         String exit;
			        	         do {
			        	        	 System.out.println("\nPress Y to exit to history menu (Y) ");
			        	        	 exit = scanner.nextLine().toUpperCase();
	        						
			        	        	 if(exit.charAt(0) == 'Y') {
			        	        		 historyMenu(user);   // exit to history page if user enter Y
			        	        	 }
			        	        	 else
			        	        		 // Display error message if user does not enter Y to exit
			        	        		 System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
			        	        		
			        	         }while(!(exit.charAt(0) == 'Y'));
			        	         
			        		 }
			        		 else 
			        			 historyMenu(user);
			        		 
			        	 }while(!(ans.charAt(0) == 'Y'));
				    }
	            }
	            
	        }catch (IOException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
	}




	
	
	