package GUI;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;

public class IcePdf {
    private final SwingController controller;
    private final JPanel viewerComponentPanel;

    public IcePdf() {
        ImageIO.scanForPlugins();
        controller = new SwingController();
        viewerComponentPanel = new SwingViewBuilder(controller).buildViewerPanel();
        ComponentKeyBinding.install(controller, viewerComponentPanel);
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

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
