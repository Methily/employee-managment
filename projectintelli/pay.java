
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

interface AS_LINE {
    void line(char ch, int n);
}

class MENU {

    public int mainMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("======= PAYROLL MANAGEMENT SYSTEM =======");
        System.out.println("1. New Employee");
        System.out.println("2. Modify Employee Record");
        System.out.println("3. Delete Employee Record");
        System.out.println("4. Display Employee Record");
        System.out.println("5. List All Employees");
        System.out.println("6. Generate Salary Slip");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
        choice = scanner.nextInt();
        return choice;
    }
}

class EMPLOYEE implements AS_LINE {
    private class Record {
        public int eid;
        String ename;
        double salry;
    }

    private Record[] records = new Record[100];
    private int recordNo = 0;
    private MENU menu = new MENU();  // Include a MENU object within EMPLOYEE

    public void line(char ch, int n) {
        for (int i = 1; i <= n; ++i)
            System.out.print(ch);
        System.out.println();
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/PAYROLL";
        String username = "root";
        String password = "5#@_HPOmen#@W";
        return DriverManager.getConnection(url, username, password);
    }
        private void   insertRecordIntoDatabase(Record record) {
            try (Connection connection = getConnection()) {
            String query = "INSERT INTO Empl (eid, ename, salry) VALUES (?,?, ?); ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, record.eid);
                preparedStatement.setString(2, record.ename);
                preparedStatement.setDouble(3, record.salry);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void fetchRecordsFromDatabase() {
        try (Connection connection = getConnection()) {
            String query = "SELECT name, salary FROM Empl";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                int i = 0;
                while (resultSet.next()) {
                    records[i] = new Record();
                    records[i].ename = resultSet.getString("ename");
                    records[i].salry = resultSet.getDouble("salry");

                    // Print the fetched data for debugging


                    i++;
                }
                recordNo = i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addRecord() {
        line('-', 40);
        System.out.println("NEW EMPLOYEE");
        line('-', 40);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: \n");
        String ename =scanner.nextLine(); // Consume the newline character
        records[recordNo] = new Record();
        records[recordNo].ename = scanner.nextLine();

        System.out.print("Enter salary: \n");
        records[recordNo].salry = scanner.nextDouble();

        insertRecordIntoDatabase(records[recordNo]);
        System.out.println("Employee added successfully!");

        recordNo++;
        displayRecord();
    }

    private void modifyRecord() {
        line('-', 40);
        System.out.println("MODIFY EMPLOYEE RECORD");
        line('-', 40);

        if (recordNo == 0) {
            System.out.println("No records found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee number (1-" + recordNo + "): ");
        int empNo = scanner.nextInt();

        if (empNo <= 0 || empNo > recordNo) {
            System.out.println("Invalid employee number.");
            return;
        }

        System.out.print("Enter new name: ");
        scanner.nextLine(); // Consume the newline character
        records[empNo - 1].ename = scanner.nextLine();

        System.out.print("Enter new salary: ");
        records[empNo - 1].salry = scanner.nextDouble();

        System.out.println("Employee record modified successfully!");
    }

    private void deleteEmployeeRecord() {
        line('-', 40);
        System.out.println("DELETE EMPLOYEE RECORD");
        line('-', 40);

        if (recordNo == 0) {
            System.out.println("No records found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee number (1-" + recordNo + "): ");
        int empNo = scanner.nextInt();

        if (empNo <= 0 || empNo > recordNo) {
            System.out.println("Invalid employee number.");
            return;
        }

        for (int i = empNo - 1; i < recordNo - 1; ++i)
            records[i] = records[i + 1];

        recordNo--;

        System.out.println("Employee record deleted successfully!");
    }

    private void displayRecord() {
        line('-', 40);
        System.out.println("DISPLAY EMPLOYEE RECORD");
        line('-', 40);

        if (recordNo == 0) {
            System.out.println("No records found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee number (1-" + recordNo + "): ");
        int empNo = scanner.nextInt();

        if (empNo <= 0 || empNo > recordNo) {
            System.out.println("Invalid employee number.");
            return;
        }

        System.out.println("Employee Name: " + records[empNo - 1].ename);
        System.out.println("Employee Salary: " + records[empNo - 1].salry);
    }

    private void listRecords() {
        line('-', 40);
        System.out.println("LIST ALL EMPLOYEES");
        line('-', 40);

        if (recordNo == 0) {
            System.out.println("No records found.");
            return;
        }

        System.out.printf("%-10s%-15s%s%n", "Employee #", "Name", "Salary");
        line('-', 40);

        for (int i = 0; i < recordNo; ++i) {
            System.out.printf("%-10d%-15s%.2f%n", i + 1, records[i].ename, records[i].salry);
        }
    }

    private void generateSalarySlip() {
        line('-', 40);
        System.out.println("GENERATE SALARY SLIP");
        line('-', 40);

        if (recordNo == 0) {
            System.out.println("No records found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter employee number (1-" + recordNo + "): ");
        int empNo = scanner.nextInt();

        if (empNo <= 0 || empNo > recordNo) {
            System.out.println("Invalid employee number.");
            return;
        }

        line('-', 40);
        System.out.println("Salary Slip");
        line('-', 40);
        System.out.println("Employee Name: " + records[empNo - 1].ename);
        System.out.println("Employee Salary: " + records[empNo - 1].salry);
        line('-', 40);
    }

    public void newEmployee() {
        addRecord();  }

    public void modification() {
        modifyRecord(); }

    public void deletion() {
        deleteEmployeeRecord();
    }

    public void display() {
        displayRecord();
    }

    public void list() {
        listRecords();
    }

    public void salarySlipFunction() {
        generateSalarySlip();
    }

    // Use the mainMenu method from the MENU interface
    public int mainMenu() {
        return menu.mainMenu();
    }
}

public class pay {
    public static void main(String[] args) {
        EMPLOYEE emp = new EMPLOYEE();
        int choice;

        do {
            choice = emp.mainMenu();

            switch (choice) {
                case 1:
                    emp.newEmployee();
                    break;
                case 2:
                    emp.modification();
                    break;
                case 3:
                    emp.deletion();
                    break;
                case 4:
                    emp.display();
                    break;
                case 5:
                    emp.list();
                    break;
                case 6:
                    emp.salarySlipFunction();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-7).");
                    break;
            }

            System.out.println();
        } while (choice != 7);
    }
}
