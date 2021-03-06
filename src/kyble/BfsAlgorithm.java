package kyble;

/**
 * Implementace reseni problemu prelevani vody pomoci BFS pruchodem stromu
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class BfsAlgorithm extends BaseAlgorithm implements IAlgorithm {

    /**
     * Implementace vlastniho algoritmu BFS
     * - pouziva metodu predka ziskejNoveStavy, ktera vraci expanzi ze zadaneho stavu
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
        fronta.add(startovni.clone());
        nalevna.addOpenedStav(startovni.clone());

        /* dokud neni fronta prazdna, tak prochazej uzly */
        /* vyjmi prvni uzel ve fronte a expanduj */
        while( !fronta.isEmpty() ) {

            aktualni = fronta.pop();
            // System.out.println("Vyjimam z fronty " + aktualni.getAktualniObsahyString());
            // System.out.print("Jeho rodice jsou: "); nalevna.vypisFrontuStavu(startovni.getParents());

            // zjistime nasledniky pro zarazeni do fronty a vlozime
            fronta.addAll(super.ziskejNoveStavy(aktualni));
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
