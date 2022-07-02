import GUI.*;
import database.SQLConnect;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Library {
    public static void main(String[] args) {
        SQLConnect.initConnection();
        Connection connection = SQLConnect.getConnection();
        try {
            Statement statement = connection.createStatement();
            MainJPanel mainJPanel = new MainJPanel();
            GridBagConstraints gbc = new GridBagConstraints();

            TopPanelButtons topPanelButtons = new TopPanelButtons();
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            mainJPanel.addComponent(topPanelButtons.getPanel(), gbc);

            SearchPanel searchPanel = new SearchPanel();
            searchPanel.addToListView("some thing",0);
            searchPanel.addToListView("some thing bigger",1);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.ipadx = 1;
            gbc.ipady = 1;
            gbc.anchor = GridBagConstraints.NORTH;
            mainJPanel.addComponent(searchPanel.getTextField(), gbc);

            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.ipadx = 1;
            gbc.ipady = 1;
            gbc.anchor = GridBagConstraints.NORTH;
            mainJPanel.addComponent(searchPanel.getScrollPane(), gbc);

            IcePdf icePdf = new IcePdf();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.ipadx = 1;
            gbc.ipady = 1;
            gbc.gridwidth = 2;
            gbc.gridheight = 2;
            mainJPanel.addComponent(icePdf.getPdfViewerComponent(), gbc);

            icePdf.getController().openDocument("E:\\courses\\cs-3332\\Software_Engineering_9th_Edition.pdf");

            MainJFrame mainJFrame = new MainJFrame();
            mainJFrame.addComponent(mainJPanel.getPanel());

            mainJFrame.show();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
