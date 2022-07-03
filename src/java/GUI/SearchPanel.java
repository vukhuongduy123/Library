package GUI;

import javax.swing.*;

public class SearchPanel {
    private final JTextField textField;
    private final JList listView;
    private final DefaultListModel defaultListModel;
    private final JScrollPane scrollPane;

    public SearchPanel() {
        textField = new JTextField();
        defaultListModel = new DefaultListModel();

        listView = new JList(defaultListModel);
        scrollPane = new JScrollPane(listView);
    }

    public void addToListView(Object obj, int index) {
        defaultListModel.add(index, obj);
    }

    public JList getListView() {
        return listView;
    }

    public JTextField getTextField() {
        return textField;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
