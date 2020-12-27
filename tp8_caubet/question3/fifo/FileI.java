package question3.fifo;


public interface FileI<E> extends Iterable<E>{

  void enfiler(E e) throws FilePleineException;
  E defiler() throws FileVideException;
  E premier() throws FileVideException;
  
  void vider();
  boolean contient(E e);
  int taille();
  int capacite();
}
