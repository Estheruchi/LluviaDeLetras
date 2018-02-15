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
    private Bandeja bandejaSup;
    private Bandeja bandejaInf;

    private ArrayList<Letra> letras;

    private Timer temporizador;
    private int tiempo = 1000;
    private int contadorTiempo = 0;

    private int puntuacion;
    private int nivelActual;
    private boolean primeraLetra = false;
    private int velocidadLetras;
    private int aciertosSeguidos;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandejaSup = new Bandeja(this, 40);
        this.bandejaInf = new Bandeja(this, 450);
        this.puntuacion = 0;
        this.aciertosSeguidos = 0;
        this.nivelActual = 1;
        manejarBandeja();
        crearLetras();
        crearTimer();

    }

    public void cambiarNivel(String lvl) {
        switch (lvl) {
            case "NIVEL 1":
                velocidadLetras = 5;
                tiempo = 1000;
                nivelActual = 1;
                break;
            case "NIVEL 2":
                velocidadLetras = 10;
                tiempo = 900;
                nivelActual = 2;
                break;
            case "NIVEL 3":
                velocidadLetras = 13;
                tiempo = 800;
                nivelActual = 3;
                break;
            case "NIVEL 4":
                velocidadLetras = 15;
                tiempo = 700;
                nivelActual = 4;
                break;
            case "NIVEL 5":
                velocidadLetras = 19;
                tiempo = 550;
                nivelActual = 5;
                break;
        }
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setAVANCE(velocidadLetras);
        }
    }

    public void restablecerVelocidad() {
        for (int i = 0; i < letras.size(); i++) {
            letras.get(i).setAVANCE(5);
        }
    }

    /**
     * Dibujamos la bandeja.
     */
    public void manejarBandeja() {
        control.dibujarBandeja(bandejaSup);
        control.dibujarBandeja(bandejaInf);
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
        bandejaSup.mover(0);
        bandejaInf.mover(0);
    }

    /**
     * Mueve la bandeja a la Izquierda
     */
    public void moverIzqda() {
        bandejaSup.mover(1);
        bandejaInf.mover(1);
    }

    /**
     * Crea el array de letras.
     */
    public void crearLetras() {
        letras = new ArrayList();
    }

    /**
     * Genera un indice de manera aleatoria. Tenemos una bandera que marca si es
     * la primera letra creada o no. Si lo es, la guarda y no vuelve a entrar.
     * Si no es la primera, siempre la buscara y si la encuentra genera un nuevo
     * indice hasta que no se repita.
     *
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

    /**
     * Recibe una letra y la busca en el array de letras actuales en pantalla.
     * Si la encuentra: La elimina, aumenta la puntuacion, aumenta el contador
     * de letras acertadas seguidas y comprueba si ha llegado a 10 para subir el
     * nivel. Actualiza la vista por medio del controlador. La bandera marca si
     * se ha encontrado o no para así restar puntos, comprobar si se ha llegado
     * a 0 en la puntuacion y finalizar la partida.
     *
     * @param letra -> Letra a buscar.
     */
    public void buscarLetra(String letra) {
        boolean encontrada = false;
        letra = letra.toUpperCase();
        for (int i = 0; i < letras.size(); i++) {
            Letra auxiliar = letras.get(i);
            if (auxiliar.getText().equals(letra)) {
                letras.remove(i);
                auxiliar.setVisible(false);
                puntuacion++;
                aciertosSeguidos++;
                comprobarPuntuacion();
                control.repintarContador(puntuacion);
                encontrada = true;
            }
        }
        if (!encontrada) {
            puntuacion--;
            aciertosSeguidos = 0;
            comprobarFin();
            control.repintarContador(puntuacion);
        }
        control.cambiaColor(encontrada);
    }

    /**
     * Comprueba las letras acertadas seguidas, si llega a 10 aumenta el nivel y
     * vuelve a iniciarlo a 0.
     */
    public void comprobarPuntuacion() {
        if (aciertosSeguidos == 10) {
            aciertosSeguidos = 0;
            control.cambiarNiveles(nivelActual + 1);
        }
    }

    /**
     * Crea un Timer. El tiempo marca el numero necesario para crear una letra
     * nueva, el contador aumenta un numero cada vez que se ejecuta el timer. Si
     * el contador alcanza al tiempo, crea una letra. Cuando el contador supera
     * o iguala al tiempo se reinicia.
     *
     * Cada vez que se ejecuta mueve las letras en pantalla y comprueba su
     * choque con la bandeja.
     *
     */
    public void crearTimer() {
        temporizador = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (contadorTiempo <= 0 || contadorTiempo >= tiempo) {
                    letraAleatoria();
                }
                contadorTiempo += 50;

                if (contadorTiempo >= tiempo) {
                    contadorTiempo = 0;
                }
                for (int i = 0; i < letras.size(); i++) {
                    Letra auxiliar = letras.get(i);
                    comprobarChoque();
                    auxiliar.mover();
                }
            }
        });
        temporizador.start();
    }

    /**
     * Para el timer y finaliza la partida
     */
    public void fin() {
        temporizador.stop();
        control.fin();
    }

    /**
     * Comprueba el choque de las letras con la bandeja CAMBIAR
     */
    public void comprobarChoque() {
        for (int i = 0; i < letras.size(); i++) {

            if (letras.get(i).getY() >= 360
                    && letras.get(i).getX() > bandejaInf.getX() - 95
                    && letras.get(i).getX() < bandejaInf.getX() + 95
                    && letras.get(i).getDireccion() == 0) {
                letras.get(i).cambiarDireccion();
            }
        }

    }

    /**
     * Comprueba si la puntuacion ha llegado a 0 y si es asi finaliza.
     */
    public void comprobarFin() {
        if (puntuacion < 0) {
            fin();
        }
    }

    public int getAciertos() {
        return puntuacion;
    }

    public void setAciertos(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int getNivelActual() {
        return nivelActual;
    }

}
