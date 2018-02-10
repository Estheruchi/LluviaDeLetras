/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lluviadeletras;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author javierflopez
 */
public class PartidaCargarGuardar {
    //puedes cambiar el nombre del archivo y la extensión a la que 
    //quieras...
    private static final String FILENAME = "datosusuario.txt";


    public PartidaCargarGuardar(){

        //Modelo modelo = new Modelo();
        //this.nivel = modelo.getnivel();
        //this.puntos = modelo.getpuntos();
        PartidaCargarGuardar t = new PartidaCargarGuardar();
        //almacenamos los datos del usuario en el objeto que hemos 
        //creado para tal efecto:
        DatosUsuario datos = new DatosUsuario();
        datos.setNivel(2); //Nivel actual datos.setNivel(modelo.getnivel());
        datos.setPuntos(1230); //Puntos actuales modelo.getpuntos()

        //serializamos (guardamos) los datos en un fichero de texto (FILENAME)
        try {
            t.guardar(datos);
        } catch (IOException e) {
            //si se produce un error...
            e.printStackTrace();
        }

        //y para leerlos nuevamente es tan sencillo como esto:
        DatosUsuario cargar = null;
        try {
            cargar = t.cargar();
        } catch (ClassNotFoundException | IOException e) {
        }
        if (cargar != null) {
            System.out.println(cargar.toString());
        }
    }


    public void guardar(DatosUsuario data) throws IOException {

        //Creamos el archivo (si existía anteriormente se borrará y creará de nuevo)
        FileOutputStream fs = new FileOutputStream(FILENAME);
        //Esta clase tiene el método writeObject() que necesitamos
        ObjectOutputStream os = new ObjectOutputStream(fs);
        //El método writeObject() serializa el objeto y lo escribe en el archivo
        os.writeObject(data);
        //cerramos el stream
        os.close();
    }

    public DatosUsuario cargar() throws IOException, ClassNotFoundException {
        DatosUsuario data;
        FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (DatosUsuario) ois.readObject();//El método readObject() recupera el objeto
        ois.close();
        return data;
    }
}
