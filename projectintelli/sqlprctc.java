
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

    public class sqlprctc {
        public static String query ="insert into demo(name,age,marks)values(?,?,?);";
        public static final String url = "jdbc:mysql://127.0.0.1:3306/university";
        public static final String username ="root";
        public static final String password ="5#@_HPOmen#@W";

        public static void main(String[] args) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter Name : ");
                String name = sc.nextLine();
                System.out.print("Enter Age : ");
                int age = sc.nextInt();
                System.out.print("Enter Marks : ");
                double marks = sc.nextDouble();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.setDouble(3, marks);

                int rowsaffacted = preparedStatement.executeUpdate();
                if (rowsaffacted > 0) {
                    System.out.println("Data Inserted Successfully ! ");
                } else {
                    System.out.println("Data not Inserted !");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

