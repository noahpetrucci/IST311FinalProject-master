import org.w3c.dom.Element;

public class Manager {
    private String ID;
    private String userName;
    private String password;

    // Default constructor for manager
    Manager() {
        ID = "0";
        userName = "Unknown";
        password = "Unknown";
    }

    // Manager constructor takes in ID, username, password
    Manager(String ID, String userName, String password) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
    }

    // Accessor methods
    public String getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}