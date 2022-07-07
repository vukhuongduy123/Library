package GUI;

import FTP.*;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;

public class MainJPanel {
    private final JPanel panel;
    private final TopButtons topPanelButtons;
    private final SearchPanel searchPanel;
    private final IcePdf icePdf;
    private JLabel statusLabel;


    FTPSample ftpConnection = new FTPSample();

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public TopButtons getTopPanelButtons() {
        return topPanelButtons;
    }

    public IcePdf getIcePdf() {
        return icePdf;
    }

    public MainJPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        AtomicInteger gridxTopPanelButtons = new AtomicInteger();
        topPanelButtons = new TopButtons();
        topPanelButtons.getButtons().forEach(element -> {
            gbc.gridx = gridxTopPanelButtons.getAndIncrement();
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 0;
            gbc.insets = new Insets(0,0,0,10);
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(element, gbc);
        });

        // Get connection and handle file in FTP.
        ftpConnection.connectFTPServer();
        
        String[] listFile = ftpConnection.getListFileFTP();
        
        try{

            for (int i = 0; i < listFile.length; i++){
                listFile[i] = (i + 1) + ", " + listFile[i];
            }
        }
        catch(Exception e){
        }
        

        // Set position and contents of Status.
        statusLabel = new JLabel();
        statusLabel.setText("Status: " + ftpConnection.getStatus());
        gbc.gridx = 6;       
        gbc.gridy = 0;       

        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(statusLabel, gbc);


        // Set contents of scrollbar area.
        
        if (ftpConnection.getStatus() == "connected"){
           
            searchPanel = new SearchPanel(listFile, true);
        }
        else{
            String[] defaultListModel = {};
            searchPanel = new SearchPanel(defaultListModel, false);
            
        }
        

        // Set text fiels area.
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(searchPanel.getTextField(), gbc);



        // Set Scroll pane position.
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(searchPanel.getScrollPane(), gbc);


        // Set IcePdf position.
        icePdf = new IcePdf();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 1;
        gbc.ipady = 1;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        panel.add(icePdf.getPdfViewerComponent(), gbc);

        
        
    }

    public JPanel getPanel() {
        return panel;
    }

}
