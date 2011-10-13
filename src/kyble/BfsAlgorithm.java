package kyble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementace reseni problemu prelevani vody pomoci BFS pruchodem stromu
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class BfsAlgorithm implements IAlgorithm {

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
     * Implementace vlastniho algoritmu
     */
    public int computeBuckets() {
        /* init */
        int cesta = 0;
        StavyKybliku aktualni = null;
        StavyKybliku startovni = nalevna.getAktualniStav();

        System.out.println("Startuji Bfs algoritmus:");
        System.out.println("- startovni stav: " + startovni.getAktualniObsahyString());
        System.out.println("- cilovy stav: " + startovni.getCiloveObsahyString());

        /* vlozime do fronty pocatecni uzel a odstartujeme prubeh */
        startovni.setYourselfToParents();
        fronta.add(startovni.clone());
        nalevna.addOpenedStav(startovni.clone());

        /* dokud neni fronta prazdna, tak prochazej uzly */
        /* vyjmi prvni uzel ve fronte a expanduj */
        while( !fronta.isEmpty() ) {
            aktualni = fronta.pop();
            System.out.println("Vyjimam ze zasobniku " + aktualni.getAktualniObsahyString());
            System.out.print("Jeho rodice jsou: "); nalevna.vypisFrontuStavu(startovni.getParents());
            // zjistime nasledniky pro zarazeni do fronty a vlozime
            fronta.addAll(ziskejNoveStavy(aktualni));
            cesta++;
            // pokud je to hledany stav, tak return, jinak stav uzavreme
            if ( aktualni.isCilovy() ) {
                System.out.println("Nasli jsme cilovy stav " + aktualni.getAktualniObsahyString());
                nalevna.setAktualniStav(aktualni);
                return cesta;
            }
        }
        System.out.println("Nenasli jsme cilovy stav.");
        return cesta;
    }

    /**
     * Funkce najde vsechny mozne nove stavy a kontroluje duplicity pomoci nalevny
     * @param aktualni
     * @return List<StavKybliku> nove stavy
     */
    private List<StavyKybliku> ziskejNoveStavy(StavyKybliku aktualni) {
        /* init */
        StavyKybliku novy = aktualni.clone();
        StavyKybliku exp;
        List<StavyKybliku> noveStavy = new ArrayList<StavyKybliku>();
        List<StavyKybliku> rodice = aktualni.clone().getParents();
        System.out.println("Spoustime expanzi novych stavu.");

        /* pro kazdy kyblik muzeme udelat tri operace */
        /* vylit ho; naplnit ho; prelit ho na souseda */
        Iterator it = novy.getKybliky().iterator();
        Kyblik kyb;
        int puvodniStav = 0;

        /* takze nejdriv zkusime postupne vsechny vyprazdnit */
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            // System.out.println("Manipuluji s kyblikem pro vyprazdneni " + kyb.toString());
            puvodniStav = kyb.getAktualneVody();
            if ( puvodniStav == 0 ) continue;
            kyb.vylejKyblik();
            System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
            // pokud vznikne novy stav, tak pridame na zasobnik
            if ( nalevna.isStavNovy(novy) ) {
                System.out.print("Novy pred ");
                nalevna.vypisFrontuStavu(novy.getParents());
                exp = novy.clone();
                System.out.print("Exp pred ");
                nalevna.vypisFrontuStavu(exp.getParents());
                exp.setYourselfToParents();
                System.out.print("Exp po ");
                nalevna.vypisFrontuStavu(exp.getParents());
                noveStavy.add(exp);
                nalevna.addOpenedStav(exp);
                System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
            }
            // obnovime kyblik, abychom vzdy delali jenom jednu akci
            kyb.setAktualneVody(puvodniStav);
        }

        /* potom zkusime kybliky postupne naplnit */
        it = novy.getKybliky().iterator();
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            // System.out.println("Manipuluji s kyblikem pro naplneni " + kyb.toString());
            puvodniStav = kyb.getAktualneVody();
            if ( puvodniStav == kyb.getKapacita() ) continue;
            kyb.naplnKyblik();
            System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
            // pokud vznikne novy stav, tak pridame na zasobnik
            if ( nalevna.isStavNovy(novy)) {
                System.out.print("Novy pred ");
                nalevna.vypisFrontuStavu(novy.getParents());
                exp = novy.clone();
                System.out.print("Exp pred ");
                nalevna.vypisFrontuStavu(exp.getParents());
                exp.setYourselfToParents();
                System.out.print("Exp po ");
                nalevna.vypisFrontuStavu(exp.getParents());
                noveStavy.add(exp);
                nalevna.addOpenedStav(exp);
                System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
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
                System.out.println("Vzniknul stav " + novy.getAktualniObsahyString());
                // pokud vznikne novy stav, tak pridame na zasobnik
                if ( nalevna.isStavNovy(novy)) {
                    exp = novy.clone();
                    System.out.print("Exp pred ");
                    nalevna.vypisFrontuStavu(exp.getParents());
                    exp.setYourselfToParents();
                    System.out.print("Exp po ");
                    nalevna.vypisFrontuStavu(exp.getParents());
                    noveStavy.add(exp);
                    nalevna.addOpenedStav(exp);
                    System.out.println("Pridali jsme novy stav " + novy.getAktualniObsahyString());
                }
                // obnovime kyblik, abychom vzdy delali jenom jednu akci
                kyb.setAktualneVody(puvodniStav);
                kyb2.setAktualneVody(puvodniStav2);
            }
        }

        return noveStavy;
    }

}
