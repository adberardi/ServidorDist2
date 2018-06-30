/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author adtony45
 */
public class MainServidor {

    /**
     * @param args the command line arguments
     * Inicia el servidor, a traves de esta clase se inicia el servidor concurrente
     * NOTA: para el rmi es importante realiazar los llamados a los metodos 
     * antes de que inicie el servidor concurrente.
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket ss = new ServerSocket(11000);
        System.out.println("Esperando peticiones");
        int id = 0;
        while ( true ){
            
            Socket socket = ss.accept();
            System.out.println("Ha llegado un cliente.");
            id = id + 1;
            Servidor.iniciarServidor(socket, ss, id);
        }
    }
    
}
