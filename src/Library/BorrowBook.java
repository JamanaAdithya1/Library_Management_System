package Library;

import java.util.Scanner;
import java.util.Set;

public class BorrowBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookname = s.next();

        int i = database.getBook(bookname);
        if(i > -1) {

            Book book = database.getBook(i);
            if(book.getBorrow_copies() > 1) {
                Borrowing borrow = new Borrowing(book, user);
                book.setBorrow_copies(book.getBorrow_copies() - 1);
                database.BorrowBook(borrow, book, i);
                System.out.println("Book borrowed\nYou must return the book 14 days within\n" + "Expiry Date: " + borrow.getFinish());
            } else {
                System.out.println("This book isn't available for borrowing");
            }
        } else {
            System.out.println("Book dosen't exist");
        }
        user.menu(database, user);
    }
}
