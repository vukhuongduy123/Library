import GUI.*;
import database.SQLConnect;
import models.BooksInfoModel;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Library {
    public static void main(String[] args) {
        SQLConnect.initConnection();
        Connection connection = SQLConnect.getConnection();
        try {
            MainJPanel mainJPanel = new MainJPanel();

            MainJFrame mainJFrame = new MainJFrame();
            DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
            mainJPanel.getSearchPanel().addToListView(new BooksInfoModel(
                    1,"string","string","string","string","string", dateFormate.parse("2019-09-07"), dateFormate.parse("2019-09-07")), 0);
            mainJFrame.addComponent(mainJPanel.getPanel());

            mainJFrame.show();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
