import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static ArrayList<Book> inventory = new ArrayList<>();
    static ArrayList<AbstractAccount> members = new ArrayList<>();
    static AbstractAccount currentUser = null;
    static Scanner sc = new Scanner(System.in);

    static void main(String[] ignored) {
        while (true) {
            int k = 3;
            if (currentUser == null) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
            } else {
                System.out.println("4. Add Book");
                System.out.println("5. Search & Borrow Books");
                System.out.println("6. Return a Book");
                System.out.println("7. Logout");
            }
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> Register();
                case "2" -> Login();
                case "3" -> System.exit(0);
                case "4" -> {
                    if (currentUser != null) AddBook();
                }

                case "5" -> {
                    if (currentUser != null) SearchAndBorrow();
                }
                case "6" -> {
                    if (currentUser != null) ReturnBook();
                }
                case "7" -> {
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void Register() {
        System.out.println("Register as: 1.Student 2.Teacher");
        String typeChoice = sc.nextLine();

        AbstractAccount m;
        if (typeChoice.equals("1")) {

            m = new StudentAccount();

        } else if (typeChoice.equals("2")) {

            m = new TeacherAccount();
        } else {
            System.out.println("Invalid type selected.");

            return;

        }
        System.out.println("Enter Id: ");
        m.setMemberId(sc.nextLine());
        System.out.print("Enter Name: ");
        m.setName(sc.nextLine());

        String phone;
        while(true){
        System.out.print("Enter Phone (10 digits): ");
        phone = sc.nextLine();

            if (phone.length() == 10) {
                break;
            } else {
                System.out.println("Error: Phone number must be exactly 10 digits. Please try again.");
            }
        }

        m.setPhoneNumber(phone);
        System.out.print("Enter Password: ");
        m.setPassword(sc.nextLine());
        System.out.print("Enter Department: ");
        m.setDepartment(sc.nextLine());

        members.add(m);
        System.out.println("Successfully Registered as: " + m.getClass().getSimpleName() + "!");
    }

    static void Login() {
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        for (AbstractAccount m : members) {
            if (m.getPhoneNumber().equals(phone) && m.getPassword().equals(password)) {
                currentUser = m;
                System.out.println("Welcome, " + m.getName() + " [ID: " + m.getMemberId() + "]" +
                        "(" + m.getClass().getSimpleName() + ")" + " from " + m.getDepartment() +
                        "Department");
                return;
            }
        }
        System.out.println("Login Failed.");
    }

    static void AddBook() {
        Book b = new Book();
        System.out.print("ISBN: ");
        b.setIsbn(sc.nextLine());
        System.out.print("Book Name: ");
        b.setName(sc.nextLine());
        System.out.println("Description: ");
        b.setDescription(sc.nextLine());
        System.out.println("Date Published: ");
        b.setDatePublished(sc.nextLine());
        System.out.print("Number of Revisions: ");
        try {
            b.setNumberOfRevisions(Integer.parseInt(sc.nextLine()));
        } catch (NumberFormatException e) {
            b.setNumberOfRevisions(0);
        }
        b.setDepartment(currentUser.getDepartment()); // Automation rule

        inventory.add(b);
        System.out.println("Added to " + b.getDepartment());
        System.out.println("Book added to " + b.getDepartment() + " inventory.");
    }

    static void SearchAndBorrow() {
        System.out.print("Search Name: ");
        String query = sc.nextLine();
        for (Book b : inventory) {
            if (b.getName().equalsIgnoreCase(query)) {
                System.out.print("Borrow? (y/n): ");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    try {
                        currentUser.borrowBook(b);
                    } catch (BookUnavailableException e) {
                        System.out.println("ALERT: " + e.getMessage());
                    }
                }
                return;
            }
        }
        System.out.println("Not Found");
    }

    static void ReturnBook(){
        List<Book> books = currentUser.getBorrowedBooks();
        if (books.isEmpty()) {
            System.out.println("No books held.");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.println(i + ". " + books.get(i).getName());
        }
        System.out.print("Select Index: ");
        try {
            int idx = Integer.parseInt(sc.nextLine());
            currentUser.returnBook(books.get(idx));
            throw new NumberFormatException();
        } catch(Exception e){
            System.out.println("Hello");
        }
    }
}


