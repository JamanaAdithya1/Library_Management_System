package Library;

import java.util.ArrayList;

public class ViewBooks implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        ArrayList<Book> books = database.getAllBooks();
        System.out.println("Name\tAuthor\tPublisher\tAddress\tStatus\tQuantity\tPrice\tBorrow_Copies");
        for (Book b: books) {
            System.out.println(b.getName() + "\t"
                    + b.getAuthor() + "\t"
                    + b.getPublisher() + "\t"
                    + b.getAddress() + "\t"
                    + b.getStatus() + "\t"
                    + b.getQuantity() + "\t"
                    + b.getPrice() + "\t"
                    + b.getBorrow_copies());
        }
        user.menu(database, user);
    }
}
