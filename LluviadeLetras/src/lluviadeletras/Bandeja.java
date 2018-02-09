/**
 * Clase bandeja: clase de tipo etiqueta.
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

    /**
     * Mueve la bandeja en funcion de la direccion recibida y los limites de la
     * ventana.
     *
     * @param direccion -> 0 derecha - 1 izquierda
     */
    public void mover(int direccion) {

        int ancho = modelo.anchoVentana();

        switch (direccion) {
            case 0:
                if (posicionX + 100 >= ancho) {
                    posicionX = ancho - 100;
                } else {
                    posicionX += AVANCE;
                }
                break;
            case 1:
                if (posicionX + 100 <= 100) {
                    posicionX = 0;
                } else {
                    posicionX -= AVANCE;
                }
                break;
        }
        this.setLocation(posicionX, POSICION_Y);
    }

    public int getPOSICION_Y() {
        return POSICION_Y;
    }

    public int getPosicionX() {
        return posicionX;
    }

}
