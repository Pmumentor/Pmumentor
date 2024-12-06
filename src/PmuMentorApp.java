import java.util.ArrayList;
import java.util.Scanner;

// User class to represent user data
class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}

// Event class to represent an event
class Event {
    private String eventName;
    private String eventDescription;

    public Event(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName + "\nDescription: " + eventDescription;
    }
}

// The Main application class
public class PmuMentorApp {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- PmuMentor App ---");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Check if username exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists. Try again.");
                return;
            }
        }

        users.add(new User(username, password));
        System.out.println("Account created successfully!");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.validatePassword(password)) {
                System.out.println("Login successful!");
                userDashboard(scanner);
                return;
            }
        }

        System.out.println("Invalid username or password. Try again.");
    }

    private static void userDashboard(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n--- User Dashboard ---");
            System.out.println("1. Create Event");
            System.out.println("2. View Events");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createEvent(scanner);
                    break;
                case 2:
                    viewEvents();
                    break;
                case 3:
                    loggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void createEvent(Scanner scanner) {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event description: ");
        String eventDescription = scanner.nextLine();

        events.add(new Event(eventName, eventDescription));
        System.out.println("Event created successfully!");
    }

    private static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            System.out.println("\n--- Events ---");
            for (Event event : events) {
                System.out.println(event);
                System.out.println("-------------------");
            }
        }
    }
}
