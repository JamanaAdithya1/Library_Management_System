package Library;

import java.util.Scanner;

public class PlaceOrder implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Order order = new Order();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookname = s.next();
        int i = database.getBook(bookname);
        if(i > -1) {
            Book book = database.getBook(i);
            order.setBook(book);
            order.setUser(user);
            System.out.println("Enter Quantity: ");
            int quantity = s.nextInt();
//            int available_quantity = book.getQuantity();
//            if(quantity < available_quantity) {
//                order.setQuantity(quantity);
//                order.setPrice(book.getPrice() * quantity);
//                database.addOrder(order, book);
//                System.out.println("Order placed successfully");
//            } else {
//                System.out.println("Only " + available_quantity + "Books are available, try to reduce the quantity");
//            }
            order.setQuantity(quantity);
            order.setPrice(book.getPrice() * quantity);
            int bookIndex = database.getBook(book.getName());
            book.setQuantity(book.getQuantity() - quantity);
            database.addOrder(order, book, bookIndex);
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Book dosen't exist");
        }
        user.menu(database, user);
    }
}
