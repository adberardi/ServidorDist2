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
public class ConexionAnillo {
    /**
     * Peticion para ingresar en el anillo.
     * @param mensaje objeto que hace referencia a lo que se quiere hacer.
     * @return Devuelve un identificador unico para los almacenes.
     */
    public int peticionAnillo( Mensaje mensaje ){
        int id;
        try {
            Socket peticionCentral = new Socket( "190.72.182.242", 11000 );
            System.out.println( "Realizando petición de entrada al anillo" );
            mensaje = new Mensaje( 1 , "localhost" );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
            oos.close();
            ois.close();
            id = respuesta.getOpcion();
        } catch (IOException ex) {
            Logger.getLogger(ConexionAnillo.class.getName()).log(Level.SEVERE, null, ex);
            id = 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionAnillo.class.getName()).log(Level.SEVERE, null, ex);
            id = 0;
        }
        return id;
    }
    
}
