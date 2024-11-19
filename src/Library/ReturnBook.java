package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter book name: ");
        Scanner s = new Scanner(System.in);
        String bookname = s.next();
        if(!database.getBorrowedBooks().isEmpty()) {
            for (Borrowing borrowedBook : database.getBorrowedBooks()) {
                  if(borrowedBook.getBook().getName().matches(bookname) && borrowedBook.getUser().getName().matches(user.getName())) {
                      Book book = borrowedBook.getBook();
                      int i =database.getAllBooks().indexOf(book);
                      if(borrowedBook.getDaysleft() < 0) {
                          System.out.println("You have exceeded the return date, pay fine");
                      }
                      book.setBorrow_copies(book.getBorrow_copies() + 1);
                      database.returnBook(borrowedBook, book, i);
                      System.out.println("Book returned\nThank you.");
                      break;
                  } else {
                      System.out.println("You didn't borrow this book");
                  }
            }
        } else {
            System.out.println("You didn't borrow this book");
        }
        user.menu(database, user);
    }
}
