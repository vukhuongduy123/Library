package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainJPanel {
    private final JPanel panel;
    private final TopButtons topPanelButtons;
    private final SearchPanel searchPanel;
    private final IcePdf icePdf;
    private final UpdateFileDialog updateFileDialog;
    private final EditFileDialog editFileDialog;

    public EditFileDialog getEditFileDialog() {
        return editFileDialog;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public TopButtons getTopPanelButtons() {
        return topPanelButtons;
    }

    public IcePdf getIcePdf() {
        return icePdf;
    }

    public UpdateFileDialog getUpdateFileDialog() {
        return updateFileDialog;
    }

    public MainJPanel(JFrame jFrame) {
        updateFileDialog = new UpdateFileDialog(jFrame);
        editFileDialog = new EditFileDialog(jFrame);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        AtomicInteger gridxTopPanelButtons = new AtomicInteger();
        topPanelButtons = new TopButtons();
        topPanelButtons.getButtons().forEach(element -> {
            gbc.gridx = gridxTopPanelButtons.getAndIncrement();
            gbc.fill = GridBagConstraints.NONE;
            gbc.gridy = 0;
            gbc.insets = new Insets(10,10,10,30);
            gbc.anchor = GridBagConstraints.WEST;
            panel.add(element, gbc);
        });

        searchPanel = new SearchPanel();

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,0,0,0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(searchPanel.getTextField(), gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(searchPanel.getScrollPane(), gbc);

        icePdf = new IcePdf();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipadx = 1;
        gbc.ipady = 1;
        gbc.gridwidth = 4;
        gbc.gridheight = 2;
        panel.add(icePdf.getPdfViewerComponent(), gbc);

    }

    public JPanel getPanel() {
        return panel;
    }

}
