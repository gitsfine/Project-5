import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Seller extends User implements java.io.Serializable {
    private ArrayList<Store> stores;

    public Seller(String userName, String email, String password) {
        super(userName, email, password);
        stores = new ArrayList<Store>();
    }

    public void createStore(Store s) {
        stores.add(s);
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<Sale> getSales() {
        ArrayList<Sale> sales = new ArrayList<>();
        for (Store s : stores) {
            sales.addAll(s.getSales());
        }
        return sales;
    }

    public void displaySales(boolean ascending) { //true is descending
        int panels = getSales().size();
        JFrame frame = new JFrame();
        GridLayout myLayout = new GridLayout(panels, 1);
        frame.setLayout(myLayout);

        String[] saleInfo = new String[getSales().size()];
        for (int i = 0; i < getSales().size(); i++) {
            saleInfo[i] = String.format("%d. %s", (i + 1), getSales().get(i).toString());
        }

        for (int i = 0; i < panels; i++) {
            Panel p = new Panel();
            TextField textField = new TextField();
            textField.setText(saleInfo[i]);
            p.add(textField);

            frame.add(p, i);
        }
    }
}
