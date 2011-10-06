package kyble;

import java.util.ArrayList;
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
     * Konstruktor vytvori novou instanci nalevny
     */
    public BfsAlgorithm(Nalevna nalevna) {
        this.nalevna = nalevna;
    }

    /**
     * Implementace vlastniho algoritmu
     */
    public int computeBuckets() {
        /* init */
        int cesta = 0;
        StavyKybliku aktualni = null;
        List<StavyKybliku> noveStavy = null;
        StavyKybliku startovni = nalevna.getAktualniStavy();
        StavyKybliku cilovy = nalevna.getCiloveStavy();
        System.out.println("Startuji Bfs algoritmus, cilovy hledame " + cilovy.getObsahyString());

        /* vlozime do fronty pocatecni uzel a odstartujeme prubeh */
        fronta.add(startovni);

        /* dokud neni fronta prazdna, tak prochazej uzly */
        /* vyjmi prvni uzel ve fronte a expanduj */
        while( !fronta.isEmpty() ) {
            aktualni = fronta.pop();
            System.out.println("Vyjimam " + aktualni.getObsahyString());
            // zjistime nasledniky pro zarazeni do fronty a vlozime
            noveStavy = ziskejNoveStavy(aktualni);
            fronta.addAll(noveStavy);
            cesta++;
            // pokud je to hledany stav, tak return
            if ( aktualni.equals(cilovy) ) {
                System.out.println("Nasli jsme cilovy stav " + aktualni.getObsahyString());
                return cesta;
            }
        }

        return cesta;
    }

    /**
     * Funkce najde vsechny mozne nove stavy a mela by kontrolovat duplicity
     * @param aktualni
     * @return List<StavKybliku> nove stavy
     */
    private List<StavyKybliku> ziskejNoveStavy(StavyKybliku aktualni) {
        /* init */
        int pocetKybliku = this.nalevna.getPocetKybliku();
        StavyKybliku novy = null;
        List<StavyKybliku> noveStavy = new ArrayList<StavyKybliku>();

        /* pro kazdy kyblik muzeme udelat tri operace */
        /* vylit ho; naplnit ho; prelit ho na souseda */
        
        /* takze nejdriv zkusime postupne vsechny vyprazdnit */
        for (int i = 0; i < pocetKybliku; i++) {
            //novy = nalevna.emptyKyblik(aktualni, i);
            if ( nalevna.isStavNovy(novy)) {
                noveStavy.add(novy);
            }
        }

        /* potom zkusime kybliky postupne naplnit */

        /* nakonec zkusime prelevat vodu na jednotlive sousedy */

        return noveStavy;
    }

}
