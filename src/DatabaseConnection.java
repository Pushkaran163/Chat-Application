import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    private static final String URL = "jdbc:mysql://localhost:3306/chat_app";
    // private static final String URL = "jdbc:mysql://127.0.0.1:3306/chat_app";
    private static final String USER = "root";
    private static final String PASSWORD = "Sayanyellow12!_";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}