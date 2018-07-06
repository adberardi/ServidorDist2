/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;


import Remoto.ConexionRemoto;
import comunicacion.ConexionAlmacen;
import comunicacion.Conexion_Anillo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;
import peticiones.Mensaje;
import peticiones.Paquete;
import peticiones.Transporte;



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
    public static void main(String[] args) throws IOException, ClassNotFoundException, RemoteException, NotBoundException {
        int nTransporte = 0;
        int segundos = 0 ;
        int horaP = 0;
        int minutoP = 0;
        int segundosP = 0;
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
        
        if(yo.getNombre().equals( "Almacen0" ) ){
            System.out.println("dentro if");
            //while( segundos == 5 || segundos == 10 || segundos == 15 || segundos == 20 ){
                System.out.println("Cuantos segundos quiere que demore "
                        + "los transportes en salir? 5, 10, 15, 20 segundo ");
                segundos = reader.nextInt(); 
            //}
        }
        /*int loop = 0;
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
            break;
        }*/
        System.out.println("Esperando peticiones");
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        /*Registry registroCliente = LocateRegistry.getRegistry(); 
                 //System.setProperty("java.rmi.server.hostname","192.168.43.48");
                 ConexionRemoto canalCliente; 
                canalCliente = (ConexionRemoto)registroCliente.lookup("canal");
                
                 System.out.println("       RMI");
                 
                 canalCliente.almacenarTiempo(0, "1.2.3.43");*/
        while ( true ){
            /*Socket socket = ss.accept();
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
            */
            Socket socket = ss.accept();
            System.out.println("Ha llegado un cliente.");
            ObjectOutputStream oos;
            ObjectInputStream ois;
            Mensaje recibido;
            ois = new ObjectInputStream( socket.getInputStream() );
            oos = new ObjectOutputStream( socket.getOutputStream() );
            recibido = ( Mensaje ) ois.readObject();
            
            if (recibido.getOpcion() == 1){
                Json.EscriboIpSiguiente( recibido.getMensaje() );
                String pruebatrae;
                pruebatrae = Json.LeerAlmacen();
                System.out.println(pruebatrae);
                recibido.setMensaje( "Actualizado" );
                oos.writeObject( recibido );
                oos.flush();
            }
            //Recibe jun paquete
            if (recibido.getOpcion() == 2){
                espera.run20s();
                System.out.println("Recibi peticion");
                System.out.println("Esto es lo que recibi:");
                System.out.println( recibido.getTransporte().getIdentificador() );
                System.out.println( recibido.getTransporte().getIpDestinatario() );
                System.out.println( recibido.getTransporte().getPuertoDestinatario() );
                recibido.setMensaje( "Exitoso" );
                oos.writeObject( recibido );
                oos.flush();
                
                 
                /* Registry registroCliente = LocateRegistry.getRegistry(); 
                 //System.setProperty("java.rmi.server.hostname","192.168.43.48");
                 ConexionRemoto canalCliente; 
                canalCliente = (ConexionRemoto)registroCliente.lookup("canal");
                
                 System.out.println("       RMI");
                 
                 canalCliente.almacenarTiempo(0, recibido.getTransporte().getIdentificador());*/ 
                                
                
                
                
                //Aqui envio al siguiente almacen
                ConexionAlmacen peticion1 = new ConexionAlmacen();
                System.out.println("aqui");
                espera.run(segundos);
                Paquete paquete = new Paquete();
                ArrayList<Paquete> paquetes = new ArrayList<>();
                paquetes.add(paquete);
                Transporte transporte = new Transporte("prueba","prueba",1,paquetes);
                peticion1.peticionEnvioTransporte(mensaje, Json.LeerAlmacen() , transporte);
                

                
            }
            
            if (recibido.getOpcion() == 3){
            
            }
            //Aqui es fundamental colocar la ip de la maquina que tiene el almacen0

            if ( !Json.LeerAlmacen().equals( "192.168.43.48" ) ){
                if(yo.getNombre().equals( "Almacen0" ) && nTransporte < 4){
                    nTransporte++;
                    ConexionAlmacen peticion1 = new ConexionAlmacen();
                    System.out.println("aqui");
                    espera.run(segundos);
                    Paquete paquete = new Paquete();
                    Calendar tiempo = new GregorianCalendar();
                    horaP = tiempo.get(Calendar.HOUR);
                    minutoP = tiempo.get(Calendar.MINUTE);
                    segundosP = tiempo.get(Calendar.SECOND);
                    String fecha = String.valueOf(horaP)+":"+String.valueOf(minutoP)+":"+String.valueOf(segundosP);
                    ArrayList<Paquete> paquetes = new ArrayList<>();
                    paquete.setTiempoSalida(fecha);
                    paquetes.add(paquete);
                    Transporte transporte = new Transporte("prueba","prueba",1,paquetes);
                    espera.run10s();
                    peticion1.peticionEnvioTransporte(mensaje, Json.LeerAlmacen() , transporte);
                }
            }
        }      
    }

    
}
