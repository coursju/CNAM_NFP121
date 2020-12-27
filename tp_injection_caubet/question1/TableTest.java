package question1;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import container.*;


public class TableTest extends junit.framework.TestCase{
 
    public void test1() throws Exception{
    ApplicationContext ctx = Factory.createApplicationContext("./Question1/README.TXT");
    Table t = ctx.getBean("table");
    assertEquals(Table.class,ctx.getType("table"));
    t.setInt(4);t.setInt(2);t.setInt(2);t.setInt(1);
    assertEquals("[3, 4, 4, 2, 2, 1]",t.toString());
    }

    
}
