package service;

import model.Book;
import java.io.*;
import java.util.*;

public class LibraryManagementSystem {

    private static final String FILE_PATH = "data/books.dat";
    private List<Book> books = new ArrayList<>();

    public LibraryManagementSystem() {
        loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.forEach(System.out::println);
    }

    public void issueBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (!b.isIssued()) {
                    b.setIssued(true);
                    saveBooks();
                    System.out.println("Book issued successfully.");
                    return;
                }
                System.out.println("Book already issued.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    saveBooks();
                    System.out.println("Book returned successfully.");
                    return;
                }
                System.out.println("Book was not issued.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void saveBooks() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving books.");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadBooks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            books = (List<Book>) ois.readObject();
        } catch (Exception e) {
            books = new ArrayList<>();
        }
    }
}
