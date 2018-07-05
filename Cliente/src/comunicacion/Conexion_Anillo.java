/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.Mensaje;

/**
 *
 * @author marvian
 * Clase en la cual se realia toda la comunicacion entre los almacenes y el
 * servidor de estadisticas.
 */
public class Conexion_Anillo {
    /**
     * Peticion para ingresar en el anillo.
     * @param mensaje objeto que hace referencia a lo que se quiere hacer.
     * @return Devuelve un identificador unico para los almacenes.
     */
    public String peticionAnillo( Mensaje mensaje ){
        String nombre;
        try {
            Socket peticionCentral = new Socket( "localhost", 11000 );
            System.out.println( "Realizando petición de entrada al anillo" );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            //Descomentar para probar el error de comunicacion.
            //peticionCentral.setSoTimeout(1);
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
            oos.close();
            ois.close();
            nombre = respuesta.getMensaje();
        }
        /*
        Descomentar para probar el error de comunicacion.
        
        catch (SocketTimeoutException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            //Hay que leer el json que se recibirá en algún momento con la info
            //de los otros servidores.
            System.out.println("Error en comunicación buscando otro servidor, por favor espere.");
            nombre = "500";
        }
        */catch (IOException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            nombre = "500";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            nombre = "500";
        }
        
        
        return nombre;
    }
    
}
