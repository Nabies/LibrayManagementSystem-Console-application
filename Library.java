import java.sql.SQLException;
import java.util.Scanner;

public class Library
{
    static final Scanner scan=new Scanner(System.in);
    static String userName;
    static String userEmail;
    static String userPass;

    public static void main(String [] args) throws SQLException
    {
        enterLibrary();
    }
    public static void enterLibrary() throws SQLException
    {
        System.out.println("WELCOME TO OUR LIBRARY\n");
        System.out.println("New User? Press 1 to Register");
        System.out.println("Existing User? Press 2 to Login\n");
        int option=Integer.parseInt(scan.nextLine());
        switch(option)
        {
            case 1:
            {
                System.out.println("Enter your Name");
                userName=scan.nextLine();
                System.out.println("Enter your Email Address");
                userEmail=scan.nextLine();
                System.out.println("Enter your Password");
                userPass=scan.nextLine();

                if(Databasehandler.registerUser(userName, userEmail, userPass))
                    UserLogs.DashBoard(userName,userEmail); 	// Name for display; Email for book order
                else
                {
                    System.out.println("Registration UnSuccessful..!!!\n\n");
                    enterLibrary();
                }

                break;
            }
            case 2:
            {
                System.out.println("Press 1 for Admin Login");
                System.out.println("Press 2 for User Login");
                System.out.println("Press 3 to Go Back");

                int opt=Integer.parseInt(scan.nextLine());

                switch (opt)
                {
                    case 1:
                    {
                        System.out.println("Enter your Email Address");
                        userEmail=scan.nextLine();
                        System.out.println("Enter your Password");
                        userPass=scan.nextLine();

                        if(Databasehandler.CheckAdmin(userEmail, userPass)) {
                            System.out.println("WELCOME ADMIN):\n");
                            AdminLogs.DashBoard(userName, userEmail);
                        }
                        else
                        {
                            System.out.println("Invalid AdminEmail or AdminPassword.\n\n\t Thank You");
                            enterLibrary();
                        }
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter your Email Address");
                        userEmail=scan.nextLine();
                        System.out.println("Enter your Password");
                        userPass=scan.nextLine();

                        if(Databasehandler.CheckUser(userEmail, userPass)) {
                            System.out.println("WELCOME USER):\n");
                            UserLogs.DashBoard(userName, userEmail);
                        }
                        else
                        {
                            System.out.println("Invalid UserEmail or UserPassword.\n\n\t Thank You");
                            enterLibrary();
                        }
                        break;
                    }
                    case 3:
                    {
                        enterLibrary();
                        break;
                    }
                    default:
                    {
                        System.out.println("Please Enter the Correct Number\n\n");
                        enterLibrary();
                        break;
                    }
                }
                break;
            }
            default:
            {
                System.out.println("Please Enter the Correct Number\n\n");
                enterLibrary();
                break;
            }
        }
    }
}