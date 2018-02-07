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
    private int idTimer;
    private int puntuacion;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
        tiemposCaida = new ArrayList();
        this.idTimer = 0;
        this.puntuacion = 0;

        manejarBandeja();
        crearLetras();
        timerCrear();

    }

    /**
     * Dibujamos la bandeja.
     */
    public void manejarBandeja() {
        control.dibujarBandeja(bandeja);
    }

    /**
     * Obtenemos el ancho de la ventana
     */
    public int anchoVentana() {
        int x = control.anchoVentana();
        return x;
    }

    /**
     * Obtenemos el alto de la ventana
     */
    public int altoVentana() {
        int y = control.altoVentana();
        return y;
    }

    /**
     * Mueve la bandeja a la Derecha
     */
    public void moverDcha() {
        bandeja.mover(0);
    }

    /**
     * Mueve la bandeja a la Izquierda
     */
    public void moverIzqda() {
        bandeja.mover(1);
    }

    /**
     * Llenamos el array de letras con tantas letras como se indique en
     * NUM_LETRAS
     */
    public void crearLetras() {
        letras = new ArrayList();
        for (int i = 0; i < NUM_LETRAS; i++) {
            letras.add(new Letra(this, ABC[i]));
        }
    }

    /**
     * Genera un numero aleatorio que funcionara como indice. Si la letra
     * correspondiente a dicho indice no ha caido cae. Si, sin embargo está
     * cayendo, busca otra.
     *
     * Si encuentra que no esta cayendo, si es la primera vez crea un timer si
     * ya habia caido, vuelve a iniciar su timer.
     *
     */
    public void letraAleatoria() {
        int indice = (int) Math.floor(Math.random() * 24);
        Letra generada = letras.get(indice);

        if (!generada.isEstado()) {

            generada.setEstado(true);
            generada.setPosY(-50);

            control.dibujarLetra(generada);
            generada.setText(ABC[indice]);

            if (generada.getIdTimer() != -1) {
                generada.setVisible(true);
                tiemposCaida.get((generada.getIdTimer())).start();

            } else {
                timerCaer(generada);
                generada.setVisible(true);

            }
            control.refrescar();

        } else {
            letraAleatoria();
        }
    }

    /**
     *
     * @param letra
     */
    public void buscarLetra(String letra) {
        letra = letra.toUpperCase();
        for (int i = 0; i < letras.size(); i++) {
            if (letras.get(i).getText().equals(letra)) {

                letras.get(i).setVisible(false);
                letras.get(i).setEstado(false);
                letras.get(i).setPosY(-50);

                tiemposCaida.get((letras.get(i).getIdTimer())).stop();
            }
        }
    }

    public void timerCaer(Letra letra) {
        Timer temporizadorCaer = new Timer(tiempoCaida, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letra.mover();
            }
        });
        letra.setIdTimer(idTimer);
        idTimer++;
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
