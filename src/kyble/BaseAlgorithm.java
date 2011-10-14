package kyble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Spolecny predek pro algoritmy Bfs, Dfs, Astar
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class BaseAlgorithm {

    Nalevna nalevna = null;

    /* fronta cekajicich uzlu */
    LinkedList<StavyKybliku> fronta = new LinkedList<StavyKybliku>();

    /**
     * Neco jako konstruktor
     * @param nalevna
     */
    public void init(Nalevna nalevna) {
        this.nalevna = nalevna;
    }

    /**
     * Funkce najde vsechny mozne nove stavy a kontroluje duplicity pomoci nalevny
     * @param aktualni
     * @return List<StavKybliku> nove stavy
     */
    protected List<StavyKybliku> ziskejNoveStavy(StavyKybliku aktualni) {
        /* init */
        StavyKybliku novy = aktualni.clone();
        StavyKybliku exp;
        List<StavyKybliku> noveStavy = new ArrayList<StavyKybliku>();
        // System.out.println("Spoustime expanzi novych stavu.");

        /* pro kazdy kyblik muzeme udelat tri operace */
        /* vylit ho; naplnit ho; prelit ho na souseda */
        Iterator it = novy.getKybliky().iterator();
        Kyblik kyb;
        int puvodniStav = 0;

        /* takze nejdriv zkusime postupne vsechny kyble vyprazdnit */
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            puvodniStav = kyb.getAktualneVody();
            if ( puvodniStav == 0 ) continue;
            kyb.vylejKyblik();
            // System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
            // pokud vznikne novy stav, tak pridame na zasobnik
            if ( nalevna.isStavNovy(novy) ) {
                exp = novy.clone(); // vytvorime kopii stavu
                exp.addYourselfToParents(); // pridame ho do cesty k rodicum
                noveStavy.add(exp); // pridame novy stav na zasobnik
                nalevna.addOpenedStav(exp); // pridame novy stav do fronty otevrenych
                // System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
            }
            // obnovime kyblik, abychom vzdy delali jenom jednu akci
            kyb.setAktualneVody(puvodniStav);
        }

        /* potom zkusime kybliky postupne naplnit */
        it = novy.getKybliky().iterator();
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            puvodniStav = kyb.getAktualneVody();
            if ( puvodniStav == kyb.getKapacita() ) continue;
            kyb.naplnKyblik();
            // System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
            // pokud vznikne novy stav, tak pridame na zasobnik
            if ( nalevna.isStavNovy(novy)) {
                exp = novy.clone();
                exp.addYourselfToParents();
                noveStavy.add(exp);
                nalevna.addOpenedStav(exp);
                // System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
            }
            // obnovime kyblik, abychom vzdy delali jenom jednu akci
            kyb.setAktualneVody(puvodniStav);
        }

        /* nakonec zkusime prelevat vodu na jednotlive sousedy */
        it = novy.getKybliky().iterator();
        Iterator it2;
        int puvodniStav2 = 0;
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            // ale prelit kyblik muzeme do jakehokoli souseda
            it2 = novy.getKybliky().iterator();
            Kyblik kyb2;
            while( it2.hasNext() ) {
                kyb2 = (Kyblik) it2.next();
                if ( kyb.equals(kyb2)) continue;
                puvodniStav = kyb.getAktualneVody();
                puvodniStav2 = kyb2.getAktualneVody();
                if ( puvodniStav == 0 ) continue;
                if ( puvodniStav2 == kyb2.getKapacita() ) continue;
                kyb.prelejKyblik(kyb2);
                // System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
                // pokud vznikne novy stav, tak pridame na zasobnik
                if ( nalevna.isStavNovy(novy)) {
                    exp = novy.clone();
                    exp.addYourselfToParents();
                    noveStavy.add(exp);
                    nalevna.addOpenedStav(exp);
                    // System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
                }
                // obnovime kyblik, abychom vzdy delali jenom jednu akci
                kyb.setAktualneVody(puvodniStav);
                kyb2.setAktualneVody(puvodniStav2);
            }
        }

        return noveStavy;
    }

}
