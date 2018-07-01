/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MetodosRemoto;


import Remoto.ConexionRemoto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

 

/**
 *
 * @author antonio
 */
public class RemotoServidor extends UnicastRemoteObject implements ConexionRemoto{

    private long tiempo_llegada;
    private String ipEmisor;
    
    public RemotoServidor() throws RemoteException{
        
    }
    
    public void getDireccionIp() throws RemoteException{
        Registry registro_ip = LocateRegistry.getRegistry("127.0.0.1",1099);
        System.out.println("    Direccion Ip captado");
    }
    
     public void getProducto() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void almacenarTiempo(long tiempo_llegada, String ipEmisor) throws RemoteException{
        this.tiempo_llegada = tiempo_llegada;
        this.ipEmisor = ipEmisor;
        File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
        try {
            FileWriter lector = new FileWriter(dato.getAbsoluteFile(),true);
            PrintWriter buffer = new PrintWriter(lector);
            buffer.println(this.ipEmisor + " " + this.tiempo_llegada);
            System.out.println("Exito al guardar"+this.ipEmisor);
            buffer.close();
        }
        catch(IOException error){
            System.out.println("Error al abrir el archivo! "+error);
        }
    }
    
    public float obtenerTiempo(String ipEmisor){
        this.ipEmisor = ipEmisor;
        long tiempo_total = 0;
        int contador = 0;
        String conversion = "";
        File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
        try{
            FileReader guardado = new FileReader(dato);
            BufferedReader buffer = new BufferedReader(guardado);
            while((conversion = buffer.readLine()) != null){
                String linea = conversion;
                String[] subcadena = linea.split(" ");
                String subcadenaIp = subcadena[0];
                String subcadenaTiempo = subcadena[1];
                System.out.println("-->Tiempo:"+subcadenaTiempo);
                System.out.println("-->Ip:"+subcadenaIp);
                if(subcadenaIp.equals(ipEmisor)){
                    tiempo_total += Long.parseLong(subcadenaTiempo);
                    contador += 1;
                    System.out.println("Leyendo: "+tiempo_total+" Contador:"+contador);
                    //buffer.readLine();
                }
            }
            if(contador != 0){
                return tiempo_total/contador;
            }
            System.out.println("Saliendo");
            buffer.close();
        }
        catch(Exception error){
            System.out.println("Error: "+error);
        }
        return contador;
    }

    @Override
    public int getTiempoLlegada() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //Funcion encargada de obtener el tiempo de envio de los paquete cuando son altos
    public void cargaMax (){
        
    }
    
}
