/**/
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Estheruchi
 */
public class Controlador extends MouseAdapter implements KeyListener, ActionListener {

    private Modelo modelo;
    private Vista vista;
    private VistaFin vfin;
    private PartidaCargarGuardar partida;
    private DatosUsuario data;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
        partida = new PartidaCargarGuardar();
        data = new DatosUsuario();
    }

    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Controla moviemiento de bandeja con derecha e izquierda, las teclas que
     * interfieren en los atajos de teclado para que no resten puntos y si no es
     * ninguno de estos, sabe que es una letra.
     *
     * @param e -> Evento producido
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getExtendedKeyCode()) {
            case KeyEvent.VK_LEFT:
                modelo.moverIzqda();
                break;
            case KeyEvent.VK_RIGHT:
                modelo.moverDcha();
                break;
            case KeyEvent.VK_CONTROL:
                break;
            case KeyEvent.VK_1:
                break;
            case KeyEvent.VK_2:
                break;
            case KeyEvent.VK_3:
                break;
            case KeyEvent.VK_4:
                break;
            case KeyEvent.VK_5:
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
        {
            try {
                partida.guardar(data);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            
            case "Cargar":
        {
            try {
                partida.cargar();
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case "Salir":
                this.fin();
                break;
            case "REINTENTAR":
                vista.dispose();
                new Controlador();
                vfin.dispose();
                modelo.restablecerVelocidad();
                break;
            case "Nivel 1":
                cambiarNivel1();
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 2":
                cambiarNivel2();
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 3":
                cambiarNivel3();
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 4":
                cambiarNivel4();
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 5":
                cambiarNivel5();
                modelo.setAciertos(0);
                vista.actualizaContador(0);
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

    public void cambiarNiveles(int nivel) {
        vista.actualizaNivel(nivel);
        modelo.cambiarNivel("NIVEL " + nivel);
    }
    ///niveles
}
