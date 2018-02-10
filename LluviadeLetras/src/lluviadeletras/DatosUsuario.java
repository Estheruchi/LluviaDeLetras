/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.io.Serializable;

/**
 *
 * @author javierflopez
 */
public class DatosUsuario implements Serializable {

    private Modelo modelo;
    int nivel = 0;
    int puntos = 0;
    
    public DatosUsuario(){
        //modelo = new Modelo();
        //this.nivel = modelo.getnivel();
        //this.puntos = modelo.getpuntos();
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

    //es buena idea poner siempre un método toString() para
    //mostrar todos los datos del objeto.
    public String toString() {
        return "Puntos: " + puntos + "\nNivel: " + nivel;
    }

}
