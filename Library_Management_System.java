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
    Book book;
    private String bookTitle;
    private int stock;

    public void setBookStock(Book book) {
        this.bookTitle = book.getBookGenre();
        this.stock = book.getStock();
        this.book=book;
        notifyObservers(bookTitle, stock);
    }
    public Book getBook(){
        return book;
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
        book.updateStock(newStock);
        notifyObservers(bookTitle, stock);
    }
}
interface Book{
    int getStock();
    void updateStock(int stock);
    String getBookGenre();
}
class FantasyBook implements Book{
    int stock;
    String Genre="Fantasy";

    public FantasyBook(int stock){
       this.stock=stock;
    }
    public void updateStock(int newStock){
        this.stock=newStock;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public String getBookGenre() {
        return this.Genre;
    }
}
class MysteryBook implements Book{
    int stock;
    String Genre="Mystery";

    public MysteryBook(int stock){
        this.stock=stock;
    }
    public void updateStock(int newStock){
        this.stock=newStock;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public String getBookGenre() {
        return this.Genre;
    }
}
class PoetryBook implements Book{
    int stock;
    String Genre="Poetry";

    public PoetryBook(int stock){
        this.stock=stock;
    }
    public void updateStock(int newStock){
        this.stock=newStock;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public String getBookGenre() {
        return this.Genre;
    }
}
class Factory
{
public static Book bookCreater(String s,int stock){
    if (s.equalsIgnoreCase("FantasyBook")){
        return new FantasyBook(stock);
    } else if (s.equalsIgnoreCase("MysteryBook")) {
        return new MysteryBook(stock);
    } else if (s.equalsIgnoreCase("PoetryBook")) {
        return new PoetryBook(stock);
    }else return null;
}
}

public class Library_Management_System {
    public static void main(String[] args) {
        LibraryManager libraryManager = LibraryManager.getInstance();
        // Creating observers (librarians)
        Observer librarian1 = new LibraryObserver("Librarian 1");
        Observer librarian2 = new LibraryObserver("Librarian 2");
        Book fantasyBook = Factory.bookCreater("FantasyBook",50);

        // Creating subject (book stock tracker)
        BookStockTracker stockTracker = new BookStockTracker();

        // Registering observers to the subject
        stockTracker.registerObserver(librarian1);
        stockTracker.registerObserver(librarian2);
        // Setting initial stock for a book
        stockTracker.setBookStock(fantasyBook);

        // Updating stock and notifying observers
        stockTracker.updateStock(30);


    }
}
class LibraryManager{
    private static LibraryManager instance = new LibraryManager();
    private LibraryManager(){};
    public static LibraryManager getInstance(){
        return instance;
    }
    public static void manageLibrary(){
        System.out.println("Library management tasks are being handled.");
    }
    public void displaySystemStatus() {
        System.out.println("Library Manager is operational.");
       
    }
}
class User{
    BookStockTracker stockTracker;
    Book book;
    public User(BookStockTracker stockTracker){
        this.stockTracker=stockTracker;
        book = stockTracker.getBook();
    }
    public void borrowBook(){
        book.updateStock(book.getStock()-1);
    }
    public void returnBook(Book book){
        book.updateStock(book.getStock()+1);
    }
}