/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Estheruchi
 */
public class VistaFin extends JFrame {

    /*CONSTANTES*/
    private static final int ALTO = 600;
    private static final int ANCHO = 600;

    private Controlador control;

    public VistaFin(Controlador control) {
        this.control = control;
        crearInterfaz();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void crearInterfaz() {
        JLabel titulo = new JLabel("GAME OVER");
        this.add(titulo);
        JButton reintentar = new JButton("REINTENTAR");
        this.add(reintentar);

        this.setLayout(null);
        this.setSize(ANCHO, ALTO);
        this.setResizable(false);
    }
}
