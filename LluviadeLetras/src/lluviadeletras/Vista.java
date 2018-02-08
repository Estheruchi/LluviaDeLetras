/**/
package lluviadeletras;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Estheruchi
 */
public class Vista extends JFrame {

    /*CONSTANTES*/
    private static final int ALTO = 600;
    private static final int ANCHO = 600;
    private static final int NUM_NIVELES = 5;

    private Controlador control;
    private JMenuBar mbMenu;
    private ArrayList<JMenuItem> niveles;
    private JLabel etiquetaPuntos, nivelActual;
    private int nivel = 1;

    public Vista(Controlador control) {
        this.control = control;
        this.addKeyListener(control);
        //this.getContentPane().setBackground(Color.getHSBColor(100, 180, 171));
        crearInterfaz();
    }

    public void crearInterfaz() {
        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setResizable(false);
        crearMenu();
        crearEtiquetaPuntosNivel();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void crearMenu() {
        mbMenu = new JMenuBar();
        this.setJMenuBar(mbMenu);
        crearMenuArchivo();
        crearMenuNivel();
    }

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

    public void crearMenuNivel() {
        JMenu mNivel = new JMenu("Nivel");
        niveles = new ArrayList();
        mbMenu.add(mNivel);

        for (int i = 0; i < NUM_NIVELES; i++) {
            JMenuItem miLvl = new JMenuItem("Nivel " + (i + 1));
            miLvl.addActionListener(control);
            niveles.add(miLvl);
            mNivel.add(miLvl);
        }
    }

    public void crearEtiquetaPuntosNivel() {
        CustomLetra cl = new CustomLetra();

        etiquetaPuntos = new JLabel("PUNTOS: ");
        this.add(etiquetaPuntos);
        etiquetaPuntos.setBounds(50, 470, 300, 40);
        etiquetaPuntos.setFont(cl.MyFont(1, 31f));

        nivelActual = new JLabel("NIVEL " + nivel);
        this.add(nivelActual);
        nivelActual.setBounds(410, 70, 300, 40);
        nivelActual.setFont(cl.MyFont(1, 31f));
    }

    public void dibujarBandeja(Bandeja bandeja) {
        this.add(bandeja);
        refrescar();
    }

    public void dibujarLetra(Letra letra) {
        this.add(letra);
        refrescar();
    }

    public void fallo() {
        //Parpadeo que avisa de fallo 
    }

    public void refrescar() {
        this.repaint();
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

    public void actualizaNivel() {
        if(nivel<5){
            nivel++;
        }
        nivelActual.setText("Nivel: " + nivel);
    }

}
