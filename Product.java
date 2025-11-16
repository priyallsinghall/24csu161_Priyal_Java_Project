package inventory;

// Base class for all products
public abstract class Product {
    protected String id;
    protected String name;
    protected double price;
    protected int stock;

    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // will be different for each type
    public abstract String getCategory();

    // convert to a line for text file
    public String toCSV() {
        return id + "," + name + "," + price + "," + stock + "," + getCategory();
    }
}
