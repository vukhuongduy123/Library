package GUI;

import javax.swing.*;
import java.awt.*;

public class MainJFrame {
    private final JFrame jFrame;

    public MainJFrame() {
        jFrame = new JFrame();
        jFrame.setLayout(new BorderLayout());
        jFrame.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        jFrame.setTitle("Library");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void addComponent(Component component) {
        jFrame.add(component);
    }

    public void show() {
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
