package Library;

import java.util.Scanner;

public class DeleteAllData implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Are you sure that you want to erase all the data?\n1.Yes\n2.No");
        int choice = s.nextInt();
        if(choice == 1) {
            database.deleteAllData();
            System.out.println("Data erased successfully🥲");
        } else {
            System.out.println("Good Choice! Data will not be erased");
            user.menu(database, user);
        }
    }
}
