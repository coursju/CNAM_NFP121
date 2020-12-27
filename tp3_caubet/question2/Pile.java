package question2;

import question1.PilePleineException;
import question1.PileVideException;
import java.util.*; 

public class Pile implements PileI {

    private Object[] zone;
    private int ptr;
    private int size;

    public Pile(int taille) {
        if (taille <= 0){
            taille = CAPACITE_PAR_DEFAUT;
        }
        size = taille;
        this.zone = new Object[taille];
        this.ptr = 0;
    }

    public Pile() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();
        this.zone[this.ptr] = o;
        this.ptr++;    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        this.ptr--;
        return zone[ptr];
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();        
        return zone[ptr-1];
    }

    public int capacite() {
        return size;
    }

    public int taille() {
        return ptr;
    }

    public boolean estVide() {
        return ptr == 0;
    }

    public boolean estPleine() {
        return ptr == zone.length;
    }

    public boolean equals(Object o) {
        if( o instanceof Pile){
            Pile p = (Pile)o;
            if( this.taille() == p.taille() && this.capacite() == p.capacite() ){
                for( int i = 0; i< this.taille(); i++){
                    if( this.zone[i] != p.zone[i] ){ return false;}
                }
            } else{return false;}
        }else{return false;}
        return true;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (int i = ptr - 1; i >= 0; i--) {
            sb.append(zone[i]);
            if (i > 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}