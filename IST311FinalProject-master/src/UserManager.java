import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManager extends JPanel {
    private JButton userButton;
    private JPanel userManagerSelect;
    private JButton managerButton;
    private JButton newUserButton;
    private JButton backButton;
    private Interface myGUI;
    public static boolean manager = false;
    public static boolean newUser = false;

    public UserManager(Interface g) {
        myGUI = g;

        // add action Listener for Button Event
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager = false;
                newUser = false;
                myGUI.changePanel("userManagerSelect", "login");
            }

        });

        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager = true;
                newUser = false;
                myGUI.changePanel("userManagerSelect", "login");
            }

        });
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUser = true;
                myGUI.changePanel("userManagerSelect", "newUserForm");
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myGUI.changePanel("userManagerSelect", "start");
            }
        });
    }

    public JPanel getPanel() {
        return userManagerSelect;
    }
}