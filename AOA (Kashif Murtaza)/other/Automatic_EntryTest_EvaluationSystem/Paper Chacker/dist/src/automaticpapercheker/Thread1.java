/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticpapercheker;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

/**
 *
 * @author Basham
 */
public class Thread1
{

    //Main class
    //Declare GUI components
    public Thread1() {
        createJUI();
    }
    
	static JFrame frmMain;
	static Container pane;
	static JLabel label;
	static JProgressBar barDo;
        static JLabel icon;
	public void createJUI(){ //Main void
		//Set Look and Feel
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, ex);
                }

		//Create all components
		frmMain = new JFrame("Sample progress bar application");
		frmMain.setSize(300, 100); //Window size 300x100 pixels
		pane = frmMain.getContentPane();
		pane.setLayout(null); //Use the null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit when X is clicked
		label=new JLabel("Loading Files ........");
		barDo = new JProgressBar(0, 100); //Min value: 0 Max value: 100	
                label.setFont(new Font("Traditional Arabic", Font.BOLD, 24));
		//Add components to pane
		pane.add(label);
		pane.add(barDo);
                icon=new JLabel();
                Icon ic = new ImageIcon("logo.jpg");
                icon.setIcon(ic);
                icon.setBounds(0, 0,800,100);
		//Position controls (X, Y, width, height)
		barDo.setBounds(160, 300, 500, 30);
		label.setBounds(250, 225, 300, 30);
                pane.add(icon);
                frmMain.setBackground(new Color(224, 191, 125, 255));
                pane.setBackground(new Color(224, 191, 125, 255));
		//Make frame visible
                frmMain.setSize(800,505);
                frmMain.setLocation(300, 50);
		frmMain.setResizable(false); //No resize
		frmMain.setVisible(true);

		//Add action listeners
		//btnDo.addActionListener(new btnDoAction()); //Add the button's action
//                new Thread(new FileLoading.thread1()).start();
                new Thread(new thread1()).start();
	}
        
        public static void main(String args[]){
            new Thread1();
        }
        /////////////////////////////////
        
  
        
        
        ////////////////////////////////

	//The action
	public  class btnDoAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			new Thread(new thread1()).start(); //Start the thread
		}
	}

	//The thread
	public  class thread1 implements Runnable {
            boolean flag=false;
            int NumOfFiles=0;
            public void run(){
                   while(FileLoader.numOfFiles==-1){
                       try{
                            Thread.sleep(2000);
                       }
                       catch(Exception ex){
                           JOptionPane.showMessageDialog(null, ex);
                       }
                   }
                   
                   NumOfFiles=FileLoader.numOfFiles;
                   double value=100/NumOfFiles; 
                   int i=0;
                    while(i<NumOfFiles){
                        
                         barDo.setValue((int)((i+1)*value)); //Set value
                         barDo.repaint(); //Refresh graphics
                         i=FileLoader.files.size();
                         try{
                            Thread.sleep(2000);
                       }
                       catch(Exception ex){
                           JOptionPane.showMessageDialog(null, ex);
                       }
                    }
                    FileLoader.flag=true;
                    frmMain.setVisible(false);
                    
                   
            }
                
	}
}
