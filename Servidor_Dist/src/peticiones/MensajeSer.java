/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peticiones;

import java.io.Serializable;

/**
 *
 * @author Marvian
 */

//Objeto usando para el manejo de json en los Servidores
public class MensajeSer implements Serializable {
    private String id;
    private String ip;
    private int opcion;

    public MensajeSer(String id, String ip, int i) {
        this.id = id;
        this.ip = ip;
        this.opcion = i;
    }

    public MensajeSer() {
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the opcion
     */
    public int getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
    
    
}
