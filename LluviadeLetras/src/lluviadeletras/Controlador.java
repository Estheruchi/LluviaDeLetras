/**/
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Estheruchi
 */
public class Controlador extends MouseAdapter implements KeyListener, ActionListener {

    private Modelo modelo;
    private Vista vista;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
    }

    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_LEFT:
                modelo.moverIzqda();
                break;
            case KeyEvent.VK_RIGHT:
                modelo.moverDcha();
                break;
            default:
                modelo.buscarLetra("" + e.getKeyChar());
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

    public void dibujarLetra(Letra letra) {
        vista.dibujarLetra(letra);
    }

    public int anchoVentana() {
        int anchoVista = vista.getANCHO();
        return anchoVista;
    }

    public int altoVentana() {
        int altoVista = vista.getALTO();
        return altoVista;
    }

    public void fin() {
        vista.dispose();
        //System.exit(0);
        VistaFin vfin = new VistaFin(this);
    }
}
