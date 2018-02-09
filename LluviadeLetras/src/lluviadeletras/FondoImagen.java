/*
    Clase FondoImagen de tipo panel que establece un fondo para la aplicacion 
 */
package lluviadeletras;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Vic
 */
public class FondoImagen extends JPanel {

    @Override
    public void paint(Graphics g) {
        ImageIcon imgFondo = new ImageIcon(getClass().getResource("/Imagenes/fondo.png"));
        g.drawImage(imgFondo.getImage(), 0, 0, 600, 600, this);
        setOpaque(false);
        super.paint(g); // para que pinte el resto de los componentes
        super.paintComponent(g);
    }

}
