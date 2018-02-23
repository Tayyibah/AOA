/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Basham
 */
public class CreateTemplete {
    
    public static void create(){
        Static.template=new int[10][1][];
        int i=0;
        for(int j=0;j<10;j++){
            Static.template[i][0]=readFile(j+".txt");
            i++;
        }
        
    }
    public static int[] readFile(String filename){
        try{
            int[] arr=new int[40*200];
            FileReader fr=new FileReader("character_template\\"+filename);
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
}
