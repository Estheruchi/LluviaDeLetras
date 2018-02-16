/*
    Clase que almacena puntos y nivel.
 */
package lluviadeletras;

import java.io.Serializable;

/**
 *
 * @author javierflopez
 */
public class Datos implements Serializable {

    int nivel = 0;
    int puntos = 0;
    String nombre;

    public Datos(int puntos, int nivel,String nombre) {
        this.nivel = nivel;
        this.puntos = puntos;
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

}
