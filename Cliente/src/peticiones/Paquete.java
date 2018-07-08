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

//Objeto que procesan los almacenes 
public class Paquete implements Serializable{
    private String ipEmisor; //Quien lo envia
    private String ipDestinatarioFinal; // quien lo recibira
    private String mensaje; //mensaje a procesar
    
    /*tiempoSalida: tiempo tomando en el que se subio el paquete al transporte*/
    private String tiempoSalida;
    /*tiempoSalida: tiempo tomando en el que el almacen final bajo el paquete 
    del transporte*/
    private String tiempoLlegado;
    
    /**
     * @return the ipEmisor
     */
    public String getIpEmisor() {
        return ipEmisor;
    }

    /**
     * @param ipEmisor the ipEmisor to set
     */
    public void setIpEmisor(String ipEmisor) {
        this.ipEmisor = ipEmisor;
    }

    /**
     * @return the ipDestinatarioFinal
     */
    public String getIpDestinatarioFinal() {
        return ipDestinatarioFinal;
    }

    /**
     * @param ipDestinatarioFinal the ipDestinatarioFinal to set
     */
    public void setIpDestinatarioFinal(String ipDestinatarioFinal) {
        this.ipDestinatarioFinal = ipDestinatarioFinal;
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
     * @return the tiempoSalida
     */
    public String getTiempoSalida() {
        return tiempoSalida;
    }

    /**
     * @param tiempoSalida the tiempoSalida to set
     */
    public void setTiempoSalida(String tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
    }

    /**
     * @return the tiempoLlegado
     */
    public String getTiempoLlegado() {
        return tiempoLlegado;
    }

    /**
     * @param tiempoLlegado the tiempoLlegado to set
     */
    public void setTiempoLlegado(String tiempoLlegado) {
        this.tiempoLlegado = tiempoLlegado;
    }

    
}
