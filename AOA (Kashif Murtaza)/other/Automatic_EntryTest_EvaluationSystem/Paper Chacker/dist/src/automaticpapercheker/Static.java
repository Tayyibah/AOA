/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Basham
 */
public class Static {
    public static ArrayList<String> solution;
    public static int dx=0;
    public static int dy=0;
    public static double angle=236.7662;
    public static boolean filestatus[];
    public static int [][][]template;
    public static BufferedImage lowerMarker;
    public static BufferedImage uperMarker;
    public static int totalQuestion;
    public static boolean cancelFlag=false;
    public static void loadFileNames(String path){
        
    }
    public static void loadMarker(){
        lowerMarker=ImageUtils.loadImage("lowerMarker.png");
        uperMarker=ImageUtils.loadImage("uperMarker.png");
    }    
    public static void loadSoluionFile(String path){
        solution=new ArrayList<>();
        try{
//            String f2="solution.txt";
//            FileInputStream is=new FileInputStream(path);
//            int length=(int)f1.length();
//            byte []array=new byte[(int)length];
//            is.read(array, 0,length);
//            is.close();
//            FileOutputStream os=new FileOutputStream(f2);
//            os.write(array);
//            os.close();
            FileReader fr =new FileReader(path);
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            while(line!=null){
                solution.add(line);
                line=br.readLine();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
