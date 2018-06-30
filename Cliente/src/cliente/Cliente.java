/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;

/**
 *
 * @author gilbert
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String prueba = "192.168.25.07";
        String pruebatrae;
        
        Json.EscriboIpSiguiente(prueba);
        
        pruebatrae = Json.LeerAlmacen();
        
        System.out.println(pruebatrae);
    }
    
}
