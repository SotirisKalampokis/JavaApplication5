/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.io.FileReader;
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
 
            Object obj = P.parse(new FileReader("C:\\Users\\Sotiris\\Desktop\\Devices.Json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            iDidThis jsonCrete = new iDidThis();
            jsonCrete.iDidThisSmile(jsonObject);

 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    
}
