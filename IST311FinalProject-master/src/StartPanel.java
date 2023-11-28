import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {
    private JButton startButton;
    private JPanel startPanel;
    private Interface myGUI;

    public StartPanel(Interface g) {
        myGUI = g;
        // add action Listener for Button Event
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartActionPerformed(e);
            }
        });
    }

    public JPanel getPanel() {
        return startPanel;
    }

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {
        myGUI.changePanel("start", "userManagerSelect");

    }
}