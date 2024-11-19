package Library;

import java.util.Scanner;

public class Exit implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Are you sure that you want to exit?\n1.Yes\n2.Main menu");
        int choice = s.nextInt();
        if(choice == 1) {
            System.out.println("Exiting...");
            return;
        } else {
            user.menu(database, user);
        }
    }
}
