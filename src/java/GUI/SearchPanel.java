package GUI;

import javax.swing.*;

public class SearchPanel {
    private final JTextField textField;
    private JList listView;
    private final DefaultListModel defaultListModel;
    private final JScrollPane scrollPane;
    
    

    public SearchPanel(String[] arr, Boolean checkValue) {
        if (checkValue == false){
            textField = new JTextField();
            defaultListModel = new DefaultListModel();
            listView = new JList(defaultListModel);
            scrollPane = new JScrollPane(listView);
        }
        else{
            textField = new JTextField();
            defaultListModel = new DefaultListModel();
            listView = new JList(arr);
            scrollPane = new JScrollPane(listView);
        }
        
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


    public DefaultListModel getDefaultListModel() {
        return defaultListModel;
    }
}
