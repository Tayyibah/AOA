/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Basham
 */
public class RotationDetector {

    /**
     * @param args the command line arguments
     */

    public static float findAngel(int x1, int y1, int x2, int y2) 
    {
        float angle = (float)Math.toDegrees(Math.atan2((double)(y1 - y2),(double)(x1 - x2)));
        angle = (angle + 180.0f) % 360.0f;
        angle = 360.0f - angle;
        return (float) angle;
    }
    private static BufferedImage rotate(float angle,BufferedImage im) throws Exception 
    {
        BufferedImage oldImage = im;
        BufferedImage newImage = new BufferedImage(620,880, oldImage.getType());
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.rotate(Math.toRadians(angle), oldImage.getHeight()/ 2, oldImage.getWidth() / 2);
        graphics.translate((newImage.getWidth() - oldImage.getWidth()) / 2, (newImage.getHeight() - oldImage.getHeight()) / 2);
        graphics.drawImage(oldImage,0,0, oldImage.getWidth(), oldImage.getHeight(), null);
        
        return newImage;   
    }
         
    public static Sheet detetctRotation(Sheet sheet)throws Exception{
        
        float angled= findAngel(sheet.uperMarker.x,sheet.uperMarker.y,sheet.lowerMarker.x,sheet.lowerMarker.y);
        if( Static.angle-angled>0 ){
            System.out.println(angled-Static.angle);
            System.out.println(sheet.uperMarker.x+" "+sheet.uperMarker.y);
            sheet.image=rotate((float)(angled-Static.angle),sheet.image);
            MarkerDetector mk=new MarkerDetector();
            sheet.lowerMarker=mk.detectLowerMarker(sheet.image);
            sheet.uperMarker=mk.detectUperMarker(sheet.image);
            System.out.println(sheet.uperMarker.x+" "+sheet.uperMarker.y);
            
        }
        else if( angled-Static.angle>0 ){
            System.out.println(angled-Static.angle);
            System.out.println(sheet.uperMarker.x+" "+sheet.uperMarker.y);
            sheet.image=rotate((float)Math.abs((angled-Static.angle)),sheet.image);
            MarkerDetector mk=new MarkerDetector();
            sheet.lowerMarker=mk.detectLowerMarker(sheet.image);
            sheet.uperMarker=mk.detectUperMarker(sheet.image);
            System.out.println(sheet.uperMarker.x+" "+sheet.uperMarker.y);
        }
        return sheet;
    }
    
}