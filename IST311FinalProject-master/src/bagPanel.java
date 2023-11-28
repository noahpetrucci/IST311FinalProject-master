import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class bagPanel extends JPanel {
    private JButton checkout;
    private JButton back;
    private JPanel bagPanel;
    private JTextArea Name;
    private JTextArea Price;
    private JTextArea Total;
    private JButton clearButton;
    static animation Animation = new animation();
    private Database myDatabase;
    private String clothingID;
    private String itemName;
    private String price;
    private Interface myGUI;
    private String userName;
    double total = 0;
    private static String url = System.getProperty("user.dir");
    private static String filePath = "jdbc:ucanaccess://" + url.replace("\\", "/")
            + "/Clothing.accdb";
    public bagPanel(Interface g) {
        myGUI = g;
    }
    public void processBagChange() {
        userName = myGUI.getUserName();
        try {
            myDatabase = new Database();

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();
            ArrayList<String> bag = myGUI.getBag();
            if (((bag.size() == 0))) {
                Name.setText("Items:");
                Price.setText("Price: $0.00");
                Total.setText("Total: $0.00");
            } else if (((bag.size() == 1))) {

                ResultSet rs = stmt.executeQuery("SELECT * from Clothing WHERE ClothingID = '" + myGUI.getSelectedItem() + "'");

                while (rs.next()) {

                    clothingID = rs.getString("clothingID");
                    itemName = rs.getString("item_name");
                    price = rs.getString("price_of_item");
                }
                Name.setSize(20, 20);
                Name.setText(itemName);
                Price.setText("Price: \n" + "$" + price);
                Total.setText("Total: $" + price);
                total = Double.parseDouble(price);
            }
            // If you have more than one item in the bag
            else {

                String priceFieldString = "Price: $";
                String totalFieldString = "Total: $";
                String nameFieldString = "";

                // Go through the bag and get each items data
                for (int i = 0; i < (bag.size()); i++) {
                    String currentItem = bag.get(i);
                    myGUI.setSelectedItemInLoop(currentItem);
                    ResultSet rs = stmt.executeQuery("SELECT * from Clothing WHERE ClothingID = '" + myGUI.getSelectedItem() + "'");
                    while (rs.next()) {

                        clothingID = rs.getString("clothingID");
                        itemName = rs.getString("item_name");
                        price = rs.getString("price_of_item");

                    }
                    total += Double.parseDouble(price);
                    priceFieldString += "$" + price + " \n";
                    nameFieldString += itemName + " \n";
                }
                totalFieldString += "" + String.format("%.2f", total);
                Price.setText(priceFieldString);
                Total.setText(totalFieldString);
                Name.setText(nameFieldString);
            }
            checkout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearBag();

                    myDatabase.updateBalance(myGUI.getUserName(), total);
                    //myDatabase.updateQuantity(bag);

                    myGUI.changePanel("bagPanel", "confirmationPage");

                    Animation.setSize(400, 400);
                    Animation.setLocation(200, 600);
                    Animation.show();
                    Animation.go();
                }
            });

            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myGUI.changePanel("bagPanel", "shopPanel");
                }
            });
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addQuantityBecauseBagClear();
                    clearBag();
                }
            });
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }
    public static void stopAnimation() {
        Animation.dispose();
    }
    public void clearBag() {

        myGUI.clearArrayList();
        Name.setText("");
        Price.setText("");
        Total.setText("");
    }
    public void addQuantityBecauseBagClear() {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();
            String quantity = "";

            for (int i = 0; i < (myGUI.getBag().size()); i++) {
                ResultSet rs = stmt.executeQuery("SELECT quantity from Clothing WHERE ClothingID = '" + clothingID + "'");
                while (rs.next()) {
                    quantity = rs.getString("quantity");
                    stmt.executeUpdate("UPDATE Clothing SET quantity = '" + (Integer.parseInt(quantity) + 1) + "' WHERE ClothingID =  '" + clothingID + "'");
                }
            }
            stmt.close();
            con.close();
        }
        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return bagPanel;
    }
}