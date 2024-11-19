package Library;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewOrders implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookname = s.next();
        int i = database.getBook(bookname);
        if(i > -1) {
            System.out.println("Book\t\tUser\t\tPrice\t\tQuantity");
            for(Order order: database.getAllOrders()) {
                if(order.getBook().getName().matches(bookname)) {
                    System.out.println(order.getBook().getName() + "\t"
                            + order.getUser().getName() + "\t"
                            + order.getPrice() + "\t"
                            + order.getQuantity()
                    );
                }
            }
        } else {
            System.out.println("Book dosen't exist");
        }

        user.menu(database, user);
    }
}