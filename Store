import java.util.*;

public class Store implements java.io.Serializable {
    private String storeName;
    private Seller owner;
    private static ArrayList<Product> products;
    private ArrayList<Sale> sales;

    public Store(String name, Seller seller) {
        storeName = name;
        owner = seller;
        products = new ArrayList<Product>();
        sales = new ArrayList<Sale>();
    }

    public String getName() {
        return storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean createProduct(Product p) {
        for (Product product : products) {
            if (product.getName() == p.getName()) {
                return false;
            }
        }
        products.add(p);
        return true;
    }

    public boolean editProduct(Product p, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        for (Product product : products) {
            if (product != p && product.getName() == p.getName()) {
                return false; //Name in use
            }
        }
        p.edit(name, milkType, coffeeType, syrup, special, price, quantity);
        return true;
    }

    public void deleteProduct(Product p) {
        products.remove(p);
    }

    public void addSale(Sale s) {
        sales.add(s);
    }

    public List<Sale> getSales() {
        return sales;
    }
}
