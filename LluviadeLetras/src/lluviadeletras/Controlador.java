/**/
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
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
    private Serializador serializador;
    private Datos data;

    public Controlador() {
        vista = new Vista(this);
        modelo = new Modelo(this);
        serializador = new Serializador();
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
                guardarDatos();
                break;
            case "Cargar":
                cargarDatos();
                break;
            case "Salir":
                this.fin();
                break;
            case "REINTENTAR":
                reiniciar();
                break;
            case "Nivel 1":
                cambiarNiveles(1);
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 2":
                cambiarNiveles(2);
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 3":
                cambiarNiveles(3);
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 4":
                cambiarNiveles(4);
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;
            case "Nivel 5":
                cambiarNiveles(5);
                modelo.setAciertos(0);
                vista.actualizaContador(0);
                break;

        }
    }

    public void reiniciar() {
        vista.dispose();
        new Controlador();
        cambiarNiveles(1);
        vfin.dispose();
        modelo.restablecerVelocidad();
    }

    /**
     * Creamos datos para almacenar los puntos y el nivel actual. Llamamos a la
     * funcion de guardado que serializa.
     */
    public void guardarDatos() {
        try {
            data = new Datos(modelo.getAciertos(), modelo.getNivelActual());
            serializador.guardar(data);
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creamos unos datos con lo que hemos leido en la carga.
     */
    public void cargarDatos() {
        try {
            data = serializador.cargar();
            modelo.setAciertos(data.getPuntos());
            vista.actualizaContador(modelo.getAciertos());
            modelo.setNivelActual(data.getNivel());
            cambiarNiveles(modelo.getNivelActual());
            vista.refrescar();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public void repintarContador(int contador) {
        vista.actualizaContador(contador);
    }

    public void cambiarNiveles(int nivel) {
        vista.actualizaNivel(nivel);
        modelo.cambiarNivel("NIVEL " + nivel);
    }

}
