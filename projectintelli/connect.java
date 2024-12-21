/*import java.sql.*;
public class connect {
    private static final String url="jdbc:mysql://127.0.0.1:3306/PAYROLL";
    private static final String username ="root";
    private static final String password="5#@_HPOmen#@W";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url,username,password);
            Statement statement = con.createStatement();
            String query ="select * from Empl;";
            ResultSet resultSet= statement.executeQuery(query);

            while(resultSet.next()){
                int id =resultSet.getInt("eid");
                String name = resultSet.getString("ename");
                int age = resultSet.getInt("age");
                double marks = resultSet.getDouble("salry");
                String eid ;
                String ename ;
                System.out.println("ID : "+eid);
                System.out.println("Name : "+ename);
                System.out.println("Age : "+age);

            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}*/
import java.sql.*;
import java.util.Scanner;

public class connect {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/PAYROLL";
    private static final String username = "root";
    private static final String password = "5#@_HPOmen#@W";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try (Scanner scanner = new Scanner(System.in);
             Connection con = DriverManager.getConnection(url, username, password)) {

            System.out.print("Enter employee ID: ");
            int eid = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            System.out.print("Enter employee name: ");
            String ename = scanner.nextLine();

            System.out.print("Enter employee age: ");
            Integer age = scanner.nextInt();


            String query = "INSERT INTO Empl(eid, ename, age) VALUES (?,?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, eid);
                preparedStatement.setString(2, ename);
                preparedStatement.setInt(3, age);

                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) affected.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
