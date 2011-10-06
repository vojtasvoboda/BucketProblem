package kyble;

import java.util.LinkedList;

/**
 * Implementace reseni problemu prelevani vody pomoci BFS pruchodem stromu
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class BfsAlgorithm implements IAlgorithm {

    Nalevna nalevna = null;

    /* fronta cekajicich uzlu */
    LinkedList<StavKybliku> fronta = new LinkedList<StavKybliku>();

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
        /* delka cesty */
        int cesta = 0;

        StavKybliku aktualni = null;
        System.out.println("Startuji Bfs algoritmus.");

        /* vlozime do fronty pocatecni uzel a odstartujeme prubeh */
        fronta.add(new StavKybliku());

        /* dokud neni fronta prazdna, tak prochazej uzly */
        /* 1) vyjmu prvni uzel ve fronte a expanduji */
        /* 2) vyjmuty prvni ulozim do cesty */
        while( !fronta.isEmpty() ) {
            aktualni = fronta.pop();
            // System.out.println("Vyjimam {" + aktualni.getCislo() + "," + aktualni.getHodnota() + "}");
            // fronta.addAll(aktualni.getChilds());
        }

        return cesta;
    }

}
