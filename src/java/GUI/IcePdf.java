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
        PropertiesManager properties = new PropertiesManager(System.getProperties(),
                ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, "false");
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ROTATE, "false");
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_TOOL, "false");
        properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "1");
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_STATUSBAR_VIEWMODE, Boolean.FALSE);
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_PAGENAV, "false");

        ResourceBundle messageBundle = ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE);
        new FontPropertiesManager(properties, System.getProperties(), messageBundle);

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
