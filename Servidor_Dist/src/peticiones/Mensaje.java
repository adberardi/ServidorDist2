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

//Objeto que viaja entre almancenes
public class Mensaje implements Serializable{
    private int opcion; // Atributo para saber que tipo de peticion se realizara
    private String mensaje; // Atributo usado para dar respuesta
    private Transporte transporte; // Objeto que contiene los paquetes
    private String ip;
    
    public Mensaje( int opcion, String mensaje ){
        this.opcion = opcion;
        this.mensaje = mensaje;
    }
    
    public Mensaje( int opcion, String mensaje, Transporte transporte ){
        this.opcion = opcion;
        this.mensaje = mensaje;
        this.transporte = transporte;
    }
    
    public Mensaje( int opcion, String mensaje, String ip ){
        this.opcion = opcion;
        this.mensaje = mensaje;
        this.ip = ip;
    }
    
    public Mensaje(){}
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

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the transporte
     */
    public Transporte getTransporte() {
        return transporte;
    }

    /**
     * @param transporte the transporte to set
     */
    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
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
}
