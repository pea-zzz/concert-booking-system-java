package ConcertBooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInfo {
	
	private String userID;
	private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;

    // Getter methods
    public String getUserID()
    {
    	return userID;
    }
    
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setter methods
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public UserInfo(String userID) {
    	this.userID = userID;
    }

    // Constructor
    public UserInfo(String userID, String userName, String password, String phoneNumber, String email, String address) {
        this.userID = userID;
    	this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    
    
    //Display user current information
  	public void displayUserInfo(UserInfo userInfo) {
  		
  		Scanner scanner = new Scanner(System.in);
  		HomePage home = new HomePage();
  		
  		System.out.println("\n======================================");
  		System.out.println("\t     Your Profile");
  		System.out.println("======================================");
  		System.out.println("   User ID		: " + userInfo.userID);
  		System.out.println("1> Username		: " + userInfo.getUserName());
  		System.out.println("2> Password		: ********");
  		System.out.println("3> Phone Number \t: " + userInfo.phoneNumber);
  		System.out.println("4> Email		: " + userInfo.email);
  		System.out.println("5> Address		: " + userInfo.address);
  		
  		
  		//let user to input through keyboard with particular character
  		String c;
  		do {
  			System.out.println("\nEdit user profile press Y/y"); 
  	  		System.out.println("Back to main page press B/b");
  	  		
  	  		c = scanner.nextLine().toUpperCase();
  			
  			if (c.charAt(0) == 'Y') {
  				updateUserInfo(userInfo);
  			}
  			else if (c.charAt(0) == 'B') {
  				home.displayHomePage(userInfo);
  			}
  			
  			
  			if (!((c.charAt(0) == 'Y') || (c.charAt(0) == 'B'))) {
  	            System.out.println("Error: Invalid choice. Please enter Y/B.");
  	        }
       		 
  		}while(!((c.charAt(0) == 'Y') || (c.charAt(0) == 'B')));
  		scanner.close();
  	}
  		
 
  public static void inputValue(UserInfo userInfo) {
  		Scanner inputNum = new Scanner(System.in);
  		int number = inputNum.nextInt();
  		
  		switch(number) {
  		case 1:
  			System.out.println("View concert");
  			break;
  			
  		case 2:
  			System.out.println("Search for booking");
  			break;
  			
  		case 3:
  			System.out.println("Booking history");
  			break;
  			
  		case 4:
  			System.out.println("Update profile");
  			userInfo.displayUserInfo(userInfo);

  			break;
  			
  		case 5:
  			System.out.println("Contact us");
  			break;
  			
  		case 6:
  			System.exit(0);
  			break;
  			
  		default:
  				System.out.println("Wrong input, please enter 1 - 6 integer number.");
  				inputValue(userInfo);
  		}
  		inputNum.close();
  	}
   

  	
  	//read file
  	public static ArrayList<UserInfo> readFile()   {
  		ArrayList<UserInfo> users = new ArrayList<>();
  		//HomePage home = new HomePage();
  		
  		try{
  			BufferedReader ReadFile = new BufferedReader(new FileReader("User.txt"));
  			
  			String line = ReadFile.readLine();
  			while (line != null) {
  				String[] UserData = line.split(",");
  				
  				//Array of storing user information
  				String userID = UserData[0];
  				String userName = UserData[1];
  				String password = UserData[2];
  				String phoneNumber = UserData[3];
  				String Email = UserData[4];
  				String Address = UserData [5];
  				
  				
  				UserInfo user = new UserInfo(userID, userName, password, phoneNumber, Email, Address);
  				users.add(user);
  				
  				line = ReadFile.readLine();//Read next line
  			}
  			
  			ReadFile.close();
  		}catch (IOException e) {
  			e.printStackTrace();
  		}
  		
  		return users;
  		
  		}
  	
  	
  	//let user to recreate their own user information
  	public void updateUserInfo(UserInfo userInfo) {
  		Scanner editProfile = new Scanner(System.in);

  		//display current user value
  		System.out.println("\n=================================================");
  		System.out.println("\t\t   Edit Profile");
  		System.out.println("=================================================");
  		System.out.println("\t   Your unique ID : " + userInfo.userID);
  		System.out.println("1> Username		: " + userInfo.getUserName() + "(Press 1)");
  		System.out.println("2> Password		: ********" + "(Press 2 )");
  		System.out.println("3> Phone Number \t: " + userInfo.phoneNumber + "(Press 3)");
  		System.out.println("4> Email		: " + userInfo.email + "(Press 4)");
  		System.out.println("5> Address		: " + userInfo.address + "(Press 5)");
  		System.out.println("6> Back (Press 6)");
  		System.out.println("");
  		System.out.println("Follow the instruction above to make changes.");
  	
  		
  		
  		int choice;
  		while (true) {
  	        try {
  	            System.out.print("\nEnter your choice: ");
  	            String input = editProfile.nextLine();
  	            choice = Integer.parseInt(input);
  	            break; // Exit loop if input is valid
  	        } catch (NumberFormatException e) {
  	            System.out.println("Error: Invalid input. Please enter a digit.");
  	        }
  	    }
  		
  		//choose to create new user information by using switch
  		switch (choice) {
  		case 1:
  			editNewUsername(userInfo);
  			infoUpdated(userInfo);
  			updateUserInfo(userInfo);
  			break;
  			
  		case 2:
  			changePassword(userInfo);
  			updateUserInfo(userInfo);
  			
  		case 3:
  			editNewPhoneNumber(userInfo);
  			infoUpdated(userInfo);
  			updateUserInfo(userInfo);
  			break;
  			
  		case 4:
  			editNewEmail(userInfo);
  			infoUpdated(userInfo);
  			updateUserInfo(userInfo);
  			break;
  			
  		case 5:
  			editNewAddress(userInfo);
  			infoUpdated(userInfo);
  			updateUserInfo(userInfo);
  			break;
  			
  		case 6:
  			displayUserInfo(userInfo);
  			break;
  			
  		default:
  			System.out.println("Invalid input, please try again...");
  			updateUserInfo(userInfo);
  			break;
  		}
  		editProfile.close();
  		
  	}
  	
  	
  	//function to edit new user name
  	public void editNewUsername(UserInfo userInfo) {
  		Scanner editProfile = new Scanner(System.in);
  		String aUserName;
  		try {
            BufferedReader reader = new BufferedReader(new FileReader("User.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 6) { // Ensure the line has all required fields
                    String username = userData[1];
                    do {
              			System.out.print("Please enter your new username : ");
              	  		aUserName = editProfile.nextLine();
              	  		
              	  		if (!(aUserName.equals(username))) {
              	  			userInfo.userName = aUserName;
              	  			infoUpdated(userInfo);
              	  			System.out.println("Successfully updated ! ");
              	  			updateUserInfo(userInfo);
              	  		}
              	  		else 
              	  			System.out.println("Username already exists. Please choose another User Name.");
              	  		
              		}while(aUserName.equals(username));
                }
            }
  		} catch (Exception exc) {
            System.out.println("File Error: " + exc.getMessage());
        }
  		
  		editProfile.close();
  	}
  	
  	//function to edit new phone number
  	public void editNewPhoneNumber(UserInfo userInfo) {
  		Scanner editProfile = new Scanner(System.in);
  		String aPhoneNumber;
  		do {
  			System.out.print("Please enter your new phone number (10-11 digits starting with '01'): ");
  			aPhoneNumber = editProfile.nextLine();
  			
            if (aPhoneNumber.matches("^01\\d{8,9}$")) {
                userInfo.phoneNumber = aPhoneNumber;
                infoUpdated(userInfo);
                System.out.println("Successfully updated ! ");
                updateUserInfo(userInfo);
            } 
            else
                System.out.println("Invalid phone number format. Please enter a valid phone number.");
            
        } while (!(aPhoneNumber.matches("^01\\d{8,9}$")));
  		editProfile.close();
  	}

  	//function to edit new email
  	public void editNewEmail(UserInfo userInfo) {
  		Scanner editProfile = new Scanner(System.in);
  	  	String aEmail;
  	  	
  	  	do {
  	  		System.out.print("Please enter your new email : ");
  	  		aEmail = editProfile.nextLine();
  	  		
  	  		if (aEmail.contains("@") && aEmail.endsWith(".com")) {
  	  			userInfo.email = aEmail;
  	  			infoUpdated(userInfo);
  	  			System.out.println("Successfully updated ! ");
  	  			updateUserInfo(userInfo);
  	  		} 
  	  		else 
  	  			System.out.println("Invalid email format. Please enter a valid email address.");
  	  		
  	  	} while (!(aEmail.contains("@") || aEmail.endsWith(".com")));
  	  	editProfile.close();
  	}
  	
  	
  	//function to edit new address
  	public void editNewAddress(UserInfo userInfo) {
  		
  		Scanner editProfile = new Scanner(System.in);
  	  	String aAddress;
  	  	System.out.print("Please enter your new address : ");
  	  	aAddress = editProfile.nextLine();
  	  	userInfo.address = aAddress;
  	  	infoUpdated(userInfo);
  	  	System.out.println("Successfully updated ! ");
  	  	updateUserInfo(userInfo);
  	  	editProfile.close();
  }	
  	
  	
  	public void infoUpdated(UserInfo userInfo) {
  	    int userIndex = -1;
  	    ArrayList<UserInfo> users = readFile();
  	    for (int i = 0; i < users.size(); i++) {
  	    	//check user id
  	        if (users.get(i).getUserID().equals(userInfo.getUserID())) {
  	            userIndex = i;
  	            break;
  	        }
  	    }
  	    if (userIndex >= 0) {
  	    	// replace the old user info with the updated one
  	        users.set(userIndex, userInfo); 
  	    } else {
  	    	// add a new user if it doesn't exist in the list
  	        users.add(userInfo); 
  	    }
  	    
  	    writeUserToFile(users, "User.txt");
  	   
  	}
  	
  	
  	//write data into User.txt
  	public void writeUserToFile(ArrayList<UserInfo> users, String fileName) {
  	    try  {
  	    	FileWriter writer = new FileWriter(fileName, false);
  	        for (UserInfo user : users) {
  	            writer.write(user.getUserID() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getAddress() + "\n");
  	        }
  	        writer.close();
  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
  	}

  	 
  	
  	//change user password
  	public void changePassword(UserInfo userInfo) {
  		
  		System.out.print("Please enter your current password : ");
  		Scanner psw = new Scanner(System.in);
  		String currentPass = psw.nextLine();
  		//to confirm user current password before recreate the new password
  		if (userInfo.password.equals(currentPass)) {
  			System.out.print("Please enter your new 8 digits password : ");
  			while(true) {
  				String newPassword = psw.nextLine();
  				if(newPassword.length()==8) {  //restrict the digit of password can be entered
  				password = newPassword;
  				infoUpdated(userInfo);
  				return;
  				}
  				else {
  					System.out.println("Please re-enter your new password with 8 digits : ");
  				}
  			}
  		}
  		else {
  			System.out.print("Invalid password !");
  			changePassword(userInfo);
  		}
  		System.out.println("Successfully updated ! ");
  		updateUserInfo(userInfo);
  	}
}
