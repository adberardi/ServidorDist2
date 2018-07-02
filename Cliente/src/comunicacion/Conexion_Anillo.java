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
            Socket peticionCentral = new Socket( "192.168.1.104", 11000 );
            System.out.println( "Realizando petición de entrada al anillo" );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
            oos.close();
            ois.close();
            nombre = respuesta.getMensaje();
        } catch (IOException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            nombre = "500";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            nombre = "500";
        }
        return nombre;
    }
    
}
