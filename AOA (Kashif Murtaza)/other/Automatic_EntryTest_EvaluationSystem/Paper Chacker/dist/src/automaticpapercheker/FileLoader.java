/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Basham
 */
public class FileLoader {
    JProgressBar progressBar;
    JFrame f;
    String directory;
    public static ArrayList<Sheet>files;
    public static int numOfFiles=-1;
    OCR roolnumberDectector;
    public static boolean flag=false;
    public static ArrayList<String>solFiles=new ArrayList<>();    
    
    public FileLoader() {
        roolnumberDectector=new OCR();
    }
    
    public FileLoader(String directory) {
        this.directory = directory;
        roolnumberDectector=new OCR();
    }

    public FileLoader(String directory, ArrayList<Sheet> files) {
        this.directory = directory;
        this.files = files;
        roolnumberDectector=new OCR();
    }

    public ArrayList<Sheet> getFiles() {
        return files;
    }
    
    public void loadFiles()throws Exception{
        
        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        files=new ArrayList<>();
        String file=null;
        numOfFiles=listOfFiles.length;
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if (listOfFiles[i].isFile()) 
            {
                 file = listOfFiles[i].getName();
                 BufferedImage temp=ImageUtils.loadImage(directory+"\\"+file);
                 files.add(getSheet(temp));                 
             }
        }
    }
    public Sheet getSheet(BufferedImage img)throws Exception{
        MarkerDetector mk=new MarkerDetector();
        Point p1=mk.detectLowerMarker(img);
        Point p2=mk.detectUperMarker(img);
        Sheet s=new Sheet(img, p2, p1);
        BufferedImage rollimage=img.getSubimage(p2.x-235,p2.y-30,200,60);
        String roll=roolnumberDectector.getRollNumber(rollimage);
        s.setRollNum(roll);
        s=RotationDetector.detetctRotation(s);
        return s;
    }
    public static ArrayList<String> getSolFiles() {
        return solFiles;
    }
    
    public String getFileName(String name){
        return name.substring(0,name.length()-4)+".txt";
    }
    public BufferedImage loadImage(String name){
            BufferedImage img = null;
            try {
                  img = ImageIO.read(new File(name));
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            return img;
        }

    public String getDirectory() {
        return directory;
    }



    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
   
}
