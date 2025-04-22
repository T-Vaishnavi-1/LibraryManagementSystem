import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {
   private static String url="jdbc:mysql://localhost:3306/library_db";
   private static String user="root";
   private static String passwd="abc123";
   public static Connection getConnection()
   {
   try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           return DriverManager.getConnection(url,user,passwd);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    
}}