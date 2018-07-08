/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remoto;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author antonio
 */

//Clase usa para el manejo de RMI
public interface ConexionRemoto extends Remote {
    public String getDireccionIp() throws RemoteException;
        public void getProducto() throws RemoteException;
        public int getTiempoLlegada() throws RemoteException;
        public float cargaMax (String ipEmisor) throws RemoteException;
        public boolean almacenarTiempo(long tiempo_llegada, String ipEmisor) throws RemoteException;
        public boolean almacenarTiempo(String ipEmisor, int cargaPaquete, long tiempo_llegada) throws RemoteException;
        public float obtenerTiempo(String ipEmisor) throws  RemoteException;
        public float obtenerTiempoTotal(String ipEmisor) throws RemoteException;
}
