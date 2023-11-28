
/*
 * Database.java
 *
 */

import java.sql.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

//Database Class
public class Database {
    private int customerNumber = 0;
    private int initialBalance = 0;
    private int clothingID = 0;
    private String stringbalance = "";
    private double doubleBalance = 0;

    public Database() {

    }

    //Declaration of variables
    private static String url = System.getProperty("user.dir");
    private static String filePath = "jdbc:ucanaccess://" + url.replace("\\", "/")
            + "/Clothing.accdb";

    //createTable() drops the current table and creates a new one
    public void createTable() {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            //this code may need to be commented out because an exception will be thrown
            //if this table doesn't exist in the database
            stmt.execute("DROP TABLE Manager");

            stmt.execute("CREATE TABLE Manager" +
                    "(ID varchar(255), username varchar(255)," +
                    " password varchar(255))");
            stmt.close();
            con.close();
        }
        // detect problems interacting with the database
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
    }//end createTable()

    //this method accepts the student data as input and stores it to the database
    public void storeRecord(String id, String username, String password) {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            //this Insert statement puts student info in the database
            stmt.executeUpdate("INSERT INTO Manager VALUES ('" + id + "','" + username + "','" + password + "')");
            stmt.close();
            con.close();
        }//end try
        catch (Exception e) {
            e.printStackTrace();
        }//end catch
    }//end storeRecord()

    public Manager[] getQueryData() {
        Manager managerArray[] = new Manager[3];
        int numManager = 0;
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Manager");
            while (rs.next()) {
                String rsID = rs.getString("ID");
                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");
                managerArray[numManager] = new Manager(rsID, rsUsername, rsPassword);
                numManager++;
            }
            stmt.close();
            con.close();
        }
        // detect problems interacting with the database
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // detect problems loading database driver
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null,
                    classNotFound.getMessage(), "Driver Not Found",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } finally {
            return managerArray;
        }


    }

    //this method is invoked when a new user submits their info.
    public boolean newUserInfo(String email, String first, String user, String last, String phone, String password) {
        boolean fieldNull = false;
        if (user.length() == 0 && password.length() == 0) {
            return fieldNull;
        }
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(userID) AS userID FROM Customer ");
            while (rs.next()) {
                String maxID = rs.getString("userID");
                customerNumber = Integer.parseInt(maxID);
                customerNumber++;
            }
            stmt.executeUpdate("INSERT INTO Customer(UserID, username, password, email, first_name, last_name,phone_number,balance) VALUES (" + customerNumber + ",'" + user + "','" + password + "','" + email + "','" + first + "','" + last + "','" + phone + "'," + initialBalance + ")");
            fieldNull = true;
            stmt.close();
            con.close();
        }
        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
        return fieldNull;
    }

    //this method validates a user that is attempting to log in
    public boolean validateUser(String user, String pass) {
        boolean foundMatchUser = false;
        if (user.length() == 0 && pass.length() == 0) {
            return foundMatchUser;
        }
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Customer");
            while (rs.next()) {
                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");
                if (rsUsername.equals(user) && rsPassword.equals(pass))
                    foundMatchUser = true;
            }
            stmt.close();
            con.close();
        }
        // detect problems interacting with the database
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        // detect problems loading database driver
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null,
                    classNotFound.getMessage(), "Driver Not Found",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } finally {
            return foundMatchUser;
        }
    }

    //this method validates a manger that is attempting to log in
    public boolean validateManager(String user, String pass) {
        boolean foundMatch = false;
        if (user.length() == 0 && pass.length() == 0) {
            return foundMatch;
        }
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");


            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from Manager");

            while (rs.next()) {

                String rsUsername = rs.getString("username");
                String rsPassword = rs.getString("password");

                if (rsUsername.equals(user) && rsPassword.equals(pass))
                    foundMatch = true;

            }

            stmt.close();

            con.close();

        }
        // detect problems interacting with the database
        catch (SQLException sqlException) {
            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }

        // detect problems loading database driver
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null,
                    classNotFound.getMessage(), "Driver Not Found",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        } finally {
            return foundMatch;
        }

    }

    //this method updates clothing in the database
    public void updateClothing(String itemID, String itemName, String price, String quantity, String description, String imageAddress, String size, String pImageAddress) {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Clothing WHERE ClothingID = " + itemID + "");
            boolean flag = false;
            String rsClothingID = null;
            String rsItemName = null;
            String rsPrice = null;
            String rsQuantity = null;
            String rsCategory = null;
            String rsDescription = null;
            String rsImageAddress = null;
            String rsSize = null;
            String rsPimageAddress = null;
            while (rs.next()) {
                rsClothingID = rs.getString("ClothingID");
                rsItemName = rs.getString("item_name");
                rsPrice = rs.getString("price_of_item");
                rsQuantity = rs.getString("quantity");
                rsCategory = rs.getString("category");
                rsDescription = rs.getString("description");
                rsImageAddress = rs.getString("Image_Address");
                rsSize = rs.getString("Size");
                rsPimageAddress = rs.getString("p_image_address");
            }
            if (itemName.length() > 1) {
                rsItemName = itemName;
            }
            if (price.length() > 1) {
                rsPrice = price;
            }
            if (quantity.length() > 1) {
                rsQuantity = quantity;
            }
            if (description.length() > 1) {
                rsDescription = description;
            }
            if (imageAddress.length() > 1) {
                rsImageAddress = imageAddress;
            }
            if (size.length() > 1) {
                rsSize = size;
            }
            if (pImageAddress.length() > 1) {
                rsPimageAddress = pImageAddress;
            }
            flag = true;
            managerMenu.itemUpdated(flag);
            stmt.executeUpdate("UPDATE Clothing SET ClothingID = '" + rsClothingID + "', item_name = '" + rsItemName + "',price_of_item = '" + rsPrice + "',quantity = '" + rsQuantity + "', category = '" + rsCategory + "', description = '" + rsDescription + "',Image_Address=  '" + rsImageAddress + "', Size =  '" + rsSize + "',p_image_address = '" + rsPimageAddress + "'  WHERE ClothingID =  '" + rsClothingID + "'");
            stmt.close();
            con.close();
        }
        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method deleted a clothing record in the database
    public void deleteRecord(String itemID) {

        try {
            // load database driver class
            boolean flag = false;
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");


            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();

            //stmt.executeQuery("DELETE * FROM Customer WHERE userName = 'user1'");
            stmt.executeUpdate("DELETE * FROM Clothing WHERE ClothingID = " + itemID + "");
            flag = true;
            managerMenu.itemDeleted(flag);


            stmt.close();

            con.close();

        }
        // detect problems interacting with the database
        catch (SQLException sqlException) {

            JOptionPane.showMessageDialog(null,
                    sqlException.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }

        // detect problems loading database driver
        catch (ClassNotFoundException classNotFound) {
            JOptionPane.showMessageDialog(null,
                    classNotFound.getMessage(), "Driver Not Found",
                    JOptionPane.ERROR_MESSAGE);

            System.exit(1);
        }

    }

    //This method creates a new clothing record in the database
    public void newClothingInfo(String itemName, String price, String category, String quantity, String description, String imageAddress, String pImageAddress, String size) {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT MAX(ClothingID) AS ClothingID FROM Clothing");
            while (rs.next()) {
                String maxClothingID = rs.getString("ClothingID");
                clothingID = Integer.parseInt(maxClothingID);
                clothingID++;
            }
            stmt.executeUpdate("INSERT INTO Clothing(clothingID, item_name, price_of_item, quantity, category, description,image_address,Size,p_image_address ) VALUES (" + clothingID + ",'" + itemName + "','" + price + "','" + quantity + "','" + category + "','" + description + "','" + imageAddress + "','" + size + "','" + pImageAddress + "')");
            stmt.close();
            con.close();
        }

        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method updates a user's balance when they purchase an item
    public void updateBalance(String username, double total) {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT balance FROM Customer WHERE username = '" + username + "'");
            while (rs.next()) {
                stringbalance = rs.getString("balance");
                doubleBalance = Double.parseDouble(stringbalance);
            }
            total = total + doubleBalance;
            stmt.executeUpdate("UPDATE Customer SET balance = '" + total + "' WHERE username =  '" + username + "'");
            stmt.close();
            con.close();
        }
        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method takes in a string representing the clothingID and updates the quantity if the item is added to cart
    public void updateItem(String clothingID) {
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            // connect to database
            Connection con = DriverManager.getConnection(filePath);

            Statement stmt = con.createStatement();
            String quantity = "";


            ResultSet rs = stmt.executeQuery("SELECT quantity from Clothing WHERE ClothingID = '" + clothingID + "'");
            while (rs.next()) {
                quantity = rs.getString("quantity");
                stmt.executeUpdate("UPDATE Clothing SET quantity = '" + (Integer.parseInt(quantity) - 1) + "' WHERE ClothingID =  '" + clothingID + "'");
            }

            stmt.close();
            con.close();

        }

        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method clears the user balance
    public void clearUserBalance(String userID) {
        String balance = "0";
        try {
            // load database driver class
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            // connect to database
            Connection con = DriverManager.getConnection(filePath);
            Statement stmt = con.createStatement();


            stmt.executeUpdate("UPDATE Customer SET balance = '" + balance + "'WHERE UserID = '" + userID + "'");

            stmt.close();
            con.close();

        }

        // detect problems loading database driver
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}// end Database class