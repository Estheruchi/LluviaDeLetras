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
    private final String[] ABC = {"A", "B", "C", "D", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z"};

    private int tiempoCaida = 100;
    private int tiempoCreacion = 1000;

    private Controlador control;
    private Bandeja bandeja;
    private ArrayList<Letra> letras;
    private ArrayList<Timer> tiemposCaida;
    private Timer temporizadorCrear;
    //private Timer temporizadorCaer;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
        tiemposCaida = new ArrayList();
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
        int indice = (int) Math.floor(Math.random() * 24);

        if (!letras.get(indice).isEstado()) {
            System.out.println("CAE " + indice);
            System.out.println(letras.get(indice));
            letras.get(indice).setEstado(true);
            control.dibujarLetra(letras.get(indice));
            letras.get(indice).setText(ABC[indice]);
            timerCaer(letras.get(indice));
        } else {
            letraAleatoria();
        }
    }

    public void timerCaer(Letra letra) {
        Timer temporizadorCaer = new Timer(tiempoCaida, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letra.mover();
            }
        });
        temporizadorCaer.start();
        tiemposCaida.add(temporizadorCaer);
    }

    public void timerCrear() {
        temporizadorCrear = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letraAleatoria();
            }
        });
        temporizadorCrear.start();
    }

    public int getTiempoCaida() {
        return tiempoCaida;
    }

    public void pararCaida() {
        for (int i = 0; i < tiemposCaida.size(); i++) {
            tiemposCaida.get(i).stop();
        }
    }

    public void fin() {
        pararCaida();
        temporizadorCrear.stop();
        control.fin();
    }

}
