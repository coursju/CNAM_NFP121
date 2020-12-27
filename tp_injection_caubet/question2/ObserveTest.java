package question2;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import container.*;



public class ObserveTest extends junit.framework.TestCase{
    
    public void test1(){
       ApplicationContext ctx=Factory.createApplicationContext("./Question2/README.TXT");
       Observe obs = ctx.getBean("observe");
       Observateur o = new Observateur();
       
       assertEquals(Observe.class,ctx.getType("observe"));
       assertEquals(2, obs.countObservers());
       assertEquals("[liste, encore, et encore]",obs.toString());
       
       obs.setObserver(o);
       obs.setAdd("truc");
       
       assertEquals(3, obs.countObservers());
       assertEquals("[liste, encore, et encore, truc]",obs.toString());
    }
   
}
