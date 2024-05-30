package database;

// Mainly used for retrieving information about books

public class Book {
    private String author;
    private String title;
    private String datePublished;
    private String category;
    private int quantity;
    private int availableQuantity;
    private int book_id;

    public Book(String author, String title, String datePublished, String category, int quantity, int availableQuantity, int book_id) {
        this.author = author;
        this.title = title;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.datePublished = datePublished;
        this.category = category;
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
    
    public String getDatePublished() {
    	return datePublished;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public int getQuantity() {
    	return quantity;
    }
    
    public int getAvailableQuantity() {
    	return availableQuantity;
    }
    
    public int getId() {
    	return book_id;
    }
}
