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
    private IAlgorithm mujAlgoritmus;

    /* stavy co jsme jiz prosli */
    List<StavyKybliku> closed = new ArrayList<StavyKybliku>();

    /**
     * Konstruktor
     */
    public Nalevna(List<Kyblik> kybliky) {
        this.kybliky = kybliky;
        this.pocet = kybliky.size();
    }

    /**
     * Spusti vypocet cesty k cilove konfiguraci
     * @return
     */
    public int computeBuckets() {
        if ( this.mujAlgoritmus == null ) {
            System.err.println("Nutno nastavit strategii reseni! Pomoci Nalevna.setStrategy()");
        }
        this.mujAlgoritmus.init(this);
        return this.mujAlgoritmus.computeBuckets();
    }

    /**
     * Provede preliti jednoho kybliku do druheho
     * @param zdrojovy
     * @param cilovy
     */

    /**
     * TODO
     * - prelitKyblik(StavKybliku, ID zdroj, ID cil)
     *
     */
    public void prelitKyblik(Kyblik zdrojovy, Kyblik cilovy) {

        /* init cilovy */
        int cilovyKapacita = cilovy.getKapacita();
        int cilovyAktualne = cilovy.getAktualneVody();

        /* init zdrojovy */
        int kolikMuzeme = zdrojovy.getAktualneVody();

        /* vypocitame novy stav cilovyho */
        int novyStavCilovy = cilovyAktualne + kolikMuzeme;
        if ( novyStavCilovy > cilovyKapacita ) novyStavCilovy = cilovyKapacita;

        /* vypocitame novy stav zdrojoveho */
        int kolikSeUlilo = novyStavCilovy - cilovyAktualne;
        int novyStavZdrojovy = kolikMuzeme - kolikSeUlilo;

        /* nastavime novy obsahy */
        zdrojovy.setAktualneVody(novyStavCilovy);
        cilovy.setAktualneVody(novyStavZdrojovy);

    }

    /**
     * Vylije kyblik s danym id v dannem stavovem prostoru
     * @param stav
     * @param id
     */
    public void vylitKyblik(StavyKybliku stav, int id) {

    }

    /**
     * Naplni kyblik s danym id v danem stavu stavoveho prostoru
     * @param stav
     * @param id
     */
    public void naplnitKyblik(StavyKybliku stav, int id) {

    }

    /**
     * Vrati nam cilovy stav vsech kybliku
     * @return StavKybliku
     */
    public StavyKybliku getCiloveStavy() {
        /* init */
        Kyblik kyblik = null;
        int[] stavy = new int[this.pocet];
        int i = 0;
        /* musime zjistit stav z kybliku */
        Iterator it = kybliky.iterator();
        while ( it.hasNext() ) {
            kyblik = (Kyblik) it.next();
            stavy[i] = kyblik.getCilovyStav();
            i++;
        }
        return new StavyKybliku(stavy);
    }

    /**
     * Vrati nam startovni, nebo aktualni stavy kybliku
     * @return
     */
    public StavyKybliku getAktualniStavy() {
        /* init */
        Kyblik kyblik = null;
        int[] stavy = new int[this.pocet];
        int i = 0;
        /* musime zjistit stav z kybliku */
        Iterator it = kybliky.iterator();
        while ( it.hasNext() ) {
            kyblik = (Kyblik) it.next();
            stavy[i] = kyblik.getAktualneVody();
            i++;
        }
        return new StavyKybliku(stavy);
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
    public void addStav(StavyKybliku stav) {
        this.closed.add(stav);
    }

    /**
     * Vrati nam, jestli je stav novy, nebo jsme ho jiz prochazeli
     * @param stav
     * @return boolean
     */
    public boolean isStavNovy(StavyKybliku stav) {
        return this.closed.contains(stav);
    }

    /**
     * Nastavime algoritmus resici problem
     * Design pattern Strategy
     * @param alg
     */
    public void setStrategy(IAlgorithm alg) {
        this.mujAlgoritmus = alg;
    }

}
