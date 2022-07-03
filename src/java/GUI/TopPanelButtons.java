package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TopPanelButtons {
    public enum ButtonsName {
        UPDATE, DELETE, INFO, OPEN;
    }
    private final List<JButton> buttons;
    private final JPanel panel;

    private JButton createButton(String name, boolean enabled) {
        JButton button = new JButton(name);
        button.setName(name);
        button.setEnabled(enabled);
        return button;
    }

    public TopPanelButtons() {
        buttons = new ArrayList<>();
        buttons.add(createButton(ButtonsName.UPDATE.toString(), true));
        buttons.add(createButton(ButtonsName.DELETE.toString(), false));
        buttons.add(createButton(ButtonsName.INFO.toString(), false));
        buttons.add(createButton(ButtonsName.OPEN.toString(), false));
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

    public List<JButton> getButtons() {
        return buttons;
    }
}
