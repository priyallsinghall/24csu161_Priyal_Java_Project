package inventory;

import java.io.*;
import java.util.ArrayList;

public class InventoryManager {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<SaleRecord> sales = new ArrayList<>();

    private final String PRODUCT_FILE = "products.txt";
    private final String SALES_FILE = "sales.txt";

    public InventoryManager() {
        loadProducts();
        loadSales();
    }

    public void addProduct(Product p) {
        products.add(p);
        saveProducts();
    }

    public Product findProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public void updateStock(String id, int qty) {
        Product p = findProduct(id);
        if (p != null) {
            int newStock = p.getStock() + qty;
            if (newStock >= 0) {
                p.setStock(newStock);
                saveProducts();
            } else {
                System.out.println("Not enough stock!");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    public void recordSale(SaleRecord s) {
        sales.add(s);
        saveSales();
    }

    public void generateReport() {
        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            System.out.println(p.getId() + " | " + p.getName() + " | " + p.getCategory() + " | Stock: " + p.getStock() + " | Price: " + p.getPrice());
        }

        System.out.println("\n--- Sales List ---");
        for (SaleRecord s : sales) {
            System.out.println(s);
        }
    }

    // ---------------- File I/O ---------------- //

    private void saveProducts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PRODUCT_FILE))) {
            for (Product p : products) {
                pw.println(p.toCSV());
            }
        } catch (Exception e) {
            System.out.println("Error saving products.");
        }
    }

    private void saveSales() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SALES_FILE))) {
            for (SaleRecord s : sales) {
                pw.println(s.toCSV());
            }
        } catch (Exception e) {
            System.out.println("Error saving sales.");
        }
    }

    private void loadProducts() {
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                String id = x[0];
                String name = x[1];
                double price = Double.parseDouble(x[2]);
                int stock = Integer.parseInt(x[3]);
                String category = x[4];

                if (category.equalsIgnoreCase("Consumable"))
                    products.add(new ConsumableProduct(id, name, price, stock));
                else
                    products.add(new DurableProduct(id, name, price, stock));
            }
        } catch (Exception e) { /* ignore if file empty */ }
    }

    private void loadSales() {
        try (BufferedReader br = new BufferedReader(new FileReader(SALES_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] x = line.split(",");
                // productId, name, category, qty, totalPrice, dateMillis
                SaleRecord s = new SaleRecord(
                        new DurableProduct(x[0], x[1], 0, 0),
                        Integer.parseInt(x[3])
                );
                sales.add(s);
            }
        } catch (Exception e) { /* ignore */ }
    }
}
