package controller;

import GUI.MainJPanel;
import GUI.TopButtons;
import com.google.gson.Gson;
import models.BooksInfoModel;
import models.MessageModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class EventController {
    private Socket socket;
    private MainJPanel mainJPanel;

    private void sendMessage(MessageModel messageModel) {
        Gson gson = new Gson();
        String res = gson.toJson(messageModel);

        System.out.println(res);
    }

    private void initTopButtonsEvent() {
        mainJPanel.getTopPanelButtons().getButtons().forEach(element -> element.addActionListener(e -> {
            BooksInfoModel booksInfoModel = (BooksInfoModel) mainJPanel.getSearchPanel().getListView().getSelectedValue();
            if (element.getName().equals(TopButtons.ButtonsName.DELETE.toString())) {
                sendMessage(new MessageModel(Message.DELETE_BOOK, new Object[]{booksInfoModel}));
            } else if (element.getName().equals(TopButtons.ButtonsName.UPDATE.toString())) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter(null, "pdf"));
                int option = fc.showOpenDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    String filepath = file.getPath();
                }
                sendMessage(new MessageModel(Message.UPDATE_BOOK, new Object[]{booksInfoModel}));

            } else if (element.getName().equals(TopButtons.ButtonsName.INFO.toString())) {
                String message = "";
                message += "name: " + booksInfoModel.getName() + "\n";
                message += "author: " + booksInfoModel.getAuthour() + "\n";
                message += "categories: " + booksInfoModel.getCategories() + "\n";
                message += "descr: " + booksInfoModel.getDescr() + "\n";
                message += "translator: " + booksInfoModel.getTranslator() + "\n";
                message += "addedTime: " + booksInfoModel.getAddedTime() + "\n";
                message += "published: " + booksInfoModel.getPublished() + "\n";
                JOptionPane.showMessageDialog(null, message, "Info message", JOptionPane.INFORMATION_MESSAGE);
            } else if (element.getName().equals(TopButtons.ButtonsName.INFO.toString())) {
                sendMessage(new MessageModel(Message.UPDATE_BOOK, new Object[]{booksInfoModel}));
            } else if(element.getName().equals(TopButtons.ButtonsName.OPEN_LOCAL.toString())) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter(null, "pdf"));
                int option = fc.showOpenDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    mainJPanel.getIcePdf().getController().openDocument(file.getPath());
                }
            }
        }));
    }

    private void initSearchPanelEvent() {
        mainJPanel.getSearchPanel().getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
                    if (mainJPanel.getSearchPanel().getTextField().getText() != null)
                        sendMessage(new MessageModel(Message.FIND_BOOK, new Object[]{mainJPanel.getSearchPanel().getTextField().getText()}));
                    else
                        sendMessage(new MessageModel(Message.GET_ALL_BOOKS, null));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        mainJPanel.getSearchPanel().getListView().addListSelectionListener(e -> {
            if (mainJPanel.getSearchPanel().getListView().getSelectedValue() != null) {
                mainJPanel.getTopPanelButtons().getButtons().forEach(element -> element.setEnabled(true));
            }
        });
    }

    public void initControl() {
        initTopButtonsEvent();

    }

    public EventController(MainJPanel panel) {
        mainJPanel = panel;
    }

    public void setConnection(int port, String ip) {
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToListView(Object obj) {
        mainJPanel.getSearchPanel().getDefaultListModel().add(0, obj);
    }



}
