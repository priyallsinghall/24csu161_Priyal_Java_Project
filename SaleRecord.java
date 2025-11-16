package inventory;

import java.util.Date;

public class SaleRecord {
    private String productId;
    private String productName;
    private String category;
    private int quantity;
    private double totalPrice;
    private Date date;

    public SaleRecord(Product p, int quantity) {
        this.productId = p.getId();
        this.productName = p.getName();
        this.category = p.getCategory();
        this.quantity = quantity;
        this.totalPrice = p.getPrice() * quantity;
        this.date = new Date();
    }

    public String toCSV() {
        return productId + "," + productName + "," + category + "," + quantity + "," + totalPrice + "," + date.getTime();
    }

    @Override
    public String toString() {
        return productName + " (" + category + ")  x" + quantity + "  = " + totalPrice + "  on " + date;
    }
}
