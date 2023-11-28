import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class confirmationPage extends  JPanel {
    private JButton backToShopping;
    private JButton closeApp;
    private JPanel confirmationPage;

    private Interface myGUI;


    public confirmationPage(Interface g){
        myGUI = g;


        backToShopping.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myGUI.clearArrayList();
                bagPanel.stopAnimation();

                myGUI.changePanel("confirmationPage","shopPanel");
            }
        });
        closeApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                myGUI.clearArrayList();

                System.exit(i);
            }
        });
    }
    public JPanel getPanel() {return confirmationPage;}
}
