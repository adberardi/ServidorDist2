package Main;
import MetodosRemoto.RemotoServidor;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticiones.AvisoSiguiente;
import peticiones.Mensaje;
import peticiones.MensajeSer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adtony45
 */
public class Servidor{
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    public static void cancelarAlmacen(Socket socket, ServerSocket ss, int id ){
        try {
            //ObjectOutputStream oos;
            //ObjectInputStream ois;
            Mensaje mensaje;
            ois = new ObjectInputStream( socket.getInputStream() );
            oos = new ObjectOutputStream( socket.getOutputStream() );
            mensaje = ( Mensaje ) ois.readObject();
            mensaje.setMensaje( "500" );
            mensaje.setOpcion(id);
            oos.writeObject( mensaje );
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    
    /**
     * Metodo que se llama al iniciar el servidor para recibir peticiones via
     * sockets.
     * Opciones en la recepcion:
     *  1 = Almacen que quiere ingresar al anillo y además al ingresar todos
     * se hace la llamada para notificar sobre nodos siguientes.
     */
    public static void iniciarServidor( Socket socket, ServerSocket ss, int id ){
        new Thread(){
            @Override
            public void run(){
                try{
                    
                    Mensaje mensaje;
                    int nServidores = 0;
                    ois = new ObjectInputStream( socket.getInputStream() );
                    oos = new ObjectOutputStream( socket.getOutputStream() );
                    ArrayList<Almacen> almacenes = new ArrayList<>();
                    ArrayList<MensajeSer> replicas = new ArrayList<>();
                    while ( true ){
                        mensaje = ( Mensaje ) ois.readObject();
                        if ( mensaje.getOpcion() == 1 ){
                            almacenes = Json.Leer();
                            Almacen almacen = new Almacen();
                            String ipLlegada = String.valueOf( socket.getInetAddress() );
                            ipLlegada = ipLlegada.substring(1, ipLlegada.length());
                            almacen.setIp( ipLlegada );
                            int id = 0;
                            if ( almacenes != null ){    
                                if ( almacenes.get( almacenes.size() - 1 ).getNombre().equals( "null" )  ){
                                    almacen.setNombre( "Almacen0" );
                                    almacenes.get( 0 ).setIp( almacen.getIp() );
                                    almacenes.get( 0 ).setNombre( almacen.getNombre() );
                                }
                                else{
                                    if ( almacenes.get( almacenes.size() - 1 ).getNombre().equals( "Almacen0" ) ){
                                        almacen.setNombre( "Almacen1" );
                                        Almacen almacenSguiente = almacenes.get( almacenes.size() - 1 );
                                        almacenes.add( almacen );
                                        
                                    }
                                    else{
                                        if ( almacenes.get( almacenes.size() - 1 ).getNombre().equals( "Almacen1" ) ){
                                            almacen.setNombre( "Almacen2" );
                                            Almacen almacenSguiente = almacenes.get( almacenes.size() - 1 );
                                            almacenes.add( almacen );
                                            
                                        }
                                        else{
                                            if ( almacenes.get( almacenes.size() - 1 ).getNombre().equals( "Almacen2" ) ){
                                                almacen.setNombre( "Almacen3" );
                                                Almacen almacenSguiente = almacenes.get( almacenes.size() - 1 );
                                                almacenes.add( almacen );
                                                
                                                
                                            }
                                        }
                                    }
                                }   
                            }
                            else{
                                System.out.println("Error");
                            }
                            Json.Escribir( almacenes );
                            System.out.println("Recibi tu petición y te acepto" + mensaje.getMensaje());
                            mensaje.setMensaje( almacen.getNombre() );
                            mensaje.setOpcion(id);
                            oos.writeObject( mensaje );
                            oos.flush();
                            //Esta llamada se hace cuando estan todos los nodos conectados
                            AvisoSiguiente.Avisar( almacenes, oos, ois );
                            /*
                            DESCOMENTAR PARA CUANDO ESTEN LOS 4 ALMACENES
                            if ( almacenes.size() - 1 == 4 ){
                                AvisoSiguiente.Iniciar( almacenes.get(0), oos, ois );
                                
                            
                            }*/
                            
                        }
                        
                        if(mensaje.getOpcion() == 2 && nServidores <3 ){
                            String ipLlegada = String.valueOf( socket.getInetAddress() );
                            ipLlegada = ipLlegada.substring(1, ipLlegada.length());
                            System.out.println("Recibi tu petición servidor");
                            MensajeSer replica = new MensajeSer();
                            replicas = Json.LeerReplicas();
                            if ( replicas.get( replicas.size() - 1 ).getId().equals( "null" )  ){
                                replicas.get(0).setId("");
                                replicas.get(0).setIp(ipLlegada);
                            }
                            else{
                                replica.setId("");
                                replica.setIp(ipLlegada);
                                replicas.add(replica);
                            }
                            Json.EscribirReplicas(replicas);
                            nServidores++;
                        }
                        
                        if (nServidores  == 3){
                            Json.AcomodoReplicas();
                        }
                    
                    //oos.close();
                    //ois.close();
                    //socket.close();
                    //ss.close();
                    }
                }
                catch(Exception e){
                    System.out.println("algo malo");   
                     e.printStackTrace();
                }
                
                
            }
            
        }.start();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*final int puerto_conexion = 12000;
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
				}*/
}