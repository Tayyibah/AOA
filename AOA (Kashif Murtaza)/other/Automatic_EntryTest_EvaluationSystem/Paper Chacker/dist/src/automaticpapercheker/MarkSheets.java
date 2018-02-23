/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;
import ExamSheet.Cryption;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
/**
 *
 * @author Basham
 */
public class MarkSheets {
    Data refData;
    Data curData;
    int curmx;
    int curmy;
    double udy;
    public int verDis;
    public int herDis;
    public boolean stackflag=false;
    BufferedImage tic;
    BufferedImage cross;
        public void prepare(){
        int dx=Static.dx;
        int dy=Static.dy;
        refData.x1=refData.x1-dx;
        refData.x2=refData.x2-dx;
        refData.x3=refData.x3-dx;
        refData.x4=refData.x4-dx;
        refData.y1=refData.y1-dy;
        refData.y2=refData.y2-dy;
        refData.y3=refData.y3-dy;
        refData.y4=refData.y4-dy;
    }
    public MarkSheets(Data x){
        refData=x;
        curData=new Data();
        this.udy=0;
        verDis=0;
        herDis=0;
        tic=ImageUtils.loadImage("tic.png");
        cross=ImageUtils.loadImage("cross.png");
    }   
    public boolean[] Mark(){
        ArrayList<Sheet> sheets=FileLoader.files;
        prepare();
        int result[]=new int[Static.totalQuestion];
        boolean [] status=new boolean[sheets.size()];
        for( int l=0;l<sheets.size();l++){
            ArrayList<Point> index=new ArrayList<>();
            BufferedImage im=null;
            try {
                im = readjust(sheets.get(l));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
            String rollNum=sheets.get(l).getRollNum();
            rollNum= Cryption.decrypt(rollNum);
            int y=curData.y1-curData.radius+1,x=0;
            int height=(curData.radius*2)+4; 
            int width=(curData.radius*2)+4;
            int dis=curData.x2-curData.x1;
            int vdis=16;
            for(int i=0;i<Static.totalQuestion;i++){
                if(i%2==0)
                    x=curData.x1-curData.radius;
                else
                    x=curData.x3-curData.radius;
                BufferedImage circle1=im.getSubimage(x-1, y-1, height, width);
                BufferedImage circle2=im.getSubimage(x+dis-1, y-1, height, width);
                BufferedImage circle3=im.getSubimage(x+(dis*2)-1, y-1, height, width);
                BufferedImage circle4=im.getSubimage(x+(dis*3)-1, y-1, height, width);
                index.add(new Point(x, y));
                int rs=0;
                if(i%2==1){
                    rs=getResult(circle1, circle2, circle3, circle4,true);
                    y=y-verDis;
                    curData.x1=curData.x1-herDis;
                    curData.x3=curData.x3-herDis;
                    verDis=0;
                    herDis=0;
                }
                else{
                    rs=getResult(circle1, circle2, circle3, circle4,false);
                }
                result[i]=rs;
                if(i%2==1){
                    y=y+vdis;

                }
                
            }
            saveResult(result,rollNum,im,index);
            
        }
         return status;
    }
   
     public String getSerialNumber(int ser){
          if(ser<10){
              return "000"+ser;
          }
          else if(ser<100){
              return "00"+ser;
          }
          else if(ser<100){
              return "0"+ser;
          }
          else {
              return ser+"";
          }
     } 
    
    
    public void findCenter(BufferedImage im){
        int heigh=im.getHeight();
        int width=im.getWidth();
        int arr[]=ImageUtils.readLuminance(im);
        int m=0,k=0,sumy=0 ,sumx=0;
        for(int i=0;i<width;i++){
            for(int j=0;j<heigh;j++){
                if(arr[m]<180){
                    k++;
                    sumy=sumy+i;
                    sumx=sumx+j;
                }
                m++;
            }
        
        }
        int cy=sumy/k;
        int cx=sumx/k;
        if(7-cy>1||7-cy<-1){
            verDis=7-cy;
        }
        else if(7-cx>1||7-cx<-1){
            herDis=7-cx;
        }
         
       
    }
    public BufferedImage readjust(Sheet im)throws Exception{
        BufferedImage sheet=getSheet(im);
        int x=sheet.getWidth()-20;
        int y=sheet.getWidth()-21;
        double xdiff=x-refData.mrkx;
        curmx=x;
        curmy=21;
        if(xdiff>1 ||xdiff<-1){
            double frac=xdiff/refData.mrkx;
            curData.x1=(int)(refData.x1+Math.round(frac*refData.x1));
            curData.x2=(int)(refData.x2+Math.round(frac*refData.x2));
            curData.x3=(int)(refData.x3+Math.round(frac*refData.x3));
            curData.x4=curData.x1;
            frac=(y-refData.mrky)/refData.mrky;
            curData.y1=(int)(refData.y1+Math.round(frac*refData.y1));
            curData.y2=curData.y3=curData.y1;
            curData.y4=(int)(refData.y4+Math.round(frac*refData.y4));
            curData.radius=refData.radius;
        }
        else{
            curData.x1=refData.x1;
            curData.x2=refData.x2;
            curData.x3=refData.x3;
            curData.x4=refData.x4;
            curData.y1=refData.y1;
            curData.y2=refData.y2;
            curData.y3=refData.y3;
            curData.y4=refData.y4;
            curData.radius=refData.radius;
            curData.mrkx=refData.mrkx;
            curData.mrky=refData.mrky;
        }
        return sheet;
   }
    public static BufferedImage getSheet(Sheet im)throws Exception{
        BufferedImage sheet=im.image.getSubimage(im.lowerMarker.x-21, im.uperMarker.y-20,(im.uperMarker.x+20)-(im.lowerMarker.x-21),(im.lowerMarker.y+20)-(im.uperMarker.y-20)); 
        return sheet;    
   }
    
    
    public void saveResult(int result[],String filename,BufferedImage img,ArrayList<Point> index){
        try{
            FileWriter fw=new FileWriter(PaperChaker.directory+"\\"+filename+".txt");
            PrintWriter pw=new PrintWriter(fw);
            int count=0;
            boolean rs[]=new boolean[Static.totalQuestion];
            //System.out.println(filename);
            for(int i=0;i<result.length;i++){
              //  System.out.println((i+1)+"   "+result[i]);
                String acrs=Static.solution.get(i);
                char c=(char)(result[i]+96);
                if(acrs.equals(c+"")){
                    pw.println("True");
                    rs[i]=true;
                    count++;
                }
                else{
                    rs[i]=false;
                    pw.println("False");
                }
                pw.println("False");
            }
            BufferedImage sheet=img;
            pw.println("Total True = "+count);
            pw.println("Total False = "+(result.length-count));
            pw.println("Total Question= "+(Static.totalQuestion));
            fw.close();
            pw.close();
            Graphics2D g = (Graphics2D)sheet.getGraphics();
            for(int i=0;i<Static.totalQuestion;i++){
                 Point p=index.get(i);
                
                if(rs[i]){
                    g.drawImage(tic,p.x-20,p.y, null);
                }
                else{
                    g.drawImage(cross,p.x-20,p.y, null);
                }
            }
            ImageIO.write(sheet, "jpg",new File(PaperChaker.directory+"\\"+filename+".jpg"));

            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public int getResult(BufferedImage c1,BufferedImage c2,BufferedImage c3,BufferedImage c4,boolean flag){
        try{
            boolean res=isFilled(ImageUtils.readLuminance(c1));
            int i=-1;
            if(res==true){
                if(flag){
                   findCenter(c1);
                }
                if(i==-1){
                    i=1;
                }
                else{
                   return 0; 
                }
                
            }
            res=isFilled(ImageUtils.readLuminance(c2));
             if(res==true){
                 if(flag){
                    findCenter(c2);
                    
                }
                if(i==-1){
                    i=2;
                }
                else{
                   return 0; 
                }
                
            }
            res=isFilled(ImageUtils.readLuminance(c3));
             if(res==true){
                 if(flag){
                    findCenter(c3);
                   
                }
                if(i==-1){
                    i=3;
                }
                else{
                   return 0; 
                }
                
            }
            res=isFilled(ImageUtils.readLuminance(c4));
             if(res==true){
                 if(flag){
                    findCenter(c4);
                    
                }
                if(i==-1){
                    i=4;
                }
                else{
                   return 0; 
                }
                
            }
             return i;
            
           
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
         return 0;
    }
    public static boolean isFilled(int data[]){
        float intensity=0;
        for(int i=0;i<data.length;i++){
            if(data[i]<180){
                intensity++;
            }
        }
        int r=(int)((intensity/data.length)*100);
        if(r>20){
            return true;
        }
        return false;
        
    }
    
    //////////////////////////
    public void compareResult(String file,int arr[],BufferedImage img, ArrayList<Point> index)throws Exception{
       ArrayList<String> solution=new ArrayList<>();
       BufferedImage sheet=img;
       System.out.println(file);
       int count=0;
        try{
            FileReader fr =new FileReader("D:\\Software\\Study Software\\downloads\\sheets\\"+ file);
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            while(line!=null){
                solution.add(line);
                line=br.readLine();
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        Graphics2D g = (Graphics2D)sheet.getGraphics();
        boolean rs[]=new boolean[Static.totalQuestion];
         for(int i=0;i<Static.totalQuestion;i++){
             Point p=index.get(i);
                String acrs=solution.get(i);
                char c=(char)(arr[i]+96);
                if(acrs.equals(c+"")){
                    rs[i]=true;
                }
                else if(acrs.equals(0+"")&& arr[i]==0){
                    rs[i]=true;
                }
                else if(acrs.equals(-1+"")&& arr[i]==-1){
                    rs[i]=true;
                }
                else{
                    rs[i]=false;
                    //g.drawImage(cross,p.x-20,p.y, null);
                    count++;
                }
            }
         for(int i=0;i<Static.totalQuestion;i++){
             Point p=index.get(i);
                String acrs=solution.get(i);
                char c=(char)(arr[i]+96);
                
                if(rs[i]){
                    g.drawImage(tic,p.x-20,p.y, null);
                }
                else{
                    g.drawImage(cross,p.x-20,p.y, null);
                }
            }
         ImageIO.write(sheet, "jpg",new File("resultSheet\\"+file+".jpg"));
         System.out.println("Mismatch  "+count);
    }
}
