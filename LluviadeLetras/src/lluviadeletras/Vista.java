/*
    Clase Vista: clase ventana.
 */
package lluviadeletras;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Estheruchi
 */
public class Vista extends JFrame {

    /*CONSTANTES*/
    private static final int ALTO = 600;
    private static final int ANCHO = 600;
    private static final int NUM_NIVELES = 6;

    private Controlador control;
    private JMenuBar mbMenu;
    private ArrayList<JMenuItem> niveles;
    private JLabel etiquetaPuntos, nivelActual;
    private int nivel;
    private FondoImagen fondo;

    public Vista(Controlador control) {
        this.control = control;
        this.addKeyListener(control);
        this.nivel = 1;

        crearFondo();
        crearInterfaz();

    }

    /**
     * Crea la interfaz de la ventana. Posicion, tamaño, componentes...
     */
    public void crearInterfaz() {
        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setResizable(false);
        this.setLocation(400, 50);
        crearMenu();
        crearEtiquetaPuntosNivel();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Añade un fondo para la ventana.
     */
    public void crearFondo() {
        fondo = new FondoImagen();
        this.add(fondo);
        fondo.setLayout(null);
        fondo.setBounds(0, 0, 600, 600);

    }

    /**
     * Creación del menú.
     */
    public void crearMenu() {
        mbMenu = new JMenuBar();
        this.setJMenuBar(mbMenu);
        crearMenuArchivo();
        crearMenuNivel();
    }

    /**
     * Creación de las opciones del menú
     */
    public void crearMenuArchivo() {
        JMenu mArchivo = new JMenu("Archivo");

        JMenuItem miGuardar = new JMenuItem("Guardar");
        miGuardar.addActionListener(control);

        JMenuItem miCargar = new JMenuItem("Cargar");
        miCargar.addActionListener(control);

        JMenuItem miSalir = new JMenuItem("Salir");
        miSalir.addActionListener(control);

        mbMenu.add(mArchivo);
        mArchivo.add(miGuardar);
        mArchivo.add(miCargar);
        mArchivo.add(miSalir);
    }

    /**
     * Crea las opciones del menú Nivel.
     */
    public void crearMenuNivel() {
        JMenu mNivel = new JMenu("Nivel");
        niveles = new ArrayList();
        mbMenu.add(mNivel);

        for (int i = 1; i < NUM_NIVELES; i++) {
            JMenuItem miLvl = new JMenuItem("Nivel " + i);
            miLvl.addActionListener(control);
            niveles.add(miLvl);
            mNivel.add(miLvl);
            String letra = "" + i;
            char num = letra.charAt(0);
            miLvl.setAccelerator(KeyStroke.getKeyStroke(num, KeyEvent.CTRL_MASK));
        }

    }

    /**
     * Crea las etiquetas de la puntuacion y el nivel
     */
    public void crearEtiquetaPuntosNivel() {
        CustomLetra cl = new CustomLetra();

        etiquetaPuntos = new JLabel("PUNTOS: ");
        fondo.add(etiquetaPuntos);
        etiquetaPuntos.setBounds(50, 470, 300, 40);
        etiquetaPuntos.setFont(cl.MyFont(1, 31f));
        etiquetaPuntos.setForeground(Color.WHITE);

        nivelActual = new JLabel("NIVEL " + nivel);
        fondo.add(nivelActual);
        nivelActual.setBounds(410, 10, 300, 40);
        nivelActual.setFont(cl.MyFont(1, 31f));
        nivelActual.setForeground(Color.WHITE);
    }

    /**
     * Añade la bandeja recibida a la ventana.
     *
     * @param bandeja -> bandeja a pintar
     */
    public void dibujarBandeja(Bandeja bandeja) {
        fondo.add(bandeja);
        refrescar();
    }

    /**
     * Añade la letra a la ventana
     *
     * @param letra -> Letra a pintar
     */
    public void dibujarLetra(Letra letra) {
        fondo.add(letra);
        refrescar();
    }

    /**
     * Hace repaint para que se refresque la vista
     */
    public void refrescar() {
        fondo.repaint();
    }

    public static int getALTO() {
        return ALTO;
    }

    public static int getANCHO() {
        return ANCHO;
    }

    public void actualizaContador(int contador) {
        etiquetaPuntos.setText("PUNTOS: " + contador);
    }

    public void actualizaNivel(int valor) {
        nivelActual.setText("Nivel: " + valor);
    }

    public int getNivel() {
        return nivel;
    }

    public void cambiaColor(Boolean encontrada) {
        if (!encontrada) {
            etiquetaPuntos.setForeground(Color.RED);
        } else {
            etiquetaPuntos.setForeground(Color.WHITE);
        }
    }
}
