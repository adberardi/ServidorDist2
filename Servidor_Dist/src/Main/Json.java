/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Marvian
 */
public class Json {
    
    static ArrayList<Almacen> almacenes = new ArrayList<Almacen>();
     
    public static void Escribir(ArrayList<Almacen> listaAlmacenes){
		File archivo = null;
		
                
		JSONObject usuariosJson = new JSONObject();
		JSONArray almacenes = new JSONArray();
		for (int i=0 ; i < listaAlmacenes.size() ; i++){
			Almacen almacen = new Almacen();
			almacen.setNombre(listaAlmacenes.get(i).getNombre());
			almacen.setIp(listaAlmacenes.get(i).getIp());	
			JSONObject almacenJson = new JSONObject();
			almacenJson.put("Nombre",( listaAlmacenes.get(i).getNombre()));
			almacenJson.put("Ip", (listaAlmacenes.get(i).getIp()));
			almacenes.add(almacenJson);	
		}
		usuariosJson.put("Almacenes", almacenes);
		try{
			
			File miDir = new File (".");
			archivo = new File (miDir.getCanonicalPath()+"/Anillo.Json");
			
			if (!archivo.exists())
				archivo.createNewFile();
			
			FileWriter fw = new FileWriter (archivo.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(usuariosJson.toJSONString());
			bw.close();
		}
		catch (IOException e){		
		}
    }
    
    public static ArrayList<Almacen> Leer() throws IOException{
            File archivo = null;
		JSONParser parseando = new JSONParser();
		try{
		    File miDir = new File (".");
		    archivo = new File (miDir.getCanonicalPath() +"/Anillo.Json");
		    FileInputStream fis = new FileInputStream(archivo);			 
		    Object objeto = parseando.parse(new InputStreamReader(fis));	
                    JSONObject objetoJson = (JSONObject) objeto;
                    JSONArray arregloAlmacenes = (JSONArray)objetoJson.get("Almacenes");;
                    System.out.println("Tamaño Array LEYENDO: " + arregloAlmacenes.size());
                    almacenes.clear();
			
		    try{   
			for (int i = 0; i < arregloAlmacenes.size(); i++){
                            JSONObject almacenJson = (JSONObject)arregloAlmacenes.get(i);
			    almacenes.addAll(datosAlmacenes(almacenJson));
			    System.out.println("Tamaño Array TERMINE DE LEER: " + almacenes.size());
			    System.out.println(almacenes.get(i).getNombre());
			}
			return almacenes;
	            }
	            catch(Exception e) {System.out.println(e);}
	}
	catch(ParseException e){
		System.out.println("Ha ocurrido un error"+e.getMessage());
		System.out.println(e);
	}
	return almacenes;
		
    }
    
    public static String buscarAlmacenSig(String Ip) throws IOException{
	ArrayList<Almacen> listaAlmacenes = new ArrayList<>();
	listaAlmacenes = Leer();
	Almacen almacenFinal = new Almacen();
	for (int i = 0; i < listaAlmacenes.size(); i++){
		
            if ((listaAlmacenes.get(i).getIp().equals(Ip))){
                
                if (i == listaAlmacenes.size()-1){
                    almacenFinal = listaAlmacenes.get(0);
                    System.out.println("Consegui el Almacen en el archivo");
                    break;
                }
                
                else{
		almacenFinal = listaAlmacenes.get(i+1);
		System.out.println("Consegui al usuario en el archivo");
		break;
                }
            }
            else if (i == (listaAlmacenes.size()-1) ) {
		almacenFinal = null;
		System.out.println("No esta aun");
            }
	}

	if (almacenFinal != null){
            System.out.println("ipAlmacenSIg" + almacenFinal.getIp());
            return almacenFinal.getIp();
        }
        
        else {
            return null;
        }
	
    }
    
    public static void EliminarAlmacen(String ip) throws IOException {
        
        ArrayList<Almacen> nuevaLista = new ArrayList<Almacen>();
        
        File archivo = null;
		
	almacenes = Leer();
        
        for (int i = 0; i < almacenes.size(); i++){
            System.out.println("despues del for "+ i);;
            
            if ((almacenes.get(i).getIp().equals(ip))){
                System.out.println("conseguien en "+ i);;               
            
            }
            else {
                System.out.println("en el else "+ i);;
             nuevaLista.add(almacenes.get(i));
            }          
            
            Escribir(nuevaLista);
        
        }
            
    }
    
    
    public static ArrayList<Almacen> datosAlmacenes(JSONObject almacenJson){
		Almacen almacen = new Almacen();
		ArrayList<Almacen> almacenes = new ArrayList<>();
		almacen.setNombre(((String) almacenJson.get("Nombre")));
                almacen.setIp(((String) almacenJson.get("Ip")));
                almacenes.add(almacen);
                return almacenes;
	}
}
