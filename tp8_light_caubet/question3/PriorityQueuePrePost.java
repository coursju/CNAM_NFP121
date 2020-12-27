package question3;

 

import java.util.*;

public class PriorityQueuePrePost extends QueuePrePost{

  protected boolean pre_add(int i){
    return true;
  }
  protected boolean post_add(int i){
    return isSortedByPriority();
  }
  @Override
  public void add(int i){
     // Eiffel, cofoja, etc ...
    assert super.pre_add(i) || this.pre_add(i);
    // pre_super ==> pre_sub   // page 176 Liskov 
    assert !super.pre_add(i) || this.pre_add(i) : "pre assertion add invalide !";
    Exception cause = null;
    try{
        super.add(i);
    }catch(Exception e){
        cause = e;
        throw e;
    }finally{
        // (pre_super && post_sub) ==> post_super Liskov
        assert !(super.pre_add(i) && this.post_add(i)) || super.post_add(i,cause) : "post assertion add invalide !";
        // Eiffel
        assert this.post_add(i) && super.post_add(i,cause) : "post assertion add invalide !";
    }
  }
  
  public PriorityQueuePrePost( QueueI q){
    super(q);
    assert q.repOk() : " repOk invalide !!!";
  }
  
  
  protected boolean pre_remove(){
    return true;
  }
  protected boolean post_remove(){
    return isSortedByPriority();
  }
  
  public void remove(int i){
    assert pre_remove() || super.pre_remove(): "pre assertion retirer invalide !";
    assert !pre_remove() || super.pre_remove(): "pre assertion retirer invalide !";
    Exception cause = null;
    int res = -1;
    try{
      res = super.remove();
    }catch(Exception e){
      cause = e;
      throw e;
    }finally{
    // // (pre_super && post_sub) ==> post_super
      assert !(super.pre_remove() & post_remove()) | super.post_remove(res, cause);
      assert post_remove() & super.post_remove(res,cause);
    }

  }
  
  
  

  private boolean isSortedByPriority(){
    List<Integer> liste = new ArrayList<Integer>();
    Iterator<Integer> it = super.iterator();
    while(it.hasNext()){
      liste.add(it.next());
    }
    return isSorted(liste);
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