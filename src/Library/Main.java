package Library;

import java.util.Scanner;

public class Main {
    static Scanner s;
    static Database database;

    // main function (login or create a new user)
    public static void main(String[] args) {
        database = new Database();
        System.out.println("Welcome to Library Management System");
        s = new Scanner(System.in);
        int num;
        do {
            System.out.println("0.Exit\n1.Login\n2.New User");
            num = s.nextInt();

            switch (num) {
                case 1: login(); break;
                case 2: newuser(); break;
            }
        } while (num != 0);
    }

    // Login function
    private static void login() {
        System.out.println("Enter phone number: ");
        String phonenumber = s.next();
        System.out.println("Enter email: ");
        String email = s.next();
        int n = database.login(phonenumber, email); // Call login function in database
        if(n != -1) {
            User user = database.getUser(n);
            user.menu(database, user);
        } else {
            System.out.println("User dosen't exist");
            return;
        }
    }

    private static void newuser() {
        System.out.println("Enter name");
        String name = s.next();
        if (database.userExists(name)) {
            System.out.println("User already exists.");
            return;
        }
        System.out.println("Enter phone number: ");
        String phonenumber = s.next();
        System.out.println("Enter email: ");
        String email = s.next();
        System.out.println("1.Admin\n2.Normal User");
        int newUserChoice = s.nextInt();
        User user;
        if (newUserChoice == 1) {
            user = new Admin(name, email, phonenumber);
        } else {
            user = new NormalUser(name, email, phonenumber);
        }
        database.addUser(user);
        user.menu(database, user);
    }
}