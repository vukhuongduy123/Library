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
    private final TopPanelButtons topPanelButtons;
    private final SearchPanel searchPanel;
    private final IcePdf icePdf;

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public MainJPanel() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        topPanelButtons = new TopPanelButtons();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        topPanelButtons.getButtons().forEach(element -> element.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BooksInfoModel booksInfoModel = (BooksInfoModel) searchPanel.getListView().getSelectedValue();
                if (element.getName().equals(TopPanelButtons.ButtonsName.DELETE.toString())) {
                    EventController.sendMessage(new MessageModel(Message.DELETE_BOOK,
                            new Object[]{booksInfoModel}));
                } else if (element.getName().equals(TopPanelButtons.ButtonsName.UPDATE.toString())) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new FileNameExtensionFilter(null, "pdf"));
                    int option = fc.showOpenDialog(null);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        String filepath = file.getPath();

                    }

                    EventController.sendMessage(new MessageModel(Message.UPDATE_BOOK, new Object[]{booksInfoModel}));

                } else if (element.getName().equals(TopPanelButtons.ButtonsName.INFO.toString())) {
                    String message = "";
                    message += "name: " + booksInfoModel.getName() + "\n";
                    message += "author: " + booksInfoModel.getAuthour() + "\n";
                    message += "categories: " + booksInfoModel.getCategories() + "\n";
                    message += "descr: " + booksInfoModel.getDescr() + "\n";
                    message += "translator: " + booksInfoModel.getTranslator() + "\n";
                    message += "addedTime: " + booksInfoModel.getAddedTime() + "\n";
                    message += "published: " + booksInfoModel.getPublished() + "\n";
                    JOptionPane.showMessageDialog(null, message, "Info message", JOptionPane.INFORMATION_MESSAGE);
                } else if (element.getName().equals(TopPanelButtons.ButtonsName.INFO.toString())) {
                    EventController.sendMessage(new MessageModel(Message.UPDATE_BOOK, new Object[]{booksInfoModel}));

                }
            }
        }));
        panel.add(topPanelButtons.getPanel(), gbc);

        searchPanel = new SearchPanel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTH;
        searchPanel.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    if (searchPanel.getTextField().getText() != null)
                        EventController.sendMessage(new MessageModel(Message.FIND_BOOK, new Object[]{searchPanel.getTextField().getText()}));
                    else
                        EventController.sendMessage(new MessageModel(Message.GET_ALL_BOOKS, null));
                }
             }

            @Override
            public void keyReleased(KeyEvent e) {}

        });
        panel.add(searchPanel.getTextField(), gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        searchPanel.getListView().addListSelectionListener(e -> {
            if (searchPanel.getListView().getSelectedValue() != null) {
                topPanelButtons.getButtons().forEach(element -> element.setEnabled(true));
            }
        });
        panel.add(searchPanel.getScrollPane(), gbc);

        icePdf = new IcePdf();
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
        panel.add(icePdf.getPdfViewerComponent(), gbc);
    }

    public JPanel getPanel() {
        return panel;
    }

}
