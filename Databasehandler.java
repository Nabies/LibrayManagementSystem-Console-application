import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databasehandler
{
    public static boolean CheckUser(String email,String pass) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT * FROM users WHERE user_email=? and user_pass=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, pass);

        ResultSet rs=pst.executeQuery();
        return rs.next();
    }
    public static boolean CheckAdmin(String email,String pass) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT * FROM admin WHERE admin_email=? and admin_pass=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, pass);

        ResultSet rs=pst.executeQuery();
        return rs.next();
    }
    public static boolean registerUser(String name, String email, String pass) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT * FROM users WHERE user_email=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);

        ResultSet rs=pst.executeQuery();
        if(rs.next())
        {
            System.out.println("Sorry User..!!! This Email Address Already Exists...!!!");
            return false;
        }

        String insertQuery="INSERT INTO users (user_name,user_email,user_pass) VALUES (?, ?, ?)";
        PreparedStatement inspst=conn.prepareStatement(insertQuery);
        inspst.setString(1, name);
        inspst.setString(2, email);
        inspst.setString(3, pass);
        inspst.executeUpdate();

        System.out.println("Account Registration Successful");

        return true;
    }
    public static void AvailableBooks() throws SQLException
    {
        System.out.println("\t\tAvailable Books");

        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT book_name, book_author,book_year,book_rack,book_department,book_status FROM books";
        PreparedStatement pst=conn.prepareStatement(query);

        ResultSet rs=pst.executeQuery();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|\tBOOK NAME\t|\tBOOK AUTHOR\t\t|\tBOOK YEAR\t|\tBOOK RACK\t|\tBOOK DEPARTMENT\t|\tAVAILABLE\t|");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        while(rs.next())
        {
            System.out.println("|\t"+rs.getString("book_name")+"\t|\t"+rs.getString("book_author")+"\t|\t"+rs.getString("book_year")+"\t\t|\t"+rs.getString("book_rack")+"\t\t\t|\t"+rs.getString("book_department")+"\t\t|\t"+((rs.getInt("book_status")==1)?"AVAILABLE":"NOT AVAILABLE")+"\t\t|");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        }
        System.out.println();
    }
    public static void AddBook(String bookName,String bookAuthor,String bookYear,String bookRack,String bookDepartment) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="INSERT INTO books(book_name,book_author,book_year,book_rack,book_department,book_status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, bookName);
        pst.setString(2, bookAuthor);
        pst.setString(3, bookYear);
        pst.setString(4, bookRack);
        pst.setString(5, bookDepartment);
        pst.setInt(6, 1);
        pst.executeUpdate();

        System.out.println("Book Added Successfully");
    }
    public static void ShowBookID() throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT book_id, book_name FROM books";
        PreparedStatement pst=conn.prepareStatement(query);

        ResultSet rs=pst.executeQuery();
        System.out.println("-----------------------------------------");
        System.out.println("|\tBOOK ID\t|\tBOOK NAME\t|");
        System.out.println("-----------------------------------------");
        while(rs.next())
        {
            System.out.println("|\t"+rs.getString("book_id")+"\t|\t"+rs.getString("book_name")+"\t|");
            System.out.println("-----------------------------------------");
        }
        System.out.println();
    }
    public static void DeleteBook(int bookId) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="DELETE FROM books WHERE book_id=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, bookId);

        pst.executeUpdate();
        System.out.println("Book Deleted Successfully");
    }
    public static void SearchByBookName(String bookName) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT book_name, book_author,book_status FROM books WHERE book_name LIKE ?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, "%"+bookName+"%");

        ResultSet rs=pst.executeQuery();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\tBOOK NAME\t|\tBOOK AUTHOR\t|\tAVAILABLE\t|");
        System.out.println("-------------------------------------------------------------------------");
        while(rs.next())
        {
            System.out.println("|\t"+rs.getString("book_name")+"\t|\t"+rs.getString("book_author")+"\t|\t"+((rs.getInt("book_status")==1)?"AVAILABLE":"NOT AVAILABLE")+"\t|");
            System.out.println("-------------------------------------------------------------------------");
        }
        System.out.println();
    }
    public static void SearchByAuthorName(String authorName) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT book_name, book_author,book_status FROM books WHERE book_author LIKE ?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, "%"+authorName+"%");

        ResultSet rs=pst.executeQuery();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|\tBOOK NAME\t|\tBOOK AUTHOR\t|\tAVAILABLE\t|");
        System.out.println("-------------------------------------------------------------------------");
        while(rs.next())
        {
            System.out.println("|\t"+rs.getString("book_name")+"\t|\t"+rs.getString("book_author")+"\t|\t"+((rs.getInt("book_status")==1)?"AVAILABLE":"NOT AVAILABLE")+"\t|");
            System.out.println("-------------------------------------------------------------------------");
        }
        System.out.println();
    }
    public static void SearchByBookYear(String bookYear) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT book_name, book_author,book_year,book_status FROM books WHERE book_year LIKE ?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, "%"+bookYear+"%");

        ResultSet rs=pst.executeQuery();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("|\tBOOK NAME\t|\tBOOK AUTHOR\t|\tBOOK YEAR\t|\tAVAILABLE\t|");
        System.out.println("---------------------------------------------------------------------------------------------");
        while(rs.next())
        {
            System.out.println("|\t"+rs.getString("book_name")+"\t|\t"+rs.getString("book_author")+"\t|\t"+rs.getString("book_year")+"\t|\t"+((rs.getInt("book_status")==1)?"AVAILABLE":"NOT AVAILABLE")+"\t|");
            System.out.println("-----------------------------------------------------------------------------------------");
        }
        System.out.println();
    }
    public static void PlaceOrder(int book_id,String email) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        setNotAvaialbe(book_id);
        int user_id=getId(email);
        java.util.Date date1=new java.util.Date();
        java.sql.Date issueDate=new java.sql.Date(date1.getTime());
        java.util.Date date2=new java.util.Date();
        java.sql.Date returnDate=new java.sql.Date(date2.getTime()+10*24*60*60*1000);


        String query="INSERT INTO orderbook (book_id,user_id,issue_date,return_date) VALUES (?, ?, ?, ?)";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, book_id);
        pst.setInt(2, user_id);
        pst.setDate(3, issueDate);
        pst.setDate(4, returnDate);

        pst.executeUpdate();

        System.out.println("Order has Been Placed...!!!");
        System.out.println("Please Return the book within this date : "+returnDate);

    }
    public static int getId(String email) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT user_id FROM users WHERE user_email=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);

        ResultSet rs=pst.executeQuery();
        if(rs.next())
            return rs.getInt("user_id");
        return -1;
    }
    public static void setNotAvaialbe(int book_id) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="UPDATE books SET book_status=0 WHERE book_id=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, book_id);

        pst.executeUpdate();
    }
    public static void issueDetails() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * from orderbook INNER JOIN users ON users.user_id = orderbook.user_id " +
                "INNER JOIN books ON books.book_id=orderbook.book_id";
        PreparedStatement pst = conn.prepareStatement(query);

        ResultSet rs = pst.executeQuery();
        int f = 0;

        while (rs.next()) {
            if(f==0){
                System.out.println("\t\tIssue Details of Users");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("|\tUSER NAME\t|\tBOOK NAME\t|\tISSUE DATE\t|\tRETURN DATE\t|");
                System.out.println("-------------------------------------------------------------------------------------------------");
                f=1;
            }
            System.out.println("|\t" + rs.getString("user_name") + "\t|\t" + rs.getString("book_name") +
                    "\t|\t" + rs.getString("issue_date") + "\t|\t" + rs.getString("return_date") + "\t|");
            System.out.println("-------------------------------------------------------------------------------------------------");
        }

        if (f == 0) {
            System.out.println("No Records Found!!!");
            System.out.println();
        }
    }
    public static void bookissueDetails(String book_name) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();

        String query = "SELECT * FROM users INNER JOIN orderbook INNER JOIN books on users.user_id=orderbook.user_id " +
                "AND orderbook.book_id=books.book_id where books.book_name=?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1,book_name);

        ResultSet rs = pst.executeQuery();
        int f = 0;

        while (rs.next()) {
            if(f==0){
                System.out.println("\t\tBookIssue Details of Users");
                System.out.println("-------------------------------------------------------------------------------------------------");
                System.out.println("|\tBOOK ID\t|\tBOOK NAME\t|\tUSER ID\t|\tUSER NAME\t|");
                System.out.println("-------------------------------------------------------------------------------------------------");
                f=1;
            }
            System.out.println("|\t" + rs.getString("book_id")+"\t|\t"+rs.getString("book_name")+"\t|\t" +
                    rs.getString("user_id") + "\t|\t" + rs.getString("user_name") + "\t|");
            System.out.println("-------------------------------------------------------------------------------------------------");
        }

        if (f == 0) {
            System.out.println("No Records Found!!!");
            System.out.println();
        }
    }
    public static boolean seereturnBookDetails(String email) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT * from orderbook INNER JOIN users ON users.user_id = orderbook.user_id INNER JOIN books ON books.book_id=orderbook.book_id WHERE users.user_email=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs=pst.executeQuery();
        int f=0;//found
        while(rs.next())
        {
            if(f==0){
                System.out.println("\t\tBooks to be Returned");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("|\tBOOK ID\t\t|\tBOOK NAME\t|\tISSUE DATE\t|\tRETURN DATE\t|");
                System.out.println("-----------------------------------------------------------------------------------------");
                f=1;
            }
            System.out.println("|\t"+rs.getString("book_id")+"\t\t|\t"+rs.getString("book_name")+"" +
                    "\t\t|\t"+rs.getString("issue_date")+"\t\t|\t"+rs.getString("return_date")+"\t\t|");
            System.out.println("-----------------------------------------------------------------------------------------");
        }
        System.out.println();

        if(f==0)
        {
            System.out.println("No Records Found!!!");
            System.out.println();
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean returnBookDetails(String email) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="SELECT * from orderbook INNER JOIN users ON users.user_id = orderbook.user_id INNER JOIN books ON books.book_id=orderbook.book_id WHERE users.user_email=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setString(1, email);

        ResultSet rs=pst.executeQuery();

        int f=0;
            while(rs.next())
            {
                if(f==0){
                    System.out.println("\t\tBooks to be Returned");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    System.out.println("|\tBOOK ID\t\t|\tBOOK NAME\t|\tISSUE DATE\t|\tRETURN DATE\t|");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    f=1;
                }
                System.out.println("|\t"+rs.getString("book_id")+"\t\t|\t"+rs.getString("book_name")+"\t\t|\t"+rs.getString("issue_date")+"\t\t|\t"+rs.getString("return_date")+"\t|\t");
                System.out.println("-----------------------------------------------------------------------------------------");
            }
            System.out.println();

        if(f==0)
        {
            System.out.println("No Records Found!!!");
            System.out.println();
            return false;
        }
        else{
            return true;
        }
    }
    public static void bookReturn(int book_id) throws SQLException
    {
        setAvaialbe(book_id);
        deleteOrder(book_id);
        System.out.println("Book Returned Successfully...!!!");
    }
    public static void deleteOrder(int book_id) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="DELETE FROM orderbook WHERE book_id=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, book_id);

        pst.executeUpdate();
    }
    public static void setAvaialbe(int book_id) throws SQLException
    {
        Connection conn=DatabaseConnection.getConnection();

        String query="UPDATE books SET book_status=1 WHERE book_id=?";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1, book_id);

        pst.executeUpdate();
    }
}

//multiple times we can use prepares ststement
//sql injectoion