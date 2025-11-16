package inventory;

import java.util.Scanner;

public class InventoryManagementSystem {

    public static void main(String[] args) {

        // -------- LOGIN GUI --------
        LoginManager login = new LoginManager();

        boolean loggedIn = login.showLoginGUI();
        if (!loggedIn) {
            System.out.println("Login Failed. Exiting program...");
            return; // stop program if login fails
        }

        System.out.println("Login Successful! Welcome.\n");

        // -------- Start Console Menu After Login --------
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Inventory Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Stock");
            System.out.println("3. Record Sale");
            System.out.println("4. Show Report");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {

                case 1:
                    System.out.print("Consumable or Durable (C/D)? ");
                    String type = sc.nextLine();

                    System.out.print("Product ID: ");
                    String id = sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Product p = type.equalsIgnoreCase("C") ?
                            new ConsumableProduct(id, name, price, stock) :
                            new DurableProduct(id, name, price, stock);

                    manager.addProduct(p);
                    System.out.println("Product added successfully.");
                    break;

                case 2:
                    System.out.print("Product ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Quantity (+/-): ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    manager.updateStock(pid, qty);
                    break;

                case 3:
                    System.out.print("Product ID: ");
                    String ps = sc.nextLine();
                    Product found = manager.findProduct(ps);

                    if (found != null) {
                        System.out.print("Quantity sold: ");
                        int q = sc.nextInt();
                        sc.nextLine();
                        manager.recordSale(new SaleRecord(found, q));
                        manager.updateStock(ps, -q);
                        System.out.println("Sale recorded.");
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 4:
                    manager.generateReport();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option!");
            } // switch end
        } // while end

    } // main end

} // class end
