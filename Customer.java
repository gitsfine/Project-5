import java.util.*;

public class Customer extends User implements java.io.Serializable {
    private ArrayList<Sale> history;
    private ArrayList<Product> cart;

    public Customer(String userName, String email, String password) {
        super(userName, email, password);
        history = new ArrayList<Sale>();
        cart = new ArrayList<Product>();
    }

    public List<Product> getCart() {
        return cart;
    }

    public boolean addToCart(Product p) {
        // Check if item exists
        for (Product exisitingProduct : cart) {
            if (exisitingProduct.equals(p)) {
                // product already exists, do not add
                return false;
            }
        }

        cart.add(p);
        return true;
    }

    public boolean removeFromCart(Product p) {
        return cart.remove(p);
    }

    public void addToSaleHistory(Sale s) {
        history.add(s);
    }

    public List<Sale> getSaleHistory() {
        return history;
    }

    public boolean checkout() {
        boolean success = true;
        for (Product p : cart) {
            if(p.buy(this) == false) {
                success = false;
            }
        }
        cart = new ArrayList<Product>();
        return success;
    }
}
