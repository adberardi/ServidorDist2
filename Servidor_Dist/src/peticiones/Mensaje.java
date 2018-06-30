/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peticiones;

import java.io.Serializable;

/**
 *
 * @author adtony45
 */
public class Mensaje implements Serializable{
    private int opcion;
    private String mensaje;
    public Mensaje( int opcion, String mensaje ){
        this.opcion = opcion;
        this.mensaje = mensaje;
    }
    public Mensaje(){
        
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
    
         
    
}
