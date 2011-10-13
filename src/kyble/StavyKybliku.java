package kyble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Jedna konfigurace vsech kybliku = jeden stav stavoveho prostoru
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class StavyKybliku implements Cloneable {

    private List<Kyblik> kybliky;
    private List<StavyKybliku> rodice;

    /**
     * Konstruktor
     * @param stavy
     */
    public StavyKybliku(List<Kyblik> kybliky) {
        this.kybliky = kybliky;
        this.rodice = new ArrayList<StavyKybliku>();
    }

    /**
     * Prazdny konstruktor vytvori stav, kde ma kazdy kyblik nulu
     */
    public StavyKybliku() {
        this.kybliky = new ArrayList<Kyblik>();
        this.rodice = new ArrayList<StavyKybliku>();
    }

    /**
     * Vrati seznam rodicu
     * @return List<StavyKybliku> rodice
     */
    public List<StavyKybliku> getParents() {
        return this.rodice;
    }

    /**
     * Prida sama sebe na seznam rodicu
     */
    public void addYourselfToParents() {
        this.rodice.add(this);
    }

    /**
     * Vrati vsechny kybliky
     * @return
     */
    public List<Kyblik> getKybliky() {
        // return obsahy;
        return this.kybliky;
    }

    /**
     * Vrati kyblik se spravnym cislem
     * @param cislo
     * @return
     */
    public Kyblik getKyblik(int cislo) {
        Iterator it = kybliky.iterator();
        Kyblik kyb;
        while( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            if ( kyb.getCislo() == cislo ) {
                return kyb;
            }
        }
        return null;
    }

    /**
     * Kontrola totoznosti
     * @param testovany
     * @return
     */
    public boolean equals(StavyKybliku testovany) {
        Iterator it = testovany.getKybliky().iterator();
        Kyblik kyb;
        Kyblik kyb2;
        int cislo = 0;
        // projdeme vsechny kybliky ve stavu
        while( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            cislo = kyb.getCislo();
            kyb2 = this.getKyblik(cislo);
            if ( !kyb.equals(kyb2) ) return false;
        }
        return true;
    }

    /**
     * Vrati jestli je stav cilovy, nebo ne
     * - projde vsechny kybliky a koukne se, jestli aktualni stav odpovida cilovemu
     * @return
     */
    public boolean isCilovy() {
        Iterator it = this.kybliky.iterator();
        Kyblik kyb;
        int aktualniStav = 0;
        int cilovyStav = 0;
        while ( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            aktualniStav = kyb.getAktualneVody();
            cilovyStav = kyb.getCilovyStav();
            if ( cilovyStav != aktualniStav) return false;
        }
        return true;
    }

    /**
     * Vrati obsahy jako string
     * @return
     */
    public String getAktualniObsahyString() {
        String ret = "{";
        Iterator it = kybliky.iterator();
        Kyblik act;
        while( it.hasNext() ) {
            act = (Kyblik) it.next();
            ret += act.getAktualneVody() + ",";
        }
        ret += "}";
        return ret;
    }

    /**
     * Vrati obsahy jako string
     * @return
     */
    public String getCiloveObsahyString() {
        String ret = "{";
        Iterator it = kybliky.iterator();
        Kyblik act;
        while( it.hasNext() ) {
            act = (Kyblik) it.next();
            ret += act.getCilovyStav() + ",";
        }
        ret += "}";
        return ret;
    }

    @Override
    public StavyKybliku clone() {
        StavyKybliku novy = new StavyKybliku();
        Iterator it = this.kybliky.iterator();
        Kyblik kyb;
        while( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            novy.kybliky.add(new Kyblik(kyb.getCislo(), kyb.getAktualneVody(), kyb.getKapacita(), kyb.getCilovyStav()));
        }
        it = this.rodice.iterator();
        StavyKybliku stav;
        while( it.hasNext() ) {
            stav = (StavyKybliku) it.next();
            novy.rodice.add(stav);
        }
        return novy;
    }

    @Override
    public String toString() {
        Iterator it = this.kybliky.iterator();
        Kyblik kyb;
        String vypis = "";
        while( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            vypis += kyb.toString() + " ";
        }
        return vypis;
    }


}
