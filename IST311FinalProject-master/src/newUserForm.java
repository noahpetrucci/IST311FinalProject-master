import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newUserForm extends JPanel {
    private JPanel newUserForm;
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField usernameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JButton submitButton;
    private JButton backButton;
    private JTextField passwordField;
    private JLabel passwordLabel;
    private JLabel archiveCoLabel;
    private Interface myGUI;
    private Database myDatabase;
    public newUserForm(Interface g) {
        myGUI = g;
        myDatabase = new Database();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("newUserForm", "userManagerSelect");

            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String firstName = firstNameField.getText();
                String userName = usernameField.getText();
                String lastName = lastNameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String password = passwordField.getText();
                if (myDatabase.newUserInfo(email, firstName, userName, lastName, phoneNumber, password) == true)
                    myGUI.changePanel("newUserForm", "login");
                else {
                    JOptionPane.showMessageDialog(null,
                            "Invalid New User Info", "Invalid User Info",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public JPanel getPanel() {
        return newUserForm;
    }

}