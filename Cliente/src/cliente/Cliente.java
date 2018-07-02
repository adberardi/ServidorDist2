/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import comunicacion.Conexion_Anillo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.Mensaje;


/**
 *
 * @author adtony45
 */
public class Cliente {

    /**
     * @param args the command line arguments
     * Clase en la cual se inicia un almacen y realiza una primera llamada para
     * entrar al anillo.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(12000);
        String nombre = "null";
        Conexion_Anillo peticion = new Conexion_Anillo();
        Mensaje mensaje = new Mensaje ( 1 , "localhost" );
        nombre = peticion.peticionAnillo( mensaje );
        if ( nombre.equals( "500" ) )    {
            System.out.println("No se puede ingresar, ya se llego al limite");
        }
        else{
            System.out.println("Ha sido registrado en el anillo, su id es" +
                    nombre);
            }
        
        System.out.println("Esperando peticiones");
        while ( true ){
            Socket socket = ss.accept();
            System.out.println("Ha llegado un cliente.");
            ObjectOutputStream oos;
            ObjectInputStream ois;
            Mensaje recibido;
            ois = new ObjectInputStream( socket.getInputStream() );
            oos = new ObjectOutputStream( socket.getOutputStream() );
            recibido = ( Mensaje ) ois.readObject();
            String prueba = "192.168.25.07";
            String pruebatrae;
            Json.EscriboIpSiguiente( recibido.getMensaje() );
            pruebatrae = Json.LeerAlmacen();
            System.out.println(pruebatrae);
            recibido.setMensaje( "Actualizado" );
            oos.writeObject( recibido );
            oos.flush();
            
            if (recibido.getOpcion() == 2){
                
            }
        }
        
        
        
        
        
        
        
        
    }
    
}
