package Model;

public class Book {

    int id;
    String title;
    String Author;
    boolean isIssued;

    public Book() {

    }
    public Book(int id, String title, String author, boolean isIssued) {
        this.id = id;
        this.title = title;
        this.Author = author;
        this.isIssued = isIssued;
    }

    public Book(int id, String title, String Author) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", Author='" + Author + '\'' +
                ", isIssued=" + isIssued +
                '}';
    }
}
