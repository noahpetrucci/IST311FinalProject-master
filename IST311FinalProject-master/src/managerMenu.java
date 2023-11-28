import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerMenu extends JPanel {
    private JPanel managerMenu;
    private JButton deleteClothingButton;
    private JButton addClothingButton;
    private JButton updateClothingButton;
    public JTextField clothingIDTextField;
    private JLabel clothingIDLabel;
    private JTextField itemNameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField descriptionField;
    private JTextField imageAdressTextField;
    private JLabel itemNameLabel;
    private JLabel priceLabel;
    private JLabel QuantitiyLabel;
    private JLabel descriptionLabel;
    private JLabel imageLabel;
    private JButton backButton;
    private JButton undoSelectionButton;
    private JButton submitButton;
    private JTextField sizeField;
    private JLabel sizeLabel;
    private JTextField categoryField;
    private JLabel categoryLabel;
    private JTextField PimageAddressLabel;
    private JLabel PImageAddress;
    private JButton clearUserBalanceButton;
    private JTextField userIDField;
    private JLabel userIDLabel;
    private JButton inventoryChartButton;
    private Interface myGUI;
    private boolean update = false;
    private boolean delete = false;
    private boolean add = false;
    private boolean clearBalance = false;
    private static boolean itemDeleted;
    private static boolean itemUpdated;
    private Database myDatabase;

    public managerMenu(Interface g) {
        myGUI = g;
        myDatabase = new Database();
        clothingIDTextField.setVisible(false);
        clothingIDLabel.setVisible(false);
        itemNameField.setVisible(false);
        itemNameLabel.setVisible(false);
        priceField.setVisible(false);
        priceLabel.setVisible(false);
        quantityField.setVisible(false);
        QuantitiyLabel.setVisible(false);
        descriptionLabel.setVisible(false);
        descriptionField.setVisible(false);
        imageLabel.setVisible(false);
        categoryField.setVisible(false);
        categoryLabel.setVisible(false);
        imageAdressTextField.setVisible(false);
        submitButton.setVisible(false);
        sizeField.setVisible(false);
        sizeLabel.setVisible(false);
        PImageAddress.setVisible(false);
        PimageAddressLabel.setVisible(false);
        submitButton.setVisible(false);
        userIDLabel.setVisible(false);
        userIDField.setVisible(false);

        deleteClothingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clothingIDTextField.setVisible(true);
                clothingIDLabel.setVisible(true);
                addClothingButton.setVisible(false);
                updateClothingButton.setVisible(false);
                submitButton.setVisible(true);
                delete = true;
                add = false;
                update = false;
                clearBalance = false;
                clearUserBalanceButton.setVisible(false);
                inventoryChartButton.setVisible(false);
            }
        });
        updateClothingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clothingIDTextField.setVisible(true);
                clothingIDLabel.setVisible(true);
                itemNameField.setVisible(true);
                itemNameLabel.setVisible(true);
                priceField.setVisible(true);
                priceLabel.setVisible(true);
                quantityField.setVisible(true);
                QuantitiyLabel.setVisible(true);
                descriptionLabel.setVisible(true);
                descriptionField.setVisible(true);
                imageLabel.setVisible(true);
                imageAdressTextField.setVisible(true);
                addClothingButton.setVisible(false);
                deleteClothingButton.setVisible(false);
                PImageAddress.setVisible(true);
                PimageAddressLabel.setVisible(true);
                submitButton.setVisible(true);
                sizeField.setVisible(true);
                sizeLabel.setVisible(true);
                update = true;
                delete = false;
                add = false;
                clearBalance = false;
                userIDLabel.setVisible(false);
                userIDField.setVisible(false);
                inventoryChartButton.setVisible(false);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("managerMenu", "userManagerSelect");
            }
        });
        undoSelectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clothingIDTextField.setVisible(false);
                clothingIDLabel.setVisible(false);
                itemNameField.setVisible(false);
                itemNameLabel.setVisible(false);
                priceField.setVisible(false);
                priceLabel.setVisible(false);
                quantityField.setVisible(false);
                QuantitiyLabel.setVisible(false);
                descriptionLabel.setVisible(false);
                descriptionField.setVisible(false);
                imageLabel.setVisible(false);
                imageAdressTextField.setVisible(false);
                sizeLabel.setVisible(false);
                sizeField.setVisible(false);
                categoryLabel.setVisible(false);
                categoryField.setVisible(false);
                addClothingButton.setVisible(true);
                updateClothingButton.setVisible(true);
                deleteClothingButton.setVisible(true);
                submitButton.setVisible(false);
                PimageAddressLabel.setVisible(false);
                PImageAddress.setVisible(false);
                submitButton.setVisible(false);
                add = false;
                update = false;
                delete = false;
                clothingIDTextField.setText("");
                itemNameField.setText("");
                userIDLabel.setVisible(false);
                userIDField.setVisible(false);
                clearBalance = false;
                inventoryChartButton.setVisible(true);
                clearUserBalanceButton.setVisible(true);
            }
        });
        addClothingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemNameField.setVisible(true);
                itemNameLabel.setVisible(true);
                priceField.setVisible(true);
                priceLabel.setVisible(true);
                quantityField.setVisible(true);
                QuantitiyLabel.setVisible(true);
                descriptionLabel.setVisible(true);
                descriptionField.setVisible(true);
                imageLabel.setVisible(true);
                imageAdressTextField.setVisible(true);
                submitButton.setVisible(true);
                sizeField.setVisible(true);
                sizeLabel.setVisible(true);
                updateClothingButton.setVisible(false);
                deleteClothingButton.setVisible(false);
                PimageAddressLabel.setVisible(true);
                PImageAddress.setVisible(true);
                submitButton.setVisible(true);
                categoryField.setVisible(true);
                categoryLabel.setVisible(true);
                add = true;
                update = false;
                delete = false;
                inventoryChartButton.setVisible(false);
                clearUserBalanceButton.setVisible(false);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (delete == true) {
                    myDatabase.deleteRecord(clothingIDTextField.getText());
                    if (itemDeleted == true) {
                        clothingIDTextField.setText("Clothing ID " + clothingIDTextField.getText() + " is deleted");
                    }
                }
                if (update == true) {
                    myDatabase.updateClothing(clothingIDTextField.getText(), itemNameField.getText(), priceField.getText(), quantityField.getText(), descriptionField.getText(), imageAdressTextField.getText(), sizeField.getText(), PImageAddress.getText());
                    if (itemUpdated == true) {
                        clothingIDTextField.setText("Clothing ID " + clothingIDTextField.getText() + " is updated");
                        itemNameField.setText("");
                        priceField.setText("");
                        quantityField.setText("");
                        descriptionField.setText("");
                        imageAdressTextField.setText("");
                        sizeField.setText("");
                        PimageAddressLabel.setText("");
                    }
                }
                if (add == true) {
                    myDatabase.newClothingInfo(itemNameField.getText(), priceField.getText(), categoryField.getText(), quantityField.getText(), descriptionField.getText(), imageAdressTextField.getText(), PimageAddressLabel.getText(), sizeField.getText());
                    itemNameField.setText("Clothing Added");
                    priceField.setText("");
                    quantityField.setText("");
                    descriptionField.setText("");
                    imageAdressTextField.setText("");
                    sizeField.setText("");
                    PimageAddressLabel.setText("");
                    categoryField.setText("");
                }
                if ((clearBalance == true)) {
                    myDatabase.clearUserBalance(userIDField.getText());
                    userIDField.setText("Balance Cleared");
                }

            }
        });

        clearUserBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBalance = true;
                add = false;
                update = false;
                delete = false;
                updateClothingButton.setVisible(false);
                deleteClothingButton.setVisible(false);
                addClothingButton.setVisible(false);
                userIDField.setVisible(true);
                userIDLabel.setVisible(true);
                submitButton.setVisible(true);
                inventoryChartButton.setVisible(false);
            }
        });
        inventoryChartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("managerMenu", "barChart");
            }
        });
    }

    public static void itemDeleted(boolean flag) {
        itemDeleted = flag;
    }

    public static void itemUpdated(boolean flag) {
        itemUpdated = flag;
    }

    public JPanel getPanel() {
        return managerMenu;
    }

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {
        myGUI.changePanel("managerMenu", "newUserForm");
    }
}