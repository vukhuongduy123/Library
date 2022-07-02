package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TopPanelButtons {
    private final List<JButton> buttons;
    private final JPanel panel;

    public TopPanelButtons() {
        buttons = new ArrayList<>();
        JButton button = new JButton("Update");
        buttons.add(button);
        button = new JButton("Delete");
        buttons.add(button);
        button = new JButton("Info");
        buttons.add(button);

        panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        panel.setLayout(flowLayout);
        buttons.forEach(panel::add);
    }

    public JPanel getPanel() {
        return panel;
    }
}
