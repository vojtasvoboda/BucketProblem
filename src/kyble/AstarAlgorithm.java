package kyble;

import java.util.PriorityQueue;

/**
 * Implementace reseni problemu prelevani vody pomoci A* pruchodem stromu
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class AstarAlgorithm extends BaseAlgorithm implements IAlgorithm {

    /* fronta cekajicich uzlu */
    PriorityQueue<StavyKybliku> 
            prioritniFronta = new PriorityQueue<StavyKybliku>
            (10, new StavyKyblikuComparator());

    /**
     * Implementace vlastniho algoritmu Astar
     */
    public int computeBuckets() {
        /* init */
        int cesta = 0;
        StavyKybliku aktualni = null;
        StavyKybliku startovni = nalevna.getAktualniStav();

        System.out.println("Startuji Astar algoritmus:");
        System.out.println("- startovni stav: " + startovni.getAktualniObsahyString());
        System.out.println("- cilovy stav: " + startovni.getCiloveObsahyString());

        /* vlozime do fronty pocatecni uzel a odstartujeme prubeh */
        prioritniFronta.add(startovni.clone());
        nalevna.addOpenedStav(startovni.clone());

        /* dokud neni fronta prazdna, tak prochazej uzly */
        /* vyjmi prvni uzel ve fronte a expanduj */
        while( !prioritniFronta.isEmpty() ) {

            // aktualni = fronta.pop();
            aktualni = prioritniFronta.poll();
            // System.out.println("Vyjimam z fronty " + aktualni.getAktualniObsahyString());
            // System.out.print("Jeho rodice jsou: "); nalevna.vypisFrontuStavu(startovni.getParents());

            // zjistime nasledniky pro zarazeni do fronty a vlozime
            prioritniFronta.addAll(super.ziskejNoveStavy(aktualni));
            cesta++;

            // pokud je to hledany stav, tak return
            if ( aktualni.isCilovy() ) {
                System.out.println("Nasli jsme cilovy stav " + aktualni.getAktualniObsahyString());
                nalevna.setAktualniStav(aktualni);
                return cesta;
            }
        }
        System.out.println("Nenasli jsme cilovy stav.");
        return cesta;
    }

}
