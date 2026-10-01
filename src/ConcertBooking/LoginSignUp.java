package ConcertBooking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.security.auth.login.AccountLockedException;

public class LoginSignUp 
{
    private static final int Max_Login_Attempts = 3; 		//max login number
    private static final int Lockout_Duration_Minutes = 3; 	//lock account 3 minutes
    private static Map<String, Integer> loginAttemptsMap = new HashMap<>();
    private static Map<String, LocalDateTime> lockoutMap = new HashMap<>();

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<UserInfo> users = readUsersFromFile("User.txt");
        UserInfo currentUser = null;

        while (true) 
        {
        	
        	System.out.println("========================================");
        	System.out.println("        Welcome to Buy2U Ticket         ");
        	System.out.println("========================================");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) 
            {
                case "1":
                    currentUser = login(scanner, users);
                    if (currentUser != null) 
                    {
                    	HomePage homepage = new HomePage();
                        homepage.displayHomePage(currentUser);
                        return;
                    }
                    break;
                    
                case "2":
                    signUp(scanner, users);
                    break;
                    
                case "3":
                	System.out.println("\n========================================");
                	System.out.println("               Good Bye!                ");
                	System.out.println("   Welcome to Buy2U Ticket next time!   ");
                	System.out.println("========================================");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please choose again.\n");
            }
        }
    }

    private static UserInfo login(Scanner scanner, ArrayList<UserInfo> users) 
    {
        System.out.print("\nEnter user ID: ");
        String userID = scanner.next();
        
        // Check if the user name exists in the user.txt
        boolean userExists = false;
        for (UserInfo user : users) {
            if (user.getUserID().equals(userID)) 
            {
                userExists = true;
                break;
            }
        }
        
        if (!userExists) {
            System.out.println("\nUser ID does not exist. Would you like to sign up? (Y/N)");
            String signUpChoice;
            do {
                System.out.print("Enter 'Y' to sign up or 'N' to go back to the menu page: ");
                signUpChoice = scanner.next().toUpperCase(); // Convert input to upper case
                if (signUpChoice.equals("Y")) 
                {
                    signUp(scanner, users); // Call the sign up method
                    return null;
                } else if (signUpChoice.equals("N")) {
                    return null;
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            } while (!signUpChoice.equals("Y") && !signUpChoice.equals("N"));
        }
        
        
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (int i = 0; i < Max_Login_Attempts; i++) 
        {
            try {
                UserInfo user = validateUser(userID, password, users);
                return user;
            } catch (InvalidLoginException e) {
                System.out.println(e.getMessage());
                if (i < Max_Login_Attempts - 1) 
                {
                    System.out.print("Enter password again: ");
                    password = scanner.next();
                }
            } catch (AccountLockedException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }

        return null;
    }

    //Lock User Account when user login more than 3 times
    private static UserInfo validateUser(String userID, String password, ArrayList<UserInfo> users) throws InvalidLoginException, AccountLockedException {
        LocalDateTime lockoutTime = LocalDateTime.now().minusMinutes(Lockout_Duration_Minutes);

        if (lockoutMap.containsKey(userID) && lockoutMap.get(userID).isAfter(lockoutTime)) 
        {
            long remainingTime = Duration.between(LocalDateTime.now(), lockoutMap.get(userID).plusMinutes(Lockout_Duration_Minutes)).toSeconds();
            throw new AccountLockedException("Account locked. Please try again after " + remainingTime + " seconds.");
        }

        for (UserInfo user : users) {
            if (user.getUserID().equals(userID) && user.getPassword().equals(password)) 
            {
                loginAttemptsMap.remove(userID);
                return user;
            }
        }

        int attempts = loginAttemptsMap.getOrDefault(userID, 0) + 1;
        loginAttemptsMap.put(userID, attempts);

        if (attempts >= Max_Login_Attempts) 
        {
            lockoutMap.put(userID, LocalDateTime.now());
            throw new AccountLockedException("Account locked. Please try again after " + Lockout_Duration_Minutes + " minutes.");
        }

        throw new InvalidLoginException("Invalid username or password.");
    }
    
    //Sign Up a new account
    private static void signUp(Scanner scanner, ArrayList<UserInfo> users) 
    {
    	String userID;
        boolean validUserID = false;
        do {
            System.out.print("Enter new user ID (8 digits): ");
            userID = scanner.nextLine();
            if (userID.matches("\\d{8}")) { // Check if userID consists of 8 digits
                boolean userIDExists = false;
                for (UserInfo user : users) {
                    if (user.getUserID().equals(userID)) {
                        userIDExists = true;
                        break;
                    }
                }
                if (!userIDExists) {
                    validUserID = true;
                } else {
                    System.out.println("User ID already exists. Please choose another user ID.");
                }
            } else {
                System.out.println("Invalid user ID format. Please enter a 8-digit user ID.");
            }
        } while (!validUserID);
        
        String username;
        boolean validUsername = true; 
        do {
            System.out.print("Enter new User Name: ");
            username = scanner.nextLine();
            for (UserInfo user : users) {
                if (user.getUserName().equals(username)) {
                    System.out.println("User Name already exists. Please choose another User Name.");
                    validUsername = false;
                    break;
                }
            }
        } while (!validUsername);

        String password;
        do {
            System.out.print("Enter password (8 digits ): ");
            password = scanner.nextLine();
            if (password.length() != 8) 
            {
                System.out.println("Password must be 8 digits long.");
            }
        } while (password.length() != 8);

        String phoneNumber;
        boolean validPhoneNumber = false;
        do {
            System.out.print("Enter phone number (10-11 digits starting with '01'): ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("^01\\d{8,9}$")) 
            {
                validPhoneNumber = true;
            } 
            else
            {
                System.out.println("Invalid phone number format. Please enter a valid phone number.");
            }
        } while (!validPhoneNumber);

        String email;
        boolean validEmail = false;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (email.contains("@") && email.endsWith(".com")) 
            {
                validEmail = true;
            } 
            else 
            {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        } while (!validEmail);
        
        System.out.print("Enter address (State & City): ");
        String address = scanner.nextLine();

        UserInfo newUser = new UserInfo(userID, username, password, phoneNumber, email, address);
        users.add(newUser);
        writeUserToFile(newUser, "User.txt");
        System.out.println("Sign up successful!");

        // clear input buffer 
        scanner.nextLine();
        HomePage homepage = new HomePage();
        homepage.displayHomePage(newUser); 
        return;
    }

    //read data in User.txt
    public static ArrayList<UserInfo> readUsersFromFile(String fileName) {
        ArrayList<UserInfo> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 6) { // Check if userData contains at least 6 elements
                    UserInfo user = new UserInfo(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5]);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    //write data into User.txt
    public static void writeUserToFile(UserInfo user, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(user.getUserID() + "," + user.getUserName() + "," + user.getPassword() + "," + user.getPhoneNumber() + "," + user.getEmail() + "," + user.getAddress() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    


}
