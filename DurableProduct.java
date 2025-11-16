package inventory;

// Example: Electronics, Furniture, Tools etc.
public class DurableProduct extends Product {

    public DurableProduct(String id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public String getCategory() {
        return "Durable";
    }
}
