/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Estheruchi
 */
public class Controlador implements KeyListener, ActionListener {

    private Modelo modelo;
    private Vista vista;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()==KeyEvent.VK_LEFT){
            //modelo.mueveLaBarraDerecha();
        }
        
        if(e.getExtendedKeyCode()==KeyEvent.VK_RIGHT){
            //modelo.mueveLaBarraDerecha();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Guardar":
                break;
                //modelo.guardarPartida();
            case "Cargar":
                break;
                //modelo.cargarPartida();
            case "Salir":
                //vista.salirApp();
                break;
            case "Nivel 1":
                break;
            case "Nivel 2":
                break;
            case "Nivel 3":
                break;
            case "Nivel 4":
                break;
            case "Nivel 5":

        }
    }
}
