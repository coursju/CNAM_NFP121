package question3;

import java.util.Set;
import java.util.HashSet;
/**
 * classe hashsetfactory
 * @param T a definir
 */
public class HashSetFactory<T> implements Factory<Set<T>>{

    /**
     * methode create
     * @return hashset<T>
     */
    public Set<T> create() {
        return new HashSet<T>();
    }
}