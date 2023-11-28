import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class barChart {
    private JPanel barChart;
    public JSlider pantsSlider;
    private JSlider slider2;
    private JSlider hatSlider;
    private JSlider fagranceSlider;
    private JSlider topSlider;
    private JSlider shoesSlider;
    private JTextField Shirts;
    private JTextField Pants;
    private JTextField Hats;
    private JTextField Fragrance;
    private JTextField Tops;
    private JTextField Shoes;
    private JButton backButton;
    private Interface myGUI;
    public static int n = 0;
    private Database myDatabase;
    private static String url = System.getProperty("user.dir");
    private static String filePath = "jdbc:ucanaccess://" + url.replace("\\", "/")
            + "/Clothing.accdb";

// gather all the information on the quantity in the database and display that information in the form of a JSlider
    public barChart(Interface g) throws ClassNotFoundException, SQLException {
        myGUI = g;
        myDatabase = new Database();

        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

        Connection con = DriverManager.getConnection(filePath);

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Fragrances'");

        int totalFragrances = 0;
        String currentQuantity = "";

        while (rs.next()) {
            currentQuantity = rs.getString("quantity");
            totalFragrances += Integer.parseInt(currentQuantity);

        }
        ResultSet rsHat = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Hats'");

        int totalHats = 0;
        String hatCurrentQuantity = "";

        while (rsHat.next()) {
            hatCurrentQuantity = rsHat.getString("quantity");
            totalHats += Integer.parseInt(hatCurrentQuantity);
        }

        ResultSet rsPants = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Pants'");

        int totalPants = 0;
        String pantsCurrentQuantity = "";

        while (rsPants.next()) {
            pantsCurrentQuantity = rsPants.getString("quantity");
            totalPants += Integer.parseInt(pantsCurrentQuantity);
        }

        ResultSet rsShirt = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Shirts'");

        int totalShirt = 0;
        String shirtCurrentQuantity = "";

        while (rsShirt.next()) {
            shirtCurrentQuantity = rsShirt.getString("quantity");
            totalShirt += Integer.parseInt(shirtCurrentQuantity);
        }

        ResultSet rsTops = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Tops'");

        int totalTops = 0;
        String topsCurrentQuantity = "";

        while (rsTops.next()) {
            topsCurrentQuantity = rsTops.getString("quantity");
            totalTops += Integer.parseInt(topsCurrentQuantity);
        }

        ResultSet rsShoes = stmt.executeQuery("SELECT quantity from Clothing WHERE category = 'Shoes'");

        int totalShoes = 0;
        String shoesCurrentQuantity = "";

        while (rsShoes.next()) {
            shoesCurrentQuantity = rsShoes.getString("quantity");
            totalShoes += Integer.parseInt(shoesCurrentQuantity);
        }

        int n = 15;
        fagranceSlider.setValue(totalFragrances);
        hatSlider.setValue(totalHats);
        pantsSlider.setValue(totalPants);
        slider2.setValue(totalShirt);
        topSlider.setValue(totalTops);
        shoesSlider.setValue(totalShoes);

        pantsSlider.setEnabled(false);
        pantsSlider.setValue(n);
        slider2.setEnabled(false);
        hatSlider.setEnabled(false);
        fagranceSlider.setEnabled(false);
        topSlider.setEnabled(false);
        shoesSlider.setEnabled(false);
        Shirts.setEnabled(false);
        Pants.setEnabled(false);
        Hats.setEnabled(false);
        Fragrance.setEnabled(false);
        Tops.setEnabled(false);
        Shoes.setEnabled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("barChart", "managerMenu");
            }
        });
    }

    public static void topsTotal(int total) {
    }

    public JPanel getPanel() {
        return barChart;
    }
}