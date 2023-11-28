
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.sql.SQLException;
import java.util.ArrayList;

public class Interface extends Manager{
    private StartPanel startForm;
    private LoginPanel loginForm;
    private newUserForm newUser;
    private managerMenu managerMenu;
    private shopPanel shopPanel;
    private UserManager UserManage;
    private bagPanel bagPanel;
    private confirmationPage confirmationPage;

    private  clothingInfoPanel clothingInfoPanel;
    private barChart barChart;
    private JFrame frame;
    private String xmlID;      // temporary storage for first name from xml
    private String xmlUsername;       //temporary storage for last name from xml
    private String xmlPassword;   //temporary storage for degree status from xml
    private static final int MAX_RECORDS = 3;
    private Manager currentManager;//store an instance of current Student which is a StudentRecord
    Database myDatabase = new Database();

  //public clothingInfoPanel clothingDataArray[] = new clothingInfoPanel[20];
    public Manager managerArray[] = new Manager[MAX_RECORDS];//store all student records
    private int nextManager = 0;         // location of next empty position in the array
    private int numManager = 0;         // number of input student records

    public static String centerAddress = "";
    public String rightAddress = "";
    public String leftAddress = "";

    public int[] clothingIndex = new int[19];
    private int currentClothingNumber = 0;
    public String clothingImage;
    public String currentItem = "null";
    public ArrayList<String> bag = new ArrayList<String>();
    public String userName = "null";
    public void storeData ()
    {
        // create table in the database
        myDatabase.createTable();
        // store each Student Record in the table
        for (int i = 0; i<numManager; i++)
        {
            myDatabase.storeRecord(
                    managerArray[i].getID(),
                    managerArray[i].getUserName(),
                    managerArray[i].getPassword());
        }
    }//end storeData

        //the method reads info from the input XML file, and then stores it in the studentArray[]
        public void readFile(String filename){
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setValidating(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            NodeList list = document.getElementsByTagName("Manager");

            //This for loop gathers all the student attributes, puts them in a StudentRecord object
            //then stores that student in the StudentArray
            for(int i = 0; i < list.getLength(); i++)
            {
                Element element = (Element)list.item(i);
               // xmlDegreeStatus = element.getAttribute("status");
                xmlID = getID(element);
                xmlUsername = getUserName(element);
                xmlPassword = getPassword(element);
                Manager man = new Manager(xmlID, xmlUsername, xmlPassword);

                // store student record in array
                managerArray[nextManager] = man;

                // increment number of student records and move to next position in studentArray
                nextManager++;
                numManager++;

            }//end for loop loading the studentArray[] with full student records

        }//end try block
        catch (ParserConfigurationException parserException)
        {
            parserException.printStackTrace();
        }//end catch block
        catch (SAXException saxException)
        {
            saxException.printStackTrace();
        }//end catch block
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }//end catch block

    }//end readFile()

    //initialize the application
    public void initialize() throws SQLException, ClassNotFoundException {
        frame = new JFrame("Archive Co.");
        startForm = new StartPanel(this);
        loginForm = new LoginPanel(this);
        UserManage = new UserManager(this);
        newUser = new newUserForm(this);
        managerMenu = new managerMenu(this);
        shopPanel = new shopPanel(this);
        bagPanel = new bagPanel(this);
        confirmationPage = new confirmationPage(this);
        clothingInfoPanel = new clothingInfoPanel(this);
        barChart = new barChart(this);

        frame.add(startForm.getPanel());
        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.pack();
        readFile("ManagerXML.xml");
        storeData();
        myDatabase.getQueryData();
    }

    public void changePanel(String oldPanel, String newPanel) {
        //remove panels
        if (oldPanel.equals("start"))
            frame.remove(startForm.getPanel());
        else if (oldPanel.equals("login"))
            frame.remove(loginForm.getPanel());
        else if (oldPanel.equals(("userManagerSelect")))
            frame.remove(UserManage.getPanel());
        else if (oldPanel.equals("newUserForm"))
            frame.remove(newUser.getPanel());
        else if (oldPanel.equals("managerMenu"))
            frame.remove(managerMenu.getPanel());
        else if (oldPanel.equals("shopPanel"))
            frame.remove(shopPanel.getPanel());
        else if (oldPanel.equals("bagPanel"))
            frame.remove(bagPanel.getPanel());
        else if (oldPanel.equals("confirmationPage"))
            frame.remove(confirmationPage.getPanel());
        else if (oldPanel.equals("clothingInfoPanel"))
            frame.remove(clothingInfoPanel.getPanel());
        else if (oldPanel.equals("barChart"))
            frame.remove((barChart.getPanel()));

        //add panels
        if (newPanel.equals("start"))
            frame.add(startForm.getPanel());
        else if (newPanel.equals("login"))
            frame.add(loginForm.getPanel());
        else if (newPanel.equals("userManagerSelect"))
            frame.add(UserManage.getPanel());
        else if (newPanel.equals("newUserForm"))
            frame.add(newUser.getPanel());
        else if (newPanel.equals("managerMenu"))
            frame.add(managerMenu.getPanel());
        else if (newPanel.equals("shopPanel"))
            frame.add(shopPanel.getPanel());
        else if (newPanel.equals("bagPanel")){
            bagPanel.processBagChange();
            frame.add(bagPanel.getPanel());
    }
        else if (newPanel.equals("confirmationPage")) {
            frame.add(confirmationPage.getPanel());
        }
        else if (newPanel.equals("clothingInfoPanel")){
            clothingInfoPanel.processClothingChange();
            frame.add(clothingInfoPanel.getPanel());
        }
        else if (newPanel.equals("barChart"))
            frame.add(barChart.getPanel());

        frame.validate();
        frame.repaint();
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        Interface myApp = new Interface();
        myApp.initialize();
    }
    public String getID(Element parent){
        NodeList child = parent.getElementsByTagName("Id");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();
    }//end getID
    public String getUserName(Element parent){
        NodeList child = parent.getElementsByTagName("username");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();
    }//end getFirstName
    public String getPassword(Element parent){
        NodeList child = parent.getElementsByTagName("password");
        Node childTextNode = child.item(0).getFirstChild();
        return childTextNode.getNodeValue();
    }//end getFirstName

    public void setClothingImage(String clothingImage){
        this.clothingImage = clothingImage;
    }
    public void setCurrentClothingNumber(int n){
        currentClothingNumber = n;
    }
    public int getCurrentClothingNumber(){
        return currentClothingNumber;
    }
    public void setSelectedItem(String n){
        currentItem = n;
        bag.add(currentItem);
        changePanel("clothingInfoPanel","shopPanel");
    }
    public void setSelectedItemInLoop(String n){
        currentItem = n;
    }
    public String getSelectedItem(){
        return currentItem;
    }
    public ArrayList<String> getBag(){
        return bag;
    }
    public void setUsername(String n){
        userName = n;
    }
    public String getUserName(){
        return userName;
    }
    public void clearArrayList() {
        bag.clear();
    }
}

