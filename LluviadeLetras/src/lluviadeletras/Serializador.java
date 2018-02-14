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
public class Serializador {

    private static final String FILENAME = "datosusuario.txt";
    private Datos data;

    /*CONSTRUCTOR*/
    public Serializador() {

    }

    /**
     * Creamos el archivo, si ya existía lo reemplaza. Escribimos el objeto en
     * el fichero.
     *
     * @param d
     * @throws IOException
     */
    public void guardar(Datos d) throws IOException {
        FileOutputStream fs = new FileOutputStream(FILENAME);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(d);
        os.close();
    }

    /**
     * Recuperas el objeto del fichero.
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Datos cargar() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (Datos) ois.readObject();
        ois.close();
        return data;
    }
}
