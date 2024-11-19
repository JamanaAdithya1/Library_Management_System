package Library;

public class Book {
    private String name;
    private String author;
    private String publisher;
    private String address; // Collection
    private String status;  // borrow status
    private int quantity;   // No. of copies for sale
    private double price;
    private int borrow_copies; // Copies for borrowing

    public Book() {};

    public Book(String name, String author, String publisher, String address, int quantity, double price, int borrow_copies) {
            this.name = name;
            this.author = author;
            this.publisher = publisher;
            this.address = address;
            this.quantity = quantity;
            this.price = price;
            this.borrow_copies = borrow_copies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBorrow_copies() {
        return borrow_copies;
    }

    public void setBorrow_copies(int borrow_copies) {
        this.borrow_copies = borrow_copies;
    }

    public String toString() {
        String text = "Book name: " + name + "\n" +
                "Book author: " + author + "\n" +
                "Book publisher: " + publisher + "\n" +
                "Book collection address: " + address + "\n" +
                "Quantity: " + String.valueOf(quantity) + "\n" +
                "Book price: " + String.valueOf(price) + "\n" +
                "Borrowing copies: " + String.valueOf(borrow_copies);
        return text;
    }
    public String toString2() {
        String text = name + "<N/>"
                + author + "<N/>"
                + publisher + "<N/>"
                + address + "<N/>"
                + String.valueOf(quantity) + "<N/>"
                + String.valueOf(price) + "<N/>"
                + String.valueOf(borrow_copies);
        return text;
    }

    public Book parseBook(String s) {
        String[] a = s.split("<N/>");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setAddress(a[3]);
        book.setQuantity(Integer.parseInt(a[4]));
        book.setPrice(Double.parseDouble(a[5]));
        book.setBorrow_copies(Integer.parseInt(a[6]));
        return book;
    }
}
