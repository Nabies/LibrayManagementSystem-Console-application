import java.sql.SQLException;
import java.util.Scanner;

public class AdminLogs
{
    static final Scanner scan=new Scanner(System.in);//we cannot change the variable

    public static void DashBoard(String name,String email) throws SQLException
    {
        System.out.println("Press 1 for Available Books");
        System.out.println("Press 2 for Add Book");
        System.out.println("Press 3 for Delete Books");
        System.out.println("Press 4 for Issue Details");
        System.out.println("Press 5 for exit");

        int option=Integer.parseInt(scan.nextLine());
        switch(option)
        {
            case 1:
            {
                Databasehandler.AvailableBooks();
                DashBoard(name, email);
                break;
            }
            case 2:
            {
                System.out.println("Enter The Book Details\n");
                String bookName,bookAuthor,bookYear,bookRack,bookDepartment;

                System.out.println("Enter the Book Name");
                bookName=scan.nextLine();
                System.out.println("Enter the Book Author");
                bookAuthor=scan.nextLine();
                System.out.println("Enter the Book Year");
                bookYear=scan.nextLine();
                System.out.println("Enter the Book Rack");
                bookRack=scan.nextLine();
                System.out.println("Enter the Book Department");
                bookDepartment=scan.nextLine();

                Databasehandler.AddBook(bookName,bookAuthor,bookYear,bookRack,bookDepartment);
                DashBoard(name, email);
                break;
            }
            case 3:
            {
                Databasehandler.ShowBookID();
                System.out.println("Enter The Book Details\n");
                int bookId;

                System.out.println("Enter the Book ID");
                bookId=Integer.parseInt(scan.nextLine());

                Databasehandler.DeleteBook(bookId);

                DashBoard(name, email);
                break;
            }
            case 4:
            {
                System.out.println("Press 1 for all issued details");
                System.out.println("Press 2 for issued book details");
                int opt=Integer.parseInt(scan.nextLine());
                switch(opt)
                {
                    case 1:
                    {
                        Databasehandler.issueDetails();
                        DashBoard(name, email);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter book name to search");
                        String bookName=scan.nextLine();
                        Databasehandler.bookissueDetails(bookName);

                        DashBoard(name, email);
                        break;
                    }
                }
            }

            default:
            {
                System.out.print("Thank You!");
                break;
            }
        }
    }
}