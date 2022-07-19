import GUI.*;
import controller.EventController;
import javax.swing.*;

public class Library {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            Class.forName("controller.EventController");
            String ip = JOptionPane.showInputDialog(MainJFrame.getInstance().getFrame(), "Enter your ip", "Local host");
            EventController.getInstance().setConnection(8080, ip);

            MainJPanel.getInstance().getLoginFormDialog().show(true);

            EventController.getInstance().initBooksList();

            MainJFrame.getInstance().addComponent(MainJPanel.getInstance().getPanel());
            MainJFrame.getInstance().show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}