/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.Color;
import java.awt.Font;
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
    private static int VELOCIDAD = 5;
    private static int SUBIENDO = 1;
    private static int BAJANDO = 0;

    /*VARIABLES DE CADA PANEL*/
    private Modelo modelo;
    private Color color;
    private String letra;
    private boolean estado; //si ha salido o no
    private int direccion;


    /*TRUE: CAYENDO FALSE: OCULTO*/
    private int posX;
    private int posY = - 50;

    /*CONSTRUCTOR*/
    public Letra(Modelo modelo, String letra) {
        this.modelo = modelo;
        this.letra = letra;
        this.estado = false;
        this.direccion = BAJANDO;

        generarColor();
        generarPosicion();
        dibujarPanel();

    }

    public void aumentarVelocidad() {
        VELOCIDAD += 3;
        System.out.println(VELOCIDAD);
    }

    public void cambiarDireccion() {
        if (direccion == BAJANDO) {
            direccion = SUBIENDO;
        } else {
            direccion = BAJANDO;
        }
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void generarColor() {
        int r = (int) Math.round(Math.random() * 255);
        int g = (int) Math.round(Math.random() * 255);
        int b = (int) Math.round(Math.random() * 255);
        color = new Color(r, g, b);
        this.setBackground(Color.black);
        this.setOpaque(false);
    }

    public void generarPosicion() {
        this.posX = (int) Math.floor(Math.random() * (500 - 50 + 1) + 50);
    }

    public void dibujarPanel() {
        this.setBounds(posX, posY, ALTO, ANCHO);
        this.setFont(new Font("Arial",1,20));
        this.setForeground(Color.CYAN);
    }

    public void mover() {
        if (direccion == BAJANDO) {
            if (posY >= (modelo.altoVentana() - 100)) {
                modelo.fin();
            } else {
                posY += VELOCIDAD;
            }
        } else {
            if (posY <= 0) {
                modelo.fin();
            } else {
                posY -= VELOCIDAD;
            }
        }

        this.setLocation(posX, posY);
    }

    public void setVELOCIDAD(int VELOCIDAD) {
        Letra.VELOCIDAD = VELOCIDAD;
    }

    public int getDireccion() {
        return direccion;
    }

}
