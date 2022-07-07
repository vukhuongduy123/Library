import GUI.*;
import controller.EventController;
import database.SQLConnect;
import models.BooksInfoModel;
import FTP.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class Library {
    public static void main(String[] args) {
        SQLConnect.initConnection();
        Connection connection = SQLConnect.getConnection();



        
    //     ftpSample.connectFTPServer();
        try {
            MainJPanel mainJPanel = new MainJPanel();
            MainJFrame mainJFrame = new MainJFrame();
            DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
            EventController eventController = new EventController(mainJPanel);
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



