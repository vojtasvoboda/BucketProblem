package kyble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementace mista, kde se manipuluje s kybliky - lze prelit dva kybliky, vratit stav kybliku
 * - Kontext řešení
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class Nalevna {

    /* aktualni stav stavoveho prostoru */
    private StavyKybliku aktualniStav;

    /* algoritmus reseni */
    private IAlgorithm mujAlgoritmus;

    /* stavy co jsme jiz prosli */
    List<StavyKybliku> openedStates = new ArrayList<StavyKybliku>();

    /**
     * Konstruktor
     */
    public Nalevna(List<Kyblik> kybliky) {
        this.aktualniStav = new StavyKybliku(kybliky);
    }

    /**
     * Spusti vypocet cesty k cilove konfiguraci
     * @return
     */
    public int computeBuckets() {
        if ( this.mujAlgoritmus == null ) {
            System.err.println("Nutno nastavit strategii pruchodu! Pomoci Nalevna.setStrategy()!");
            return 0;
        }
        this.mujAlgoritmus.init(this);
        int cesta = this.mujAlgoritmus.computeBuckets();
        System.out.println("Vypis fronty stavu:");
        vypisFrontuStavu(this.aktualniStav.getParents());
        System.out.println("Reseni se nachazelo v hloubce " + this.aktualniStav.getParents().size());
        return cesta;
    }

    /**
     * Vrati nam startovni, nebo aktualni stav kybliku
     * @return
     */
    public StavyKybliku getAktualniStav() {
        return this.aktualniStav;
    }

    /**
     * Pridame jeden stav kybliku, ktery jsme zrovna prosli a uz ho nechceme v budoucnu prochazet
     * @param stav
     */
    public void addOpenedStav(StavyKybliku stav) {
        this.openedStates.add(stav);
    }

    /**
     * Vrati nam, jestli je stav novy, nebo jsme ho jiz prochazeli
     * @param stav
     * @return boolean
     */
    public boolean isStavNovy(StavyKybliku testovany) {
        // System.out.println("Aktualni stav Opened:"); this.vypisOpenedFrontu();
        // projdeme opened stavy
        Iterator it = this.openedStates.iterator();
        StavyKybliku stav;
        /* nejdriv projdeme vsechny otevrene stavy */
        while( it.hasNext() ) {
            stav = (StavyKybliku) it.next();
            // System.out.print("Porovnavam " + testovany.getAktualniObsahyString() + " s " + stav.getAktualniObsahyString() + " ");
            if (stav.equals(testovany)) {
                // System.out.println("- a je stejny");
                return false;
            } else {
                // System.out.println("- a neni stejny");
            }
        }
        return true;
    }

    /**
     * Nastavime algoritmus resici problem
     * Design pattern Strategy
     * @param alg
     */
    public void setStrategy(IAlgorithm alg) {
        this.mujAlgoritmus = alg;
    }

    /**
     * Vypise aktualni stavy kybliku
     */
    public void vypisAktualniStavy() {
        System.out.println("Aktualni stavy kybliku:");
        System.out.println(this.aktualniStav.getAktualniObsahyString());
    }

    /**
     * Vypise jiz otevrene uzly
     */
    public void vypisFrontuStavu(List<StavyKybliku> list) {
        Iterator it = list.iterator();
        StavyKybliku stav;
        while( it.hasNext() ) {
            stav = (StavyKybliku) it.next();
            System.out.print("" + stav.getAktualniObsahyString() + " ");
        }
        System.out.println("");
    }

    /**
     * Nastavi aktualni stav, typicky pri konci hledani
     * @param aktualni
     */
    public void setAktualniStav(StavyKybliku aktualni) {
        this.aktualniStav = aktualni;
    }

    public void vypisPosloupnostStavu() {

    }

}
