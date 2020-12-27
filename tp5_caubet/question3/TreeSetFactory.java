package question3;

import java.util.Set;
import java.util.TreeSet;
/**
 * classe treesetfactory
 * @param T a definir
 */
public class TreeSetFactory<T extends Comparable<T>> implements Factory<Set<T>>{

    /**
     * methode create
     * @return treeset<T>
     */
    public Set<T> create() {
        return new TreeSet<T>();
    }
}