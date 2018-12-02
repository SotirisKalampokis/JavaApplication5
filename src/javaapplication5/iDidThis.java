/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Sotiris
 */
public class iDidThis {
    
public void iDidThisSmile(Object ob){
    JSONObject JSob = (JSONObject) ob;
    JSONArray Div = (JSONArray) JSob.get("Devices");
    String s="";
    s=s+("{\n\t\"Devices\":[\n");
    for(Object sdiv : Div ){
        JSONObject Items = (JSONObject) sdiv;
        JSONArray Hou = (JSONArray) Items.get("onhours");
        int onkwh = Integer.parseInt(Items.get("devkw").toString());
        double onKw = onkwh / 60.0;
        int hours = 0;
        int mins = 0;
        double nowKw=0;
        int i = 0;

        s=s+("\t\t{\n\t\t\t\"devid\" : " + Items.get("devid")+",\n\t\t\t\"devtype\" : \""+Items.get("devtype")+"\",\n");
        s=s+("\t\t\t\"OnHours\":{\n");
        for(Object OnHours : Hou ){
            i++;
            if(OnHours.toString().equals("1")){
                while(hours<=6*i){
                    //test for now 
                    Random rand = new Random();
                    if(rand.nextBoolean()){
                        nowKw = 20*onKw;
                    }
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":"+nowKw+",\n");

                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+"\n");
                    }else if(hours>=10 && mins<20){
                       s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+",\n");

                    }else{
                       s=s+("\t\t\t\t\""+hours+":"+mins+"\":"+nowKw+",\n");

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
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":"+nowKw+",\n");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+"\n");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":"+nowKw+",\n");
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
        JSONArray SbHou = (JSONArray) Items.get("stbhours");
        i = 0;
        onkwh = Integer.parseInt(Items.get("stbkw").toString());
        onKw = onkwh / 60.0;
        hours = 0;
        mins = 0;
        nowKw=0;
        s=s+("\t\t{\n\t\t\t\"devid\" : " + Items.get("devid")+",\n\t\t\t\"devtype\" : \""+Items.get("devtype")+"\",\n");
        s=s+("\t\t\t\"StbHours\":{\n");
        for(Object StbHours : SbHou ){
            i++;
            if(StbHours.toString().equals("1")){
                while(hours<=6*i){
                    //test for now 
                    Random rand = new Random();
                    if(rand.nextBoolean()){
                        nowKw = 20*onKw;
                    }
                    if(hours<10 && mins<20){
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":"+nowKw+",\n");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+"\n");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":"+nowKw+",\n");
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
                        s=s+("\t\t\t\t\""+"0"+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else if (hours<10){
                        s=s+("\t\t\t\t\""+"0"+hours+":"+mins+"\":"+nowKw+",\n");
                    }else if(hours==24){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+"\n");
                    }else if(hours>=10 && mins<20){
                        s=s+("\t\t\t\t\""+hours+":0"+mins+"\":"+nowKw+",\n");
                    }else{
                        s=s+("\t\t\t\t\""+hours+":"+mins+"\":"+nowKw+",\n");
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
        
    }
    s=s.substring(0,s.length()-2);
    s=s+("\n\t]\n");
    s=s+("}\n");
    try (FileWriter file = new FileWriter("C:\\Users\\Sotiris\\Desktop\\test.Json")) {

            file.write(s);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
