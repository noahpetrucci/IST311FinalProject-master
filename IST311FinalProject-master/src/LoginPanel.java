import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends  JPanel {
    private JPanel loginPanel;
    private JTextField userIDField;
    private JLabel userIDLabel;
    private JButton submitButton;
    private JButton backButton;
    private JPasswordField passwordField;

    private Interface myGUI;

    private Database myDatabase;

    public LoginPanel(Interface app)
    {
        myGUI = app;
        myDatabase = new Database();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.setUsername(userIDField.getText());
                if(UserManager.manager == true){
                    if(myDatabase.validateManager(userIDField.getText(), String.valueOf(passwordField.getPassword())) == true){
                    userIDField.setText("");
                    passwordField.setText("");
                    myGUI.changePanel("login", "managerMenu");}
                    else  {JOptionPane.showMessageDialog( null,
                           "Invalid Login", "Invalid User ID",
                            JOptionPane.ERROR_MESSAGE );
                        userIDField.setText("");
                        passwordField.setText("");}

                }

                if(UserManager.manager== false) {
                    if(myDatabase.validateUser(userIDField.getText(), String.valueOf(passwordField.getPassword())) == true){
                        userIDField.setText("");
                        passwordField.setText("");
                        myGUI.changePanel("login", "shopPanel");}
                    else  {JOptionPane.showMessageDialog( null,
                            "Invalid Login", "Invalid User ID",
                            JOptionPane.ERROR_MESSAGE );
                        userIDField.setText("");
                        passwordField.setText("");}

                }


            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.changePanel("login", "userManagerSelect");
            }
        });
    }

    public JPanel getPanel()
    {
        return loginPanel;
    }
}
