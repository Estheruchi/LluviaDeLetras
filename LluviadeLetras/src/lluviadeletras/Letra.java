/*
  Clase Letra: Es una clase de tipo botón que se gestiona a sí misma.
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
    
    private static int SUBIENDO = 1;
    private static int BAJANDO = 0;

    /*VARIABLES DE CADA BOTÓN*/
    private Modelo modelo;
    private Color color;
    private String letra;
    private int direccion;

    //private int velocidad;
    private int pulsaciones;
    private int avance;

    private int posX;
    private int posY = - 50;
    private int idTimer;

    /*CONSTRUCTOR*/
    public Letra(Modelo modelo, String letra) {
        this.modelo = modelo;
        this.letra = letra;
        this.direccion = BAJANDO;
        this.setBackground(Color.black);
        this.setOpaque(false);

        generarPosicion();
        generarVelocidad();
        generarPulsaciones();
        dibujarPanel();
    }

    public void generarVelocidad() {
        this.avance = (int) Math.floor(Math.random() * (5 - 1 + 1) + 1);
  
    }

    /**
     * Genera mediante un aletorio el numero de pulsaciones necesarios para
     * elimianr una letra.
     */
    public void generarPulsaciones() {
        this.pulsaciones = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
        generarColorPulsacion();
    }

    public void generarColorPulsacion() {
        switch (pulsaciones) {
            case 1:
                this.setForeground(Color.GREEN);
                break;
            case 2:
                this.setForeground(Color.ORANGE);
                break;
            case 3:
                this.setForeground(Color.RED);
                break;
        }
    }

    /**
     * Cambia la direccion de la letra. Sube o cae.
     */
    public void cambiarDireccion() {
        if (direccion == BAJANDO) {
            direccion = SUBIENDO;
        } else {
            direccion = BAJANDO;
        }
    }

    /**
     * Cambia la posicionY (vertical)
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Genera una posicionX (horizontal) de manera alearoría para que todas las
     * letras tengan una posicion diferente.
     */
    public void generarPosicion() {
        this.posX = (int) Math.floor(Math.random() * (500 - 50 + 1) + 50);
    }

    /**
     * Establece posicion, tamaño, fuente y color de la letra.
     */
    public void dibujarPanel() {
        this.setBounds(posX, posY, ALTO, ANCHO);
        this.setFont(new Font("Arial", 1, 18));
    }

    /**
     * Controla el movimiento de la letra en funcion de la direccion y los
     * límites de la ventana.
     */
    public void mover() {
        if (direccion == BAJANDO) {
            if (posY >= (modelo.altoVentana() - 100)) {
                modelo.fin();
            } else {
                posY += avance; 
            }
        } else {
            if (posY <= 0) {
                modelo.fin();
            } else {
                posY -= avance;
            }
        }
        this.setLocation(posX, posY);
    }

    /**
     *
     * @return direccion -> Devuelve la direccion actual de la letra.
     */
    public int getDireccion() {
        return direccion;
    }

    public int getPulsaciones() {
        return pulsaciones;
    }

    public void setPulsaciones(int pulsaciones) {
        this.pulsaciones = pulsaciones;
    }

    public void setIdTimer(int idTimer) {
        this.idTimer=idTimer;
    }

    public int getIdTimer() {
        return idTimer;
    }
    
}
