package question2;

import container.*;

public class Assembleur
{
   public static void assemble(){
       ApplicationContext ctx=Factory.createApplicationContext("./Question2/README.TXT");
       Observe obs = ctx.getBean("observe");
       System.out.println("observe: " + obs);
   }
}