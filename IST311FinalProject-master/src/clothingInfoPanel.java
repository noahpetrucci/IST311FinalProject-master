import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.*;
public class clothingInfoPanel extends  JPanel{
    private JButton addToCart;
    private JButton back;
    private JTextArea Description;
    private JPanel clothingInfoPanel;
    private JLabel ClothingLabel;
    private JLabel ProportionLabel;
    private JTextArea sizeTextArea;
    private JTextArea nameTextArea;
    private JTextArea priceTextArea;
    private JTextArea quantityTextArea;
    private Interface myGUI;
    private Database myDatabase;
    private String clothingID;
    private String itemName;
    private String price;
    private String quantity;
    private String category;
    private String sDescription;
    private String cImage;
    private String size;
    private String pImage;
    private static String url = System.getProperty("user.dir");
    private static String filePath = "jdbc:ucanaccess://" + url.replace("\\", "/")
            + "/Clothing.accdb";
    // This method loads the clothingInfoPanel
   public clothingInfoPanel(Interface g) {
       myGUI = g;
       // Adds listener for addToCart Button
       addToCart.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // If the item quantity isn't 0, send the itemID out to the setSelectedItem method
               if (Integer.parseInt(quantity) > 0) {
                   myGUI.setSelectedItem(clothingID);
                   myDatabase.updateItem(clothingID);
               }
               else {
                   // If the item quantity is o or below, throw an out of stock message
                   JOptionPane.showMessageDialog( null,
                           "Item out of stock!", "Item out of stock!",
                           JOptionPane.ERROR_MESSAGE );
               }
           }
       });
   }
   // This method is called when a clothing item is selected
   public void processClothingChange() {
       try {
           myDatabase = new Database();

           Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

           Connection con = DriverManager.getConnection(filePath);

           Statement stmt = con.createStatement();

           ResultSet rs = stmt.executeQuery("SELECT * from Clothing WHERE ClothingID = '" + myGUI.getCurrentClothingNumber() + "'");

           while (rs.next()) {
               clothingID = rs.getString("clothingID");
               itemName = rs.getString("item_name");
               price = rs.getString("price_of_item");
               quantity = rs.getString("quantity");
               category = rs.getString("category");
               sDescription = rs.getString("description");
               cImage = rs.getString("Image_Address");
               size = rs.getString("Size");
               pImage = rs.getString("p_image_address");
           }
           nameTextArea.setText("Name: " + itemName);
           Description.setText("Description: " + sDescription);
           priceTextArea.setText("Price: $" + price);
           sizeTextArea.setText("Size: " + size);
           quantityTextArea.setText("Quantity: "+ quantity);

           ImageIcon center = (new ImageIcon(cImage));

           Image image = center.getImage(); // transform it
           Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
           center = new ImageIcon(newimg);  // transform it back

           ClothingLabel.setIcon(center);

               ImageIcon p = (new ImageIcon(pImage));

               Image proImage = p.getImage(); // transform it
               Image img = proImage.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
               p = new ImageIcon(img);  // transform it back

               ProportionLabel.setIcon(p);

           back.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   myGUI.changePanel("clothingInfoPanel", "shopPanel");
               }
           });
       }
       catch (SQLException sqlException) {
           JOptionPane.showMessageDialog(null,
                   sqlException.getMessage(), "Database Error",
                   JOptionPane.ERROR_MESSAGE);
           System.exit(1);
       }//end catch block

// detect problems loading database driver
       catch (ClassNotFoundException classNotFound) {
           JOptionPane.showMessageDialog(null,
                   classNotFound.getMessage(), "Driver Not Found",
                   JOptionPane.ERROR_MESSAGE);

           System.exit(1);
       }//end catch block
   }
    public JPanel getPanel()
    {
        return clothingInfoPanel;
    }
}