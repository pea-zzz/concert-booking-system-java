package ConcertBooking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ViewAllConcert {
	
    private static List<Concert> concerts = new ArrayList<>();
    static {
        Concert concert1 = new Concert("25TH Anniversary New Boyz Concert", "13/07/2024", "8.00PM", "Plenary Hall KLCC");
        concert1.addSeat("VIP MYR498", 498, 100);
        concert1.addSeat("P1  MYR398", 398, 150);
        concert1.addSeat("P2  MYR298", 298, 200);
        concert1.addSeat("P3  MYR198", 198, 250);
        concert1.addSeat("P4  MYR98", 98, 300);
        concerts.add(concert1);

        Concert concert2 = new Concert("Ed Sheeran + - = ÷ x TOUR", "24/08/2024", "8.30PM", "Bukit Jalil National Stadium KL");
        concert2.addSeat("VIP MYR1388", 1388, 200);
        concert2.addSeat("P1  MYR888", 888, 300);
        concert2.addSeat("P2  MYR788", 788, 450);
        concert2.addSeat("P3  MYR598", 598, 300);
        concert2.addSeat("P4  MYR398", 398, 300);
        concert2.addSeat("P5  MYR298", 298, 200);
        concerts.add(concert2);

        Concert concert3 = new Concert("IU H.E.R World Tour Concert In Kuala Lumpur", "08/06/2024 (SAT)", "7.30PM", "AXIATA ARENA");
        concert3.addSeat("VIP MYR888", 888, 400);
        concert3.addSeat("P1  MYR688", 688, 450);
        concert3.addSeat("P2  MYR588", 588, 300);
        concert3.addSeat("P3  MYR488", 488, 300);
        concert3.addSeat("P4  MYR388", 388, 200);
        concerts.add(concert3);
    }
    
    public static void displayConcertSeatPrice(Concert concert) {
    	// Display selected concert info
    	System.out.println("\n" + "------------------------------------------------------");
        System.out.println("\t" + concert.getName());
        System.out.println("------------------------------------------------------");
        System.out.println("Date: " + concert.getDate());
        System.out.println("Time: " + concert.getTime());
        System.out.println("Venue: " + concert.getVenue() + "\n");
        
        System.out.println("------------------------");
        System.out.println("  Seating area & Price  ");
        System.out.println("------------------------");
        List<String> seatAreas = concert.getSeatAreas();
        for (int i = 0; i < seatAreas.size(); i++) {
            System.out.println((i + 1) + ". " + seatAreas.get(i));
        }
    }
    
    public static void displayBookingTerms() {
    	System.out.println("\n===========================================================================");
    	System.out.println("                            Terms and Condition ");
    	System.out.println("===========================================================================");
    	System.out.println(
    			"1. Please check the seats assigned to you before you proceed with payment.\r\n"
    			+ "\r\n"
    			+ "2. Prices are quoted in Ringgit Malaysia(exclude 8% SST).\r\n"
    			+ "\r\n"
    			+ "3. There will be a RM4 ticket Booking Fee & RM10 Operational Fee (exclude 8% SST) "
    			+ "applicable per transaction.\r\n"
    			+ "\r\n"
    			+ "3. No refund or exchange of tickets is allowed once your booking is confirmed.\r\n"
    			+ "\r\n"
    			+ "4. Lost or damaged ticket(s) will not be entertained.\r\n"
    			+ "\r\n"
    			+ "5. In the event of an Event being cancelled or postponed, or if the venue or \r\n"
    			+ "content is significantly changed, we will do our best to inform all purchasers \r\n"
    			+ "using the contact details provided when the order was made. However, it is the \r\n"
    			+ "responsibility of the purchaser to check whether the Event is going ahead at the \r\n"
    			+ "scheduled date, time and venue. We cannot be held liable for any expenses you may \r\n"
    			+ "incur in respect of travel, accommodation, or otherwise arising from cancellation \r\n"
    			+ "or postponement of an Event.\r\n"
    			+ "\r\n"
    			+ "6. Other terms and conditions apply.");
    }
    
    
    public static void viewConcertDetail(UserInfo user) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("======================================================");
        System.out.println("                      Buy2U Ticket            ");
        System.out.println("======================================================");
        System.out.println("Available Concerts:");
        System.out.println("--------------------");
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println((i + 1) + ". " + concerts.get(i).getName());
        }
        System.out.println("\n" + (concerts.size() + 1) + ". Back to Home Page");
        
        
        System.out.print("\n" + "Enter the number of the concert you want to view: ");
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= concerts.size() + 1) {
                    break; // Exit loop if input is valid
                } else {
                	// Display error message if user enter the value outside the range of choices
                    System.out.println("Invalid choice. Please enter 1 - " + (concerts.size() + 1) + " integer number from the above list.");
                    System.out.print("\nEnter the number of the concert you want to view: ");
                }
            } catch (NumberFormatException e) {
            	// Display error message if user enter a non-digit value
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("\nEnter the number of the concert you want to view: ");
            }
        }

        // Continue with the rest of your code based on the valid choice
        if (choice >= 1 && choice <= concerts.size()) {
            // Display concert info
            displayConcertSeatPrice(concerts.get(choice - 1)); // Display selected concert info
            displayBookingTerms(); // Display general booking terms

            System.out.println("\nDo You want to book (Y/N)?");
            System.out.print("Enter Y to book ticket: ");
            String yes = scanner.nextLine();
            
            if (yes.equalsIgnoreCase("Y")) {
                bookingProgress(concerts.get(choice - 1), user);
            } else {
                HomePage homepage = new HomePage();
                homepage.displayHomePage(user); // Go back to home page
            }
        } else {
            HomePage homepage = new HomePage();
            homepage.displayHomePage(user); // Go back to home page
        }
        scanner.close();
    }
    
    public static void bookConcert(UserInfo user) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("======================================================");
        System.out.println("                     Buy2U Ticket            ");
        System.out.println("======================================================");
        System.out.println("Available Concerts:");
        System.out.println("--------------------");
        for (int i = 0; i < concerts.size(); i++) {
            System.out.println((i + 1) + ". " + concerts.get(i).getName());
        }
        System.out.println("\n" + (concerts.size() + 1) + ". Back to Home Page");
        
        System.out.print("\nEnter the number of the concert you want to book: ");
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                if (choice >= 1 && choice <= concerts.size()) {
                    bookingProgress(concerts.get(choice - 1), user);
                    break;
                } else if (choice == concerts.size() + 1) {
                    HomePage homepage = new HomePage();
                    homepage.displayHomePage(user); // Go back to home page 
                    break;
                } else {
                	// Display error message if user enter the value outside the range of choices
                    System.out.println("Invalid choice. Please enter 1 - " + (concerts.size() + 1) + " integer number from the above list.");
                    // Prompt user to re-enter the choice
                    System.out.print("\nEnter the number of the concert you want to book: ");
                }
            } catch (NumberFormatException e) {
            	// Display error message if user enter a non-digit value
                System.out.println("Invalid input. Please enter a number.");
                // Prompt user to re-enter the choice
                System.out.print("\nEnter the number of the concert you want to book: ");
            }
        }scanner.close();
    }
        
    
    
    public static void bookingProgress(Concert concert, UserInfo user) {
      
        try (Scanner scanner = new Scanner(System.in)) {
        	
        	displayConcertSeatPrice(concert);
        	
        	System.out.print("\nEnter seating area: ");
        	int areaChoice;
        	while (true) {
        	    try {
        	        areaChoice = Integer.parseInt(scanner.nextLine());
        	        if (areaChoice >= 1 && areaChoice <= concert.getSeatAreas().size()) {
        	            break;    // Exit loop if input is valid
        	        } else {
        	        	// Display error message if user enter the value outside the range of choices
        	            System.out.println("Invalid choice. Please enter a number between 1 and " + concert.getSeatAreas().size() + " from the above available seating area.");
        	         	// Prompt user to re-enter the choice
        	            System.out.print("\nEnter seating area: ");
        	        }
        	    } catch (NumberFormatException e) {
        	    	// Display error message if user enter a non-digit value
        	        System.out.println("Invalid input. Please enter a number.");
        	        // Prompt user to re-enter the choice
        	        System.out.print("\nEnter seating area: ");
        	    }
        	}

        	// Valid area choice
        	String selectedArea = concert.getSeatAreas().get(areaChoice - 1);
        	int price = concert.getSeatPrices().get(areaChoice - 1);
            if (areaChoice >= 1 && areaChoice <= concert.getSeatAreas().size()) {
                // Check if the seating area is available
                if (!isSeatingAreaAvailable(concert, selectedArea)) {
                    System.out.println("Sorry, the selected seating area is already full. Please choose another seating area.");
                    displayAvailableSeatingAreas(concert);
                    bookingProgress(concert, user);
                    return;
                }
                
                System.out.print("Enter number of tickets: ");
                int numberOfTickets = scanner.nextInt();
                scanner.nextLine(); // Consume newline left by nextInt()

                System.out.print("Do you want consecutive seat numbers? (Y/N): ");
                String consecutiveChoice;
                do {
                    consecutiveChoice = scanner.nextLine().toUpperCase();
                    
                    if (!((consecutiveChoice.charAt(0)== 'Y') || (consecutiveChoice.charAt(0)== 'N'))) {
                        System.out.println("Error: Invalid choice. Please enter 'Y' for Yes or 'N' for No.");
                        System.out.print("\nDo you want consecutive seat numbers? (Y/N): ");
                    }
                } while (!((consecutiveChoice.charAt(0)== 'Y') || (consecutiveChoice.charAt(0)== 'N')));

                boolean consecutiveSeats = consecutiveChoice.equals("Y");

                
                // Generate random seat numbers
                List<String> seatNumbers = generateSeatNumbers(selectedArea, numberOfTickets, scanner, consecutiveSeats);


                
                if (seatNumbers != null) {
                    // Calculate total amount
                    int totalAmount = price * numberOfTickets;
                    int totalAmountWithFee = totalAmount + 14;

                    System.out.print("Enter ticket holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter IC: ");
                    String ic = scanner.nextLine();

                    System.out.println("\n" + "Confirm booking?");
                    System.out.print("Enter Y to confirm: ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        String bookingID = generateBookingID();
                        // Write booking info to file
                        writeBookingInfoToFile(bookingID, concert, selectedArea, price, numberOfTickets, totalAmount, totalAmountWithFee, name, ic, seatNumbers, user);
                        // Display booking details and price
                        System.out.println("\n" + "-------------------------------------------");
                        System.out.println("\t" + "   Booking Details    ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Concert: " + concert.getName());
                        System.out.println("Date: " + concert.getDate());
                        System.out.println("Time: " + concert.getTime());
                        System.out.println("Venue: " + concert.getVenue());
                        System.out.println("Seating Area: " + selectedArea);
                        System.out.println("Seat Numbers: " + String.join(", ", seatNumbers));
                        System.out.println("Number of Tickets: " + numberOfTickets);
                        System.out.println("Total Amount (exclude addition fee): RM" + totalAmount);
                        System.out.println("Operation Fee: RM10");
                        System.out.println("Booking Fee: RM4");
                        System.out.println("Total Price(include additional fee): RM" + totalAmountWithFee);
                        System.out.println("-------------------------------------------");

                        // Ask for payment method
                        String payment;
                        do {
                        	System.out.println("\nPlease select payment method:");
                            System.out.println("1. Tng E-wallet");
                            System.out.println("2. Debit Card");
                            System.out.println("3. Online Banking");
                            System.out.println("4. Credit Card");
                            System.out.print("\n" + "Enter your choice: ");                    
                            payment = scanner.nextLine(); 
                            
                            while(!(payment.equals("1") || payment.equals("2") || payment.equals("3") || payment.equals("4"))) {
                            	System.out.println("Invalid choice. Please enter valid integer choice");
                            	System.out.println("\nPlease select payment method:");
                                System.out.println("1. Tng E-wallet");
                                System.out.println("2. Debit Card");
                                System.out.println("3. Online Banking");
                                System.out.println("4. Credit Card");
                                System.out.print("\n" + "Enter your choice: ");                    
                                payment = scanner.nextLine(); 
                            }
                            
                            if ((payment.equals("1") || payment.equals("2") || payment.equals("3") || payment.equals("4"))) {
                            	// Simulate loading to process payment
                                System.out.print("\n" + "Processing payment");
                                for (int i = 0; i < 5; i++) {
                                    Thread.sleep(1000); // Pause for one second
                                    System.out.print(".");
                                }
                                System.out.println();

                                // Display payment success message
                                System.out.println("\n" + "Booking successful!");
                                System.out.println("-------------------------------------------");
                                System.out.println("\t" + "   Booking Details    ");
                                System.out.println("-------------------------------------------");
                                System.out.println("Your booking ID is: " + bookingID);
                                System.out.println("Concert: " + concert.getName());
                                System.out.println("Date: " + concert.getDate());
                                System.out.println("Time: " + concert.getTime());
                                System.out.println("Venue: " + concert.getVenue());
                                System.out.println("Seating Area: " + selectedArea);
                                System.out.println("Seat Numbers: " + String.join(" ", seatNumbers));
                                System.out.println("Ticket Holder: " + name);
                                System.out.println("IC: " + ic);
                                System.out.println("Number of Tickets: " + numberOfTickets);
                                System.out.println("Total Amount (exclude addition fee): RM" + totalAmount);
                                System.out.println("Operation Fee: RM10");
                                System.out.println("Booking Fee: RM4");
                                System.out.println("Total Price(include additional fee): RM" + totalAmountWithFee);
                                System.out.println("-------------------------------------------");

                                String exit;
    	    					do {
    	    						System.out.println("\nPress Y to exit to Home Page(Y) ");
    	    						exit = scanner.nextLine().toUpperCase();
    	    						
    	    						while(!(exit.charAt(0) == 'Y')) {
    	    							System.out.println("Error: Invalid choice. Please enter Yes if you want to exit.");
    	    							System.out.println("\nPress Y to exit to Home Page (Y) ");
    	    							exit = scanner.nextLine().toUpperCase();
    	    						}
    	    						
    	    						if(exit.charAt(0) == 'Y') {
    	    							HomePage homepage = new HomePage();
    	                                homepage.displayHomePage(user);
    	    						}
    	    					
    	    					}while(!(exit.charAt(0) == 'Y'));
                                
                            }
                            
                        }while (!(payment.equals("1") || payment.equals("2") || payment.equals("3") || payment.equals("4")));
                        
                    } else {
                        System.out.println("Booking cancelled.");
                        HomePage homepage = new HomePage();
                        homepage.displayHomePage(user); // Go back to home page
                    }
                } else {
                    System.out.println("Returning to seating area selection...");
                    displayConcertSeatPrice(concert);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Payment processing interrupted.");
        }

    }
    
    private static boolean isSeatingAreaAvailable(Concert concert, String selectedArea) {
        return concert.getSeatsAvailable().getOrDefault(selectedArea, 0) > 0;
    }
    
    private static void displayAvailableSeatingAreas(Concert concert) {
        List<String> availableAreas = new ArrayList<>();
        for (String area : concert.getSeatAreas()) {
            if (isSeatingAreaAvailable(concert, area)) {
                availableAreas.add(area);
            }
        }
        for (int i = 0; i < availableAreas.size(); i++) {
            System.out.println((i + 1) + ". " + availableAreas.get(i));
        }
        System.out.print("\n" + "Enter the number of the available seating area you want to book: ");
    }


    private static List<String> generateSeatNumbers(String selectedArea, int numberOfTickets, Scanner scanner, boolean consecutiveSeats) {
        List<String> seatNumbers = new ArrayList<>();
        // Assuming each area has 100 seats and seat numbers start from 1
        int maxSeats = 100;
        Random random = new Random();

        if (consecutiveSeats) {
            // Generate consecutive seat numbers
            int startIndex = random.nextInt(maxSeats - numberOfTickets + 1) + 1;
            int endIndex = startIndex + numberOfTickets - 1;
            for (int i = startIndex; i <= endIndex; i++) {
                String seatNumber = "" + i;
                seatNumbers.add(seatNumber);
            }
        } else {
            // Allocate non-consecutive seat numbers
            Set<Integer> chosenSeats = new HashSet<>();
            while (seatNumbers.size() < numberOfTickets) {
                int seatNumber = random.nextInt(maxSeats) + 1;
                if (!chosenSeats.contains(seatNumber)) {
                    chosenSeats.add(seatNumber);
                    String nonConsecutiveSeatNumber ="" + seatNumber;
                    seatNumbers.add(nonConsecutiveSeatNumber);
                }
            }
        }
        return seatNumbers;
    }


    public static String generateBookingID() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder bookingID = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            bookingID.append(characters.charAt(random.nextInt(characters.length())));
        }
        return bookingID.toString();
    }

    public static void writeBookingInfoToFile(String bookingID, Concert concert, String selectedArea, int price, int numberOfTickets,
            int totalAmount, int totalAmountWithFee, String name, String ic, List<String> seatNumbers, UserInfo user) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("booking.txt", true))) {
    		writer.write("Booking ID: " + bookingID + ", ");
    		writer.write("Ticket Holder: " + name + ", ");
    		writer.write("IC: " + ic + ", ");
    		writer.write("Concert Name: " + concert.getName() + ", ");
    		writer.write("Date: " + concert.getDate() + ", ");
    		writer.write("Time: " + concert.getTime() + ", ");
    		writer.write("Venue: " + concert.getVenue() + ", ");
    		writer.write("Seating Area: " + selectedArea + ", ");
    		writer.write("Seat Numbers: " + String.join(" ", seatNumbers) + ", ");
    		writer.write("Ticket Price: " + price + ", ");
    		writer.write("Number of Tickets: " + numberOfTickets + ", ");
    		writer.write("Total Amount (exclude addition fee): RM " + totalAmount + ", ");
    		writer.write("Total Price(include additional fee): RM " + totalAmountWithFee + ", ");
    		writer.write("UserID: " + user.getUserID());
    		writer.write("\n");
    		} catch (IOException e) {
    			System.out.println("Error writing to file: " + e.getMessage());
    			}
    }


}