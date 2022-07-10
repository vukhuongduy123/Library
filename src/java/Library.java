import GUI.*;
import controller.EventController;

import javax.swing.*;

public class Library {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception ignored){

        }
        try {

            MainJFrame mainJFrame = new MainJFrame();
            MainJPanel mainJPanel = new MainJPanel(mainJFrame.getjFrame());

            EventController eventController = new EventController(mainJPanel);
            eventController.setConnection(8080,"localhost");
            eventController.initControl();
            eventController.initBooksList();

            mainJFrame.addComponent(mainJPanel.getPanel());

            mainJFrame.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
