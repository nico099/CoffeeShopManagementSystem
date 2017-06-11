package mpp.course.spring2017.project.coffeeshop.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class CoffeeShopUtils {
	public static Image convertByteArray2JavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
        }
        return image;
    }
	
	public static void showErrorMessgge(String msg) {
		new Alert(Alert.AlertType.ERROR, msg).showAndWait();
	}
	
	@SuppressWarnings("unchecked")
	public static Image convertPDFtoImage(String pdfFileName) {

	    Image convertedImage = null;

	    try {

	        PDDocument document = PDDocument.load(new File(pdfFileName));
	        PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 1000, ImageType.RGB);
            convertedImage = SwingFXUtils.toFXImage(bim, null);
            document.close();

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }

	    return convertedImage;
	}
}