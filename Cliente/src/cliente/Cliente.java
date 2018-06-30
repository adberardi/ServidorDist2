/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import comunicacion.Estadistica;
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
    public static void main(String[] args) throws IOException {

        ServerSocket ss;
        int id = 0;
        int puertoEscucha = 12000;
        Estadistica peticion = new Estadistica();
        Mensaje mensaje = new Mensaje ( 1 , "localhost" );
        id = peticion.peticionAnillo( mensaje );
        if ( id != 0 )    {
            System.out.println("Ha sido registrado en el anillo, su id es" +
                    id);
        }
        // TODO code application logic here
        String prueba = "192.168.25.07";
        String pruebatrae;
        
        Json.EscriboIpSiguiente(prueba);
        
        pruebatrae = Json.LeerAlmacen();
        
        System.out.println(pruebatrae);
    }
    
}
