package Library;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name: ");
        String bookname = s.next();

        for (Borrowing borrow : database.getBorrowedBooks()) {
            if(borrow.getBook().getName().matches(bookname) && borrow.getUser().getName().matches(user.getName())) {
                if(borrow.getDaysleft() < 0) {
                    System.out.println("You are " + Math.abs(borrow.getDaysleft()) + " days late\n"
                            + "You have to pay " + Math.abs(borrow.getDaysleft() * 50) + " as fine."
                    );
                } else {
                    System.out.println("You still have " + borrow.getDaysleft() + "days left, so you don't have to pay fine");
                }
            }
        }
        user.menu(database, user);
    }
}
