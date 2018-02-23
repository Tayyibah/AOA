

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Basham
 */
public class RefrenceSheet extends MouseAdapter{
    JFrame canvas;
    public Data points;
    public double angle;
    public String directory;
    public RefrenceSheet(String directory){
        points=new Data();
     
        this.directory=directory;
    }
    public static void saveImage(BufferedImage im,String fileName){
        try {
            BufferedImage bi = im;
            File outputfile = new File(fileName);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void Mark()throws Exception{
        FileLoader fl=new FileLoader(directory);
        fl.loadFiles();
        BufferedImage bm=MarkSheets.getSheet(FileLoader.files.get(0));
        points.mrky=bm.getWidth()-21;
        ShowRefSheet(bm);

    }
    public static BufferedImage loadImage(String name){
            BufferedImage img = null;
            try {
                  img = ImageIO.read(new File(name));
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            return img;
        }
    
    
     public void ShowRefSheet(BufferedImage refSheet)throws Exception{    
        canvas = new JFrame();
        points.mrkx=refSheet.getWidth()-20;
        points.mrky=refSheet.getWidth()-21;
        canvas.setUndecorated(true);
        canvas.setSize(refSheet.getWidth()+10,refSheet.getHeight()+10);
        canvas.setLocation(200,10);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = canvas.getContentPane();
        ColorPanel panel = new ColorPanel(refSheet);
        pane.add(panel);
        canvas.addMouseListener(this);
        canvas.setVisible(true);
        canvas.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                    Frame frame = (Frame)we.getSource();
                    frame.dispose();
            }
        });
        
    }
     public void mouseClicked(MouseEvent e) {
         try{
       
        if(points.x1==-1){
            int i=JOptionPane.showConfirmDialog(null, "Do You want to save "+e.getX()+","+e.getY()+" as center of first Circle");
            if(i==0){
                points.x1=e.getX();
                points.y1=e.getY();
            }
        }
        else if(points.x2==-1){
            int i=JOptionPane.showConfirmDialog(null, "Do You want to save "+e.getX()+","+e.getY()+" as center of Seconed Circle");
            if(i==0){
                points.x2=e.getX();
                points.y2=e.getY();
            }
        }
        else if(points.x3==-1){
            int i=JOptionPane.showConfirmDialog(null, "Do You want to save "+e.getX()+","+e.getY()+" as center of Third Circle");
            if(i==0){
                points.x3=e.getX();
                points.y3=e.getY();
            }
        }
        else if(points.x4==-1){
            int i=JOptionPane.showConfirmDialog(null, "Do You want to save "+e.getX()+","+e.getY()+" as center of Four Circle");
            if(i==0){
                points.x4=e.getX();
                points.y4=e.getY();
            }
        }     
        if(points.x1!=-1 && points.x2!=-1 && points.x3!=-1 && points.x4!=-1){
            int i=JOptionPane.showConfirmDialog(null, "All Required Data are Gathered Do You want to mark the sheet");
            if(i==0){
                points.radius=6;
                canvas.setVisible(false);
                MarkingInterface.points=points;
                MarkingInterface mk=new MarkingInterface();
                mk.setVisible(true);
                
            }
        }
     }
         catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
         }
    }
     class ColorPanel extends JPanel{
	BufferedImage theCat;
	public ColorPanel(BufferedImage image){
	theCat = image;
	}
 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(theCat, null,0,0);
	}
    }
}
