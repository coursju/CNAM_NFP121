package question1;

import container.*;

public class Assembleur
{
   public static void assemble(){
       ApplicationContext ctx=Factory.createApplicationContext("./Question1/README.TXT");
       Table tbl = ctx.getBean("table");
       //tbl.setInt(98);
       System.out.println("table: " + tbl);
   }
}