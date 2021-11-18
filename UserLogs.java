import java.sql.SQLException;
import java.util.Scanner;

public class UserLogs
{
    static final Scanner scan=new Scanner(System.in);

    public static void DashBoard(String name,String email) throws SQLException
    {
        System.out.println("Press 1 for Available Books");
        System.out.println("Press 2 for Search Books");
        System.out.println("Press 3 for Book Order");
        System.out.println("Press 4 for Book Return");
        System.out.println("Press 5 for Exit");

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
                System.out.println("Press 1 for Book Name");
                System.out.println("Press 2 for Author Name");
                System.out.println("Press 3 for Book Year");
                System.out.println("Press 4 for Exit");

                int opt=Integer.parseInt(scan.nextLine());
                switch(opt)
                {
                    case 1:
                    {
                        System.out.println("Enter the Book Name");
                        String bookName=scan.nextLine();
                        Databasehandler.SearchByBookName(bookName);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("Enter the Book Author Name");
                        String authorName=scan.nextLine();
                        Databasehandler.SearchByAuthorName(authorName);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("Enter the Book Year");
                        String bookYear=scan.nextLine();
                        Databasehandler.SearchByBookYear(bookYear);
                        break;
                    }
                    default:
                    {
                        DashBoard(name,email);
                        break;
                    }
                }
                DashBoard(name, email);
                break;
            }
            case 3:
            {
                System.out.println("Order Your Book");
                Databasehandler.ShowBookID();

                System.out.println("Enter the Book Id");
                int book_id=Integer.parseInt(scan.nextLine());
                Databasehandler.PlaceOrder(book_id,email);

                DashBoard(name, email);
                break;
            }
            case 4: {
                System.out.println("Press 1 To see return book");
                System.out.println("Press 2 return book");

                int opt = Integer.parseInt(scan.nextLine());
                switch (opt) {
                    case 1:
                        if (Databasehandler.seereturnBookDetails(email)) {
                            System.out.println("Please return Book on date");
                            break;
                        }
                        DashBoard(name, email);
                        break;
                    case 2:
                        if (Databasehandler.returnBookDetails(email)) {
                            System.out.println("Enter the Book Id you want to return");
                            int book_id = Integer.parseInt(scan.nextLine());
                            Databasehandler.bookReturn(book_id);
                            break;
                        }
                    default: {
                        DashBoard(name, email);
                        break;
                    }
                }
                DashBoard(name, email);
                break;
            }
            default:
            {
                System.out.println("Thank You User for Visiting....!!!");
                Library.enterLibrary();
                break;
            }
        }
    }
}