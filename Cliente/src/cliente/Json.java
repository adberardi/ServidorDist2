/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Marvian
 */

//Clase para el manejo de los archivos
public class Json {
    
    //Metodo para escribir la ip de mi vecino
    public static void EscriboIpSiguiente (String Ip){
	File archivo = null;
		
	JSONObject JsonAlmacen = new JSONObject();
			
		JsonAlmacen.put("AlmacenSig",(Ip));
            
           
            try{
			
		File miDir = new File (".");
		archivo = new File (miDir.getCanonicalPath()+"/AlmacenSig.Json");
			
		if (!archivo.exists())
                    archivo.createNewFile();
			
                    FileWriter fw = new FileWriter (archivo.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(JsonAlmacen.toJSONString());
                    bw.close();
		}
		catch (IOException e){		
		}		
    }
    
    // Metodo para leer la ip guardada de mi vecino
     public static String LeerAlmacen() throws IOException{
		
	File archivo = null;
	JSONParser parseando = new JSONParser();
        String AlmacenSigIp;
		
		
	try{
            File miDir = new File (".");
            archivo = new File (miDir.getCanonicalPath() +"/AlmacenSig.Json");
            FileInputStream fis = new FileInputStream(archivo);			 
            Object objeto = parseando.parse(new InputStreamReader(fis));	
            JSONObject objetoJson = (JSONObject) objeto;
				
              
            AlmacenSigIp = (String) objetoJson.get("AlmacenSig");
                
                
                
                return AlmacenSigIp;
            }
            
            catch(Exception e) {System.out.println(e);}
        
            return null;            
		

	}
    
    
}
