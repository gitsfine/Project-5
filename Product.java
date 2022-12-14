import java.util.*;

public class Product implements java.io.Serializable {
    private Store store;
    private String name;
    private String milkType;
    private String coffeeType;
    private String syrup;
    private String special;
    private Integer price;
    private Integer quantity;

    public Product (Store store, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        this.name = name;
        this.milkType = milkType;
        this.coffeeType = coffeeType;
        this.syrup = syrup;
        this.special = special;
        this.price = price;
        this.quantity = quantity;
        this.store = store;
    }

    public String toCombinedString() {
        return store.getName() +", "+ toListing();
    }

    public String toListing() {
        return name + ", " + milkType + ", " + coffeeType + ", " + syrup + ", " + special + ", "+ price + "$";
    }

    public boolean buy(Customer c) {
        if (quantity > 0) {
            quantity--;
            Sale s = new Sale(this, c);
            store.addSale(s);
            c.addToSaleHistory(s);
            return true;
        }
        return false;
    }

    public void edit(String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        this.name = name;
        this.milkType = milkType;
        this.coffeeType = coffeeType;
        this.syrup = syrup;
        this.special = special;
        this.price = price;
        this.quantity = quantity;
    }

    public Store getStore() {
        return store;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String toExport() {
        return name + ", " + milkType + ", " + coffeeType + ", " + syrup + ", " + special + ", " + price + ", " + quantity;
    }
}
