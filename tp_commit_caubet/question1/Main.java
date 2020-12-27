package question1;

public class Main
{
    public Main()
    {
            Contributeur a = new Contributeur("a",300);
            Contributeur b = new Contributeur("b",200);
            Contributeur c = new Contributeur("c",100);

            GroupeDeContributeurs g = new GroupeDeContributeurs("g");
            g.ajouter(a);g.ajouter(b);g.ajouter(c);

            System.out.println( g.nombreDeCotisants()+" == 3");
            System.out.println(g.solde()+" ==600");

            try{
                g.debit(120);
            }catch(Exception e){
                System.out.println( e instanceof SoldeDebiteurException);
            }
            //System.out.println(g);
            // pour ce test, les comptes des  Contributeurs b & c ont �t� d�bit�s, et le d�bit a �chou�
            // assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", a.solde()==180);
            // assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", b.solde()==80);
            // assertTrue(" � la question3 seulement l'atomicit� est souhait�e ", c.solde()==100);	    
            // assertTrue(" solde erron� ??? ", g.solde()==(180+80+100));
    }

}
