/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class display {
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/PAYROLL";
        String username = "root";
        String password = "5#@_HPOmen#@W";
        return DriverManager.getConnection(url, username, password);
    }
}*/

import java.sql.*;

public class display {
    private static final String url="jdbc:mysql://127.0.0.1:3306/PAYROLL";
    private static final String username ="root";
    private static final String password="5#@_HPOmen#@W";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Drivers");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement statement = con.createStatement();
            String query ="update Empl set ename = harshit where eid =7;";
            int rowsaffected = statement.executeUpdate(query);
            if(rowsaffected>0){
                System.out.println("Data Updated Sussesfully !");
            }
            else {
                System.out.println("Data not found ! ");
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}