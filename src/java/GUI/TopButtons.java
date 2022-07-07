package GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TopButtons {
    public enum ButtonsName {
        UPDATE, DELETE, INFO, OPEN, OPEN_LOCAL
    }
    private final List<JButton> buttons;

    private JButton createButton(String name, boolean enabled) {
        JButton button = new JButton(name);
        button.setName(name);
        button.setEnabled(enabled);
        return button;
    }

    public TopButtons() {
        buttons = new ArrayList<>();
        buttons.add(createButton(ButtonsName.UPDATE.toString(), true));
        buttons.add(createButton(ButtonsName.DELETE.toString(), false));
        buttons.add(createButton(ButtonsName.INFO.toString(), false));
        buttons.add(createButton(ButtonsName.OPEN.toString(), false));
        buttons.add(createButton(ButtonsName.OPEN_LOCAL.toString(), true));
    }

    public List<JButton> getButtons() {
        return buttons;
    }
}
