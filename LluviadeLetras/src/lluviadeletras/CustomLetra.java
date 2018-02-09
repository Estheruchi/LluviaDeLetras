/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

/**
 *
 * @author Vic
 */
import java.awt.Font;
import java.io.InputStream;

public class CustomLetra {

    private Font font = null;

    /**
     * Recoge el fichero de la fuente y lo establece.
     */
    public CustomLetra() {
        String fontName = "/Fuentes/batmfa__.ttf";
        try {
            //Se carga la fuente
            InputStream is = getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);
        }
    }

    /**
     * Crea la fuente con los parametros recibidos
     *
     * @param estilo
     * @param tamanio
     * @return
     */
    public Font MyFont(int estilo, float tamanio) {
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }

}
