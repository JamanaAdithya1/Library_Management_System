package Library;

import java.util.Scanner;

public class Search implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter book name to search");
        String bookname = s.next();

        int i = database.getBook(bookname);
        if(i != -1) {
            System.out.println(database.getBook(i).toString());
        } else {
            System.out.println("Book dosen't exist");
        }
        user.menu(database, user);
    }
}
