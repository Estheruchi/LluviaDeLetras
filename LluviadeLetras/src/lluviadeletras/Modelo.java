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

    private final String[] ABC = {"A", "B", "C", "D", "F", "G",
        "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z"};

    private int tiempoCreacion = 50;

    private Controlador control;
    private Bandeja bandejaSup;
    private Bandeja bandejaInf;

    private ArrayList<Letra> letras;
    private ArrayList<Letra> numeros;
    private ArrayList<Timer> timers;

    private Timer temporizador;
    private int tiempo = 1000;
    private int contadorTiempo = 0;
    private int idTimer;

    private int puntuacion;
    private int nivelActual;
    private boolean primeraLetra = false;
    private boolean primerNumero = false;
    private int velocidadLetras;
    private int aciertosSeguidos;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandejaSup = new Bandeja(this, 40);
        this.bandejaInf = new Bandeja(this, 450);
        this.puntuacion = 0;
        this.aciertosSeguidos = 0;
        this.nivelActual = 1;
        this.idTimer = 0;

        manejarBandeja();
        crearLetras();
        crearNumeros();
        lanzaLetras();
    }

    /**
     * Metodo para cambiar la logica de los niveles, recibe el nivel y en funcion de cual sea
     * actualiza el tiempo de creacion y las posiciones de las bandejas superior e inferior
     * @param lvl 
     */
    public void cambiarNivel(String lvl) {
        switch (lvl) {
            case "NIVEL 1":
                tiempo = 1000;
                nivelActual = 1;
                break;
            case "NIVEL 2":
                tiempo = 900;
                nivelActual = 2;
                bandejaSup.setPosX(210);
                bandejaInf.setPosX(290);
                break;
            case "NIVEL 3":
                tiempo = 850;
                nivelActual = 3;
                bandejaSup.setPosX(190);
                bandejaInf.setPosX(310);
                break;
            case "NIVEL 4":
                tiempo = 800;
                nivelActual = 4;
                bandejaSup.setPosX(170);
                bandejaInf.setPosX(330);
                break;
            case "NIVEL 5":
                tiempo = 750;
                nivelActual = 5;
                bandejaSup.setPosX(150);
                bandejaInf.setPosX(360);
                break;
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
     * Crea el array de letras,numeros y timers.
     */
    public void crearLetras() {
        letras = new ArrayList();
    }

    public void crearNumeros() {
        numeros = new ArrayList();
    }

    public void crearTimers() {
        timers = new ArrayList();
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

    /**
     * Metodo igual que el de generar letras aleatorias, pero para numeros, se pasa el numero aleatorio
     * entre 1 y 9 a string. Si salen los 9 numeros en pantalla, deja de generarlos (array lleno).
     */
    public void numeroAleatorio() {
        String textoNumero = generarNuevoNumero();
        //String textoNumero = "" + num;

        if (!primerNumero) {

            numeros.add(new Letra(this, textoNumero));
            numeros.get(numeros.size() - 1).setText(textoNumero);
            numeros.get(numeros.size() - 1).setVisible(true);
            control.dibujarLetra(numeros.get(numeros.size() - 1));
            primerNumero = true;
        } else {

            for (int i = 0; i < numeros.size(); i++) {
                if (numeros.get(i).getText().equals(textoNumero)) {

                    if (numeros.size() < 9) {
                        textoNumero = generarNuevoNumero();
                        i = 0;
                    }
                }
            }

            numeros.add(new Letra(this, textoNumero));
            numeros.get(numeros.size() - 1).setText(textoNumero);
            numeros.get(numeros.size() - 1).setVisible(true);
            control.dibujarLetra(numeros.get(numeros.size() - 1));
        }

    }

    /**
     * Generar un numero del 1 al 24 para darselo al array de letras
     *
     * @return
     */
    public int generarNuevaLetra() {
        int indice = (int) Math.floor(Math.random() * 24);
        return indice;
    }

    /**
     * Dar un numero del 1 al 9 para generar numeros aleatorios
     *
     * @return
     */
    public String generarNuevoNumero() {
        int num = (int) Math.floor(Math.random() * 9);
        String numero = "" + num;
        return numero;
    }

    /**
     * Recibe una letra y la busca en el array de letras actuales en pantalla.
     * Si la encuentra: La elimina, aumenta la puntuacion, aumenta el contador
     * de letras acertadas seguidas y comprueba si ha llegado a 10 para subir el
     * nivel. 
     * Si no la encuentra realiza el mismo proceso en busca de un numero.
     * Actualiza la vista por medio del controlador. La bandera marca si
     * se ha encontrado o no para así restar puntos, comprobar si se ha llegado
     * a 0 en la puntuacion y finalizar la partida. 
     *
     * @param letra -> Letra a buscar.
     */
    public void buscarLetra(String letra) {
        boolean encontrada = false;
        boolean esNum = false;

        letra = letra.toUpperCase();
        for (int i = 0; i < letras.size(); i++) {
            Letra auxiliar = letras.get(i);

            if (auxiliar.getText().equals(letra)) {
                int n = auxiliar.getPulsaciones();
                n--;
                auxiliar.setPulsaciones(n);
                auxiliar.generarColorPulsacion();
                if (n <= 0) {
                    letras.remove(i);
                    auxiliar.setVisible(false);
                    puntuacion++;
                    aciertosSeguidos++;
                    comprobarPuntuacion();
                    control.repintarContador(puntuacion);
                }
                encontrada = true;
            }
        }

        if (!encontrada) {
            for (int i = 0; i < numeros.size(); i++) {
                Letra auxiliar = numeros.get(i);

                if (auxiliar.getText().equals(letra)) {
                    int n = auxiliar.getPulsaciones();
                    n--;
                    auxiliar.setPulsaciones(n);
                    auxiliar.generarColorPulsacion();
                    if (n <= 0) {
                        numeros.remove(i);
                        auxiliar.setVisible(false);
                        puntuacion++;
                        aciertosSeguidos++;
                        comprobarPuntuacion();
                        control.repintarContador(puntuacion);
                    }
                    esNum = true;
                }
            }
            if (!esNum) {
                puntuacion--;
                aciertosSeguidos = 0;
                comprobarFin();
                control.repintarContador(puntuacion);
            }
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
    public void lanzaLetras() {
        temporizador = new Timer(tiempoCreacion, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tiempo == 750 || tiempo == 800) {
                    if (contadorTiempo <= 0 || contadorTiempo >= tiempo) {
                        numeroAleatorio();
                    }
                }

                if (contadorTiempo <= 0 || contadorTiempo >= tiempo) {
                    letraAleatoria();
                }

                contadorTiempo += 50;

                if (contadorTiempo >= tiempo) {
                    contadorTiempo = 0;
                }

                comprobarLetras();
                comprobarNumeros();

            }
        });
        temporizador.start();
    }

    /**
     * Comprueba si hay choque y desplaza las letras hacia arriba o hacia abajo
     */
    public void comprobarLetras() {
        for (int i = 0; i < letras.size(); i++) {
            Letra auxiliar = letras.get(i);
            comprobarChoqueLetras();
            auxiliar.mover();
        }
    }

    /**
     * Comprueba si hay choque y desplaza los numeros hacia arriba o hacia abajo
     */
    public void comprobarNumeros() {
        for (int i = 0; i < numeros.size(); i++) {
            Letra auxiliar = numeros.get(i);
            comprobarChoqueNumeros();
            auxiliar.mover();
        }
    }

    public void iniciarNumeros() {
        numeroAleatorio();
    }

    /**
     * Para el timer y finaliza la partida
     */
    public void fin() {
        temporizador.stop();
        control.fin();

    }

    /**
     * Comprueba los choques. Si choca con la bandeja superior cambia la
     * direccion a bajando y si choca con la bandeja inferior cambia la
     * direccion a subiendo.
     */
    public void comprobarChoqueLetras() {
        for (int i = 0; i < letras.size(); i++) {

            if (letras.get(i).getY() >= 410
                    && letras.get(i).getX() > bandejaInf.getX() - 40
                    && letras.get(i).getX() < bandejaInf.getX() + 95
                    && letras.get(i).getDireccion() == 0) {
                letras.get(i).cambiarDireccion();

            } else if (letras.get(i).getY() <= 40
                    && letras.get(i).getX() > bandejaSup.getX() - 40
                    && letras.get(i).getX() < bandejaSup.getX() + 95
                    && letras.get(i).getDireccion() == 1) {
                letras.get(i).cambiarDireccion();
            }
        }

    }

    /**
     * Misma funcionalidad de las letras pero con el array de numeros
     */
    public void comprobarChoqueNumeros() {
        for (int i = 0; i < numeros.size(); i++) {

            if (numeros.get(i).getY() >= 410
                    && numeros.get(i).getX() > bandejaInf.getX() - 40
                    && numeros.get(i).getX() < bandejaInf.getX() + 95
                    && numeros.get(i).getDireccion() == 0) {
                numeros.get(i).cambiarDireccion();

            } else if (numeros.get(i).getY() <= 40
                    && numeros.get(i).getX() > bandejaSup.getX() - 40
                    && numeros.get(i).getX() < bandejaSup.getX() + 95
                    && numeros.get(i).getDireccion() == 1) {
                numeros.get(i).cambiarDireccion();
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

    public Timer getTemporizador() {
        return temporizador;
    }

    public void pararTimer() {
        temporizador.stop();
    }

    public void iniciarTimer() {
        temporizador.start();
    }

}
