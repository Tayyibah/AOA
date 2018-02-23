/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Basham
 */
public class ImageUtils {
    /*
     * Load the image File 
     */
    public static BufferedImage loadImage(String filename){
        BufferedImage img = null;
        try {
              img = ImageIO.read(new File(filename));
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return img;
    } 
    
    /*
     * Convert the Bufferem image to a Matrix
     */
    
    public static int[] readLuminance(BufferedImage sourceImage) {
        int type = sourceImage.getType();
        int width=sourceImage.getWidth();
        int height=sourceImage.getHeight();
        int picsize= sourceImage.getHeight()*width;
        int data[]=new int[picsize];
        if (type == BufferedImage.TYPE_INT_RGB || type == BufferedImage.TYPE_INT_ARGB) {
                int[] pixels = (int[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
                for (int i = 0; i < picsize; i++) {
                    int p = pixels[i];
                    int r = (p & 0xff0000) >> 16;
                    int g = (p & 0xff00) >> 8;
                    int b = p & 0xff;
                    data[i] = luminance(r, g, b);
                }
        } 
        else if (type == BufferedImage.TYPE_BYTE_GRAY) {
                byte[] pixels = (byte[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
                for (int i = 0; i < picsize; i++) {
                        data[i] = (pixels[i] & 0xff);
                }
        }
        else if (type == BufferedImage.TYPE_USHORT_GRAY) {
                short[] pixels = (short[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
                for (int i = 0; i < picsize; i++) {
                        data[i] = (pixels[i] & 0xffff) / 256;
                }
        }
        else if (type == BufferedImage.TYPE_3BYTE_BGR) {
                byte[] pixels = (byte[]) sourceImage.getData().getDataElements(0, 0, width, height, null);
                int offset = 0;
                for (int i = 0; i < picsize; i++) {
                    int b = pixels[offset++] & 0xff;
                    int g = pixels[offset++] & 0xff;
                    int r = pixels[offset++] & 0xff;
                    data[i] = luminance(r, g, b);
                }
        }
        else {
                throw new IllegalArgumentException("Unsupported image type: " + type);
        }
        return data;    
    }
    
     private static int luminance(float r, float g, float b) {
	return Math.round(0.299f * r + 0.587f * g + 0.114f * b);
    }
     
     /*
      * Converts the RGB image to Black&White Image
      */
     public static int [] convert(int []arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]>200){
                arr[i]=0;
            }
            else{
                arr[i]=1;
                
            }
        }
        return arr;
    }
     
    /*
     * Calculate similarity between two images
     * return % similarity 
     */
     
    public static  double getSimilarity(BufferedImage image1,BufferedImage image2)throws Exception{

        BufferedImage image =  image1;
        int[] clr=  readLuminance(image1); 
        BufferedImage images = image2;
        int[]clre=  readLuminance(images); 
        clr=convert(clr);
        clre=convert(clre);
        double size=0;
        double p=0;
        //CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
        if(clr.length>clre.length){
            size=clre.length;
        }
        else{
            size=clr.length;
        }
        for(int i=0;i<size;i++){ 
            if(clr[i]==clre[i] )
            {
                p=p+1;
            }
        }
        double w=p/size;
        //CALUCLATING PERCENTAGE
        double x =(100*w);
        return x;
    }
    
    
}
