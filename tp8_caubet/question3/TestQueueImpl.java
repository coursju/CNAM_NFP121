package question3;



import java.util.*;
import java.lang.reflect.*;

public class TestQueueImpl extends junit.framework.TestCase{
  protected QueueI q,q1;
  
  @Override
  protected void setUp(){
    
   this.q = new QueueAf(new QueuePrePost(new QueueRepOk(new QueueAf(new QueueImpl(4)))));
   this.q1 = new QueueAf(new QueuePrePost(new QueueRepOk(new QueueAf(new QueueImpl(10)))));
// //    this.q = new QueuePrePost(new QueueRepOk(new QueueImpl(4)));
// //    this.q1 = new QueuePrePost(new QueueRepOk(new QueueImpl(10)));
// 
// //     this.q = new QueueRepOk(new QueueImpl(4));
// //     this.q1 = new QueueRepOk(new QueueImpl(10));
//     this.q = new QueueImpl(4);
//     this.q1 = new QueueImpl(10);
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
    assertEquals(q.size(),q.capacity());
    try{
      q.add(6);
      fail(" Exception attendue !");
    }catch(RuntimeException e){
    }
  }
  
	public void testRemove(){
		q.add(3);
		q.add(5);
	  q.add(2);
	  q.add(1);
	  assertEquals(3, q.remove());
	  assertEquals(5, q.remove());
	  q.add(7);
	  assertEquals(2, q.remove());
	  assertEquals(1, q.remove());
	  assertEquals(7, q.remove());
	  try{
	    q.remove();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testElement(){
		q.add(3); assertEquals(3, q.element());
		q.add(5); assertEquals(3, q.element());
	  q.add(2); assertEquals(3, q.element());
	  q.add(1); assertEquals(3, q.element());
	  assertEquals(3, q.remove());
	  assertEquals(5, q.remove());
	  assertEquals(2, q.element());
 	  assertEquals(2, q.remove());
	  assertEquals(1, q.remove());
	  try{
	    q.element();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testCapacity(){
	  QueueImpl q = new QueueImpl(5);
	  assertEquals(5,q.capacity());
	  try{
	    q = new QueueImpl(-5);
	    fail();
		}catch(RuntimeException e){
	  }
	  try{
	    q = new QueueImpl(0);
	    fail();
		}catch(RuntimeException e){
	  }   
	  
	 }
	 
	public void testClear(){
	  QueueImpl q = new QueueImpl(5);
	  assertEquals(5,q.capacity());
	  q.add(5);
	  q.add(3);
	  assertEquals(5,q.capacity());
	  assertEquals(2,q.size());
	  q.clear();
	  assertEquals(5,q.capacity());
	  assertEquals(0,q.size());
	  
	 }
	
}

