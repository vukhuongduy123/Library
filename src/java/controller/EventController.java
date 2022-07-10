package controller;

import GUI.EditFileDialog;
import GUI.MainJPanel;
import GUI.TopButtons;
import GUI.UpdateFileDialog;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventController {
    private Socket socket;
    private final MainJPanel mainJPanel;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    private MessageModel sendMessage(MessageModel messageModel) {
        if (socket == null)
            return null;
        MessageModel res = null;
        try {
            objectOutputStream.writeObject(messageModel);
            res = (MessageModel) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void initUpdateFileDialog() {
        UpdateFileDialog updateFileDialog = mainJPanel.getUpdateFileDialog();

        updateFileDialog.getChoseFileButton().addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter(null, "pdf"));
            int option = fc.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filepath = file.getPath();
                JTextField urlTextField = updateFileDialog.getTextFields().stream()
                        .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.URL.toString())).findFirst().orElse(null);
                urlTextField.setText(filepath);
            }
        });

        updateFileDialog.getOkButton().addActionListener(e -> {
            updateFileDialog.setButtonValue(1);
            JTextField nameTextField = updateFileDialog.getTextFields().stream()
                    .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.NAME.toString())).findFirst().orElse(null);
            JTextField urlTextField = updateFileDialog.getTextFields().stream()
                    .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.URL.toString())).findFirst().orElse(null);
            if (nameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name field cant be null", "Error",
                        JOptionPane.INFORMATION_MESSAGE, null);
            } else if (urlTextField.getText().isEmpty() ||
                    !(Files.exists(Paths.get(urlTextField.getText())) && !Files.isDirectory(Paths.get(urlTextField.getText())))) {
                JOptionPane.showMessageDialog(null, "File path is not exist", "Error",
                        JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                updateFileDialog.show(false);
            }
        });

        updateFileDialog.getCancelButton().addActionListener(e -> {
            updateFileDialog.setButtonValue(0);
            updateFileDialog.show(false);
        });

    }

    private void initEditFileDialog() {
        EditFileDialog editFileDialog = mainJPanel.getEditFileDialog();

        editFileDialog.getOkButton().addActionListener(e -> {
            editFileDialog.setButtonValue(1);
            JTextField nameTextField = editFileDialog.getTextFields().stream()
                    .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.NAME.toString())).findFirst().orElse(null);
            if (nameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name field cant be null", "Error",
                        JOptionPane.INFORMATION_MESSAGE, null);
            } else {
                editFileDialog.show(false);
            }
        });

        editFileDialog.getCancelButton().addActionListener(e -> {
            editFileDialog.setButtonValue(0);
            editFileDialog.show(false);
        });
    }

    private void initTopButtonsEvent() {
        mainJPanel.getTopPanelButtons().getButtons().forEach(element -> element.addActionListener(e -> {
            BooksInfoModel booksInfoModel = (BooksInfoModel) mainJPanel.getSearchPanel().getListView().getSelectedValue();
            if (element.getName().equals(TopButtons.ButtonsName.DELETE.toString())) {
                MessageModel messageModel = sendMessage(new MessageModel(Message.DELETE_BOOK, new Object[]{booksInfoModel}));
                if(messageModel == null)
                    return;
                mainJPanel.getSearchPanel().getDefaultListModel().removeElement(booksInfoModel);
            } else if (element.getName().equals(TopButtons.ButtonsName.UPDATE.toString())) {
                UpdateFileDialog updateFileDialog = mainJPanel.getUpdateFileDialog();
                updateFileDialog.show(true);
                if (updateFileDialog.getButtonValue() == 0)
                    return;

                try {
                    BooksInfoModel newBook = new BooksInfoModel();
                    newBook.setName(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.NAME.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setAuthour(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.AUTHOR.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setCategories(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.CATEGORIES.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setTranslator(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.TRANSLATOR.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setDescr(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.DESCRIPTION.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setPublished(new SimpleDateFormat("yyyy-MM-dd").parse(updateFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.PUBLISHED.toString()))
                            .findFirst().orElse(null).getText()));

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    newBook.setAddedTime(formatter.parse(formatter.format(new Date())));

                    MessageModel messageModel = sendMessage(new MessageModel(Message.UPDATE_BOOK,
                            new Object[]{newBook, Files.readAllBytes(Paths.get(updateFileDialog.getTextFields().stream()
                                    .filter(p -> p.getName().equals(UpdateFileDialog.TextFieldsName.URL.toString())).
                                            findFirst().orElse(null).getText()))}));
                    if (messageModel != null && messageModel.getMessage().equals("success")) {
                        mainJPanel.getSearchPanel().getDefaultListModel().add(0, (BooksInfoModel) messageModel.getArgs()[0]);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (element.getName().equals(TopButtons.ButtonsName.INFO.toString())) {
                String message = "name: " + booksInfoModel.getName() + "\n"
                        + "author: " + booksInfoModel.getAuthour() + "\n"
                        + "categories: " + booksInfoModel.getCategories() + "\n"
                        + "descr: " + booksInfoModel.getDescr() + "\n"
                        + "translator: " + booksInfoModel.getTranslator() + "\n"
                        + "addedTime: " + booksInfoModel.getAddedTime() + "\n"
                        + "published: " + booksInfoModel.getPublished() + "\n";
                JOptionPane.showMessageDialog(null, message, "Info message", JOptionPane.INFORMATION_MESSAGE);
            } else if (element.getName().equals(TopButtons.ButtonsName.OPEN.toString())) {
                MessageModel messageModel = sendMessage(new MessageModel(Message.OPEN, new Object[]{booksInfoModel}));
                if(messageModel == null)
                    return;
                byte[] bytes = (byte[]) messageModel.getArgs()[0];
                mainJPanel.getIcePdf().getController().openDocument(bytes, 0,bytes.length,null,null);
            } else if (element.getName().equals(TopButtons.ButtonsName.OPEN_LOCAL.toString())) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter(null, "pdf"));
                int option = fc.showOpenDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    mainJPanel.getIcePdf().getController().openDocument(file.getPath());
                }
            } else if (element.getName().equals(TopButtons.ButtonsName.EDIT.toString())) {

                EditFileDialog editFileDialog = mainJPanel.getEditFileDialog();
                editFileDialog.show(true);
                if (editFileDialog.getButtonValue() == 0)
                    return;

                try {
                    BooksInfoModel newBook = new BooksInfoModel();
                    newBook.setId(booksInfoModel.getId());
                    newBook.setName(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.NAME.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setAuthour(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.AUTHOR.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setCategories(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.CATEGORIES.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setTranslator(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.TRANSLATOR.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setDescr(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.DESCRIPTION.toString()))
                            .findFirst().orElse(null).getText());
                    newBook.setPublished(new SimpleDateFormat("yyyy-MM-dd").parse(editFileDialog.getTextFields().stream()
                            .filter(p -> p.getName().equals(EditFileDialog.TextFieldsName.PUBLISHED.toString()))
                            .findFirst().orElse(null).getText()));
                    newBook.setBookContentId(booksInfoModel.getBookContentId());

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    newBook.setAddedTime(formatter.parse(formatter.format(new Date())));
                    MessageModel messageModel = sendMessage(new MessageModel(Message.EDIT_BOOK, new Object[]{newBook}));

                    if(messageModel == null)
                        return;
                    mainJPanel.getSearchPanel().getDefaultListModel().removeElement(booksInfoModel);
                    mainJPanel.getSearchPanel().getDefaultListModel().addElement(newBook);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }));
    }

    private void initSearchPanelEvent() {
        mainJPanel.getSearchPanel().getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String arg = "";
                    if (mainJPanel.getSearchPanel().getTextField().getText() != null)
                        arg = mainJPanel.getSearchPanel().getTextField().getText();
                    MessageModel res = sendMessage(new MessageModel(Message.FIND_BOOKS, new Object[]{arg}));
                    if(res == null)
                        return;
                    mainJPanel.getSearchPanel().getDefaultListModel().removeAllElements();
                    for (int i = 0; i < res.getArgs().length; i++) {
                        mainJPanel.getSearchPanel().getDefaultListModel().add(0, (BooksInfoModel) res.getArgs()[i]);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
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
        initUpdateFileDialog();
        initEditFileDialog();
    }

    public EventController(MainJPanel panel) {
        mainJPanel = panel;
    }

    public void setConnection(int port, String ip) {
        try {
            socket = new Socket(ip, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initBooksList() {
        MessageModel res = sendMessage(new MessageModel(Message.FIND_BOOKS, new Object[]{""}));
        if(res == null)
            return;
        for (int i = 0; i < res.getArgs().length; i++) {
            mainJPanel.getSearchPanel().getDefaultListModel().add(0, (BooksInfoModel) res.getArgs()[i]);
        }
    }

}
