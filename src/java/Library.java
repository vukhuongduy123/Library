import GUI.*;
import controller.EventController;
import database.SQLConnect;
import models.BooksInfoModel;

import javax.swing.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Library {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception ignored){

        }

        SQLConnect.initConnection();
        Connection connection = SQLConnect.getConnection();
        try {
            MainJPanel mainJPanel = new MainJPanel();

            MainJFrame mainJFrame = new MainJFrame();
            DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
            EventController eventController = new EventController(mainJPanel);
            eventController.setConnection(8080,"localhost");
            eventController.addToListView(new BooksInfoModel(
                    1,"string","string","string","string","string", dateFormate.parse("2019-09-07"), dateFormate.parse("2019-09-07")));
            eventController.initControl();
            mainJFrame.addComponent(mainJPanel.getPanel());

            mainJFrame.show();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
