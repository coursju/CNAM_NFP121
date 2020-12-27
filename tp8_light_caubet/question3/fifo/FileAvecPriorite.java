package question3.fifo;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;

public class FileAvecPriorite<E extends Comparable<? super E>> extends FileImpl<E>{
  
  protected Comparator<? super E> comparator;
  
  public FileAvecPriorite(int capacite,  Comparator<? super E> comparator){
    super(capacite);
    this.comparator = comparator;
  }

  public void enfiler(E e){
    super.enfiler(e);
    Collections.sort(super.liste, comparator);
  }

  public boolean repOk(){
    return super.repOk() && isSorted(this);
  }
       
  
  // http://stackoverflow.com/questions/3047051/how-to-determine-if-a-list-is-sorted-in-java

		 private  static <T extends Comparable<? super T>> 
                       boolean isSorted(Iterable<T> iterable) {
    Iterator<T> iter = iterable.iterator();
    if (!iter.hasNext()) {
        return true;
    }
    T t = iter.next();
    while (iter.hasNext()) {
        T t2 = iter.next();
        if (t.compareTo(t2) < 0) {
            return false;
        }
        t = t2;
    }
    return true;
  }
 
}
