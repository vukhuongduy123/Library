package GUI;

import controller.EventController;
import controller.Message;
import models.BooksInfoModel;
import models.MessageModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class MainJPanel {
    private final JPanel panel;
    private final TopButtons topPanelButtons;
    private final SearchPanel searchPanel;
    private final IcePdf icePdf;

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

        topPanelButtons = new TopButtons();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;

        panel.add(topPanelButtons.getPanel(), gbc);

        searchPanel = new SearchPanel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(searchPanel.getTextField(), gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(searchPanel.getScrollPane(), gbc);

        icePdf = new IcePdf();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.gridheight = 2;
        panel.add(icePdf.getPdfViewerComponent(), gbc);

    }

    public JPanel getPanel() {
        return panel;
    }

}
