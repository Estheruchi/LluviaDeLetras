/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

/**
 *
 * @author Estheruchi
 */
public class Controlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador() {
        modelo = new Modelo(this);
        vista = new Vista(this);
    }
}
