package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }
    
    public boolean add(T t) {
        if(!(this.contains(t))){
          return this.table.add(t);       
          }
        return false;
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        Ensemble<T> t = new Ensemble();
        t.addAll(this);
        t.addAll(e);
        return t;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        Ensemble<T> t = new Ensemble<T>();
        t.addAll(this);
        t.retainAll(e);
        return t;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        Ensemble<T> t = new Ensemble<T>();  
        t.addAll(this);
        t.removeAll(e);
        return t;
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) {
        return union(e).diff(inter(e));
    }
    
}
