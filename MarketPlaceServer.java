import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.net.*;

public class MarketplaceServer {
    private static ServerSocket serverSocket;
    private static Marketplace marketplace;
    private static Boolean running;
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(4242);
            System.out.println("Marketplace server started!");
            marketplace = loadMarketplace();
            if(marketplace == null){
                marketplace = new Marketplace();
            }
            running=true;
            while (running)
                new MarketplaceClientHandler(serverSocket.accept()).start();
            
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static class MarketplaceClientHandler extends Thread {
        private Socket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private User currentlySignedInUser; 

        public MarketplaceClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
            out = new ObjectOutputStream(clientSocket.getOutputStream()); // get the output stream of client.
            in = new ObjectInputStream(clientSocket.getInputStream());    // get the input stream of client.
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            while (running) {
                // FIND OUT WHAT OPERATION TO RUN AND WHAT TO SEND TO CLIENT
                // commandName arg1, arg2, ect
                ServerCommand serverCommand;
                try {
                    serverCommand = (ServerCommand) in.readObject(); 
                }
                catch(Exception e) {
                    e.printStackTrace();
                    break;
                }
                synchronized (marketplace) {
                    if (currentlySignedInUser != null) {
                        marketplace.setSignedInUser(currentlySignedInUser);
                    }
                    switch (serverCommand.commandName) {
                        case "isLoggedInUserSeller":
                            serverCommand.outBool = marketplace.isLoggedInUserSeller();
                            break;
                        case "getLoggedInUsername":
                            serverCommand.outString = marketplace.getLoggedInUsername();
                            break;
                        case "createAccount":
                            serverCommand.outBool = marketplace.createAccount(serverCommand.inStrings[0],serverCommand.inStrings[1],serverCommand.inStrings[2],serverCommand.inBools[0]);
                            if(serverCommand.outBool == true)currentlySignedInUser = marketplace.getSignedInUser();
                            break;
                        case "attemptLogin":
                            serverCommand.outBool = marketplace.attemptLogin(serverCommand.inStrings[0],serverCommand.inStrings[1]);
                            if(serverCommand.outBool == true)currentlySignedInUser = marketplace.getSignedInUser();
                            break;
                        case "listAllStores":
                            serverCommand.outStoreList = marketplace.listAllStores();
                            break;
                        case "viewProducts":
                            serverCommand.outProductList = marketplace.viewProducts(serverCommand.inStores[0]);
                            break;
                        case "searchProducts":
                            serverCommand.outProductList = marketplace.searchProducts(serverCommand.inStrings[0]);
                            break;
                        case "buyNow":
                            serverCommand.outBool = marketplace.buyNow(serverCommand.inProducts[0]);
                            break;
                        case "addToCart":
                            serverCommand.outBool = marketplace.addToCart(serverCommand.inProducts[0]);
                            break;
                        case "viewCart":
                            serverCommand.outProductList = marketplace.viewCart();
                            break;
                        case "deleteItemFromCart":
                            marketplace.deleteItemFromCart(serverCommand.inProducts[0]);
                            break;
                        case "checkoutCart":
                            serverCommand.outBool = marketplace.checkoutCart();
                            break;
                        case "dashboardSaleCounterByStore":
                            serverCommand.outString = marketplace.dashboardSaleCounterByStore(serverCommand.inBools[0]);
                            break;
                        case "dashboardPurchasesByStore":
                            serverCommand.outString = marketplace.dashboardPurchasesByStore(serverCommand.inBools[0]);
                            break;
                        case "exportDashboardPurchasesByStore":
                            serverCommand.outString = marketplace.dashboardPurchasesByStore(serverCommand.inBools[0]);
                            break;
                        case "createStore":
                            serverCommand.outBool = marketplace.createStore(serverCommand.inStrings[0]);
                            break;
                        case "listStoresForSeller":
                            serverCommand.outStoreList = marketplace.listStoresForSeller();
                            break;
                        case "listProducts":
                            serverCommand.outProductList = marketplace.listProducts(serverCommand.inStores[0]);
                            break;
                        case "createProduct":
                            serverCommand.outBool = marketplace.createProduct(serverCommand.inStores[0], serverCommand.inStrings[0], serverCommand.inStrings[1], serverCommand.inStrings[2], serverCommand.inStrings[3], serverCommand.inStrings[4], serverCommand.inInts[0], serverCommand.inInts[1]);
                            break;
                        case "removeProduct":
                            marketplace.removeProduct(serverCommand.inProducts[0]);
                            break;
                        case "editProduct":
                            serverCommand.outBool = marketplace.editProduct(serverCommand.inProducts[0], serverCommand.inStrings[0], serverCommand.inStrings[1], serverCommand.inStrings[2], serverCommand.inStrings[3], serverCommand.inStrings[4], serverCommand.inInts[0], serverCommand.inInts[1]);
                            break;
                        case "customerRevenueSaleList":
                            serverCommand.outString = marketplace.customerRevenueSaleList(serverCommand.inStores[0]);                          
                            break;
                        case "getProductList":
                            serverCommand.outString = marketplace.getProductList(serverCommand.inStores[0]);
                            break;
                        case "exportProductList":
                            serverCommand.outString = marketplace.exportProductList(serverCommand.inStores[0],serverCommand.inStrings[0]);
                            break;
                        case "importProductList":
                        serverCommand.outBool = marketplace.importProductList(serverCommand.inStores[0],serverCommand.inStrings[0]);
                            break;
                        case "dashboardCustomerNumSales":
                        serverCommand.outString = marketplace.dashboardCustomerNumSales(serverCommand.inBools[0]);
                            break;
                        case "dashboardProductNumSales":
                        serverCommand.outString = marketplace.dashboardProductNumSales(serverCommand.inBools[0]);
                            break;
                        case "quit":
                            running= false;
                            break;
                        }
                        marketplace.setSignedInUser(null);
                        saveMarketplace(marketplace);
                    }
                    try {
                        out.reset();
                        out.writeObject(serverCommand);
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
            }
    }
    
    public static Marketplace loadMarketplace() {
        Marketplace m = null;
        try {
            FileInputStream fileIn = new FileInputStream("./MarketplaceState.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            m = (Marketplace) in.readObject();
            in.close();
            fileIn.close();
            return m;
        } catch (Exception e) {
            return m;
        }
    }

    public static void saveMarketplace(Marketplace m) {
        try {
         FileOutputStream fileOut = new FileOutputStream("./MarketplaceState.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(m);
         out.close();
         fileOut.close();
      } catch (Exception e) {
         // nothing
      }
    }
    
}
