package question3;



import java.util.*;
import java.lang.reflect.*;
import question3.fifo.*;

public class TestPriorityQueueAF extends junit.framework.TestCase{
  protected PriorityQueueImpl q,q1;
  
  @Override
  protected void setUp(){
    this.q = new PriorityQueueImpl(4);
    this.q1 = new PriorityQueueImpl(4); 
  }
 
    
  
  public void testAf(){
    FileI<Integer> queue = q.af();
		q.add(3);
 		q.add(2);
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
 		q.add(4);
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
 		q.add(1);
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
 		assertEquals(q.capacity(), q.af().taille());
 		try{
 		  q.af().enfiler(22);
 		  fail("exception attendue");
 		 }catch(Exception e){
 		   assertTrue(e instanceof FilePleineException);
 		 }
 		 
 		assertTrue("queue not sorted ? ", isSorted(queue));
    assertTrue("queue not sorted ? ", isSorted(q.af()));
 	}
 
 	
  public void testAdd(){
		q.add(3); 
 		q.add(2);
 		q.add(4);
    q.add(5); 
    assertEquals(4, q.size());
    assertEquals(q.size(),q.af().taille());
    assertTrue("q not sorted ? ", isSorted(q));
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
 		assertEquals(4, q.capacity());
    try{
      q.add(22);
    }catch(Exception e){
      assertTrue(e instanceof RuntimeException);
      try{
        q.af().enfiler(22);
        fail("une exception est attendue ");
      }catch(Exception e1){
        assertTrue(e1 instanceof FilePleineException);
      }
    }
  }


	public void testRemove(){
	  q.clear();
		q.add(3); 
		assertTrue(q.contains(3)); 
		assertTrue(q.af().contient(3));
    FileI<Integer> queue = q.af();
		int res  = q.remove();
		int res2 = queue.defiler();
		assertFalse(q.contains(3)); 
		assertFalse(q.af().contient(3));
    assertTrue("q not sorted ? ", isSorted(q));
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
	}
	
		public void testRemoveBis(){
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
 	  assertTrue(isSorted(q));
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
	

	public void testSize(){
		q.add(3);
		assertEquals(1, q.size()); 
		assertEquals(1, q.af().taille());
		q.remove();
		assertEquals(0, q.size()); 
		assertEquals(0, q.af().taille());
    assertTrue("q not sorted ? ", isSorted(q));
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
	}
	

	public void testEquals(){
	  q.add(3);q.add(4);q.add(2);
	  q1.add(3);q1.add(4);q1.add(2);
	  assertTrue(q.equals(q1)); 
	  assertTrue(q.af().toString().equals(q1.af().toString()));
	  assertEquals(q.hashCode(),q1.hashCode());
	  assertEquals(q.af().toString().hashCode(),q1.af().toString().hashCode());	  
	  assertTrue(q1.equals(q)); 
	  assertTrue(q1.af().toString().equals(q1.af().toString()));
	  q1.add(5);
	  assertFalse(q.equals(q1)); 
	  assertFalse(q.af().toString().equals(q1.af().toString())); 
    assertTrue("q not sorted ? ", isSorted(q));
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));
	 }
	  

	public void testIterator(){
		q.add(3);q.add(4);q.add(2);
		List<Integer> liste1 = new ArrayList<>();
		for(int i : q){
		  liste1.add(i);
		}
		List<Integer> liste2 = new ArrayList<>();
		for(int i : q.af()){
		  liste2.add(i);
		}
		assertEquals(3, q.size());   
		assertEquals(3, q.af().taille());
		assertEquals(3, liste1.size());
		assertEquals(liste1.size(), liste2.size());
		assertEquals(liste1, liste2);
		q.add(1);
		assertEquals(q.size(),q.capacity());
		
		Iterator<Integer> it1 = q.iterator();
		Iterator<Integer> it2 = q.af().iterator();
		assertEquals(it1.next(), it2.next());
		assertEquals(it1.next(), it2.next());
		assertEquals(it1.next(), it2.next());
		assertEquals(it1.next(), it2.next());

		try{
		  it1.next();
		  fail("Exception attendue ?");
		}catch(Exception e){
		  assertTrue(e instanceof NoSuchElementException);
		  try{
		    it2.next();
		  }catch(Exception e1){
		    assertTrue(e1 instanceof NoSuchElementException);
		  }
		}
		  
    assertTrue("q not sorted ? ", isSorted(q));
 		assertTrue("q.af not sorted ? ", isSorted(q.af()));

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

