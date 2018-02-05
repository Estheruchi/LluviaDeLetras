/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Letra extends JButton {

    /*VARIABLES COMUNES*/
    private static final int ALTO = 50;
    private static final int ANCHO = 50;
    private static int AVANCE = 10;

    /*VARIABLES DE CADA PANEL*/
    private Modelo modelo;
    private Color color;
    private String letra;
    private boolean estado;

    /*TRUE: CAYENDO FALSE: OCULTO*/
    private int posX;
    private int posY = - 50;

    /*CONSTRUCTOR*/
    public Letra(Modelo modelo, String letra) {
        this.modelo = modelo;
        this.letra = letra;
        this.estado = false;
        generarColor();
        generarPosicion();
        dibujarPanel();
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void generarColor() {
        int r = (int) Math.round(Math.random() * 255);
        int g = (int) Math.round(Math.random() * 255);
        int b = (int) Math.round(Math.random() * 255);
        color = new Color(r, g, b);
        this.setBackground(color);
        this.setOpaque(true);
    }

    public void generarPosicion() {
        this.posX = (int) Math.floor(Math.random() * (500 - 50 + 1) + 50);
    }

    public void dibujarPanel() {
        //this.addActionListener(control);
        this.setBounds(posX, posY, ALTO, ANCHO);
    }

    public void mover() {

        if (posY >= (modelo.altoVentana() - 100)) {
            modelo.fin();
            //posY = -100;
        } else {
            posY += AVANCE;
        }

        this.setLocation(posX, posY);
    }
}
