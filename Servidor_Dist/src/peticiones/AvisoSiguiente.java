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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que es llamada unicamente para notificar a un nodo quien es el nodo
 * siguiente en el anillo.
 * @author marvian
 */
public class AvisoSiguiente {
    public static void Avisar( ArrayList<Almacen> almacenes, ObjectOutputStream oos,
            ObjectInputStream ois){
        for ( int i = 0; i <= almacenes.size() - 1; i++ ){
            //NOtificaciones desde el nodo original hasta el segundo
            if ( almacenes.size() - 1 == 0 ){
                try {
                        Mensaje mensaje;
                        Socket peticionCentral = new Socket( almacenes.get( i ).getIp(), 12000 );
                        System.out.println( "Realizando aviso de siguiente" );
                        mensaje = new Mensaje( 1 , almacenes.get( 0 ).getIp() );
                        oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
                        ois = new ObjectInputStream( peticionCentral.getInputStream() );
                        oos.writeObject(mensaje);
                        oos.flush();
                        Mensaje respuesta = new Mensaje();
                        respuesta = (Mensaje)ois.readObject();
                        System.out.println("Estatus de respuesta sobre aviso: " + 
                                respuesta.getMensaje() );
                        //oos.close();
                        //ois.close();
                        //peticionCentral.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    } catch (ClassNotFoundException ex) {

                        ex.printStackTrace();
                    }
                    catch  ( Exception ex ) {

                        ex.printStackTrace();
                    }
            }
            else{
                if ( i != ( almacenes.size() - 1) ){
                    try {
                        Mensaje mensaje;
                        Socket peticionCentral = new Socket( almacenes.get( i ).getIp(), 12000 );
                        System.out.println( "Realizando aviso de siguiente" );
                        mensaje = new Mensaje( 1 , almacenes.get( i + 1 ).getIp() );
                        oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
                        ois = new ObjectInputStream( peticionCentral.getInputStream() );
                        oos.writeObject(mensaje);
                        oos.flush();
                        Mensaje respuesta = new Mensaje();
                        respuesta = (Mensaje)ois.readObject();
                        System.out.println("Estatus de respuesta sobre aviso: " + 
                                respuesta.getMensaje() );
                        //oos.close();
                        //ois.close();
                        peticionCentral.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    } catch (ClassNotFoundException ex) {

                        ex.printStackTrace();
                    }
                }
                //Notificacion para el ultimo nodo
                else{
                    try {
                        Mensaje mensaje;
                        Socket peticionCentral = new Socket( almacenes.get( i ).getIp(), 12000 );
                        System.out.println( "Realizando aviso de siguiente" );
                        mensaje = new Mensaje( 1 , almacenes.get( 0 ).getIp() );
                        oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
                        ois = new ObjectInputStream( peticionCentral.getInputStream() );
                        oos.writeObject(mensaje);
                        oos.flush();
                        Mensaje respuesta = new Mensaje();
                        respuesta = (Mensaje)ois.readObject();
                        System.out.println("Estatus de respuesta sobre aviso: " + 
                                respuesta.getMensaje() );
                        //oos.close();
                        //ois.close();
                        //peticionCentral.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    } catch (ClassNotFoundException ex) {

                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    public static void Iniciar( Almacen almacenInicial, ObjectOutputStream oos,
            ObjectInputStream ois ){
        try {
                        Mensaje mensaje;
                        Socket peticionCentral = new Socket( almacenInicial.getIp(), 12000 );
                        System.out.println( "Realizando aviso de siguiente" );
                        mensaje = new Mensaje( 4 , almacenInicial.getIp() );
                        oos = new ObjectOutputStream( peticionCentral.getOutputStream() );
                        ois = new ObjectInputStream( peticionCentral.getInputStream() );
                        oos.writeObject(mensaje);
                        oos.flush();
                        Mensaje respuesta = new Mensaje();
                        respuesta = (Mensaje)ois.readObject();
                        System.out.println("Estatus de respuesta sobre aviso: " + 
                                respuesta.getMensaje() );
                        //oos.close();
                        //ois.close();
                        //peticionCentral.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    } catch (ClassNotFoundException ex) {

                        ex.printStackTrace();
                    }
                    catch  ( Exception ex ) {

                        ex.printStackTrace();
                    }
    }
}
