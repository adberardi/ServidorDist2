/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Json.almacenes;
import MetodosRemoto.RemotoServidor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author adtony45
 */
public class MainServidor {
    
    static Almacen almacen = new Almacen();

    /**
     * @param args the command line arguments
     * Inicia el servidor, a traves de esta clase se inicia el servidor concurrente
     * NOTA: para el rmi es importante realiazar los llamados a los metodos 
     * antes de que inicie el servidor concurrente.
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket ss = new ServerSocket(11000);
        System.out.println("Esperando peticiones");
        int id = 0;
        while ( true ){
            
            Socket socket = ss.accept();
            System.out.println("Ha llegado un cliente.");
            id = id + 1;
            if ( id == 5){
                Servidor.cancelarAlmacen(socket, ss, id);
            }
            else{
                Servidor.iniciarServidor(socket, ss, id);
            }
            
        }
        
        //Agregado por Antonio para probar las estadisticas del tiempo promedio. 
        //RemotoServidor prueba = new RemotoServidor();
        //prueba.almacenarTiempo(1, "192.168.15.11");
        //prueba.almacenarTiempo(2, "192.17.123.1");
        //prueba.almacenarTiempo(3, "192.17.123.1");
        //long tiempo_promedio = prueba.obtenerTiempo("192.168.15.11");
        //float tiempo_promedio = prueba.obtenerTiempo("192.17.123.1");
        //System.out.println("El tiempo promedio es: "+tiempo_promedio);
        
        /*
        //Prueba que crea
        try {
        almacenes = Json.Leer();
        
        
        }
        
        catch(FileNotFoundException e){
            System.out.println("Archivo vacio.");
        } 
        catch (IOException ex) {
            System.out.println("error");
        }
        
        
        
            if (almacenes.isEmpty()){
                
                almacen.setNombre("almacen0");
                almacen.setIp("192.65.22.22");
                almacenes.add(almacen);

                Json.Escribir(almacenes);

            } else {
                System.out.println("entro");
               int valor = almacenes.size();
               
            System.out.println(valor);
            String numero = Integer.toString(valor);
                almacen.setNombre("almacen"+numero);
                almacen.setIp("192.65.203.203");
                almacenes.add(valor, almacen);

                Json.Escribir(almacenes);
            }*/
                
        /*
        prueba que consigue el siguiente
        String Almacen0S, Almacen1S, Almacen2S;
        
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");
        System.out.println("192.65.22.22");
        System.out.println(Almacen0S);
        Almacen1S = Json.buscarAlmacenSig("192.65.200.200");
        System.out.println("192.65.200.200");
        System.out.println(Almacen1S);
        Almacen2S = Json.buscarAlmacenSig("192.65.300.400");
        System.out.println("192.65.300.300");
        System.out.println(Almacen2S);
                
        */
        /*
        prueba que elimina
        String Almacen0S;
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");
        System.out.println("antes de eliminar"+Almacen0S);
        Json.EliminarAlmacen("192.65.22.22");
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");

        */
        
    }
    
}
