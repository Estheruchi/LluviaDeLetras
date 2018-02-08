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

    private int tiempoCreacion = 100;

    private Controlador control;
    private Bandeja bandeja;

    private ArrayList<Letra> letras;

    private Timer temporizadorCrear;
    private int puntuacion;
    private int contador = 0;
    private int aciertos;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
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
     * //
     */
    public void letraAleatoria() {
        int indice = (int) Math.floor(Math.random() * 24);
        Letra auxiliar = letras.get(indice);

        while (!auxiliar.isEstado()) {
            auxiliar.setEstado(true);
            auxiliar.setPosY(-50);
            control.dibujarLetra(auxiliar);
            auxiliar.setText(ABC[indice]);
            control.refrescar();

        }
    }

//    public int crearLetraAleatoria() {
//        int indice = (int) Math.floor(Math.random() * 24);
//        Letra auxiliar = letras.get(indice);
//
//        while (!auxiliar.isEstado()) {
//            auxiliar.setEstado(true);
//            control.dibujarLetra(auxiliar);
//            auxiliar.setText(ABC[indice]);
//
//            auxiliar.mover();
//            if (auxiliar.isEstado() == true) {
//                break;
//            }
//        }
//        return indice;
//    }
    //para eliminar la letra que se pulsa si coincide 
    public void buscarLetra(String letra) {
        letra = letra.toUpperCase();
        for (int i = 0; i < letras.size(); i++) {
            Letra auxiliar = letras.get(i);

            if (auxiliar.getText().equals(letra)) {
                System.out.println("el texto del axuliar es: "+auxiliar.getText()+" -- y el de la letra: "+letra);
                letras.remove(i);  //remove i
                auxiliar.setVisible(false);
                aciertos++;
                control.dileVistaActualizaCont(aciertos);
            }
//            else {
//                //aciertos--;
//                //System.out.println("no has acertado");
//                //control.dileVistaActualizaCont(aciertos);
//            }
        }
    }

    public void timerCrear() {
        temporizadorCrear = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //letraAleatoria();

                for (int i = 0; i < letras.size(); i++) {
                    Letra auxiliar = letras.get(i);
                    auxiliar.mover();
                }

                if (contador == 0 || contador == 1000) {
                    letraAleatoria();
                }
                contador += 50;

                if (contador == 1000) {
                    contador = 0;
                }

                //control.refrescar();
            }
        });
        temporizadorCrear.start();
    }

    public void fin() {
        temporizadorCrear.stop();
        control.fin();
    }

    public void establecerNiveles() {

    }

    //
}
