/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImagenUmbralizada;

import java.awt.Graphics;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author jorge
 */
public class Lienzo extends JPanel {

    private static Dimension dim;

    private Mat vanillaImage;
    private Mat thresholdImage;
    private BufferedImage displayedImage;

    public Lienzo() {
        super();
    }

    public Mat getVanillaImage() {
        return vanillaImage;
    }

    public BufferedImage getImage() {
        return cloneImage(displayedImage);
    }

    private static BufferedImage cloneImage(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void applyThreshold(int threshold, Mat image, Lienzo lienzo) {
        thresholdImage = umbralizar(image, threshold);
        displayedImage = (BufferedImage) HighGui.toBufferedImage(thresholdImage);
        lienzo.SelectImage(displayedImage);
        repaint();

    }

    private static Mat umbralizar(Mat imagen_original, Integer umbral) {
        // crear dos imágenes en niveles de gris con el mismo
        // tamaño que la original
        Mat imagenGris = new Mat(imagen_original.rows(),
                imagen_original.cols(),
                CvType.CV_8U);
        Mat imagenUmbralizada = new Mat(imagen_original.rows(),
                imagen_original.cols(),
                CvType.CV_8U);
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original,
                imagenGris,
                Imgproc.COLOR_BGR2GRAY);
        // umbraliza la imagen:
        // - píxeles con nivel de gris > umbral se ponen a 1
        // - píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris,
                imagenUmbralizada,
                umbral,
                255,
                Imgproc.THRESH_BINARY);
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
    }

    public void SelectImage(BufferedImage displayedImage) {
        this.displayedImage = displayedImage;
    }

    public static Dimension openImage(File fichero, Boolean resize, Lienzo lienzo) {
        try {

            BufferedImage image = ImageIO.read(fichero);
            BufferedImage aux;

            if (resize) {
                aux = checkSize(image);
                image = aux;
            }
            if (dim == null) {
                dim = new Dimension(image.getWidth(), image.getHeight());
            }
            lienzo.SelectImage(image);
            return dim;
        } catch (IOException e) {
            return null;
        }
    }

    private static BufferedImage checkSize(BufferedImage image) {
        if (image.getWidth() > 1024 || image.getHeight() > 768) {
            double widthCoeficient = image.getWidth() / 1024.;
            double heightCoeficient = image.getHeight() / 768;
            image = rescale(image, widthCoeficient, heightCoeficient);
            System.out.println("a");
        } else {
            dim = new Dimension(image.getWidth(), image.getHeight());
        }

        System.out.println(dim.getWidth());
        System.out.println(dim.getHeight());
        return image;
    }

    private static BufferedImage rescale(BufferedImage image, double widthCoeficient, double heightCoeficient) {
        BufferedImage before = image;
        int w = before.getWidth();
        int h = before.getHeight();
        double coeficient;
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        if (widthCoeficient > heightCoeficient) {
            coeficient = widthCoeficient;
        } else {
            coeficient = heightCoeficient;
        }
        at.scale(1 / coeficient, 1 / coeficient);
        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        int newWidth = new Double(image.getWidth() * 1 / coeficient).intValue();
        int newHeight = new Double(image.getHeight() * 1 / coeficient).intValue();
        dim = new Dimension(newWidth, newHeight);
        return after;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(displayedImage, 0, 0, null);
    }

}
