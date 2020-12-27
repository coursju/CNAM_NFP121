package question3.fifo;



import java.util.*;
import java.lang.reflect.*;

public class TestFileImpl extends junit.framework.TestCase{
  protected FileI q,q1;
  
  @Override
  protected void setUp(){
    this.q = new FileImpl(4);
    this.q1 = new FileImpl(10);
  }

  public void testEnfiler(){
    q.enfiler(3);
    assertTrue(q.contient(3));
    assertEquals(1, q.taille());
    q.enfiler(5);
    assertEquals(2, q.taille());
    assertTrue(q.contient(5));  
    q.enfiler(2);
    assertEquals(3, q.taille());
    assertTrue(q.contient(2));
    q.enfiler(1);
    assertEquals(4, q.taille());
    assertTrue(q.contient(1));
    assertEquals(q.taille(),q.capacite());
    try{
      q.enfiler(6);
      fail(" Exception attendue !");
    }catch(Exception e){
      assertTrue(e instanceof FilePleineException);
    }
  }
  
	public void testDefiler(){
		q.enfiler(3);
		q.enfiler(5);
	  q.enfiler(2);
	  q.enfiler(1);
	  assertEquals(3, q.defiler());
	  assertEquals(5, q.defiler());
	  q.enfiler(7);
	  assertEquals(2, q.defiler());
	  assertEquals(1, q.defiler());
	  assertEquals(7, q.defiler());
	  try{
	    q.defiler();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testpremier(){
		q.enfiler(3); assertEquals(3, q.premier());
		q.enfiler(5); assertEquals(3, q.premier());
	  q.enfiler(2); assertEquals(3, q.premier());
	  q.enfiler(1); assertEquals(3, q.premier());
	  assertEquals(3, q.defiler());
	  assertEquals(5, q.defiler());
	  assertEquals(2, q.premier());
 	  assertEquals(2, q.defiler());
	  assertEquals(1, q.defiler());
	  try{
	    q.premier();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testCapacite(){
	  FileImpl q = new FileImpl(5);
	  assertEquals(5,q.capacite());
	  try{
	    q = new FileImpl(-5);
	    fail();
		}catch(Exception e){
		  assertTrue( e instanceof FileCapaciteException);
	  }
	  try{
	    q = new FileImpl(0);
	    fail();
	  }catch(Exception e){
		  assertTrue( e instanceof FileCapaciteException);
	  }   
	  
	 }
	 
	public void testVider(){
	  FileImpl q = new FileImpl(5);
	  assertEquals(5,q.capacite());
	  q.enfiler(5);
	  q.enfiler(3);
	  assertEquals(5,q.capacite());
	  assertEquals(2,q.taille());
	  q.vider();
	  assertEquals(5,q.capacite());
	  assertEquals(0,q.taille());
	  try{
	    q.premier();
	    fail("une exception est attendue !!!");
	  }catch(Exception e){
	     assertTrue(e instanceof FileVideException);
	  }
	  try{
	    q.premier();
	    fail("une exception est attendue !!!");
	  }catch(Exception e){
	     assertTrue(e instanceof FileVideException);
	  }
	   
	  
	 }
	
}

