/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peticiones;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marvian
 */
public class Transporte implements Serializable{
    
    private String identificador;
    private String ipDestinatario;
    private int puertoDestinatario;
    private ArrayList<Paquete> paquetes;
    
    /*SegundosEnMax: segundos totales en el que el transporte esta a su 
    maxima capacidad*/
    private float SegundosEnMax;
    
    /*tiempoInicioLleno= tiempo tomado cuando se lleno el transporte a 
    su maxima capacidad (5 paquetes)*/
    private long tiempoInicioLleno;
    
    /*tiempoFinLleno= tiempo tomado cuando dejo de estar lleno el transporte a 
    su maxima capacidad (5 paquetes)*/
    private long tiempoFinLleno;
    
    /*cantidadTotalPaquetes: variable que lleva la cuenta de todo los paquete 
    que se intentaron subir con exito o no*/
    private int cantidadTotalPaquetes;
    
    /*cantidadTotalPaquetesSubidos: variable que lleva la cuenta de todo los paquete 
    que se intentaron subir con exito*/
    private int cantidadTotalPaquetesSubidos;

    public Transporte(String prueba, String prueba0, int i, ArrayList<Paquete> paquetes) {
        this.identificador = prueba;
        this.ipDestinatario = prueba0;
        this.puertoDestinatario = i;
        this.paquetes = paquetes;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the ipDestinatario
     */
    public String getIpDestinatario() {
        return ipDestinatario;
    }

    /**
     * @param ipDestinatario the ipDestinatario to set
     */
    public void setIpDestinatario(String ipDestinatario) {
        this.ipDestinatario = ipDestinatario;
    }

    /**
     * @return the puertoDestinatario
     */
    public int getPuertoDestinatario() {
        return puertoDestinatario;
    }

    /**
     * @param puertoDestinatario the puertoDestinatario to set
     */
    public void setPuertoDestinatario(int puertoDestinatario) {
        this.puertoDestinatario = puertoDestinatario;
    }

    /**
     * @return the paquetes
     */
    public ArrayList<Paquete> getPaquetes() {
        return paquetes;
    }

    /**
     * @param paquetes the paquetes to set
     */
    public void setPaquetes(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    /**
     * @return the SegundosEnMax
     */
    public float getSegundosEnMax() {
        return SegundosEnMax;
    }

    /**
     * @param SegundosEnMax the SegundosEnMax to set
     */
    public void setSegundosEnMax(float SegundosEnMax) {
        this.SegundosEnMax = SegundosEnMax;
    }

    /**
     * @return the tiempoInicioLleno
     */
    public long getTiempoInicioLleno() {
        return tiempoInicioLleno;
    }

    /**
     * @param tiempoInicioLleno the tiempoInicioLleno to set
     */
    public void setTiempoInicioLleno(long tiempoInicioLleno) {
        this.tiempoInicioLleno = tiempoInicioLleno;
    }

    /**
     * @return the tiempoFinLleno
     */
    public long getTiempoFinLleno() {
        return tiempoFinLleno;
    }

    /**
     * @param tiempoFinLleno the tiempoFinLleno to set
     */
    public void setTiempoFinLleno(long tiempoFinLleno) {
        this.tiempoFinLleno = tiempoFinLleno;
    }

    /**
     * @return the cantidadTotalPaquetes
     */
    public int getCantidadTotalPaquetes() {
        return cantidadTotalPaquetes;
    }

    /**
     * @param cantidadTotalPaquetes the cantidadTotalPaquetes to set
     */
    public void setCantidadTotalPaquetes(int cantidadTotalPaquetes) {
        this.cantidadTotalPaquetes = cantidadTotalPaquetes;
    }

    /**
     * @return the cantidadTotalPaquetesSubidos
     */
    public int getCantidadTotalPaquetesSubidos() {
        return cantidadTotalPaquetesSubidos;
    }

    /**
     * @param cantidadTotalPaquetesSubidos the cantidadTotalPaquetesSubidos to set
     */
    public void setCantidadTotalPaquetesSubidos(int cantidadTotalPaquetesSubidos) {
        this.cantidadTotalPaquetesSubidos = cantidadTotalPaquetesSubidos;
    }
    
    
}
