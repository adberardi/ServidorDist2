/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peticiones;

import Main.Json;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvian
 */
public class Replica {
    
    public void AvisoReplica(){
        String nuevoId;
        MensajeSer mensajeSer;
        try {
            Socket peticionCentral = new Socket( "192.168.1.104", 11000 );
            System.out.println( "Aviso al central que estoy arriba" );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            mensajeSer = ( MensajeSer ) ois.readObject();
            mensajeSer.setId(Json.LeerID());
            oos.writeObject(mensajeSer);
            oos.flush();
            MensajeSer respuesta = new MensajeSer();
            respuesta = (MensajeSer)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getId() );
            oos.close();
            ois.close();
            nuevoId = respuesta.getId();
        } catch (IOException ex) {
            Logger.getLogger(Replica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Replica.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void RespuestaReplica( MensajeSer mensajeSer ){
        String nuevoId;
        try {
            Socket peticionCentral = new Socket( "192.168.1.104", 11000 );
            System.out.println( "Aviso al central que estoy arriba" );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            mensajeSer = ( MensajeSer ) ois.readObject();
            mensajeSer.setId(Json.LeerID());
            oos.writeObject(mensajeSer);
            oos.flush();
            MensajeSer respuesta = new MensajeSer();
            respuesta = (MensajeSer)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getId() );
            oos.close();
            ois.close();
            nuevoId = respuesta.getId();
        } catch (IOException ex) {
            Logger.getLogger(Replica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Replica.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
}
