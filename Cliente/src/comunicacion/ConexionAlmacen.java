/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comunicacion;

import peticiones.Transporte;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.Mensaje;

/**
 *
 * @author Marvian
 */
public class ConexionAlmacen {
    
    public void peticionEnvioTransporte( Mensaje mensaje, String vecino, Transporte transporte ){
        int id;
        
        try {
            Socket peticionCentral = new Socket( vecino , 12000 );
            System.out.println( "Realizando petición para enviar transporte" );
            mensaje = new Mensaje( 2 , "prueba", transporte );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
            oos.close();
            ois.close();
            //id = respuesta.getOpcion();
        } catch (IOException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
