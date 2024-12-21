import java.util.Scanner;

interface Displayable {
    void display();
}

class Customer {
    protected String name;
    protected String address;
    protected String email;

    public Customer(String nm, String add, String em) {
        name = nm;
        address = add;
        email = em;
    }

    public void get() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        name = scanner.nextLine();
        System.out.print("Enter address: ");
        address = scanner.nextLine();
        System.out.print("Enter email: ");
        email = scanner.nextLine();
    }
}

class Product {
    protected String name;
    protected double price;

    public Product(String nm, double p) {
        name = nm;
        price = p;
    }

    public void get() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        name = scanner.nextLine();
        System.out.print("Enter product price: ");
        price = scanner.nextDouble();
    }
}

class Sales extends Customer implements Displayable {
    private int quantity;

    public Sales(String nm, String add, String em, String pname, double p, int q) {
        super(nm, add, em);
        name = nm;
        address = add;
        email = em;
        quantity = q;
    }

    public void calculate() {
        int[][] arr = new int[3][3];
        int[] productSale = new int[3];
        int[] personSale = new int[3];

        Scanner scanner = new Scanner(System.in);

        // Reading sales data
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter the number of product sold by person " + (j + 1) + ", day " + (i + 1) + ": ");
                arr[i][j] = scanner.nextInt();
            }
        }

        // Calculation
        for (int i = 0; i < 3; i++) {
            productSale[i] = 0;
            personSale[i] = 0;
            for (int j = 0; j < 3; j++) {
                personSale[i] += arr[i][j];
                productSale[i] += arr[j][i];
            }
        }

        // Displaying sales data
        System.out.println("Details of each product sold:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Product " + (i + 1) + ": " + productSale[i]);
        }

        System.out.println("Details of total product sold by each person:");
        for (int i = 0; i < 3; i++) {
            System.out.println("Person " + (i + 1) + ": " + personSale[i]);
        }
    }

    public void discount() {
        double discountPercent, amount, markedPrice;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter discount percentage: ");
        discountPercent = scanner.nextDouble();
        System.out.print("Enter marked price: ");
        markedPrice = scanner.nextDouble();

        try {
            if (discountPercent > 100) {
                throw new IllegalArgumentException("Discount percentage greater than 100");
            } else {
                double discount = 100 - discountPercent;
                amount = (discount * markedPrice) / 100;
                System.out.println("Amount after discount: " + amount);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    @Override
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Email: " + email);
        System.out.println("Quantity: " + quantity);
    }
}

public class product {
    public static void main(String[] args) {
        String name, address, email, productName;
        double price;
        int quantity;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter customer details:");
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Address: ");
        address = scanner.nextLine();
        System.out.print("Email: ");
        email = scanner.nextLine();

        System.out.println("Enter product details:");
        System.out.print("Product Name: ");
        productName = scanner.nextLine();
        System.out.print("Product Price: ");
        price = scanner.nextDouble();
        System.out.print("Quantity: ");
        quantity = scanner.nextInt();

        Sales s1 = new Sales(name, address, email, productName, price, quantity);

        System.out.println();
        s1.display();
        s1.calculate();
        s1.discount();
    }
}