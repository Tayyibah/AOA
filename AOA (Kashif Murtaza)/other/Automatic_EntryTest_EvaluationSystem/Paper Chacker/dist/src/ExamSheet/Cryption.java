/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExamSheet;

import automaticpapercheker.RotationDetector;

/**
 *
 * @author Basham
 */
public class Cryption {
    
    public static String encrypt(String roolnumber){
        char rollnum[]=roolnumber.toCharArray();
        String deg=roolnumber.substring(0,3);
        String enRoll="";
        if(deg.equals("BIT")){
            enRoll="0";
            
        }
        else if(deg.equals("BCS")){
            enRoll="1";
            
        }
        else if(deg.equals("BSE")){
            enRoll="2";
        }
        else if(deg.equals("MIT")){
            enRoll="3";
        }
        else if(deg.equals("MCS")){
            enRoll="4";
        }
        else if(deg.equals("MPH")){
            enRoll="5";
        }
        else if(deg.equals("PHD")){
            enRoll="6";
        }
        if(rollnum[3]=='F'){
            enRoll=enRoll+"0";
        }
        else{
            enRoll=enRoll+"1";
        }
        enRoll=enRoll+rollnum[4]+rollnum[5];
        if(rollnum[6]=='A'){
            enRoll=enRoll+"0";
        }
        else{
            enRoll=enRoll+"1";
        }
        enRoll=enRoll+rollnum[7]+rollnum[8]+rollnum[9];
        return enRoll;
    }
    
    public static String decrypt(String roolnumber){
        char rollnum[]=roolnumber.toCharArray();
        String deg=rollnum[0]+"";
        String enRoll="";
        if(deg.equals("0")){
            enRoll="BIT";
            
        }
        else if(deg.equals("1")){
            enRoll="BCS";
            
        }
        else if(deg.equals("2")){
            enRoll="BSE";
        }
        else if(deg.equals("3")){
            enRoll="MIT";
        }
        else if(deg.equals("4")){
            enRoll="MCS";
        }
        else if(deg.equals("5")){
            enRoll="MPH";
        }
        else if(deg.equals("6")){
            enRoll="PHD";
        }
        if(rollnum[1]=='0'){
            enRoll=enRoll+"F";
        }
        else{
            enRoll=enRoll+"S";
        }
        enRoll=enRoll+rollnum[2]+rollnum[3];
        if(rollnum[4]=='0'){
            enRoll=enRoll+"A";
        }
        else{
            enRoll=enRoll+"M";
        }
        enRoll=enRoll+rollnum[5]+rollnum[6]+rollnum[7];
        return enRoll;
    }
    
    public static  void main(String args[]){
        Cryption cr=new Cryption();
        String en=cr.encrypt("MITS09M027");
        double angle=RotationDetector.findAngel(553, 43, 114, 713);
        System.out.println(angle);
    }
}
