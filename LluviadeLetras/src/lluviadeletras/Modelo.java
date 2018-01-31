/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

/**
 *
 * @author Estheruchi
 */
public class Modelo {

    private Controlador control;
    private Bandeja bandeja;

    public Modelo(Controlador control) {
        this.control = control;
        this.bandeja = new Bandeja(this);
        manejarBandeja();
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
}
