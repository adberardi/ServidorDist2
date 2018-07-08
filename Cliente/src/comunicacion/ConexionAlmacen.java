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
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.Mensaje;

/**
 *
 * @author Marvian
 */

//Clase para manejar el envio y la comunicacion entre almacenes
public class ConexionAlmacen {
    int secuencia;//atributo usado para saber la secuencia entre paquetes 
    
    public void peticionEnvioTransporte( Mensaje mensaje, String vecino, Transporte transporte ){
        int id;
        int intentos = 0;
        
        do{
        try {
            
            Socket peticionCentral = new Socket( vecino , 12000 );
            System.out.println( "Realizando petición para enviar transporte" );
            System.out.println( "con la secuencia " + secuencia);
            mensaje = new Mensaje( 2 , "prueba", transporte );
            ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
            ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
            peticionCentral.setSoTimeout(1);
            oos.writeObject(mensaje);
            oos.flush();
            Mensaje respuesta = new Mensaje();
            respuesta = (Mensaje)ois.readObject();
            System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
            oos.close();
            ois.close();
            //id = respuesta.getOpcion();
        
        } catch (SocketTimeoutException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
            //Hay que leer el json que se recibirá en algún momento con la info
            //de los otros servidores.
            System.out.println("Tiempo de espera excedido"); 
            System.out.println("Intento " + intentos); 
            intentos ++;
        } catch (IOException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_Anillo.class.getName()).log(Level.SEVERE, null, ex);
        }
        }while(intentos != 3);
        
        //En caso de que mi vecino este caido y ya supere el numero maximo de reenvio
        //notifico al nodo central
        if (intentos == 3 ){            
            try {
                Socket peticionCentral = new Socket( "localhost" , 11000 );
                System.out.println( "Mi vecino esta caido" );
                mensaje = new Mensaje( 5 , vecino, transporte );
                ObjectOutputStream oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
                ObjectInputStream ois = new ObjectInputStream( peticionCentral.getInputStream() );
                oos.writeObject(mensaje);
                oos.flush();
                Mensaje respuesta = new Mensaje();
                respuesta = (Mensaje)ois.readObject();
                System.out.println("Estatus de respuesta:" + respuesta.getMensaje() );
                oos.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(ConexionAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConexionAlmacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    secuencia++;
    }
}
