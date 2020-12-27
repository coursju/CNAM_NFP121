package question4;

import java.io.InputStream;
import java.io.IOException;
import java.io.*;

/**
 * Cette classe "d�core" un fichier (InputSream) par la conversion de tous
 * les caract�res Minuscule en Majuscule
 * @author (votre nom) 
 * @version (un num�ro de version ou une date)
 */
public class UpperCaseInputStream extends FilterInputStream { // � compl�ter

    public UpperCaseInputStream (InputStream in){
        super(in);
    }

    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toUpperCase((char) (c));
    }

    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = 0; i < result; i++) {
            b[i] = (byte) Character.toUpperCase((char) (b[i]));
        }
        return result;
    }

}