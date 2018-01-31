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
    private static final String[] LETRAS = {"a", "b", "c", "d", "f", "g",
        "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z"};
    private static final int ALTO = 50;
    private static final int ANCHO = 50;

    private static String[] estado = new String[10];
    private static int c = 0;

    /*VARIABLES DE CADA PANEL*/
    private Modelo modelo;
    private Color color;
    private String letra;
    private int posX;
    private int posY = - 50;


    /*CONSTRUCTOR*/
    public Letra(Modelo modelo) {
        this.modelo = modelo;
        cargarEstado();
        generarColor();
        generarLetra();
        generarPosicion();
        dibujarPanel();
    }

    public void cargarEstado() {
        if (c == 0) {
            for (int i = 0; i < 10; i++) {
                estado[i] = "";
            }
        } else if (c == 10) {
            c = 0;
            cargarEstado();
        }
    }

    public void generarColor() {
        int r = (int) Math.round(Math.random() * 255);
        int g = (int) Math.round(Math.random() * 255);
        int b = (int) Math.round(Math.random() * 255);
        color = new Color(r, g, b);
        this.setBackground(color);
    }

    public void reiniciarLetra(int i) {
        c = i;
        cargarEstado();
        generarLetra();
    }

    public void generarLetra() {
        int n = (int) Math.floor(Math.random() * 25);
        if (comprobar(n)) {
            estado[c] = LETRAS[n];
            this.setText(LETRAS[n]);
            c++;
        } else {
            generarLetra();
        }
    }

    public boolean comprobar(int n) {
        if (c != 0) {
            for (int i = 0; i <= c; i++) {
                if (estado[i].equals(LETRAS[n])) {
                    return false;
                }
            }
        }
        return true;
    }

    public void generarPosicion() {
        this.posX = (int) Math.floor(Math.random() * (500 - 50 + 1) + 50);
    }

    public void dibujarPanel() {
        //this.addActionListener(control);
        this.setBounds(posX, posY, ALTO, ANCHO);
    }

}
