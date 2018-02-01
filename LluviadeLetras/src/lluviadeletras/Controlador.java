/**/
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
        if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
            modelo.moverIzqda();
        }

        if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
            modelo.moverDcha();
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
            default:
                System.out.println(e.getActionCommand());
                break;

        }
    }

    /*METODOS*/
    public void dibujarBandeja(Bandeja bandeja) {
        vista.dibujarBandeja(bandeja);
    }

    public int anchoVentana() {
        int anchoVista = vista.getANCHO();
        return anchoVista;
    }

    public int altoVentana() {
        int altoVista = vista.getALTO();
        return altoVista;
    }
}
