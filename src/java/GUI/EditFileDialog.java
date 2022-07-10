package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditFileDialog {
    private final JDialog dialog;
    private final List<JLabel> labels;
    private final List<JTextField> textFields;
    private final JButton okButton;
    private final JButton cancelButton;
    private int buttonValue;

    private JLabel CreateLabel(String text, String name) {
        JLabel label = new JLabel(text);
        label.setName(name);
        labels.add(label);
        return label;
    }

    public JTextField CreateTextField(String name) {
        JTextField textField = new JTextField(30);
        textField.setName(name);
        textFields.add(textField);
        return textField;
    }

    public enum LabelsName {
        NAME, AUTHOR, DESCRIPTION, CATEGORIES, TRANSLATOR, PUBLISHED, URL
    }

    public enum TextFieldsName {
        NAME, AUTHOR, DESCRIPTION, CATEGORIES, TRANSLATOR, PUBLISHED, URL
    }

    public JDialog getDialog() {
        return dialog;
    }

    public List<JLabel> getLabels() {
        return labels;
    }

    public List<JTextField> getTextFields() {
        return textFields;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public int getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(int buttonValue) {
        this.buttonValue = buttonValue;
    }

    public EditFileDialog(JFrame frame) {
        okButton = new JButton("OK");
        cancelButton = new JButton("CANCEL");
        labels = new ArrayList<>();
        textFields = new ArrayList<>();
        dialog = new JDialog(frame, "Update book dialog", true);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog.add(CreateLabel("Book's name", UpdateFileDialog.LabelsName.NAME.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.NAME.toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(CreateLabel("Authour's name", UpdateFileDialog.LabelsName.AUTHOR.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.AUTHOR.toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(CreateLabel("Descrption", UpdateFileDialog.LabelsName.DESCRIPTION.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.DESCRIPTION.toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(CreateLabel("Categories", UpdateFileDialog.LabelsName.CATEGORIES.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.CATEGORIES.toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(CreateLabel("Translator", UpdateFileDialog.LabelsName.TRANSLATOR.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.TRANSLATOR.toString()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        dialog.add(CreateLabel("Published", UpdateFileDialog.LabelsName.PUBLISHED.toString()), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        dialog.add(CreateTextField(UpdateFileDialog.TextFieldsName.PUBLISHED.toString()), gbc);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50);
        panel.setLayout(flowLayout);
        panel.add(okButton);
        panel.add(cancelButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        dialog.add(panel, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(null);

    }

    public void show(boolean isShow) {
        dialog.setVisible(isShow);
    }
}
