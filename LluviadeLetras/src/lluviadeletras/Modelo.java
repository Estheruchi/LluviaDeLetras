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

    private int NUM_LETRAS_NIVEL;
    private int NUM_LETRAS = 25;

    private final String[] ABC = {"A", "B", "C", "D", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z"};

    private int tiempoCreacion = 50;

    private Controlador control;
    private Bandeja bandeja;

    private ArrayList<Letra> letras;

    private Timer temporizadorCrear;
    private int puntuacion;
    private int contador = 0;
    private int aciertos;
    private boolean primeraLetra = false;
    private int tiempo = 1000;
    private int velocidadLetras;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
        this.puntuacion = 0;

        manejarBandeja();
        crearLetras();
        timerCrear();

    }

    public void cambiarNivel(String lvl) {
        /*for (int i = 0; i < letras.size(); i++) {
            letras.get(i).aumentarVelocidad();
        }*/
        switch (lvl) {
            case "NIVEL 1":
                velocidadLetras = 5;
                tiempo = 1000;
                break;
            case "NIVEL 2":
                velocidadLetras = 9;
                tiempo = 700;
                break;
            case "NIVEL 3":
                velocidadLetras = 13;
                tiempo = 500;
                break;
            case "NIVEL 4":
                velocidadLetras = 17;
                tiempo = 250;
                break;
            case "NIVEL 5":
                velocidadLetras = 21;
                tiempo = 100;
                break;
        }
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setVELOCIDAD(velocidadLetras);
        }
    }

    public void restablecerVelocidad() {
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setVELOCIDAD(5);
        }
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
    }

    /**
     * Genera un numero aleatorio que funcionara como indice. Si la letra
     * correspondiente a dicho indice no ha caido cae. Si, sin embargo está
     * cayendo, busca otra.
     *
     * Si encuentra que no esta cayendo, si es la primera vez crea un timer si
     * ya habia caido, vuelve a iniciar su timer.
     *
     * //
     */
    public void letraAleatoria() {
        int indice = generarNuevaLetra();
        if (!primeraLetra) {
            letras.add(new Letra(this, ABC[indice]));
            letras.get(letras.size() - 1).setText(ABC[indice]);
            letras.get(letras.size() - 1).setVisible(true);
            control.dibujarLetra(letras.get(letras.size() - 1));
            primeraLetra = true;
        } else {

            for (int i = 0; i < letras.size(); i++) {
                if (letras.get(i).getText().equals(ABC[indice])) {
                    indice = generarNuevaLetra();
                    i = 0;
                }
            }
            letras.add(new Letra(this, ABC[indice]));
            letras.get(letras.size() - 1).setText(ABC[indice]);
            letras.get(letras.size() - 1).setVisible(true);
            control.dibujarLetra(letras.get(letras.size() - 1));
        }

    }

    public int generarNuevaLetra() {
        int indice = (int) Math.floor(Math.random() * 24);
        return indice;
    }

    //para eliminar la letra que se pulsa si coincide
    public void buscarLetra(String letra) {
        boolean bandera = true;
        letra = letra.toUpperCase();
        for (int i = 0; i < letras.size(); i++) {
            Letra auxiliar = letras.get(i);

            if (auxiliar.getText().equals(letra)) {
                letras.remove(i);
                auxiliar.setVisible(false);
                aciertos++;
                control.dileVistaActualizaCont(aciertos);
                bandera = false;
            }

        }
        if (bandera) {

            aciertos--;
            comprobarAciertos();
            control.dileVistaActualizaCont(aciertos);

        }

    }

    public void timerCrear() {
        temporizadorCrear = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (contador <= 0 || contador >= tiempo) {
                    letraAleatoria();
                }
                contador += 50;

                if (contador >= tiempo) {
                    contador = 0;
                }
                for (int i = 0; i < letras.size(); i++) {
                    Letra auxiliar = letras.get(i);
                    comprobarChoque();
                    auxiliar.mover();
                }
            }
        });
        temporizadorCrear.start();
    }

    public void fin() {
        temporizadorCrear.stop();
        control.fin();
    }

    public void comprobarChoque() {
        for (int i = 0; i < letras.size(); i++) {

            //if (bandeja.getY() == letras.get(i).getY() && bandeja.getX() == letras.get(i).getX()) {
            if (letras.get(i).getY() >= 360
                    && letras.get(i).getX() > bandeja.getX() - 95
                    && letras.get(i).getX() < bandeja.getX() + 95
                    && letras.get(i).getDireccion() == 0) {
                letras.get(i).cambiarDireccion();
            }
        }

    }

    public void comprobarAciertos() {
        if (aciertos < 0) {
            fin();
        }
    }

}
