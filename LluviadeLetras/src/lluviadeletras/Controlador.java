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
//
//            case KeyEvent.VK_A:
//            case KeyEvent.VK_B:
//            case KeyEvent.VK_C:
//            case KeyEvent.VK_D:
//            case KeyEvent.VK_E:
//            case KeyEvent.VK_F:
//            case KeyEvent.VK_G:
//            case KeyEvent.VK_H:
//            case KeyEvent.VK_I:
//            case KeyEvent.VK_J:
//            case KeyEvent.VK_K:
//            case KeyEvent.VK_L:
//            case KeyEvent.VK_M:
//            case KeyEvent.VK_N:
//            case KeyEvent.VK_O:
//            case KeyEvent.VK_P:
//            case KeyEvent.VK_Q:
//            case KeyEvent.VK_R:
//            case KeyEvent.VK_S:
//            case KeyEvent.VK_T:
//            case KeyEvent.VK_U:
//            case KeyEvent.VK_V:
//            case KeyEvent.VK_W:
//            case KeyEvent.VK_X:
//            case KeyEvent.VK_Y:
//            case KeyEvent.VK_Z:
//                
//            break;

            case KeyEvent.CTRL_DOWN_MASK + KeyEvent.VK_4:
                System.out.println("fdjfksdhfs");
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
            case "Nivel 1":
                cambiarNivel1();
                break;
            case "Nivel 2":
                cambiarNivel2();
                break;
            case "Nivel 3":
                cambiarNivel3();
                break;
            case "Nivel 4":
                cambiarNivel4();
                break;
            case "Nivel 5":
                cambiarNivel5();
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
                cambiarNivel2();
                break;
            case 20:
                cambiarNivel3();
                break;
            case 30:
                cambiarNivel4();
                break;
            case 40:
                cambiarNivel5();
                break;
        }
    }

    public void cambiarNivel1() {
        vista.actualizaNivel(1);
        modelo.cambiarNivel("NIVEL 1");
    }

    public void cambiarNivel2() {
        vista.actualizaNivel(2);
        modelo.cambiarNivel("NIVEL 2");
    }

    public void cambiarNivel3() {
        vista.actualizaNivel(3);
        modelo.cambiarNivel("NIVEL 3");
    }

    public void cambiarNivel4() {
        vista.actualizaNivel(4);
        modelo.cambiarNivel("NIVEL 4");
    }

    public void cambiarNivel5() {
        vista.actualizaNivel(5);
        modelo.cambiarNivel("NIVEL 5");
    }

    ///niveles
}
