
/*
---> Project Idea:
A console-based application to manage library operations 
like adding books, issuing books, returning books, and maintaining a record of members and transactions. 
This mimics a real-world system and showcases your understanding of OOPs and file handling.

---> Features to Implement: Admin & User Roles (Optional)

Add new books
View available books
Issue a book
Return a book
View issued book records
Save book data persistently using files

--->Skills Used: Classes & Objects

Inheritance
Abstraction & Encapsulation
Exception Handling
File I/O
ArrayList & HashMap (Collections)

--->How to Explain in Interview:

I developed a Library Management System using Core Java, 
demonstrating OOPs concepts like inheritance and abstraction. 
I used file handling to store persistent book data and handled various exceptions to make it robust. 
This project helped me understand the real-world application of Java beyond simple programs.

*/


import java.io.*;
import java.util.*;

class Book implements Serializable {
    int id;
    String title, author;
    boolean isIssued;

    Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

public class LibraryManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        loadBooks();
        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    saveBooks();
                    System.out.println("Exiting... Data saved.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    static void viewBooks() {
        System.out.println("\n--- Book List ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    b.isIssued = true;
                    System.out.println("Book issued successfully.");
                    return;
                } else {
                    System.out.println("Book already issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    b.isIssued = false;
                    System.out.println("Book returned successfully.");
                    return;
                } else {
                    System.out.println("Book was not issued.");
                    return;
                }
            }
        }
        System.out.println("Book not found.");
    }

    static void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            oos.writeObject(books);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            books = (ArrayList<Book>) ois.readObject();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
    }
}
