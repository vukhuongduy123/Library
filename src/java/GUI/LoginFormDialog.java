package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginFormDialog {
    private final JLabel usernameLabel, passwordLabel;
    private final JButton loginBtn, cancelBtn;
    private final JTextField usernameTextField, passwordTextField;
    private final JDialog dialog;

    public LoginFormDialog(JFrame frame) {
        dialog = new JDialog(frame, "Login form dialog", true);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        usernameLabel = new JLabel("Username ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog.add(usernameLabel, gbc);

        passwordLabel = new JLabel("Password ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog.add(passwordLabel, gbc);

        usernameTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog.add(usernameTextField, gbc);

        passwordTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        dialog.add(passwordTextField, gbc);

        loginBtn = new JButton("Login");
        cancelBtn = new JButton("Cancel");

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(50);
        panel.setLayout(flowLayout);
        panel.add(loginBtn);
        panel.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        dialog.add(panel, gbc);


        dialog.pack();
        dialog.setLocationRelativeTo(null);
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JDialog getDialog() {
        return dialog;
    }

    public void show(boolean show) {
        dialog.setVisible(show);
    }
}
