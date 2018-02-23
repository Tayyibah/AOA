/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Basham
 */
public class Sheet {
    public BufferedImage image;
    String rollNum;
    public Point uperMarker;
    public Point lowerMarker;

    public Sheet() {
    }

    public Sheet(BufferedImage image, Point uperMarker, Point lowerMarker) {
        this.image = image;
        this.uperMarker = uperMarker;
        this.lowerMarker = lowerMarker;
    }

    public Sheet(BufferedImage image, String rollNum, Point uperMarker, Point lowerMarker) {
        this.image = image;
        this.rollNum = rollNum;
        this.uperMarker = uperMarker;
        this.lowerMarker = lowerMarker;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setLowerMarker(Point lowerMarker) {
        this.lowerMarker = lowerMarker;
    }

    public void setUperMarker(Point uperMarker) {
        this.uperMarker = uperMarker;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Point getLowerMarker() {
        return lowerMarker;
    }

    public Point getUperMarker() {
        return uperMarker;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getRollNum() {
        return rollNum;
    }
   
}
