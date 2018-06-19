package Main;
import java.net.*;
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
    final int puerto_conexion = 5000;
    ServerSocket socketServidor;
    Socket socket;
    DataOutputStream salidaDatos;
    DataInputStream mensajeEntrante;
    String mensajeCaptado;
    
    //Para el servidor
    public void inicializarServidor(){
        BufferedReader entradaDatos;
        try{
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
            salidaDatos.writeUTF("  Conexion estado....exitosa");
            
            //Se recibe el mensaje via consola.
            //mensajeCaptado = entradaDatos.readLine();
            
            //Para el envio de datos desde el cliente y servidor se usa DataInputstream
            mensajeCaptado = mensajeEntrante.readUTF();
            
            System.out.println(mensajeCaptado);
            //salidaDatos.writeUTF(" Mensaje recibido....cerrando conexion");
            //socket.close();
        }
        catch(Exception error){
            System.out.println("    Lo siento ha ocurrido un error en el servidor:"+error.getMessage());
        }
    }
}