package service_locator;

import container.ApplicationContext;
import container.Factory;
import java.util.*;

public class ServiceLocatorTests extends junit.framework.TestCase{

    public void testServiceLocator() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./service_locator/README.TXT");

        ServiceLocatorI serviceLocator = ctx.getBean("serviceLocator");
        List<String> beans = new ArrayList<>();
        System.out.println("Liste des beans issue du service locator");
        for(String service : ctx){
            System.out.println("\t" + service);
        }

        System.out.println("\nListe des beans accessibles depuis le service locator");
        for(String service : serviceLocator){
            System.out.println("\t" + service);
            beans.add(service);
        }

        syntaxe_exemples.Table table = serviceLocator.lookup("uneTable");
        table.setInt(33);table.setInt(33);
        assertEquals(3,table.taille()); // la table est un ensemble...

        table = serviceLocator.lookup("EXEMPLES","uneTable");
        table.setInt(44);table.setInt(55);
        assertEquals(5,table.taille());

        List<String> beans2 = new ArrayList<>();
        Iterator<ApplicationContext> itContainers = serviceLocator.iteratorContainers();
        while(itContainers.hasNext()){
            ApplicationContext container = itContainers.next();
            Iterator<String> itServices = serviceLocator.iteratorServices(container);
            while(itServices.hasNext()){
                String service = itServices.next();
                System.out.println("\t" + service);
                beans2.add(service);
            }
        }
        assertEquals(beans.size(), beans2.size());
        Collections.sort(beans);
        Collections.sort(beans2);
        assertTrue(beans.equals(beans2));
    }

    public void testServiceLocator2() throws Exception{
        ApplicationContext ctx = Factory.createApplicationContext("./service_locator/README.TXT");

        ServiceLocatorI serviceLocator = ctx.getBean("serviceLocator2");
        List<String> beans2 = new ArrayList<>();
        Iterator<ApplicationContext> itContainers = serviceLocator.iteratorContainers();
        while(itContainers.hasNext()){
            ApplicationContext container = itContainers.next();
            Iterator<String> itServices = serviceLocator.iteratorServices(container);
            while(itServices.hasNext()){
                String service = itServices.next();
                //System.out.println("\t" + service);
                beans2.add(service);
            }
        }

        System.out.println("beans2: " + beans2);
        // sur ce test deux beans avec le même nom
        strategy.Context context1 = serviceLocator.lookup("STRATEGY_1","context1");
        assertEquals("10 + 5 != 15 ??? ",15,context1.executeStrategy(10, 5));

        strategy.Context context2 = serviceLocator.lookup("STRATEGY_2","context1");
        assertEquals("10 + 5 != 15 ??? ",15,context2.executeStrategy(10, 5));

        Set<Integer> set = serviceLocator.lookup("EXEMPLES_1","set");
        set.add(33);set.add(33);
        assertEquals(1,set.size()); // la liste est un ensemble...
        // System.out.println("set: " + set);

        Set<Integer> set2 = serviceLocator.lookup("EXEMPLES_2","set");
        set2.add(44);set2.add(55);
        assertEquals(2,set2.size());
        // System.out.println("set2: " + set2);

        Set<Integer> set3 = serviceLocator.lookup("EXEMPLES_1","set");
        set3.add(44);
        // System.out.println("set3: " + set3);
        assertEquals(2,set3.size());
    }

}
