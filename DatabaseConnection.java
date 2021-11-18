import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    static final String DB_URL ="jdbc:mysql://localhost/librayManagementSystem";
    static final String USER="root";
    static final String PASS="";

    public static Connection getConnection() throws SQLException
    {
        Connection connection=null;
        connection =DriverManager.getConnection(DB_URL,USER,PASS);
        if(connection==null)
            System.out.println("Connection not established");
        return connection;
    }
}