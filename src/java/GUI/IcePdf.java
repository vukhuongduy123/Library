package GUI;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.swing.*;

public class IcePdf {
    private final SwingController controller;
    private final JPanel viewerComponentPanel;

    public IcePdf() {
        System.getProperties().put("org.icepdf.core.imageReference", "smoothScaled");
        System.getProperties().put("org.icepdf.core.screen.interpolation", "VALUE_INTERPOLATION_BICUBIC");
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
