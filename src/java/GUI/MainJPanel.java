package GUI;

import javax.swing.*;
import java.awt.*;

public class MainJPanel {
    private JPanel panel;
    public MainJPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        //panel.setSize(400, 400);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void addComponent(JComponent c, GridBagConstraints gbc) {
        panel.add(c, gbc);
    }
}
