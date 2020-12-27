package question3;

 
import java.util.Comparator;
import java.util.Iterator;
import java.util.Collections;
import question3.fifo.*;

public class PriorityQueueImpl extends QueueImpl {

 
                                  
  public PriorityQueueImpl(int capacity){
    super(capacity);
  }
 
  public boolean repOk(){
    return super.repOk() /* && isSorted(this)*/;
  }
  
   private final static Comparator<Integer> comparator = 
                   new Comparator<Integer>(){
                                   public int compare(Integer i1, Integer i2){
                                     return -i1.compareTo(i2);
                                   }
                                  };
                                  
  public FileI<Integer> af(){
    FileI<Integer> fifo = Fabrique.creerFileAvecPriorite(capacity(), comparator);
       // à compléter
    return fifo;
  }
  
  @Override

 public boolean equals(Object o){
    return super.equals(o);
  }
  
  
     // http://stackoverflow.com/questions/3047051/how-to-determine-if-a-list-is-sorted-in-java
  private static <T extends Comparable<? super T>> 
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
