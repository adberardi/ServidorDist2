/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peticiones;

import Main.Almacen;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que es llamada unicamente para notificar a un nodo quien es el nodo
 * siguiente en el anillo.
 * @author marvian
 */
public class AvisoSiguiente {
    public static void Avisar( String ipSiguiente, Almacen almacen ){
        try {
            Mensaje mensaje;
            Socket peticionCentral = new Socket( almacen.getIp(), 12000 );
            System.out.println( "Realizando petición de entrada al anillo" );
            mensaje = new Mensaje( 1 , ipSiguiente );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta sobre aviso: " + 
                    respuesta.getMensaje() );
            oos.close();
            ois.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            
        } catch (ClassNotFoundException ex) {
            
            ex.printStackTrace();
        }
    }
}
