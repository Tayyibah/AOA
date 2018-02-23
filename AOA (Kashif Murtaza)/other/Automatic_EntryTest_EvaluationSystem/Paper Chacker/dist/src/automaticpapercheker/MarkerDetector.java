
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Point;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author Basham
 */
public class MarkerDetector {
    /*
     * find the Upper Marker in the image;
     * return the point containing the x and y axis of upper marker
     * 
     */
    public static Point upperMarker;
    public static Point lowerMarker;
    public Point detectUperMarker(BufferedImage im){
        try{
//        
            int weidth=im.getWidth();
            int height=im.getHeight();
            int x=-1;int y=-1;
            double maxSim=0;
            int i=(int)Math.round(weidth*0.80);
            int k=(int)Math.round(height*0.20);
            for(;i<weidth-40;i++){
                for(int j=0;j<k;j++){
                    BufferedImage temp=im.getSubimage(i, j, 40, 40);
                    double sim=ImageUtils.getSimilarity(Static.uperMarker,temp);
                    if(sim>maxSim){
                        maxSim=sim;
                        x=i;
                        y=j;
                        if(maxSim>90){
                            i=weidth;
                            j=height;
                        }
                    }
                }
            }
            Point p=new Point();
            p.x=x+20;
            p.y=y+20;
            upperMarker=p;
            return p;
        //}
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
 
    /*
     * find the Upper Marker in the image;
     * return the point containing the x and y axis of upper marker
     * 
     */
    
    public Point detectLowerMarker(BufferedImage im)throws Exception{
        int height=im.getHeight();
        int width=im.getWidth();
        int x=-1;int y=-1;
        double maxSim=0;
        int i=(int)Math.round(width*0.05);
        int m=(int)Math.round(width*0.25);
        int k=(int)Math.round(height*0.75);
        int l=(int)Math.round(height*0.90);
        for(;i<m;i++){
            for(int j=k;j<l;j++){
                BufferedImage temp=im.getSubimage(i, j, 43, 43);
                double sim=ImageUtils.getSimilarity(Static.lowerMarker,temp);
                if(sim>maxSim){
                    maxSim=sim;
                    x=i;
                    y=j;
                    if(maxSim>90){
                        i=width;
                        j=height;
                    }
                }
            }
        }
        Point p=new Point();
        p.x=x+21;
        p.y=y+21;
        return p;
    }
}
class Marker{
    public double sim;
    public Point p;

    public Marker() {
    }

    public Marker(double sim, Point p) {
        this.sim = sim;
        this.p = p;
    }
    
}