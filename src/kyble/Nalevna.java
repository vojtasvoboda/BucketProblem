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

    /* seznam kybliku k dispozici */
    private List<Kyblik> kybliky;
    private int pocet;
    private StavyKybliku aktualniStav;

    /* algoritmus reseni */
    private IAlgorithm mujAlgoritmus;

    /* stavy co jsme jiz prosli */
    List<StavyKybliku> opened = new ArrayList<StavyKybliku>();

    /**
     * Konstruktor
     */
    public Nalevna(List<Kyblik> kybliky) {
        this.kybliky = kybliky;
        this.pocet = kybliky.size();
        this.aktualniStav = new StavyKybliku(kybliky);
    }

    /**
     * Spusti vypocet cesty k cilove konfiguraci
     * @return
     */
    public int computeBuckets() {
        if ( this.mujAlgoritmus == null ) {
            System.err.println("Nutno nastavit strategii reseni! Pomoci Nalevna.setStrategy()");
            return 0;
        }
        this.mujAlgoritmus.init(this);
        return this.mujAlgoritmus.computeBuckets();
    }

    /**
     * Zjisti, jestli je aktualni stav cilovy
     * @param stav
     * @return boolean stav je cilovy
     */
    public boolean isStavCilovy(StavyKybliku stav) {
        return this.aktualniStav.equals(stav);
    }

    /**
     * Vrati nam startovni, nebo aktualni stav kybliku
     * @return
     */
    public StavyKybliku getAktualniStav() {
        return this.aktualniStav;
    }

    /**
     * Vrati pocet kybliku
     */
    public int getPocetKybliku() {
        return this.pocet;
    }

    /**
     * Pridame jeden stav kybliku, ktery jsme zrovna prosli a uz ho nechceme v budoucnu prochazet
     * @param stav
     */
    public void addOpenedStav(StavyKybliku stav) {
        this.opened.add(stav);
    }

    /**
     * Vrati nam, jestli je stav novy, nebo jsme ho jiz prochazeli
     * @param stav
     * @return boolean
     */
    public boolean isStavNovy(StavyKybliku testovany) {
        // System.out.println("Aktualni stav Opened:"); this.vypisOpenedFrontu();
        // projdeme opened stavy
        Iterator it = this.opened.iterator();
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
    public void vypisOpenedFrontu() {
        Iterator it = this.opened.iterator();
        StavyKybliku stav;
        while( it.hasNext() ) {
            stav = (StavyKybliku) it.next();
            System.out.println("" + stav.getAktualniObsahyString() + " ");
        }
    }

}
