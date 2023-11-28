import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class shopPanel extends JPanel {
    private JPanel shopPanel;
    private JButton backButton;
    private JButton checkoutButton;
    private JComboBox Categories;
    private JButton rightPicture;
    private JButton centerPicture;
    private JButton leftPicture;
    private int currentCategory;
    private Interface myGUI;
    public String centerClothingImage;
    public String leftClothingImage;
    public String rightClothingImage;
    private Database myDatabase;
    public String IDLocation = "123";

    public shopPanel(Interface g) {
        myGUI = g;
        myDatabase = new Database();
        ImageIcon center = (new ImageIcon("images/Fragrances/WHITE/ARMAF_CLUB_DE_NUIT.png"));

        Image image = center.getImage(); // transform it
        Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        center = new ImageIcon(newimg);  // transform it back

        centerPicture.setIcon(center);

        ImageIcon right = (new ImageIcon("images/Fragrances/WHITE/L_EAU_D_ISSEY_ISSEYMIYAKE.png"));

        Image image1 = right.getImage(); // transform it
        Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        right = new ImageIcon(newimg1);  // transform it back

        rightPicture.setIcon(right);

        ImageIcon left = (new ImageIcon("images/Fragrances/WHITE/MARGIELA_JAZZCLUB.png"));

        Image image2 = left.getImage(); // transform it
        Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        left = new ImageIcon(newimg2);  // transform it back

        leftPicture.setIcon(left);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("shopPanel", "userManagerSelect");
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myGUI.getSelectedItem().equals("null")) {
                    JOptionPane.showMessageDialog(null,
                            "No item selected", "No item selected",
                            JOptionPane.ERROR_MESSAGE);
                } else
                    myGUI.changePanel("shopPanel", "bagPanel");
            }
        });
        Categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCategory = Categories.getSelectedIndex();
                //sets the product images based n the drop box
                if (currentCategory == 0) {
                    IDLocation = "123";
                    ImageIcon center = (new ImageIcon("images/Fragrances/WHITE/ARMAF_CLUB_DE_NUIT.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/Fragrances/WHITE/L_EAU_D_ISSEY_ISSEYMIYAKE.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/Fragrances/WHITE/MARGIELA_JAZZCLUB.png"));


                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);

                } else if (currentCategory == 1) {
                    ImageIcon center = (new ImageIcon("images/Hat/WHITE/BALENCIAGA.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/Hat/WHITE/BEANIE.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/Hat/WHITE/VUJA_DE.png"));

                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);
                } else if (currentCategory == 2) {
                    ImageIcon center = (new ImageIcon("images/Pants/WHITE/CHROME_HEARTS_DENIM.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/Pants/WHITE/HELMUT_LANG_1996.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/Pants/WHITE/NUMBER_NINE.png"));

                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);
                } else if (currentCategory == 3) {
                    ImageIcon center = (new ImageIcon("images/Shirt/WHITE/DEPT_LA_GALLERY.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/Shirt/WHITE/RAF_SIMMONS_2002.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/Shirt/WHITE/UNDERCOVER_2006.png"));

                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);

                } else if (currentCategory == 4) {
                    ImageIcon center = (new ImageIcon("images/BOTTEGA_TIRE-removebg-preview.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/MARGIELA_GAT-removebg-preview.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/RICK_OWENS.png"));

                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);
                } else if (currentCategory == 5) {
                    ImageIcon center = (new ImageIcon("images/Top/WHITE/CDG_THERMAL.png"));

                    Image image = center.getImage(); // transform it
                    Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    center = new ImageIcon(newimg);  // transform it back

                    centerPicture.setIcon(center);

                    ImageIcon right = (new ImageIcon("images/Top/WHITE/MARGIELA_SWEATER.png"));

                    Image image1 = right.getImage(); // transform it
                    Image newimg1 = image1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    right = new ImageIcon(newimg1);  // transform it back

                    rightPicture.setIcon(right);

                    ImageIcon left = (new ImageIcon("images/Top/WHITE/SEASON6_YEEZY_SWEATSHIRT.png"));

                    Image image2 = left.getImage(); // transform it
                    Image newimg2 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    left = new ImageIcon(newimg2);  // transform it back

                    leftPicture.setIcon(left);
                }
            }

        });
        leftPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCategory == 0) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(2);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 1) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(6);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 2) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(9);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 3) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(12);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 4) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(15);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 5) {
                    g.setClothingImage(leftClothingImage);
                    myGUI.setCurrentClothingNumber(17);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                }
            }

        });
        centerPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCategory == 0) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(1);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 1) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(4);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 2) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(7);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 3) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(10);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 4) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(13);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 5) {
                    g.setClothingImage(centerClothingImage);
                    myGUI.setCurrentClothingNumber(16);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                }
            }
        });
        rightPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCategory == 0) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(3);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 1) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(5);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 2) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(8);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 3) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(11);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 4) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(14);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                } else if (currentCategory == 5) {
                    g.setClothingImage(rightClothingImage);
                    myGUI.setCurrentClothingNumber(18);
                    myGUI.changePanel("shopPanel", "clothingInfoPanel");
                }
            }
        });
    }

    public JPanel getPanel() {
        return shopPanel;
    }

    private void createUIComponents() {
        Categories = new JComboBox();
        Categories.addItem("Fragrances");
        Categories.addItem("Hats");
        Categories.addItem("Pants");
        Categories.addItem("Shirts");
        Categories.addItem("Shoes");
        Categories.addItem("Tops");

    }
}