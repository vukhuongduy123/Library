package GUI;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.DocumentViewController;
import org.icepdf.ri.util.FontPropertiesManager;
import org.icepdf.ri.util.PropertiesManager;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class IcePdf {
    private SwingController controller;
    private JPanel viewerComponentPanel;

    public IcePdf() {
        controller = new SwingController();
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        viewerComponentPanel = new SwingViewBuilder(controller).buildViewerPanel();
        viewerComponentPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder()));
    }

    public JPanel getPdfViewerComponent() {
        return viewerComponentPanel;
    }

    public SwingController getController() {
        return controller;
    }
}
