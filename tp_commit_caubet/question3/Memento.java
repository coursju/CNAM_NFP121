package question3;

import question1.*;
import question2.*;
import java.util.*;
import org.jdom.*;

public class Memento {
     // Note : Un usage du patron Memento, 
     //        d’un premier visiteur pour la sauvegarde et 
     //        d’un second pour la restitution du composite, 
     //        représentent une solution possible. 
    private Element state;

    public Memento(Cotisant c) {
        this.state = c.accepter(new VisiteurToXML());
    }

    public void setState(Cotisant c) {        
        setState(c, state);
    }

    public void setState(Cotisant c, Element e) {
        if (c instanceof Contributeur) {
            setState((Contributeur)c, e);
        } else {
            setState((GroupeDeContributeurs)c, e);
        }
    }

    public void setState(Contributeur c, Element e) {
        if (c.nom().equals(e.getAttribute("nom").getValue())) {
            c.affecterSolde(Integer.parseInt(e.getAttribute("solde").getValue()));
        }
    }

    public void setState(GroupeDeContributeurs g, Element e) {
        if (g.nom().equals(e.getAttribute("nom").getValue())) {
            List childrenElements = e.getChildren();
            List<Cotisant> childrenCotisants = g.getChildren();
            for (int i = 0; i < childrenCotisants.size(); i++) {
                Element el = (Element)childrenElements.get(i);
                Cotisant c = childrenCotisants.get(i);
                setState(c, el);
            }
        }
    }
}