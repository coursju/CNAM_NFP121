package question1;


/**
 * Décrivez votre classe main ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class main
{
    Ensemble<String> e1 = new Ensemble<String>();
    Ensemble<String> e2 = new Ensemble<String>();

    public main()
    {
        e1.add("r");
        e1.add("99");
        e2.add("r");
        e2.add("45");
        System.out.println(" union de e1 et de e2 : " + e1.union(e2));
        System.out.println(" inter de e1 et de e2 : " + e1.inter(e2));
        System.out.println(" diff de e1 et de e2 : " + e1.diff(e2));
        System.out.println(" diffSym de e1 et de e2 : " + e1.diffSym(e2));
    }

        public static void main(String[] args){
        new main();
    }
    
 }
