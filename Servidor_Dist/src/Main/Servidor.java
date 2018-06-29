package Main;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adtony45
 */
public class Servidor {
    final int puerto_conexion = 1234;
    ServerSocket socketServidor;
    Socket socket;
    DataOutputStream salidaDatos;
    DataInputStream mensajeEntrante;
    String mensajeCaptado;
    
    //Para el servidor
    public void inicializarServidor(){
        BufferedReader entradaDatos;
        try{
        	while(true) {
            socketServidor = new ServerSocket(puerto_conexion);
            socket = new Socket();
            System.out.println("    Esperando conexion:");
            socket = socketServidor.accept();
            //Se inicia el socket.
            System.out.println("    Cliente conectado");
            //Canales de comunicación
            entradaDatos = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            mensajeEntrante = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            System.out.println("    Estableciendo conexion");

            //Se escribe el mensaje via consola.
            //salidaDatos.writeUTF("  Conexion estado....exitosa");
            
            //Se recibe el mensaje via consola.
            //mensajeCaptado = entradaDatos.readLine();
            
            //Para el envio de datos desde el cliente y servidor se usa DataInputstream
            mensajeCaptado = mensajeEntrante.readUTF();
            
            System.out.println(mensajeCaptado);
            //salidaDatos.writeUTF(" Mensaje recibido....cerrando conexion");
            //enviarPaquete();
            socket.close();
        	}
        }
        catch(Exception error){
            System.out.println("    Lo siento ha ocurrido un error en el servidor:"+error.getMessage());
        }   
    }
    
    //Función en desarrollo, la cual se encarga de enviar el paquete
//  public void construirPaquete() {
//	try {
//		socketConectar = new Socket(host,puertoConexionServidor);
//        //System.out.println("Acceso concedido");
//        //Para enviar un mensaje primero se establece el medio.
//        mensajeFlujo = new DataOutputStream(socketConectar.getOutputStream());
//        //BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(mensajeFlujo));
//        //Una vez establecido el medio o flujo se envia el mensaje deseado.
//        mensajeFlujo.writeUTF("Hola que tal soy nuevo con sockets");
//        //Luego se cierra la conexion.
//        //enviarPaquete();
//        //socketConectar.close();
//    }
//    catch(Exception error){
//        System.out.println("    A ocurrido un error:"+error.getMessage());
//    }
//}

//Función para enviar los paquetes en intervalos de tiempo.
		public void enviarPaquete() {
			int contador = 0;
			Timer marca = new Timer();
			TimerTask tarea = new TimerTask() {
			public void run() {
			//construirPaquete();
			}
		};
					
		marca.scheduleAtFixedRate(tarea, 0, 5000);
				}
}