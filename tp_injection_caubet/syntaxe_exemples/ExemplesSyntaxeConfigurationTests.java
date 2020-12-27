package syntaxe_exemples;

import container.ApplicationContext;
import java.util.*;
import java.io.*;

public class ExemplesSyntaxeConfigurationTests extends junit.framework.TestCase{
    // // id: exemple, creation de : ExemplesSyntaxeConfiguration
    // // id: bean.id.1, exemple, appels des mutateurs:
    // // exemple		setBool_(true)
    // // exemple	concat n'existe pas ???
    // // exemple		setConcat(elt1)
    // // exemple		setExemples_array(exemple exemple exemple)
    // // exemple		setB_(127)
    // // exemple		setI_(10)
    // // exemple		setB_array(10 20 30 40 50)
    // // exemple		setL_(10)
    // // exemple		setD(10.1E4)
    // // exemple		setS_(257)
    // // exemple		setExemples_list(null)
    // // exemple		setBool(false)
    // // exemple		setInteger_array(1 2 3 4 5)
    // // exemple		setF_(10F)
    // // exemple		setByte_array(1 2 3 4 5)
    // // exemple		setString_array(a b c d e f)
    // // exemple	element n'existe pas ???
    // // exemple		setElement(10)
    // // exemple		setC('a')
    // // exemple		setStr(test)
    // // exemple		setC_('a')
    // // exemple		setD_(20.3)
    // // exemple		setB(10)
    // // exemple		setS(10)
    // // exemple		setL(1000)
    // // exemple		setI(20)
    // // exemple		setF(0.0)
    // // exemple	element n'existe pas ???
    // // exemple		setElement(10)
    // // exemple	element n'existe pas ???
    // // exemple		setElement(20)
    // // exemple	element n'existe pas ???
    // // exemple		setElement(30)

    public void testAvecInjection() throws Exception{
        //System.getProperties().setProperty("verbose","true");
        // try (
          // BufferedReader br = new BufferedReader(new FileReader("./syntaxe_exemples/config.props"))) {
          // String line = null;
          // int lineNumber = 1;
          // while ((line = br.readLine()) != null) {
            // System.out.println(lineNumber+"\t"+line);
            // lineNumber++;
          // }
        // }
        ApplicationContext ctx = container.Factory.createApplicationContext("./syntaxe_exemples/config.props");
        ExemplesSyntaxeConfiguration exemple = ctx.getBean("exemple");

        assertEquals((byte)10, exemple.getB());

        assertEquals(101000,exemple.getD(),0);

        //System.out.println(exemple.getInteger_list());

        List<Collection<Integer>> collections = exemple.getCollections();
        // collections est composée de liste1 liste2 et set, cf. config.java ligne 99
        assertNotNull(collections);
        assertEquals(3,collections.size()); 

        List<Integer> liste1 = ctx.getBean("liste1");
        liste1.add(33);liste1.add(44);
        assertEquals(3,collections.size()); // 3 Collections et deuyx éléments au total
        assertEquals(2,exemple.getAllElementsCollections().size());
        assertTrue(exemple.getAllElementsCollections().contains(33));
        assertTrue(exemple.getAllElementsCollections().contains(44));
    }

}
