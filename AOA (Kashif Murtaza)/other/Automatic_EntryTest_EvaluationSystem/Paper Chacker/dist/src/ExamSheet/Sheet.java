/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamSheet;

/**
 *
 * @author HP
 */
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.BadElementException;
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
//import com.lowagie.text.Image;
//import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfContentByte;
//import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Sheet {
 
    /** The resulting PDF file. */
    PdfWriter writer;
    int []optionList;
    int totalQuestions;
    String testTitle;
    String path;
    String RollFileName;
    ArrayList<String>roolNumbers;
    
    /**
     * Main method.
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public Sheet(int []list,int totalQuestions,String path,String title,String roolNumFile )throws Exception{
        optionList=list;
        this.totalQuestions = totalQuestions;
        testTitle=title;
        this.path=path;
        RollFileName=roolNumFile;
        createPdf();
    }
    
    public void readRollNumber(){
        try{
            roolNumbers=new ArrayList<>();
            FileReader fr=new FileReader(RollFileName);
            BufferedReader br=new BufferedReader(fr);
            String roolnum=br.readLine();
            while(roolnum!=null){
                roolNumbers.add(roolnum);
                roolnum=br.readLine();
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
            
        }
    }
    public Sheet() {
    }

   
   
  
    /**
     * Creates a PDF with information about the movies
     * @param    filename the name of the PDF file that will be created.
     * @throws    DocumentException 
     * @throws    IOException
     */
    public void createPdf()
        throws IOException, DocumentException {
        readRollNumber();
    	// step 1
        Document document = new Document(PageSize.A4);//PageSize.A4;
        // step 2
        if(!path.isEmpty()){
            writer =PdfWriter.getInstance(document, new FileOutputStream(path+"\\"+testTitle+".pdf"));
        }
        else{
            writer =PdfWriter.getInstance(document, new FileOutputStream(testTitle+".pdf"));
        }
        
        // step 3
        document.open();
        // step 4
       for(int i=0;i<roolNumbers.size();i++){
        document.newPage();
        document.add(createTable(roolNumbers.get(i),1,totalQuestions));
       }
        // step 5
        document.close();
    }
 
    /**
     * Creates our first table
     * @return our first table
     */
    public PdfPTable createTable(String rollNum,int start,int end) throws DocumentException, BadElementException, MalformedURLException, IOException {
    	// a table with three columns
        
        PdfPTable table2 = new PdfPTable(3);
        
        FontFactory.register("c:/windows/fonts/arial.ttf", "arial");
        float[] columnWidths = new float[] {15f,90f,60f};
        table2.setWidths(columnWidths);
       
        PdfPCell cell2;
        
        Image image = Image.getInstance("logo1.jpg");
        cell2 = new PdfPCell(image, true);
        cell2.setBorder(0);
        //cell.setPaddingLeft(40f);
        cell2.setLeft(500f);
        cell2.setMinimumHeight(26f);
        //cell2.setColspan(2);
        //cell2.setBackgroundColor(Color.LIGHT_GRAY);
        //cell.setRowspan(1);
        
        table2.addCell(cell2);
         
        cell2=new PdfPCell(new Phrase(testTitle+"\nRoll #: "+rollNum,FontFactory.getFont("arial", 12, Font.NORMAL, Color.black)));
        cell2.setBorder(0);
        //cell.setPaddingLeft(40f);
        cell2.setRight(700f);
        cell2.setMinimumHeight(26f);
        //cell2.setColspan(2);
        //cell2.setBackgroundColor(Color.LIGHT_GRAY);
        //cell.setRowspan(1);
        table2.addCell(cell2);
        cell2=new PdfPCell(new Phrase(getSpacing(Cryption.encrypt(rollNum)),FontFactory.getFont("arial", 20, Font.BOLD, Color.black)));
        cell2.setBorder(0);
        //cell2.setColspan(2);
        //cell2.setBackgroundColor(Color.LIGHT_GRAY);
        table2.addCell(cell2);

        
        
        
         PdfPTable table = new PdfPTable(2);
          
         PdfPCell cell;
         cell=new PdfPCell();
//         cell.setColspan(4);
//         cell.addElement(table2);
//         cell.setBorder(0);
//         table.addCell(cell);
         float[] columnWidths2 = new float[] {40f,40f};
         table.setWidths(columnWidths2);
         PdfContentByte cb = writer.getDirectContent();  
         cb.circle(550, 800, (float)1);
         cb.fillStroke();
         cb.circle(550, 800, (float)6);
         cb.stroke();
         cb.circle(550, 800, (float)11);
         cb.stroke();
         cb.circle(550, 800, (float)16);
         cb.stroke();
         cb.circle(80, 80, (float)2);
         cb.stroke();
         cb.circle(80, 80, (float)5);
         cb.stroke();
         cb.circle(80, 80, (float)8);
         cb.stroke();
         cb.circle(80, 80, (float)11);
         cb.stroke();
         cb.circle(80, 80, (float)14);
         cb.stroke();
         cb.circle(80, 80, (float)17);
         cb.stroke();
         
         int ctr=0;
        // we add a cell with colspan 3
        int x1=150,y1=739,x2=360,x=0;

        cell = new PdfPCell(new Phrase("                 A      B       C       D"));
        cell.setBorder(0);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("                 A      B       C       D"));
        cell.setBorder(0);
        table.addCell(cell);
        PdfPCell temp=new PdfPCell(new Phrase(""));
        temp.setBorder(0);
        table.addCell(temp);
        table.addCell(temp);
        for(int i=start;i<=end;i++)
        {
            if(i%2==1)
                x=x1;
            else
                x=x2;
            
            cell = new PdfPCell(new Phrase(i+""));
            
            cell.setBorder(0);
            //cell.setHorizontalAlignment(2);
            table.addCell(cell);
            int inc=0;
            for(int j=0;j<optionList[i-1];j++)
            {
                cb.circle(x+inc,y1, 5);
                
                cb.stroke();
                float r=(float)0.7;
                cb.circle(x+inc,y1,r);
                cb.fill();
                inc=inc+30;
            }            
            
            if(i%2==0)
                y1=y1-16;
//            
            
        }
        PdfPTable t=new PdfPTable(1);
        PdfPCell c=new PdfPCell(table2);
        c.setBorder(0);
        t.addCell(c);
        PdfPCell c1=new PdfPCell(table);
        c1.setBorder(0);
        t.addCell(c1);
        return t;
    }
    public String getSerialNumber(int i){
        if(i<10)
            return "000"+i;
        else if(i<100)
            return "00"+i;
        else if(i<1000)
            return "0"+i;
        else
            return ""+i;
    }
    public String getSpacing(String rollnum){
        char[] arr=rollnum.toCharArray();
        String s="";
        for(int i=0;i<arr.length;i++){
            s=s+" "+arr[i];
        }
        return s;
    }
}

