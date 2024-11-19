package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Book book = new Book();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Book name: ");
        String bookname = s.next();
        if(database.getBook(bookname) > -1) {
            System.out.println("Book already exists");
            user.menu(database, user);
        }
        book.setName(bookname);
        System.out.println("Enter Book Author: ");
        book.setAuthor(s.next());
        System.out.println("Enter Book publisher: ");
        book.setPublisher(s.next());
        System.out.println("Enter Book collection address: ");
        book.setAddress(s.next());
        System.out.println("Enter quantity: ");
        book.setQuantity(s.nextInt());
        System.out.println("Enter Price: ");
        book.setPrice(s.nextDouble());
        System.out.println("Enter Borrowing copies: ");
        book.setBorrow_copies(s.nextInt());
        database.AddBook(book);
        System.out.println("Book is added successfully\n");
        user.menu(database, user);
    }
}
