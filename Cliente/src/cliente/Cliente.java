/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import comunicacion.ConexionAlmacen;
import comunicacion.Conexion_Anillo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
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
        int nTransporte = 0;
        int segundos = 0 ;
        Scanner reader = new Scanner(System.in);
        sleep espera = new sleep();
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
        Almacen yo = new Almacen();
        yo.setNombre(nombre);
        yo.setIp("localhost");
        
        if(yo.getNombre() == "almace0"){
            while( segundos == 5 || segundos == 10 || segundos == 15 || segundos == 20 ){
                System.out.println("Cuantos segundos quiere que demore "
                        + "los transportes en salir? 5, 10, 15, 20 segundo ");
                segundos = reader.nextInt(); 
            }
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
            
            if(yo.getNombre() == "almace0" && nTransporte < 4){
                ConexionAlmacen peticion1 = new ConexionAlmacen();
                espera.run(segundos);
                Paquete paquete = new Paquete();
                ArrayList<Paquete> paquetes = null;
                paquetes.add(paquete);
                Transporte transporte = new Transporte("prueba","prueba",1,paquetes);
                peticion1.peticionEnvioTransporte(mensaje, "prueba", transporte);
            }
           
        }      
    }
    
}
