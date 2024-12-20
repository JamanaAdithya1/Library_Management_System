package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<String> booknames = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Borrowing> borrowings = new ArrayList<>();
    private File usersfile = new File("C:\\Users\\jadit\\IdeaProjects\\LibraryManagementSystem\\Data\\Users");
    private File booksfile = new File("C:\\Users\\jadit\\IdeaProjects\\LibraryManagementSystem\\Data\\Books");
    private File ordersFile = new File("C:\\Users\\jadit\\IdeaProjects\\LibraryManagementSystem\\Data\\Orders");
    private File borrowingsFile = new File("C:\\Users\\jadit\\IdeaProjects\\LibraryManagementSystem\\Data\\Borrowings");
    private File folder = new File("C:\\Users\\jadit\\IdeaProjects\\LibraryManagementSystem\\Data");

    public Database() {
        if(!folder.exists()) {
            folder.mkdirs();
        }
        if(!usersfile.exists()) {
            try{
                usersfile.createNewFile();
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        if(!booksfile.exists()) {
            try{
                booksfile.createNewFile();
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        if(!ordersFile.exists()) {
            try{
                ordersFile.createNewFile();
            } catch (Exception e) { System.err.println(e.toString()); }
        }
        if(!borrowingsFile.exists()) {
            try{
                borrowingsFile.createNewFile();
            } catch (Exception e) { System.err.println(e.toString()); }
        }
        getUsers();
        getBooks();
        getOrders();
        getBorrowings();
    }
    public void addUser(User s) {
        users.add(s);
        usernames.add(s.getName());
        saveUsers();
    }

    // to check if the newUser is actually a new User
    public boolean userExists(String newUsername) {
        for (User user : users) {
            if(user.getName().matches(newUsername)) {
                return true;
            }
        }
        return false;
    }

    public int login(String phonenumber, String email) { // return -1 if user is not in db else return the index
        int n = -1;
        for(User s: users) {
            if(s.getPhoneNumber().matches(phonenumber) && s.getEmail().matches(email)) {
                n = users.indexOf(s);
                break;
            }
        }
        return n;
    }

    public User getUser(int n) {
        return users.get(n);
    }

    public void AddBook(Book book) {
        books.add(book);
        booknames.add(book.getName());
        saveBooks();
    }

    private void getUsers() {
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
            String s1;
            while((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1) {
                String[] a2 = s.split("<N/>");
                User user;
                if(a2[3].trim().equalsIgnoreCase("Admin")) {
                    user = new Admin(a2[0], a2[1], a2[2]);
                } else {
                    user = new NormalUser(a2[0], a2[1], a2[2]);
                }
                users.add(user);
                usernames.add(user.getName());
            }
        }
    }

    private void saveUsers() {
        String text1 = "";
        for (User user: users) {
            text1 = text1 + user.toString() + "<NewUser/>\n";
        }

        try{
            PrintWriter pw = new PrintWriter(usersfile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    private void saveBooks() {
        String text1 = "";
        for (Book book: books) {
            text1 = text1 + book.toString2() + "<NewBook/>\n";
        }

        try{
            PrintWriter pw = new PrintWriter(booksfile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private User getUserByName(String username) {
        User u = new NormalUser("");
        for(User user: users) {
            if(user.getName().matches(username)) {
                u = user;
                break;
            }
        }
        return u;
    }

    private void getBooks() {
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
            String s1;
            while((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1) {
                Book book = parseBook(s);
                books.add(book);
                booknames.add(book.getName());
            }
        }
    }

    public Book parseBook(String s) {
        s = s.replace("<NewBook>", "").trim();
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

    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public int getBook(String bookname) {
        int i = -1;
        for(Book book : books) {
            if(book.getName().matches(bookname)) {
                i = books.indexOf(book);
            }
        }
        return i;
    }

    public Book getBook(int i) {
        return books.get(i);
    }

    public void deleteBook(int i) {
        books.remove(i);
        booknames.remove(i);
        saveBooks();
    }

    public void deleteAllData() {
        if(usersfile.exists()) {
            try{
                usersfile.delete();
            } catch (Exception e) {System.err.println(e.toString());}
        }
        if (booksfile.exists()) {
            try{
                booksfile.delete();
            } catch (Exception e) {System.err.println(e.toString());}
        }
        if (ordersFile.exists()) {
            try{
                ordersFile.delete();
            } catch (Exception e) {System.err.println(e.toString());}
        }
    }

    public void addOrder(Order order, Book book, int bookIndex) {
        orders.add(order);
        books.set(bookIndex, book);
        saveOrders();
        saveBooks();
    }

    public void saveOrders() {
        String text1 = "";
        for (Order order: orders) {
            text1 = text1 + order.toString2() + "<NewOrder/>\n";
        }

        try{
            PrintWriter pw = new PrintWriter(ordersFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public void getOrders() {
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(ordersFile));
            String s1;
            while((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewOrder/>");
            for (String s : a1) {
                Order order = parseOrder(s);
                orders.add(order);
            }
        }
    }

    public Order parseOrder(String s) {
        s = s.replace("<NewOrder/>", "").trim();
        String[] a = s.split("<N/>");
        Order order = new Order(books.get(getBook(a[0])), getUserByName(a[1]), Double.parseDouble(a[2]), Integer.parseInt(a[3]));
        return order;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    private void saveBorrowings() {
        String text1 = "";
        for (Borrowing borrow: borrowings) {
            text1 = text1 + borrow.toString2() + "<NewBorrowing/>\n";
        }

        try{
            PrintWriter pw = new PrintWriter(borrowingsFile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void getBorrowings() {
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(borrowingsFile));
            String s1;
            while((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewBorrowings/>");
            for (String s : a1) {
                Borrowing borrow = parseBorrowing(s);
                borrowings.add(borrow);
            }
        }
    }

    public Borrowing parseBorrowing(String s) {
        s = s.replace("<NewBorrowing/>", "").trim();
        String[] a = s.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(a[0], formatter);
        LocalDate finish = LocalDate.parse(a[1], formatter);
        Book book = books.get(getBook(a[2]));
        User user = getUserByName(a[3]);
        Borrowing borrow = new Borrowing(start, finish, book, user);
        return borrow;
    }

    public void BorrowBook(Borrowing borrowedBook, Book book, int bookindex) {
        borrowings.add(borrowedBook);
        books.set(bookindex, book);
        saveBooks();
        saveBorrowings();
    }

    public ArrayList<Borrowing> getBorrowedBooks() {
        return borrowings;
    }

    public void returnBook(Borrowing borrowedBook, Book book, int bookIndex) {
        borrowings.remove(borrowedBook);
        books.set(bookIndex, book);
        saveBorrowings();
        saveBooks();
    }
}
