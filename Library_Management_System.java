import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String bookTitle, int newStock);
}

// Concrete Observer class
class LibraryObserver implements Observer {
    private String librarianName;

    public LibraryObserver(String name) {
        this.librarianName = name;
    }

    @Override
    public void update(String bookTitle, int newStock) {
        System.out.println("Librarian " + librarianName + ": Stock of '" + bookTitle + "' is now " + newStock);
    }
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String bookTitle, int newStock);
}

// Concrete Subject class
class BookStockTracker implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String bookTitle;
    private int stock;

    public void setBookStock(String title, int initialStock) {
        this.bookTitle = title;
        this.stock = initialStock;
        notifyObservers(bookTitle, stock);
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String bookTitle, int newStock) {
        for (Observer observer : observers) {
            observer.update(bookTitle, newStock);
        }
    }

    public void updateStock(int newStock) {
        this.stock = newStock;
        notifyObservers(bookTitle, stock);
    }
}

public class Library_Management_System {
    public static void main(String[] args) {
        // Creating observers (librarians)
        Observer librarian1 = new LibraryObserver("Librarian 1");
        Observer librarian2 = new LibraryObserver("Librarian 2");

        // Creating subject (book stock tracker)
        BookStockTracker stockTracker = new BookStockTracker();

        // Registering observers to the subject
        stockTracker.registerObserver(librarian1);
        stockTracker.registerObserver(librarian2);

        // Setting initial stock for a book
        stockTracker.setBookStock("Introduction to Java", 50);

        // Updating stock and notifying observers
        stockTracker.updateStock(40);
    }
}
