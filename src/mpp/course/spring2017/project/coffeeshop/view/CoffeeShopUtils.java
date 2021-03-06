package mpp.course.spring2017.project.coffeeshop.view;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class CoffeeShopUtils {
	public static String getConfig(String config) {
		String value = "";
		InputStream input = null;
		try {
			//load config.properties file
			Properties prop = new Properties();
			input = new FileInputStream("bin/config.properties");
			prop.load(input);			
			value = prop.getProperty(config);			
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return value;
	}

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
	
	public static byte[] convertJavaFXImage2ByteArray(ImageView imageProduct) {
		try {
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			ImageIO.write(SwingFXUtils.fromFXImage(imageProduct.getImage(), null), "png", byteOutput);
			return byteOutput.toByteArray();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
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
	
	public static String getMD5(String msg) {
		String md5 = "";
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(msg.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			md5 = bigInt.toString(16);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return md5;
	}
}
