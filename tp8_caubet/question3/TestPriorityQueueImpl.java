package question3;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TestPriorityQueueImpl extends junit.framework.TestCase{
   protected QueueI q,q1;
   
  protected void setUp(){
    this.q  = new PriorityQueuePrePost(new PriorityQueueRepOk(new PriorityQueueAf(new PriorityQueueImpl(4))));
    this.q1 = new PriorityQueuePrePost(new PriorityQueueRepOk(new PriorityQueueAf(new PriorityQueueImpl(10))));

//      this.q  = new PriorityQueueImpl(4);
//      this.q1 = new PriorityQueueImpl(10);

  }
  
   public void testAdd(){

    q.add(3);
    assertTrue(q.contains(3));
    assertEquals(1, q.size());
    
    q.add(5);
    assertEquals(2, q.size());
     assertTrue(q.contains(5));  
     q.add(2);
     assertEquals(3, q.size());
     assertTrue(q.contains(2));
     q.add(1);
     assertEquals(4, q.size());
     assertTrue(q.contains(1));
     assertTrue(isSorted(q));
     try{
       q.add(6);
//       fail(" Exception attendue !");
     }catch(RuntimeException e){
     }
  }

	public void testRemove(){
	  q.add(3);
	  q.add(5);
	  q.add(2);
	  q.add(1);
	      assertTrue(isSorted(q));
	  assertEquals(5, q.remove());
	  assertFalse(q.contains(5));
 	      assertTrue(isSorted(q));
 	  assertEquals(3, q.remove());
 	      assertTrue(isSorted(q));
 	  q.add(7);
 	  assertEquals(7, q.remove());
	      assertTrue(isSorted(q));
	  assertEquals(2, q.remove());
	  assertEquals(1, q.remove());
	  try{
	    q.remove();
	    fail();
		}catch(RuntimeException e){
		      assertTrue(isSorted(q));
	  }    
	}
	
	public void testElement(){
    q.add(3); assertEquals(3, q.element());
	  q.add(5); assertEquals(5, q.element());
	  q.add(2); assertEquals(5, q.element());
	  q.add(1); assertEquals(5, q.element());
	      assertTrue(isSorted(q));
	  assertEquals(5, q.remove());
	  assertEquals(3, q.remove());
	      assertTrue(isSorted(q));
	  assertEquals(2, q.element());
 	  assertEquals(2, q.remove());
	  assertEquals(1, q.remove());
	  try{
	    q.element();
	    fail();
		}catch(RuntimeException e){
		      assertTrue(isSorted(q));
	  }    
	}
 
  public void testIterator(){
		q.add(3);q.add(4);q.add(2);
		List<Integer> liste = new ArrayList<>();
		for(int i : q){
		  liste.add(i);
		}
		assertEquals(3, q.size());
		assertEquals(3, liste.size());
		    assertTrue(" file non triée ???", isSorted(q));
		
		assertEquals(new Integer(4),liste.get(0));
		assertEquals(new Integer(3),liste.get(1));
		assertEquals(new Integer(2),liste.get(2));
	}
	
	
	
// 	 private static List<Integer> toList(QueueI queue){
// 	   List<Integer> list = new ArrayList<>();
// 	   for(int i : queue) list.add(i);
// 	   return list;
// 	 }
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
