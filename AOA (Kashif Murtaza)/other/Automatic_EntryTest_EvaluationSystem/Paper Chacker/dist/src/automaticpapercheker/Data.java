/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

/**
 *
 * @author Basham
 */
public class Data {
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int x3;
    public int y3;
    public int x4;
    public int y4;
    public int radius;
    public int mrkx;
    public int mrky;

    public Data() {
        this.x1 = -1;
        this.y1 = -1;
        this.x2 = -1;
        this.y2 = -1;
        this.x3 = -1;
        this.y3 = -1;
        this.x4 = -1;
        this.y4 = -1;
        this.radius = -1;
        this.mrkx = -1;
        this.mrky = -1;
        
    }

    public Data(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int radius,int mrkx, int mrky) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.radius = radius;
        this.mrkx = mrkx;
        this.mrky = mrky;
    }
    
}
