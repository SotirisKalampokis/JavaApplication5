/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Sotiris
 */
public class JustMathsBro {

public void JustMathsBroCayote(Object ob){
    JSONObject JSob = (JSONObject) ob;
    JSONArray Div = (JSONArray) JSob.get("Devices");
    String s="";
    s=s+("{\n\t\"Devices\":[\n");
    NormalDistribution nd = new NormalDistribution(20,1.5);
    NormalDistribution ndOn = new NormalDistribution(22,1.5);
    UniformIntegerDistribution ud = new UniformIntegerDistribution(0,72);
    double Winter = 10.06;
    double Spring = 15.8;
    double Summer = 26.06;
    double Fall = 18.66;
        for(Object sdiv : Div ){
        JSONObject Items = (JSONObject) sdiv;
        JSONArray Hou = (JSONArray) Items.get("onhours");
        JSONArray House =(JSONArray) Items.get("onhouse");
        ArrayList<Integer> uniqeTimesRun = new ArrayList<Integer>();
        int onkwh = Integer.parseInt(Items.get("devkw").toString());
        double onKw = onkwh / 60.0;
        //int stbkwh = Integer.parseInt(Items.get("stbkw").toString());
        //double stbKw = stbkwh / 60.0;
        int tempNaM = Integer.parseInt(Items.get("period").toString());
        int timesRun = Integer.parseInt(Items.get("timesrun").toString());
        if (timesRun > 73){
            timesRun=73;
        }else if(timesRun<0){
            timesRun=0;
        }
        int hours = 0;
        int mins = 0;
        double nowKw=0;
        int i = 0;
        double temp = 0;
        int utr = 0;
        int x = 0;
        int r = 0;
        int timeRun = 0;
        double rand=0;
        //System.out.print("New\n");
        for(int k=0;k<timesRun;k++){
            do{
                utr=ud.sample();
            }while(uniqeTimesRun.contains(utr));
            uniqeTimesRun.add(utr);
            //System.out.print(uniqeTimesRun.get(k)+"\n");
        }
        uniqeTimesRun.sort(null);
       
        //Temp start
        if (tempNaM==1){
            temp=Winter;
        }else if (tempNaM==2){
            temp=Spring;
        }else if (tempNaM==3){
            temp=Summer;
        }else if (tempNaM==4){
            temp=Fall; 
        }    
        NormalDistribution ndTemp = new NormalDistribution(temp,2);
        double tempur = ndTemp.sample();
        s=s+("\t\t{\n\t\t\t\"devid\" : " + Items.get("devid")+",\n\t\t\t\"devtype\" : \""+Items.get("devtype")+"\",\n"+"\t\t\t\"Temp\" : \""+tempur+"\",\n");
        s=s+("\t\t\t\"OnHours\":{\n");
        for(Object OnHours : Hou ){
            i++;
            //nowKw=nd.sample()*stbKw;
            if(OnHours.toString().equals("1")){
                while(hours<=6*i){
                    //test for now 
                    timeRun=uniqeTimesRun.get(r);
                    if(timeRun==x){
                        nowKw = ndOn.sample()*onKw;
                        r++;
                        if(timesRun==r){
                            r--;
                        }
                    }
                    x++;
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":["+nowKw+",");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":["+nowKw+",");

                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else if(hours>=10 && mins<20){
                       s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");

                    }else{
                       s=s+("\t\t\t\t\""+hours+":"+mins+"\":["+nowKw+",");

                    }
                    rand=Math.random();
                    if(House.get(i-1).toString().equals("1")){
                        if(rand < 0.85){
                            s=s+("1],\n");
                        }else{
                            s=s+("0],\n");
                        }
                    }else if(House.get(i-1).toString().equals("1") && hours==24){
                        if(rand < 0.85){
                            s=s+("1]\n");
                        }else{
                            s=s+("0]\n");
                        }
                    }else if(House.get(i-1).toString().equals("0") && hours==24){
                        s=s+("0]\n");
                    }else if(House.get(i-1).toString().equals("0")){
                        s=s+("0],\n");
                    }
                    mins = mins + 20;
                    nowKw=0;
                    if(hours ==6*i)break;
                    if(mins == 60){
                        hours++;
                        mins=0;
                    }
                }
                

            }else{
                while(hours<=6*i){
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":["+nowKw+",");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":["+nowKw+",");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":["+nowKw+",");
                    }
                    if(House.get(i-1).toString().equals("1")){
                        if(rand < 0.85){
                            s=s+("1],\n");
                        }else{
                            s=s+("0],\n");
                        }
                    }else if(House.get(i-1).toString().equals("1") && hours==24){
                        if(rand < 0.85){
                            s=s+("1]\n");
                        }else{
                            s=s+("0]\n");
                        }
                    }else if(House.get(i-1).toString().equals("0") && hours==24){
                        s=s+("0]\n");
                    }else if(House.get(i-1).toString().equals("0")){
                        s=s+("0],\n");
                    }

                    mins = mins + 20;
                    if(hours ==6*i)break;
                    if(mins == 60){
                        hours++;
                        mins=0;
                    }
                //System.out.println(House.get(i-1).toString().equals("1"));
                }
            }
        }        
        s=s+("\t\t\t}\n");
        s=s+("\t\t},\n");
        JSONArray SbHou = (JSONArray) Items.get("stbhours");
        i = 0;
        onkwh = Integer.parseInt(Items.get("stbkw").toString());
        onKw = onkwh / 60.0;
        hours = 0;
        mins = 0;
        nowKw=0;
        s=s+("\t\t{\n\t\t\t\"devid\" : " + Items.get("devid")+",\n\t\t\t\"devtype\" : \""+Items.get("devtype")+"\",\n"+"\t\t\t\"Temp\" : \""+tempur+"\",\n");
        s=s+("\t\t\t\"StbHours\":{\n");
        for(Object StbHours : SbHou ){
            i++;  
            if(StbHours.toString().equals("1")){
                while(hours<=6*i){
                    
                    timeRun=uniqeTimesRun.get(r);
                    if(timeRun==x){
                        nowKw = nd.sample()*onKw;
                        r++;
                        if(timesRun==r){
                            r--;
                        }

                    }
                    x++;
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":["+nowKw+",");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":["+nowKw+",");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":["+nowKw+",");
                    }
                    rand=Math.random();
                    if(House.get(i-1).toString().equals("1")){
                        if(rand < 0.85){
                            s=s+("1],\n");
                        }else{
                            s=s+("0],\n");
                        }
                    }else if(House.get(i-1).toString().equals("1") && hours==24){
                        if(rand < 0.85){
                            s=s+("1]\n");
                        }else{
                            s=s+("0]\n");
                        }
                    }else if(House.get(i-1).toString().equals("0") && hours==24){
                        s=s+("0]\n");
                    }else if(House.get(i-1).toString().equals("0")){
                        s=s+("0],\n");
                    }
                    mins = mins + 20;
                    nowKw=0;
                    if(hours ==6*i)break;
                    if(mins == 60){
                        hours++;
                        mins=0;
                    }
                }
            }else{
                while(hours<=6*i){
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":["+nowKw+",");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":["+nowKw+",");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":["+nowKw+",");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":["+nowKw+",");
                    }
                    rand=Math.random();
                    if(House.get(i-1).toString().equals("1")){
                        if(rand < 0.85){
                            s=s+("1],\n");
                        }else{
                            s=s+("0],\n");
                        }
                    }else if(House.get(i-1).toString().equals("1") && hours==24){
                        if(rand < 0.85){
                            s=s+("1]\n");
                        }else{
                            s=s+("0]\n");
                        }
                    }else if(House.get(i-1).toString().equals("0") && hours==24){
                        s=s+("0]\n");
                    }else if(House.get(i-1).toString().equals("0")){
                        s=s+("0],\n");
                    }
                    mins = mins + 20;
                    if(hours ==6*i)break;
                    if(mins == 60){
                        hours++;
                        mins=0;
                    }
                }
                
            }
        }
        s=s+("\t\t\t}\n");
        s=s+("\t\t},\n");
        uniqeTimesRun.clear();
    }
 
    s=s.substring(0,s.length()-2);
    s=s+("\n\t]\n");
    s=s+("}\n");
    String desktopPath = System.getProperty("user.home") + "/Desktop";
    try (FileWriter file = new FileWriter(desktopPath+"/Over.Json")) {

            file.write(s);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
 

