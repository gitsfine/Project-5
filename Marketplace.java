import javax.swing.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Marketplace implements java.io.Serializable {
    public ArrayList<User> users;
    public ArrayList<Store> stores;

    private User signedInUser;

    public Marketplace() {
        users = new ArrayList<User>();
        stores = new ArrayList<Store>();
    }

    public boolean isLoggedInUserSeller() {
        return signedInUser instanceof Seller;
    }

    public String getLoggedInUsername() {
        return signedInUser.getUsername();
    }

    public boolean createAccount(String username, String password, String email, boolean isSeller) {
        // Check if user exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                // user already exists, quit early
                return false;
            }
        }

        User newUser;
        if (isSeller) {
            newUser = new Seller(username, email, password);
        } else {
            newUser = new Customer(username, email, password);
        }

        signedInUser = newUser;
        users.add(newUser);
        return true;
    }

    public void setSignedInUser(User u) {
        if (u == null) {
            signedInUser = null;
            return;
        }
        for (User user : users) {
            if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
                // user name and password match
                signedInUser = user;
            }
        }
    }

    public User getSignedInUser() {
        return signedInUser;
    }

    public boolean attemptLogin(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // user name and password match
                signedInUser = user;
                return true;
            }
        }
        return false;
    }

    // Start customer actions
    private Customer getCustomer() {
        if (signedInUser instanceof Customer) {
            return (Customer) signedInUser;
        } else {
            throw new Error("Seller using customer action");
        }
    }

    public List<Store> listAllStores() {
        return stores;
    }

    public List<Product> viewProducts(Store s) {
        return s.getProducts();
    }

    public List<Product> searchProducts(String item) {
        ArrayList<Product> matchedProducts = new ArrayList<>();
        for (Store s : stores) {
            for (Product p : s.getProducts()) {
                if (p.toCombinedString().indexOf(item) > 0) {
                    matchedProducts.add(p);
                }
            }
        }

        return matchedProducts;
    }

    public boolean buyNow(Product p) {
        Customer c = getCustomer();
        return p.buy(c);
    }

    public boolean addToCart(Product p) {
        Customer c = getCustomer();
        return c.addToCart(p);
    }

    public List<Product> viewCart() {
        Customer c = getCustomer();
        return c.getCart();
    }

    public void deleteItemFromCart(Product p) {
        Customer c = getCustomer();
        c.removeFromCart(p);
    }

    public boolean checkoutCart() {
        Customer c = getCustomer();
        return c.checkout();
    }

    public String dashboardSaleCounterByStore(boolean isDsc) {
        String output = "Number of sales by store\n";
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Store s : stores) {
            dashboardRow.add(s.getSales().size() + " - " + s.getName());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        return output + String.join("\n", dashboardRow) + "\n";
    }

    public String dashboardPurchasesByStore(boolean isDsc) {
        Customer c = getCustomer();
        String output = "Number of your purchases by store\n";
        ArrayList<Store> saleStores = new ArrayList<>();
        ArrayList<Integer> purchases = new ArrayList<>();
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Sale sale : c.getSaleHistory()) {
            Store saleStore = sale.getProduct().getStore();
            int pos = saleStores.indexOf(saleStore);
            if (pos == -1) {
                saleStores.add(saleStore);
                purchases.add(0);
                pos = saleStores.indexOf(saleStore);
            }
            purchases.set(pos, purchases.get(pos) + 1);
        }

        for (int i = 0; i < saleStores.size(); i++) {
            dashboardRow.add(purchases.get(i) + " - " + saleStores.get(i).getName());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        return output + String.join("\n", dashboardRow) + "\n";
    }

    public String exportDashboardPurchasesByStore(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(dashboardPurchasesByStore(false));
            myWriter.close();
            return "Done!";
        } catch (IOException e) {
            return "Unable to write csv.";
        }
    }
    // End customer actions

    // Start seller action
    private Seller getSeller() {
        if (signedInUser instanceof Seller) {
            return (Seller) signedInUser;
        } else {
            throw new Error("Customer using seller action");
        }
    }

    public boolean createStore(String name) {
        Seller s = getSeller();
        for (Store store : stores) {
            if (store.getName() == name) {
                return false;
            }
        }
        Store store = new Store(name, s);
        s.createStore(store);
        stores.add(store);
        return true;
    }

    public List<Store> listStoresForSeller() {
        Seller s = getSeller();
        return s.getStores();
    }

    public List<Product> listProducts(Store s) {
        return s.getProducts();
    }

    public boolean createProduct(Store s, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        Product p = new Product(s, name, milkType, coffeeType, syrup, special, price, quantity);
        return s.createProduct(p);
    }

    public void removeProduct(Product p) {
        p.getStore().deleteProduct(p);
    }

    public boolean editProduct(Product p, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        return p.getStore().editProduct(p, name, milkType, coffeeType, syrup, special, price, quantity);
    }

    public String customerRevenueSaleList(Store s) {
        String output = String.format("Revenue by customer for %s:\n", s.getName());
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Integer> revenue = new ArrayList<>();
        ArrayList<String> listRow = new ArrayList<String>();

        for (Sale sale : s.getSales()) {
            Customer saleBuyer = sale.getBuyer();
            int pos = customers.indexOf(saleBuyer);
            if (pos == -1) {
                customers.add(saleBuyer);
                revenue.add(0);
                pos = customers.indexOf(saleBuyer);
            }
            int price = sale.getProduct().getPrice();
            revenue.set(pos, revenue.get(pos) + price);
        }

        for (int i = 0; i < customers.size(); i++) {
            listRow.add(customers.get(i).getUsername() + " - $" + revenue.get(i));
        }

        return output + String.join("\n", listRow) + "\n";
    }

    public String getProductList(Store s) {
        String output = "Product list\n";
        ArrayList<String> listRow = new ArrayList<String>();
        List<Product> product = s.getProducts();

        for (int i = 0; i < product.size(); i++) {
            Product p = product.get(i);
            listRow.add(p.toExport());
        }

        return output + String.join("\n", listRow) + "\n";
    }

    public String exportProductList(Store s, String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath);
            myWriter.write(getProductList(s));
            myWriter.close();
            return "Done exporting product list!";
        } catch (IOException e) {
            return "Unable to write csv.";
        }
    }

    public boolean importProductList(Store s, String filepath) {
        String productString = "";
        try {
            boolean status = true;
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            br.readLine(); // Skip first line
            while ((productString = br.readLine()) != null) {
                String[] p = productString.split(", ");
                status = status && createProduct(s, p[0], p[1], p[2], p[3], p[4], Integer.parseInt(p[5]), Integer.parseInt(p[6]));
            }
            br.close();
            return status;
        } catch (IOException e) {
            return false;
        }
    }

    public void dashboardCustomerNumSalesVoid(boolean isDsc) {
        Seller s = getSeller();
        String output = "Number of your sales by customer:\n";
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Integer> purchases = new ArrayList<>();
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Sale sale : s.getSales()) {
            Customer saleBuyer = sale.getBuyer();
            int pos = customers.indexOf(saleBuyer);
            if (pos == -1) {
                customers.add(saleBuyer);
                purchases.add(0);
                pos = customers.indexOf(saleBuyer);
            }
            purchases.set(pos, purchases.get(pos) + 1);
        }

        for (int i = 0; i < customers.size(); i++) {
            dashboardRow.add(purchases.get(i) + " - " + customers.get(i).getUsername());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        String results = String.format(output + String.join("\n", dashboardRow) + "\n");
        if (dashboardRow.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no sales :(",
                    "===Sales===", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, results,
                    "===Sales===", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public String dashboardCustomerNumSales(boolean isDsc) {
        Seller s = getSeller();
        String output = "Number of your sales by customer\n";
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Integer> purchases = new ArrayList<>();
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Sale sale : s.getSales()) {
            Customer saleBuyer = sale.getBuyer();
            int pos = customers.indexOf(saleBuyer);
            if (pos == -1) {
                customers.add(saleBuyer);
                purchases.add(0);
                pos = customers.indexOf(saleBuyer);
            }
            purchases.set(pos, purchases.get(pos) + 1);
        }

        for (int i = 0; i < customers.size(); i++) {
            dashboardRow.add(purchases.get(i) + " - " + customers.get(i).getUsername());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        return output + String.join("\n", dashboardRow) + "\n";
    }

    public void dashboardProductNumSalesVoid(boolean isDsc) {
        Seller s = getSeller();
        String output = "Number of your sales by product\n";
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> purchases = new ArrayList<>();
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Sale sale : s.getSales()) {
            Product saleProduct = sale.getProduct();
            int pos = products.indexOf(saleProduct);
            if (pos == -1) {
                products.add(saleProduct);
                purchases.add(0);
                pos = products.indexOf(saleProduct);
            }
            purchases.set(pos, purchases.get(pos) + 1);
        }

        for (int i = 0; i < products.size(); i++) {
            dashboardRow.add(purchases.get(i) + " - " + products.get(i).getName());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        String results = String.format(output + String.join("\n", dashboardRow) + "\n");
        if (dashboardRow.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no sales :(",
                    "===Sales===", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, results,
                    "===Sales===", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public String dashboardProductNumSales(boolean isDsc) {
        Seller s = getSeller();
        String output = "Number of your sales by product\n";
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> purchases = new ArrayList<>();
        ArrayList<String> dashboardRow = new ArrayList<String>();

        for (Sale sale : s.getSales()) {
            Product saleProduct = sale.getProduct();
            int pos = products.indexOf(saleProduct);
            if (pos == -1) {
                products.add(saleProduct);
                purchases.add(0);
                pos = products.indexOf(saleProduct);
            }
            purchases.set(pos, purchases.get(pos) + 1);
        }

        for (int i = 0; i < products.size(); i++) {
            dashboardRow.add(purchases.get(i) + " - " + products.get(i).getName());
        }

        // sort by sale count
        if (!isDsc) {
            dashboardRow.sort((String o1, String o2) -> 1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        } else {
            dashboardRow.sort((String o1, String o2) -> -1 * (Integer.parseInt(o1.split(" - ")[0]) - Integer.parseInt(o2.split(" - ")[0])));
        }

        return output + String.join("\n", dashboardRow) + "\n";
    }

    // End seller actions
}
