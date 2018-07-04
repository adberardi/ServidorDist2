/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Json.almacenes;
import MetodosRemoto.RemotoServidor;
import Remoto.ConexionRemoto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author adtony45
 */
public class MainServidor {
    
    static Almacen almacen = new Almacen();

    /**
     * @param args the command line arguments
     * Inicia el servidor, a traves de esta clase se inicia el servidor concurrente
     * NOTA: para el rmi es importante realiazar los llamados a los metodos 
     * antes de que inicie el servidor concurrente.
     */
    public static void main(String[] args) throws IOException, RemoteException, AlreadyBoundException {
        // TODO code application logic here
        
        /*ConexionRemoto canal = new ConexionRemoto() {
            @Override
            public String getDireccionIp() throws RemoteException {
                    return "Hola"; 
            }

            @Override
            public void getProducto() throws RemoteException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getTiempoLlegada() throws RemoteException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public float cargaMax(String ipEmisor) throws RemoteException {
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
                System.out.println("-->Tiempo:"+subcadenaCarga);
                System.out.println("-->Ip:"+subcadenaIp);
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
        return 0; //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public float obtenerTiempoTotal(String ipEmisor) throws RemoteException {
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
            public boolean almacenarTiempo(long tiempo_llegada, String ipEmisor) throws RemoteException {
                try {
                    File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto.txt");
                    FileWriter lector = new FileWriter(dato.getAbsoluteFile(),true);
                    PrintWriter buffer = new PrintWriter(lector);
                    buffer.println(ipEmisor + " " + tiempo_llegada);
                    System.out.println("Exito al guardar"+ipEmisor);
                    buffer.close();
                    return true;
                }
                catch(IOException error){
            System.out.println("Error al abrir el archivo! "+error);
            return false;
        }
            }

            @Override
            public boolean almacenarTiempo(String ipEmisor, int cargaPaquete, long tiempo_llegada) throws RemoteException {
                 try {
            File dato = new File("/home/antonio/Documentos/Distribuidos/proyecto_carga.txt");
            FileWriter lector = new FileWriter(dato.getAbsoluteFile(),true);
            PrintWriter buffer = new PrintWriter(lector);
            buffer.println(ipEmisor + " " + cargaPaquete + " " + tiempo_llegada);
            //System.out.println("Exito al guardar"+ipEmisor);
            buffer.close();
            return true;
        }
        catch(IOException error){
            System.out.println("Error al abrir el archivo! "+error);
            return false;
        }
            }

            @Override
            public float obtenerTiempo(String ipEmisor) throws RemoteException {
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
        };
        
        ConexionRemoto stub = (ConexionRemoto) UnicastRemoteObject.exportObject(canal, 0);
        Registry registro = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        registro.bind("canal", stub);
        
        Scanner menu = new Scanner(System.in);
        boolean salir = false;
        int seleccion = 0;
        while(seleccion != 3){
            System.out.println("    1.Tiempo promedio que tardan los paquetes de un almacén en llegar a destino.");
            System.out.println("    2.Porcentaje de tiempo en que el transporte circula con su carga máxima.");
            System.out.println("    3.Salir");
            seleccion = menu.nextInt();
            
            if(seleccion == 1){
                canal.obtenerTiempo(" ");
            }
            if(seleccion == 2){
                canal.cargaMax("");
            }
        }*/
        
        
        ServerSocket ss = new ServerSocket(11000);
        System.out.println("Esperando peticiones");
        int id = 0;
        int nServidores = 0;
        String prueba = null;
        
        prueba = Json.LeerID();
        
        System.out.println("prueba sirve" + prueba);
        
        if( prueba.equalsIgnoreCase("1") ){
            System.out.println("entra");
            do{
                Socket socket = ss.accept();
                System.out.println("Ha llegado un Servidor");
                nServidores++;
            } while (nServidores < 3);
        }
        else {
            System.out.println("entra en el segundo");
                Socket socket = ss.accept();
                System.out.println("Envio ");
                
            
        }
        
        while ( true ){
            
            Socket socket = ss.accept();
            System.out.println("Ha llegado un cliente.");
            id = id + 1;
//<<<<<<< Updated upstream
            if ( id == 5){
                Servidor.cancelarAlmacen(socket, ss, id);
            }
            else{
                Servidor.iniciarServidor(socket, ss, id);
            }
            
        }
//=======
       
 
        }
//>>>>>>> Stashed changes
        
        //Agregado por Antonio para probar las estadisticas del tiempo promedio. 
        //RemotoServidor prueba = new RemotoServidor();
        //prueba.almacenarTiempo(1, "192.168.15.11");
        //prueba.almacenarTiempo(2, "192.17.123.1");
        //prueba.almacenarTiempo(3, "192.17.123.1");
        //loat tiempo_promedio = prueba.obtenerTiempo("192.168.15.11");
        //prueba.almacenarTiempo("192.17.100.1", 5, 1);
        //float resultado = prueba.cargaMax("192.17.100.1");
        //System.out.println("    ->Porcentaje: "+resultado);
        //float tiempo_promedio = prueba.obtenerTiempo("192.17.123.1");
        //System.out.println("El tiempo promedio es: "+tiempo_promedio);
        
        /*
        //Prueba que crea
        try {
        almacenes = Json.Leer();
        
        
        }
        
        catch(FileNotFoundException e){
            System.out.println("Archivo vacio.");
        } 
        catch (IOException ex) {
            System.out.println("error");
        }
        
        
        
            if (almacenes.isEmpty()){
                
                almacen.setNombre("almacen0");
                almacen.setIp("192.65.22.22");
                almacenes.add(almacen);

                Json.Escribir(almacenes);

            } else {
                System.out.println("entro");
               int valor = almacenes.size();
               
            System.out.println(valor);
            String numero = Integer.toString(valor);
                almacen.setNombre("almacen"+numero);
                almacen.setIp("192.65.203.203");
                almacenes.add(valor, almacen);

                Json.Escribir(almacenes);
            }*/
                
        /*
        prueba que consigue el siguiente
        String Almacen0S, Almacen1S, Almacen2S;
        
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");
        System.out.println("192.65.22.22");
        System.out.println(Almacen0S);
        Almacen1S = Json.buscarAlmacenSig("192.65.200.200");
        System.out.println("192.65.200.200");
        System.out.println(Almacen1S);
        Almacen2S = Json.buscarAlmacenSig("192.65.300.400");
        System.out.println("192.65.300.300");
        System.out.println(Almacen2S);
                
        */
        /*
        prueba que elimina
        String Almacen0S;
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");
        System.out.println("antes de eliminar"+Almacen0S);
        Json.EliminarAlmacen("192.65.22.22");
        Almacen0S = Json.buscarAlmacenSig("192.65.22.22");

        */
        
    }
    
