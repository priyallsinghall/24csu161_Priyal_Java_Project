package inventory;

// Example: Food, Medical Supplies, Stationary etc.
public class ConsumableProduct extends Product {

    public ConsumableProduct(String id, String name, double price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public String getCategory() {
        return "Consumable";
    }
}
