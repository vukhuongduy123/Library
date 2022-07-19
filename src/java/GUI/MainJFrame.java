package GUI;

import javax.swing.*;
import java.awt.*;

public class MainJFrame {
    private final JFrame frame;
    private static final MainJFrame mainJFrame;

    static {
        mainJFrame = new MainJFrame();
    }

    private MainJFrame() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.getRootPane().setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
        frame.setTitle("Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public void addComponent(Component component) {
        frame.add(component);
    }

    public void show() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static MainJFrame getInstance() {
        return mainJFrame;
    }
}
