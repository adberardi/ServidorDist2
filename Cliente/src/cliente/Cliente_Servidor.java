/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author antonio
 */
public class Cliente_Servidor {
    
    interface producto extends Remote{
        public void getProducto() throws RemoteException;
    }

}
