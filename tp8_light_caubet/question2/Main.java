package question2;


/**
 * Décrivez votre classe Main ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Main
{

    public static void Main()
    {
        Beverage b = new BeetSugar(new Whip(new Mocha(new Soy(new HouseBlend()))));
        System.out.println(b);
    }
}
