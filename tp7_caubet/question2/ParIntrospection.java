package question2;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.*;

public class ParIntrospection{

    /** Cette methode permet de relier par introspection, un observable et un observateur<p>
     * Appel par introspection de la methode observable.addObserver(observer) 
     *
     * @param observable une instance de la classe java.util.Observable ou l'une de ses derivees
     * @param observer une implementation de l'interface java.util.Observer
     * @throws NoSuchElementException en cas d'erreur
     */
    public static void lierObservableEtObserver(Object observable, Object observer) throws Exception{
        checkObservableTypes(observable, observer);
        Class observableClass = observable.getClass();
        Method method = observableClass.getMethod("addObserver", Observer.class);
        if (method == null) {
            throw new NoSuchElementException();
        }
        method.invoke(observable, observer);
    }

    /** Cette methode permet de delier par introspection, un observable et un observateur<p>
     * Appel par introspection de la methode observable.deleteObserver(observer) 
     * 
     * @param observable une instance de la classe java.util.Observable ou l'une de ses derivees
     * @param observer une implementation de l'interface java.util.Observer
     * @throws NoSuchElementException en cas d'erreur
     */
    public static void delierObservableEtObserver(Object observable, Object observer) throws Exception{
        checkObservableTypes(observable, observer);
        Class observableClass = observable.getClass();
        Method method = observableClass.getMethod("deleteObserver", Observer.class);
        if (method == null) {
            throw new NoSuchElementException();
        }
        method.invoke(observable, observer);
    }

    private static void checkObservableTypes(Object observable, Object observer) {
        if (observable == null) {
            throw new NoSuchElementException();
        }
        if (observer == null) {
            throw new NoSuchElementException();
        }
        if (!(observable instanceof Observable)) {
            throw new NoSuchElementException();            
        }
        if (!(observer instanceof Observer)) {
            throw new NoSuchElementException();            
        }
    }

    /** Cette methode permet de relier par introspection, une source et un "listener"<p>
     * Appel par introspection de la methode source.addXXXXListener(listener) 
     * 
     * @param source est une instance 
     * @param listener une implementation d'une interface XXXXListener
     * @throws NoSuchElementException en cas d'erreur
     */
    public static void lierSourceEtListener(Object source, Object listener) throws Exception{
        Class sourceClass = source.getClass();
        Class listenerClass = listener.getClass();
        Class listenerInterface = resolveListenerInterface(listenerClass);

        try {
            Method method = sourceClass.getMethod("add" + listenerInterface.getSimpleName(), listenerInterface);
            if (method == null) {
                throw new NoSuchElementException();
            }
            method.invoke(source, listener);
        } catch(NoSuchMethodException ex) {
            throw new NoSuchElementException();
        }
    }

    /** Cette methode permet de delier par introspection, une source et un "listener"<p>
     * Appel par introspection de la methode source.removeXXXXListener(listener) 
     * 
     * @param source est une instance 
     * @param listener une implementation d'une interface XXXXListener
     * @throws NoSuchElementException en cas d'erreur
     */
    public static void delierSourceEtListener(Object source, Object listener) throws Exception{
        Class sourceClass = source.getClass();
        Class listenerClass = listener.getClass();
        Class listenerInterface = resolveListenerInterface(listenerClass);

        try {
            Method method = sourceClass.getMethod("remove" + listenerInterface.getSimpleName(), listenerInterface);
            if (method == null) {
                throw new NoSuchElementException();
            }
            method.invoke(source, listener);
        } catch(NoSuchMethodException ex) {
            throw new NoSuchElementException();
        }
    }

    private static Class resolveListenerInterface(Class clazz) {
        return Arrays.stream(clazz.getInterfaces())
        .filter(c -> c.getSimpleName().endsWith("Listener"))
        .findFirst()
        .get();
    }
}