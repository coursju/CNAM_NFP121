package question3;

import java.util.Set;
import java.lang.reflect.Method;


public class IntrospectionTest extends junit.framework.TestCase{
 

	public void test1(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
  		java.util.Set<Method> set = intro.getHeritees("java.lang.Object");
  		
  		assertNotNull(set);
  	        assertEquals(0, question3.Introspection.nb);
  	        intro.nb = 0;
 		set = intro.getHeritees("java.util.AbstractCollection");
  		assertNotNull(set);
  	        assertEquals(10, question3.Introspection.nb);
  	        
  	       
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test2(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("java.util.HashSet");
  		assertNotNull(set);
  	        assertEquals(16, question3.Introspection.nb);
  	        
  	        
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test3(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("javax.swing.JButton");
  		assertNotNull(set);
  	        assertEquals(432, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
		public void test4(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("java.util.WeakHashMap");
  		assertNotNull(set);
  	        assertEquals(11, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test5(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("junit.framework.TestCase");
  		assertNotNull(set);
  	        assertEquals(44, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test6(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("java.util.AbstractSet");
  		assertNotNull(set);
  	        assertEquals(19, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test7(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("java.util.Stack");
  		assertNotNull(set);
  	        assertEquals(57, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test8(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("javax.swing.JApplet");
  		assertNotNull(set);
  	        assertEquals(286, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test9(){
	  try{
 		question3.Introspection intro = new question3.Introspection();
 		intro.nb = 0;
  		java.util.Set<Method> set;
  	        set = intro.getHeritees("java.util.Vector");
  		assertNotNull(set);
  	        assertEquals(7, question3.Introspection.nb);
 
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	
	public void test_java_util_Object(){
	  try{
  		question3.Introspection intro = new question3.Introspection();
  		java.util.Set<Method> set = intro.getHeritees("java.lang.Object");
  		assertNotNull(set);
  	  assertEquals(true, set.isEmpty());
    }catch(ClassNotFoundException e){
      fail("exception innattendue !");
    }
	}
	

}

