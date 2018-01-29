/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.util.ArrayList;
import javax.swing.JFrame;
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

    public Vista(Controlador control) {
        this.control = control;
        crearInterfaz();
    }

    public void crearInterfaz() {
        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setResizable(false);
        crearMenu();
        this.setVisible(true);
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

        /*
        JMenuItem miLvl1 = new JMenuItem("Nivel 1");
        miLvl1.addActionListener(control);

        JMenuItem miLvl2 = new JMenuItem("Nivel 2");
        miLvl2.addActionListener(control);

        JMenuItem miLvl3 = new JMenuItem("Nivel 3");
        miLvl3.addActionListener(control);

        JMenuItem miLvl4 = new JMenuItem("Nivel 4");
        miLvl4.addActionListener(control);

        JMenuItem miLvl5 = new JMenuItem("Nivel 5");
        miLvl5.addActionListener(control);

        mbMenu.add(mNivel);
        mNivel.add(miLvl1);
        mNivel.add(miLvl2);
        mNivel.add(miLvl3);
        mNivel.add(miLvl4);
        mNivel.add(miLvl5);*/
    }
}
