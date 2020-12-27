package question3;


import java.util.*;
import java.lang.reflect.*;
import question3.fifo.*;

public class TestQueueAF extends junit.framework.TestCase{
  protected QueueImpl q,q1;
  
  @Override
  protected void setUp(){
    this.q  = new QueueImpl(4);
    this.q1 = new QueueImpl(4); 
  }

  public void testAdd(){

    FileI<Integer> queue = q.af();
		q.add(3); queue.enfiler(3);
		assertEquals(q.af(),queue);
 		q.add(2); queue.enfiler(2);
 		q.add(4); queue.enfiler(4);
    assertEquals(q.af(),queue);
    q.add(5); queue.enfiler(5);
    assertEquals(q.af(),queue);
    assertEquals(q.size(),q.capacity());
    try{
      q.add(22);
    }catch(Exception e){
      assertTrue(e instanceof RuntimeException);
      try{
        queue.enfiler(22);
        fail("une exception est attendue ");
      }catch(Exception e1){
        assertTrue(e1 instanceof FilePleineException);
      }
    }
    assertEquals(q.af(),queue);
  }

	
	public void testRemove(){
		q.add(3); 
		assertTrue(q.contains(3)); 
		assertTrue(q.af().contient(3));
    FileI<Integer> queue = q.af();
		int res  = q.remove();
		int res2 = queue.defiler();
		
		assertEquals(res, res2);
		assertFalse(q.contains(3)); 
		assertFalse(q.af().contient(3));
		
		assertEquals(0,q.size());
		assertEquals(q.size(),q.size());
		assertEquals(q.af(),queue);

	}
	
  public void testRemoveBis(){
		q.add(3);
		q.add(2); 
		q.add(4); 
		q.add(3);
		assertTrue(q.contains(3)); 
		assertTrue(q.af().contient(3));
    FileI<Integer> queue = q.af();
    
		int res  = q.remove();
		int res2 = queue.defiler();
		assertEquals(res, res2);
		assertTrue(q.contains(3)); 
		assertTrue(q.af().contient(3));
 		assertEquals(q.af(),queue);
 	  res  = q.remove();
 		res2 = queue.defiler();
 		assertEquals(res, res2);
 		assertEquals(2, q.size());
 	  res  = q.remove();
 		res2 = queue.defiler();
 		assertEquals(res, res2);
 		assertEquals(1, q.size()); 
 		res  = q.remove();
 		res2 = queue.defiler();
 		assertEquals(res, res2);
 		assertEquals(0, q.size()); 
    try{
      q.remove();
    }catch(Exception e){
      assertTrue(e instanceof RuntimeException);
      try{
        queue.defiler();
        fail("une exception est attendue ");
      }catch(Exception e1){
        assertTrue(e1 instanceof FileVideException);
      }
    }
		
	}
	
	public void testSize(){
		q.add(3);
		assertEquals(1, q.size()); 
		assertEquals(1, q.af().taille());
		q.remove();
		assertEquals(0, q.size()); 
		assertEquals(0, q.af().taille());
	}
	
	public void testEquals(){
	  q.add(3);q.add(4);q.add(2);
	  q1.add(3);q1.add(4);q1.add(2);
	  assertTrue(q.equals(q1)); 
	  assertTrue(q.af().equals(q1.af()));
	  assertEquals(q.hashCode(),q1.hashCode());
	  assertEquals(q.af().hashCode(),q1.af().hashCode());	  
	  assertTrue(q1.equals(q)); 
	  assertTrue(q1.af().equals(q1.af()));
	  q1.add(5);
	  assertFalse(q.equals(q1)); 
	  assertFalse(q.af().equals(q1.af())); 
	 }
	  
	public void testIterator(){
		q.add(3);q.add(4);q.add(2);
		List<Integer> liste = new ArrayList<>();
		for(int i : q){
		  liste.add(i);
		}
		List<Integer> liste2 = new ArrayList<>();
		for(int i : q.af()){
		  liste2.add(i);
		}
		assertEquals(3, q.size());   assertEquals(3, q.af().taille());
		assertEquals(3, liste.size()); assertEquals(liste.size(), liste2.size());
		assertTrue(liste.contains(3)); assertTrue(liste2.contains(3));
		assertTrue(liste.contains(2)); assertTrue(liste2.contains(2));
		assertTrue(liste.contains(4)); assertTrue(liste2.contains(3));

	}
	
}

