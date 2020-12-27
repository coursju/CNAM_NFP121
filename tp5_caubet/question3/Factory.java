package question3;
/**
 * interface du design pattern " factory"
 * @param <T> T a definir
 */
public interface Factory<T> {
    /**
     * methode abstraite "create"
     * @return un obet de type T
     */
    public T create();
}
