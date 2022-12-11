import java.io.*;
import java.util.*;
import java.net.*;

// API for client to call server
public class MarketplaceClient{
    private Socket client = null;
    private ObjectOutputStream out = null;
    private ObjectInputStream in = null;
    
    public MarketplaceClient() {
        try {
            client = new Socket("localhost", 4242);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    public void disconnectClient() {

        try {        
            out.close();
            in.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void killServer() {
        executeCommand(new ServerCommand("quit"));
    }

    public ServerCommand executeCommand(ServerCommand sc) {
        try { 
            out.reset();  
            out.writeObject(sc);
            out.flush();
            return (ServerCommand) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sc;
    }

    public boolean isLoggedInUserSeller() {
        ServerCommand sc = new ServerCommand("isLoggedInUserSeller");

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public String getLoggedInUsername() {
        // Call server getLoggedInUsername and return value
        ServerCommand sc = new ServerCommand("getLoggedInUsername");

        sc = executeCommand(sc);

        return sc.outString;
    }

    public boolean createAccount(String username, String password, String email, boolean isSeller) {
        ServerCommand sc = new ServerCommand("createAccount");
        sc.inStrings = new String[]{username, password, email};
        sc.inBools = new Boolean[]{ isSeller };

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public boolean attemptLogin(String username, String password) {
        ServerCommand sc = new ServerCommand("attemptLogin");
        sc.inStrings = new String[]{username,password};

        sc = executeCommand(sc);

        return sc.outBool;  
        }

    // Start customer actions
    public List<Store> listAllStores() {
        ServerCommand sc = new ServerCommand("listAllStores");

        sc = executeCommand(sc);

        return sc.outStoreList;
    }

    public List<Product> viewProducts(Store s) {
        ServerCommand sc = new ServerCommand("viewProducts");
        sc.inStores = new Store[]{s};

        sc = executeCommand(sc);

        return sc.outProductList;
    }

    public List<Product> searchProducts(String item) {
        ServerCommand sc = new ServerCommand("searchProducts");
        sc.inStrings = new String[]{item};

        sc = executeCommand(sc);

        return sc.outProductList;
        
    }

    public boolean buyNow(Product p) {
        ServerCommand sc = new ServerCommand("buyNow");
        sc.inProducts = new Product[]{p};

        sc = executeCommand(sc);

        return sc.outBool;  
        }

    public boolean addToCart(Product p) {
        ServerCommand sc = new ServerCommand("addToCart");
        sc.inProducts = new Product[]{p};

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public List<Product> viewCart() {
        ServerCommand sc = new ServerCommand("viewCart");

        sc = executeCommand(sc);

        return sc.outProductList;
    }

    public void deleteItemFromCart(Product p) {
        ServerCommand sc = new ServerCommand("deleteItemFromCart");
        sc.inProducts = new Product[]{p};

    }

    public boolean checkoutCart() {
        ServerCommand sc = new ServerCommand("checkoutCart");

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public String dashboardSaleCounterByStore(boolean isDsc) {
        ServerCommand sc = new ServerCommand("dashboardSaleCounterByStore");
        sc.inBools = new Boolean[]{isDsc};       

        sc = executeCommand(sc);

        return sc.outString;
    }

    public String dashboardPurchasesByStore(boolean isDsc) {
        ServerCommand sc = new ServerCommand("dashboardPurchasesByStore");
        sc.inBools = new Boolean[]{isDsc};       

        sc = executeCommand(sc);

        return sc.outString;
    }

    public String exportDashboardPurchasesByStore(String filepath) {
        ServerCommand sc = new ServerCommand("exportDashboardPurchasesByStore");
        sc.inStrings = new String[]{filepath};      

        sc = executeCommand(sc);

        return sc.outString;
    }
    // End customer actions

    // Start seller action
    
    public boolean createStore(String name) {
        ServerCommand sc = new ServerCommand("createStore");
        sc.inStrings = new String[]{name}; 

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public List<Store> listStoresForSeller() {
         ServerCommand sc = new ServerCommand("listStoresForSeller");

         sc = executeCommand(sc);

         return sc.outStoreList;
    }
    
    public List<Product> listProducts(Store s) {
        ServerCommand sc = new ServerCommand("listProducts");
        sc.inStores = new Store[]{s};

        sc = executeCommand(sc);

        return sc.outProductList;
    }

    public boolean createProduct(Store s, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        ServerCommand sc = new ServerCommand("createProduct");
        sc.inStores = new Store[]{s};
        sc.inStrings = new String[]{name,milkType,coffeeType,syrup,special};
        sc.inInts = new Integer[]{price,quantity};

        sc = executeCommand(sc);
        
        return sc.outBool;
    }

    public void removeProduct(Product p) {
        ServerCommand sc = new ServerCommand("removeProduct");
        sc.inProducts = new Product[]{p};        
        
        sc = executeCommand(sc);
    }

    public boolean editProduct(Product p, String name, String milkType, String coffeeType, String syrup, String special, Integer price, Integer quantity) {
        ServerCommand sc = new ServerCommand("editProduct");
        sc.inProducts = new Product[]{p};    
        sc.inStrings = new String[]{name,milkType,coffeeType,syrup,special};
        sc.inInts = new Integer[]{price,quantity};

        sc = executeCommand(sc);

        return sc.outBool;
    }

    public String customerRevenueSaleList(Store s) {
        ServerCommand sc = new ServerCommand("customerRevenueSaleList");
        sc.inStores = new Store[]{s};    

        sc = executeCommand(sc);

        return sc.outString;
    }

    public String getProductList(Store s) {
        ServerCommand sc = new ServerCommand("getProductList");
        sc.inStores = new Store[]{s}; 

        sc = executeCommand(sc);

        return sc.outString;
    }

    public String exportProductList(Store s, String filepath) {
        ServerCommand sc = new ServerCommand("exportProductList");
        sc.inStores = new Store[]{s};
        sc.inStrings = new String[]{filepath};    

        sc = executeCommand(sc);

        return sc.outString;
    }

    public boolean importProductList(Store s, String filepath) {
        ServerCommand sc = new ServerCommand("importProductList");
        sc.inStores = new Store[]{s}; 
        sc.inStrings = new String[]{filepath};    

        sc = executeCommand(sc);

        return sc.outBool;
    }
    
    public String dashboardCustomerNumSales(boolean isDsc) {
        ServerCommand sc = new ServerCommand("dashboardCustomerNumSales");
        sc.inBools = new Boolean[]{isDsc}; 

        sc = executeCommand(sc);

        return sc.outString;
    }

    public String dashboardProductNumSales(boolean isDsc) {
        ServerCommand sc = new ServerCommand("dashboardProductNumSales");
        sc.inBools = new Boolean[]{isDsc};
        
        sc = executeCommand(sc);

        return sc.outString;
    }
    // End seller actions
}
