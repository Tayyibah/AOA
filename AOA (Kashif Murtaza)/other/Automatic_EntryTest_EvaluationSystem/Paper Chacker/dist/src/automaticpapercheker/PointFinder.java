/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Point;

/**
 *
 * @author Basham
 */
public class PointFinder {
    double x1,x2,x3,x4,x5,x6,x7,x8,x9;
    double angle;

    public PointFinder() {
    }

    public PointFinder(double angle) {
        this.angle = angle;
       this.angle=Math.toRadians(angle);
        x1=Math.cos(this.angle);
        x2=-(Math.sin(this.angle));
        x3=0;
        x4=(Math.sin(this.angle));
        x5=Math.cos(this.angle);
        x6=0;
        x7=0;
        x8=0;
        x9=1;
    }
    public Point finPoint(Point point){
     //   findInverse();
        double x=point.x;
        double y=point.y;
        double X=(x1*x)+(x2*y)+(x3*1);
        double Y=(x4*x)+(x5*y)+(x6*1);
        int c=(int)X;
        int d=(int)Y;
        return new Point(c, d);
    }
    public void findInverse(){
        adjaint();
        double det=(x1*((x5*x9)-(x6*x8)))  -   (x2*((x4*x9)-(x6*x7)))    +(x3*((x4*x8)-(x5*x7)));
        x1=x1/det;
        x2=x2/det;
        x3=x3/det;
        x4=x4/det;
        x5=x5/det;
        x6=x6/det;
        x7=x7/det;
        x8=x8/det;
        x9=x9/det;
    }
    public void adjaint(){
        double x1,x2,x3,x4,x5,x6,x7,x8,x9;
        x1=this.x1;
        x2=this.x4;
        x3=this.x7;
        x4=this.x2;
        x5=this.x5;
        x6=this.x8;
        x7=this.x3;
        x8=this.x6;
        x9=this.x9;
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.x4=x4;
        this.x5=x5;
        this.x6=x6;
        this.x7=x7;
        this.x8=x8;
        this.x9=x9;
    }
}
