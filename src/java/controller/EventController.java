package controller;

import GUI.MainJPanel;
import GUI.TopButtons;
import models.BooksInfoModel;
import models.MessageModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class EventController {
    private Socket socket;
    private final MainJPanel mainJPanel;

    private MessageModel sendMessage(MessageModel messageModel) {
        MessageModel res = null;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(messageModel);
            res = (MessageModel) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void initTopButtonsEvent() {
        mainJPanel.getTopPanelButtons().getButtons().forEach(element -> element.addActionListener(e -> {
            BooksInfoModel booksInfoModel = (BooksInfoModel) mainJPanel.getSearchPanel().getListView().getSelectedValue();
            if (element.getName().equals(TopButtons.ButtonsName.DELETE.toString())) {
                sendMessage(new MessageModel(Message.DELETE_BOOK, new Object[]{booksInfoModel.getId()}));
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
            } else if (element.getName().equals(TopButtons.ButtonsName.OPEN.toString())) {
                sendMessage(new MessageModel(Message.OPEN, new Object[]{booksInfoModel}));
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
                    String arg = "";
                    if (mainJPanel.getSearchPanel().getTextField().getText() != null)
                        arg = mainJPanel.getSearchPanel().getTextField().getText();
                    MessageModel res = sendMessage(new MessageModel(Message.FIND_BOOKS, new Object[]{arg}));
                    mainJPanel.getSearchPanel().getListView().removeAll();
                    for (int i = 0; i < res.getArgs().length; i++) {
                        mainJPanel.getSearchPanel().getDefaultListModel().add(0,(BooksInfoModel) res.getArgs()[i]);
                    }
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
        initSearchPanelEvent();

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

    public void addToListView(BooksInfoModel booksInfoModel) {
        mainJPanel.getSearchPanel().getDefaultListModel().add(0, booksInfoModel);
    }



}
