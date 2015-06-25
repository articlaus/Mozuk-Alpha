package com.ui.component.base;

import org.zkoss.image.AImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: tseegii
 * Date: 6/22/13
 * Time: 12:10 AM
 */
public class BaseImage {

    private BaseImage() {
    }

    public static AImage resizeImage(AImage image, Integer width, Integer height) throws IOException {

        BufferedImage bImage = ImageIO.read(image.getStreamData());

        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(bImage, 0, 0, width, height, null);
        g.dispose();

        ByteArrayOutputStream imageByte = new ByteArrayOutputStream();

        ImageIO.write(resizedImage, "jpg", imageByte);

        AImage newImage = new AImage("image", imageByte.toByteArray());


        return newImage;
    }

    public static AImage resizeImage(byte[] imageData, Integer width, Integer height) throws IOException {
        AImage image = new AImage("image", imageData);
        return resizeImage(image, width, height);
    }

    public static AImage resizeWidthImage(AImage image, Integer width) throws IOException {
        Integer oWidth = image.getWidth();

        double p = (double) width / (oWidth / 100);

        Integer height = (int) ((image.getHeight() / 100) * p);

        return resizeImage(image, width, height);
    }

    public static AImage resizeHeightImage(AImage image, Integer height) throws IOException {
        Integer oHeight = image.getHeight();

        double p = (double) height / (oHeight / 100);

        Integer width = (int) ((image.getWidth() / 100) * p);

        return resizeImage(image, width, height);
    }
}
