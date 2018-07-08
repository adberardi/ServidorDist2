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

//Clase para el manejo de RMI y todas las estadisticas
public class RemotoServidor extends UnicastRemoteObject implements ConexionRemoto{

    private long tiempo_llegada;
    private String ipEmisor;
    private int longitudPaquete;
    
    
    //import Remoto.ConexionRemoto;
    //import comunicacion.Estadistica;

    //import java.rmi.AccessException;
    //import java.rmi.NotBoundException;
    //import java.rmi.RemoteException;
    //import java.rmi.registry.LocateRegistry;
    //import java.rmi.registry.Registry;
    //import java.util.logging.Level;
    //import java.util.logging.Logger;
    //import peticiones.Mensaje;
    //    public static void main(String[] args) throws IOException, ClassNotFoundException, RemoteException, AccessException, NotBoundException {
            //Establecer conexion con RMI
        //Registry registroCliente = LocateRegistry.getRegistry();
        //ConexionRemoto canalCliente = (ConexionRemoto) registroCliente.lookup("canal");
        //System.out.println(canalCliente.getDireccionIp());
        //System.out.println(canalCliente.cargaMax("192.17.100.1"));
        //System.out.println(canalCliente.obtenerTiempoTotal("192.17.123.1"));
        //System.out.println(canalCliente.almacenarTiempo(0, "127.0.0.1"));
        //System.out.println(canalCliente.almacenarTiempo("127.0.0.1", 5, 3));

    
    public RemotoServidor() throws RemoteException{
        
    }
    
    public String getDireccionIp() throws RemoteException{
        
        return"    Direccion Ip captado";
    }
    
     public void getProducto() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     //Función usada para guardar el tiempo de los distintos "almacenes" con su
     //tiempo de llegada al destino. Autor: Antonio Berardi     
     public boolean almacenarTiempo(long tiempo_llegada, String ipEmisor) throws RemoteException{
        this.tiempo_llegada = tiempo_llegada;
        this.ipEmisor = ipEmisor;
        File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
        try {
            FileWriter lector = new FileWriter(dato.getAbsoluteFile(),true);
            PrintWriter buffer = new PrintWriter(lector);
            buffer.println(this.ipEmisor + " " + this.tiempo_llegada);
            System.out.println("Exito al guardar"+this.ipEmisor);
            buffer.close();
            return true;
        }
        catch(IOException error){
            System.out.println("Error al abrir el archivo! "+error);
            return false;
        }
    }
    
    //Se busca en el archivo creado anteriormente, para buscar por ip los tiempos 
    //Registrados y calcular su promedio. Autor: Antonio Berardi
    public float obtenerTiempo(String ipEmisor){
        this.ipEmisor = ipEmisor;
        long tiempo_total = 0;
        int contador = 0;
        String conversion = "";
        File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
        try{
            FileReader guardado = new FileReader(dato);
            BufferedReader buffer = new BufferedReader(guardado);
            while((conversion = buffer.readLine()) != null){ //Leer hasta el final del archivo
                String linea = conversion;
                String[] subcadena = linea.split(" ");//Separo la cadena extraida del archivo
                String subcadenaIp = subcadena[0];
                String subcadenaTiempo = subcadena[1];
                System.out.println("-->Tiempo:"+subcadenaTiempo);
                System.out.println("-->Ip:"+subcadenaIp);
                if(subcadenaIp.equals(ipEmisor)){//Acoge los tiempos de una ip en especifico
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
    
    
         //Función usada para guardar el tiempo de los distintos "almacenes" con su
     //tiempo de llegada al destino. Autor: Antonio Berardi
    public boolean almacenarTiempo(String ipEmisor, int cargaPaquete, long tiempo_llegada) throws RemoteException{
        this.ipEmisor = ipEmisor;
        
        try {
            File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto_carga.txt");
            FileWriter lector = new FileWriter(dato.getAbsoluteFile(),true);
            PrintWriter buffer = new PrintWriter(lector);
            buffer.println(this.ipEmisor + " " + cargaPaquete);
            System.out.println("Exito al guardar"+this.ipEmisor);
            buffer.close();
            return true;
        }
        catch(IOException error){
            System.out.println("Error al abrir el archivo! "+error);
            return false;
        }
    }
    
    
    //Se busca en el archivo creado anteriormente, para buscar por ip los tiempos 
    //Registrados y calcular su promedio. Autor: Antonio Berardi
    public float obtenerTiempoTotal(String ipEmisor){
        this.ipEmisor = ipEmisor;
        long tiempo_total = 0;
        int contador = 0;
        String conversion = "";
        File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
        try{
            FileReader guardado = new FileReader(dato);
            BufferedReader buffer = new BufferedReader(guardado);
            while((conversion = buffer.readLine()) != null){ //Leer hasta el final del archivo
                String linea = conversion;
                String[] subcadena = linea.split(" ");//Separo la cadena extraida del archivo
                String subcadenaIp = subcadena[0];
                String subcadenaTiempo = subcadena[1];
                //System.out.println("-->Tiempo:"+subcadenaTiempo);
                //System.out.println("-->Ip:"+subcadenaIp);
                if(subcadenaIp.equals(ipEmisor)){//Acoge los tiempos de una ip en especifico
                    tiempo_total += Long.parseLong(subcadenaTiempo);
                    contador += 1;
                    //System.out.println("Leyendo: "+tiempo_total+" Contador:"+contador);
                    //buffer.readLine();
                }
            }
            if(contador != 0){
                return tiempo_total;
            }
            System.out.println("Saliendo");
            buffer.close();
        }
        catch(Exception error){
            System.out.println("Error: "+error);
        }
        return 0;
    }
    
    
    //Funcion encargada de obtener el tiempo de envio de los paquete cuando son altos
    //Autor: Antonio Berardi
    public float cargaMax (String ipEmisor){
        String feof = "";
        long tiempo_total = 0;
        try{
            File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto_carga.txt");
            FileReader guardado = new FileReader(dato);
            BufferedReader buffer = new BufferedReader(guardado);
            while((feof = buffer.readLine()) != null){ //Leer hasta el final del archivo
                String linea = feof;
                String[] subcadena = linea.split(" ");//Separo la cadena extraida del archivo
                String subcadenaIp = subcadena[0];
                String subcadenaCarga = subcadena[1];
                String subcadenaTiempo = subcadena[2];
                /*System.out.println("-->Tiempo:"+subcadenaCarga);
                System.out.println("-->Ip:"+subcadenaIp);*/
                if((subcadenaIp.equals(ipEmisor))&&(subcadenaCarga.equals("5"))){//Acoge los tiempos de una ip en especifico
                    tiempo_total += Long.parseLong(subcadenaTiempo);
                    //System.out.println("Leyendo: "+tiempo_total);
                    //buffer.readLine();
                }
            }
            float tiempo = obtenerTiempoTotal(ipEmisor);
            System.out.println("Saliendo");
            buffer.close();
            if (tiempo != 0){
                return (tiempo_total*100)/tiempo;
            }
        }
        catch (Exception error){
            System.out.println("Error "+error);
        }
        return 0;
    }

    
    
}
