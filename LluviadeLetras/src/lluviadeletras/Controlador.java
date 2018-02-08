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
    private VistaFin vfin;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
    }

    public void mouseClicked(MouseEvent me) {
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
            case "REINTENTAR":
                vista.dispose();
                new Controlador();
                vfin.dispose();
                modelo.restablecerVelocidad();
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
        vfin = new VistaFin(this);
    }

    public void refrescar() {
        vista.refrescar();
    }

    public void dileVistaActualizaCont(int contador) {
        vista.actualizaContador(contador);
        switch (contador) {
            case 10:
                vista.actualizaNivel();
                modelo.cambiarNivel("NIVEL 2");
                break;
            case 20:
                vista.actualizaNivel();
                modelo.cambiarNivel("NIVEL 3");
                break;
            case 30:
                vista.actualizaNivel();
                modelo.cambiarNivel("NIVEL 4");
                break;
            case 40:
                vista.actualizaNivel();
                modelo.cambiarNivel("NIVEL 5");
                break;
        }
    }

    ///niveles
}
