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
public class OCR {
    int height;
    int width;
    int noc;
    public OCR(){
        CreateTemplete.create();
    }
    public String getRollNumber(BufferedImage im ){
        int h=im.getHeight();
        int w=im.getWidth();
        width=w;
        height=h;
        int imagen[]=ImageUtils.readLuminance(im);      
        imagen=im2bw(imagen,0.6353);
        imagen=lines(imagen);
        imagen=bwlabel(imagen);
        String rollNum="";
        //print(imagen, height);
        for(int k=0;k<noc;k++){
            ArrayList<Integer> loc =find(imagen, k+1);
            if(loc.size()>60){
                Matrix mat=imgn(loc);
                int [] img=resizeImage(convertToArray(mat), mat.width, mat.height, 24, 42);
                char c=read_Letter(img, 42);
                rollNum=rollNum+c+"";
            }
        }
        return rollNum;
        
    }
    public int[] convertToArray(Matrix m){
        int [] arr=new int[m.height*m.width];
        int l=0;
        for (int i=0;i<m.height;i++){
           for(int j=0;j<m.width;j++){
               arr[l]=m.data[i][j];
               l++;
           }
        }
        return arr;
    }
    public Matrix imgn(ArrayList<Integer> loc){
        Matrix m=new Matrix();
        int maxrows=getMax(loc, 1);
        int maxcols=getMax(loc, 0);
        int minrows=getMin(loc, 1);
        int mincols=getMin(loc, 0);
        m.setHeight(maxcols-mincols+1);
        m.setWidth(maxrows-minrows+1);
        int [][] pixel=new int[height][m.width];
        int l=0;int r=0,c=0;
        for(int i=0;i<loc.size()/2;i++){
            c=loc.get(l);
            l++;
            r=loc.get(l);
            l++;
            pixel[c-mincols][r-minrows]=1;
        }
        m.data=pixel;
        return m;
    }
    public int getMax(ArrayList<Integer> loc,int type){ 
        int i=0;
        if(type==1){
            i=1;
        }
        int max=loc.get(i);
        for(int j=0;j<loc.size()/2;j++){
            if(loc.get(i)>max){
                max=loc.get(i);
            }
            i=i+2;
        }
        return max;
    } 
    public int getMin(ArrayList<Integer> loc,int type){
        
        int i=0;
        if(type==1){
            i=1;
        }
        int min=loc.get(i);
        for(int j=0;j<loc.size()/2;j++){
            if(loc.get(i)<min){
                min=loc.get(i);
            }
            i=i+2;
        }
        return min;
    } 
    public ArrayList<Integer> find(int img[] , int n){
       int m=0;
       ArrayList<Integer> loc=new ArrayList<>();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(img[m]==n){
                    loc.add(i);
                    loc.add(j);
                    
                }
                m++;
            }
        }
        return  loc;
    }
    
    public int[] resizeImage(int[] pixels,int w1,int h1,int w2,int h2) {
        int[] temp = new int[w2*h2] ;
        double x_ratio = w1/(double)w2 ;
        double y_ratio = h1/(double)h2 ;
        double px, py ; 
        for (int i=0;i<h2;i++) {
            for (int j=0;j<w2;j++) {
                px = Math.floor(j*x_ratio) ;
                py = Math.floor(i*y_ratio) ;
                temp[(i*w2)+j] = pixels[(int)((py*w1)+px)] ;
            }
        }
        return temp ;
}
    public int[] im2bw(int arr[],double threshold){
        for(int i=0;i<arr.length;i++){
            double d=((double)arr[i])/255;
            if(d>=threshold){
                arr[i]=0;    
            }
            else{
                arr[i]=1;
            }
        }
        return arr;
    }
    public int[] readFile(){
        try{
            int[] arr=new int[40*200];
            FileReader fr=new FileReader("mat.txt");
            BufferedReader br =new BufferedReader(fr);
            String line=br.readLine();
            int m=0;
            while(line != null){
                String token[]=line.split("	");
                 for(int i=0;i<token.length;i++){
                    if(token[i].equals("false")){
                        arr[m]=0;
                    }
                    else{
                        arr[m]=1;
                    }
                    m++;
                }
                line=br.readLine();
            }
          
            return arr;
        }
        
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    
    
    public int [] bwlabel(int [] img){
        ArrayList<Integer>components=getComponents(img);
        int numOfCom=components.size()/2;
        noc=numOfCom;
        int start=0,end=0,l=1;
        for(int i=0;i<components.size()-1;i++){
            start=components.get(i);
            i++;
            end=components.get(i);
            img=labelimage(img, start, end, l);
            l++;
        }
        return img;
    }
    public int []labelimage(int [] img,int start,int end,int label){
        int index=0;
        for(int j=start;j<=end;j++){
            for(int i=0;i<height;i++){
                index=((width*i)+j);
                if(img[index]==1){
                    img[index]=label;
                }
            }   
        }
        return img;
    }
    public int [] removeColFromStart(int [] img){
        int newImg[]=new int[img.length-height];
        int m=0,n=1;
        for(int i=0;i<height;i++){
            for(int j=1;j<width;j++){
                newImg[m]=img[n];
                m++;n++;
            }
            n++;
        }
        return newImg;
    }
    public int [] removeColFromEnd(int [] img){
        int newImg[]=new int[img.length-height];
        int m=0,n=0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width-1;j++){
                newImg[m]=img[n];
                m++;n++;
            }
            n++;
        }
        return newImg;
    }
    public int [] removeRowFromStart(int [] img){
        int newImg[]=new int[img.length-width];
        int m=0;
        for(int i=width;i<img.length-width;i++){
            newImg[m]=img[i];
            m++;
        }
        return newImg;
    }
    public int [] removeRowFromEnd(int [] img){
        int newImg[]=new int[img.length-width];
        for(int i=0;i<img.length-width;i++){
            newImg[i]=img[i];
        }
        return newImg;
    }
    public ArrayList<Integer> getComponents(int []img){
        ArrayList<Integer> components=new ArrayList<>();
        boolean flag=true;
        for(int i=0;i<width;i++){
            if(flag){
                if(!isNullCol(img, i)){
                    components.add(i);
                    flag=false;
                }
            }
            else{
                if(isNullCol(img, i)){
                    components.add(i-1);
                    flag=true;
                }
            }
        }
        components.add(width-1);
        return components;
    }
    public boolean isNullCol(int [] img,int col){
        int index=0;
        for(int i=0;i<height;i++){
            index=((width*i)+col);
            if(img[index]==1){
                return false;
            }
        }
        return true;
    }
    public boolean isNullRow(int [] img,int row){
        int index=0;
        for(int i=0;i<width;i++){
            index=((width*row)+i);
            if(img[index]==1){
                return false;
            }
        }
        return true;
    }
    public int [] lines(int []img){
        int m=0;
        int w=width;
        for (int i=0;i<w;i++){ 
            boolean rs=isNullCol(img, 0);
            if(rs){
                img=removeColFromStart(img);
                width--;
            }
            else
                i=w;
        }
        for(int i=width-1;i>=0;i--){ 
            boolean rs=isNullCol(img, i);
            if(rs){
                img=removeColFromEnd(img);
                width--;
                
            }
            else{
                i=0;
            }
        }
        int h=height;
        for (int i=0;i<h;i++){ 
            boolean rs=isNullRow(img, 0);
            if(rs){
                img=removeRowFromStart(img);
                height--;
            }
            else{
                i=h;
            }
        
        }
        for(int i=height-1;i>=0;i--){ 
            boolean rs=isNullRow(img, i);
            if(rs){
                img=removeRowFromEnd(img);
                height--;
            }
            else{
                i=0;
            }
        }
        return img;
    }
    public double sumMatrix(double [] arr,int height){
        double sum=0;
        for(int i=0;i<arr.length;i++){
            sum=sum+arr[i];
        }
       return sum;
    }
    
    public char read_Letter(int mat[],int height){
        int max=-1;
        double maxsim=0,sim=0;
        for(int i=0;i<10;i++){
            sim=findSim(mat,Static.template[i][0], height, height);
            if(sim>maxsim){
                maxsim=sim;
                max=i;
            }
        }
        int vd=max;
        char letter='0';
        if(vd>=0 && vd<=9){
            letter=((char)(vd+48));
        }
        return letter;
        

    }
    public double findSim(int a[],int b[],int height_a,int height_b){
        double msa[]=subMean(a);
        double msb[]=subMean(b);
        double x=sumMatrix(dotPrduct(msa, msb),height_a);
        double y=sumMatrix(dotPrduct(msa, msa),height_a);
        double z=sumMatrix(dotPrduct(msb, msb),height_b);
        double sim=x/Math.sqrt(y*z);
        return sim;
        
    }
    public double[] dotPrduct(double a[],double b[]){
        double c[]=new double[a.length];
        for(int i=0;i<a.length;i++){
            c[i]=a[i]*b[i];
        }
        return c;
    } 
    public double[] subMean(int a[]){
        double []mean_sub=new double[a.length];
        double sum=0;
        for(int i=0;i<a.length;i++){
            sum=sum+a[i];
        }
        sum=sum/a.length;
        for(int i=0;i<a.length;i++){
            mean_sub[i]=a[i]-sum;
        }
        return mean_sub;
    }
       
}
