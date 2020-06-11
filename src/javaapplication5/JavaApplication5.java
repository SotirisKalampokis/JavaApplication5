/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Sotiris
 */
public class JavaApplication5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JSONParser P = new JSONParser();
        try {
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            Object obj = P.parse(new FileReader(desktopPath+"/Devices.Json"));
            ArrayList<Double> Under= new ArrayList<Double>();
            ArrayList<Double> Over= new ArrayList<Double>();
            ArrayList<Double> Normal= new ArrayList<Double>();
            JSONObject jsonObject = (JSONObject) obj;
            Simulation jsonObj = new Simulation();
            Normal=jsonObj.SimulationStart(jsonObject,20);
            Under=jsonObj.SimulationStart(jsonObject,19);
            Over=jsonObj.SimulationStart(jsonObject,22);
            JSONArray Div = (JSONArray) jsonObject.get("Devices");
            String s= "";
            s = s+("{\n\t\"Devices\":[\n");
            int z=0;
            for(Object sdiv : Div ){
                JSONObject Items = (JSONObject) sdiv;
                 s=s+("\t\t{\n\t\t\t\"devid\" : " + Items.get("devid")+",\n\t\t\t\"devtype\" : \""+Items.get("devtype")+"\",\n\t\t\t\"Under\" : \""+Under.get(z)+"\",\n\t\t\t\"Over\" : \""+Over.get(z)+"\",\n\t\t\t\"Normal\" : \""+Normal.get(z)+"\"\n");
                 if(Div.size()==z){
                     s=s+("\t\t}\n");
                 }else{
                    s=s+("\t\t},\n");
                 }
                 z++;
            }
            s=s.substring(0,s.length()-2);
            s=s+("\n\t]\n");
            s=s+("}\n");
            try (FileWriter file = new FileWriter(desktopPath+"/Total.Json")){

                file.write(s);
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    
}
