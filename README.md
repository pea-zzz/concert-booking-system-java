# Buy2U Ticket — Concert Booking System

Buy2U Ticket is a Java console application for browsing concerts, booking seats, managing booking history, and maintaining a user profile. It was developed as an object-oriented programming assignment and stores its data in local text files.

## Features

- Create an account and sign in with input validation
- Temporarily lock an account after three failed login attempts
- Browse concert dates, venues, seating areas, and ticket prices
- Allocate consecutive or non-consecutive seat numbers
- Calculate ticket totals with booking and operational fees
- Simulate payment by e-wallet, debit card, online banking, or credit card
- View and search booking history by booking ID
- Change a ticket holder's name or cancel a booking
- View and update user profile details
- Submit enquiries and feedback

## Project structure

```text
ASG_JAVA/
├── src/
│   ├── module-info.java
│   └── ConcertBooking/
│       ├── LoginSignUp.java          # Application entry point
│       ├── HomePage.java             # Main menu and contact form
│       ├── ViewAllConcert.java       # Concert catalogue and booking flow
│       ├── BookingHistory.java       # Booking search and management
│       ├── Concert.java              # Concert and seating model
│       ├── UserInfo.java             # User profile model and updates
│       ├── ContactUs.java            # Feedback model
│       ├── InvalidLoginException.java
│       └── AccountLockedException.java
├── .classpath
└── .project
```

The application creates or updates these files in the working directory while it runs:

- `User.txt` — registered user records
- `booking.txt` — booking records
- `feedback.txt` — contact and feedback submissions

These runtime data files are excluded from Git to avoid publishing personal information.

## Requirements

- JDK 11 or newer
- A terminal, or Eclipse IDE for Java Developers

No third-party libraries are required.

## Run from the command line

From the project root:

```bash
mkdir -p out
find src -name "*.java" -print0 | xargs -0 javac -d out
java -cp out ConcertBooking.LoginSignUp
```

On first launch, choose **Sign up** to create a local account. The application will create `User.txt` when the registration is saved.

## Run in Eclipse

1. Open Eclipse and choose **File → Import**.
2. Select **General → Existing Projects into Workspace**.
3. Choose this repository as the root directory and finish the import.
4. Open `src/ConcertBooking/LoginSignUp.java`.
5. Select **Run As → Java Application**.

## Main workflow

1. Sign up or log in.
2. View the available concerts and seating prices.
3. Select a seating area and ticket quantity.
4. Choose consecutive or randomly allocated seats.
5. Confirm the booking and select a simulated payment method.
6. Use **Booking history** to view, search, modify, or cancel bookings.

## Technical notes

- The program uses Java classes, encapsulation, collections, custom exceptions, file I/O, and input validation.
- Concert information and seat capacities are currently defined in `ViewAllConcert.java`.
- Booking IDs are generated as random eight-character alphanumeric values.
- A fixed RM14 amount is added to each transaction (RM10 operational fee and RM4 booking fee).
- Payment processing is a console simulation; no real payment service is connected.

## Important security note

This is an educational console project. User passwords and other records are stored as plain text locally, so the application is **not suitable for production use**. A production version should hash passwords, use a database, encrypt sensitive data, and integrate a secure payment provider.

## License

This repository does not currently include an open-source license. All rights are reserved by the project owner.
