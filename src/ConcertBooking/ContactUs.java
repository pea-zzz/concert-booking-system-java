package ConcertBooking;

public class ContactUs {
    private String name;
    private String email;
    private String phoneNumber;
    private String category;
    private String probDescription;

    // getter method
    public String getName() 
    {
        return name;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    public String getCategory()
    {
    	return category;
    }
    
    public String getProbDescription()
    {
    	return probDescription;
    }
    
    // constructor
    public ContactUs(String name, String email, String phoneNumber, String category, String probDescription) 
    {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.probDescription = probDescription;
    }


}

