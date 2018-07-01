/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remoto;

import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 *
 * @author antonio
 */
public interface ConexionRemoto extends Remote {
        public void getDireccionIp() throws RemoteException;
        public void getProducto() throws RemoteException;
        public int getTiempoLlegada() throws RemoteException;
}
