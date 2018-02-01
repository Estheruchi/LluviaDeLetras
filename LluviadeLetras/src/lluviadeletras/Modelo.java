/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Estheruchi
 */
public class Modelo {

    private int NUM_LETRAS = 25;
    private final String[] ABC = {"a", "b", "c", "d", "f", "g",
        "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z"};

    private int tiempoCaida = 10;
    private int tiempoCreacion = 1000;

    private Controlador control;
    private Bandeja bandeja;
    private ArrayList<Letra> letras;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
        manejarBandeja();
        crearLetras();
        timerCrear();
    }

    public void manejarBandeja() {
        control.dibujarBandeja(bandeja);
    }

    public int anchoVentana() {
        int x = control.anchoVentana();
        return x;
    }

    public int altoVentana() {
        int y = control.altoVentana();
        return y;
    }

    public void moverDcha() {
        bandeja.mover(0);
    }

    public void moverIzqda() {
        bandeja.mover(1);
    }

    public void crearLetras() {
        letras = new ArrayList();
        for (int i = 0; i < NUM_LETRAS; i++) {
            letras.add(new Letra(this, ABC[i]));
        }
    }

    public void letraAleatoria() {
        int indice = (int) Math.floor(Math.random() * 25);
        if (comprobar()) {
            System.out.println("CAE " + indice);
            System.out.println(letras.get(indice));
            letras.get(indice).setEstado(true);
            letras.get(indice).setVisible(true);
        } else {
            letraAleatoria();
        }
    }

    public boolean comprobar() {
        for (int i = 0; i < letras.size(); i++) {
            if (!letras.get(i).isEstado()) {
                return true;
            }
        }
        return false;
    }

    public void timerCrear() {
        Timer temporizador = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letraAleatoria();
            }
        });
        temporizador.start();
    }
}
