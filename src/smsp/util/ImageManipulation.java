package smsp.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageManipulation {
    
    /**
     * Ubah ukuran gambar sesuai dengan ukuran yg di tentukan
     * @param srcPath lokasi gambar asli
     * @param destPath lokasi gambar baru akan disimpan
     */
    public static void resize(File srcPath, File destPath) {
	try {
	    BufferedImage oriImg = ImageIO.read(srcPath);
	    int type = oriImg.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : oriImg.getType();
	    
	    BufferedImage scaledJpeg = ImageManipulation.resizeImage(oriImg, type);
	    ImageIO.write(scaledJpeg, "jpg", destPath);
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    /**
     * Proses scale ukuran gambar
     * @param originalImage gambar asli
     * @param type type gambar
     * @return gambar baru yang diubah ukurannya
     */
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(128, 128, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, 128, 128, null);
	g.dispose();
 
	return resizedImage;
    }

}
