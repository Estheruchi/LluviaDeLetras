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

    private int posY;
    private int posX;
    private Modelo modelo;

    public Bandeja(Modelo modelo, int posY) {
        this.posY = posY;
        this.modelo = modelo;
        this.posX = 250;
        dibujarBandeja();
    }

    public void dibujarBandeja() {
        this.setBounds(posX, posY, 100, 20);
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
                if (posX + 100 >= ancho) {
                    posX = ancho - 100;
                } else {
                    posX += AVANCE;
                }
                break;
            case 1:
                if (posX + 100 <= 100) {
                    posX = 0;
                } else {
                    posX -= AVANCE;
                }
                break;
        }
        this.setLocation(posX, posY);
    }

    public int getposY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

}
