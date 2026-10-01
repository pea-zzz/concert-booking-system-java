package ConcertBooking;

class AccountLockedException extends Exception 
{ 
	private static final long serialVersionUID = 1L;

	public AccountLockedException(String message) 
    {
        super(message);
    }
}
