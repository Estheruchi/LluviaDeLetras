/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class Bandeja extends JLabel {

    private static String RUTA = "/Imagenes/";
    private static String FICHERO = "barrita.png";
    private static int AVANCE = 20;
    private int POSICION_Y = 400;

    private int posicionX;
    private Modelo modelo;

    public Bandeja(Modelo modelo) {
        this.modelo = modelo;
        this.posicionX = 250;
        dibujarBandeja();
    }

    public void dibujarBandeja() {
        this.setBounds(posicionX, POSICION_Y, 100, 20);
        URL url = this.getClass().getResource(RUTA + FICHERO);
        ImageIcon icon = new ImageIcon(url);
        this.setIcon(icon);
    }

    public void mover(int direccion) {

        int ancho = modelo.anchoVentana();

        switch (direccion) {
            case 0: //dcha
                if (posicionX + 100 >= ancho) {
                    posicionX = ancho - 100;
                } else {
                    posicionX += AVANCE;
                }
                break;
            case 1: //izqda
                if (posicionX + 100 <= 100) {
                    posicionX = 0;
                } else {
                    posicionX -= AVANCE;
                }
                break;
        }
        this.setLocation(posicionX, POSICION_Y);
    }
}
