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
    public int heuristikaStavu = 0;

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
     * Vrati vzdalenost stavu k reseni a ulozi do tridni promenne
     * Pokud je tato funkce volana znova, mela by rovnou vratit vypocitany stav
     * @return
     */
    public int getHeuristikaStavu() {
        if ( this.heuristikaStavu > 0 ) return this.heuristikaStavu;
        computeHeuristikaStavu();
        return this.heuristikaStavu;
    }

    /**
     * Vypocita vzdalenost stavu do cile
     * - pokud je nejaky kyblik spravne naplneny a nachazi se na spravnem miste, prictu dva body
     * - pokud je nejaky kyblik spravne naplneny ale neni na spravnem miste, prictu jeden bod
     */
    public int computeHeuristikaStavu() {
        // init
        int body = 0;
        Kyblik kyb;
        Iterator it = this.kybliky.iterator();
        int i = 0;
        int aktualneVody = 0;
        // vezmu prvni kyblik
        int[] cilove = this.getCiloveObsahy();
        // projdeme vsechny kybliky ve stavu
        while( it.hasNext() ) {
            kyb = (Kyblik) it.next();
            aktualneVody = kyb.getAktualneVody();
            // pokud ma spravny cilovy stav
            if ( cilove[i] == aktualneVody ) {
                body += 2; continue;
            }
            // pokud ma cilovy stav jako jeden z moznych cilovych
            if ( in_array(cilove, aktualneVody) ) body += 1;
            i++;
        }
        // nastavime novou vzdalenost i ji vratime
        this.heuristikaStavu = body;
        // System.out.println("ComputeHeuristikaStavu na stav " + getAktualniObsahyString() + " je " + body);
        return this.heuristikaStavu;
    }

    /**
     * Vrati vzdalenost stavu do konce
     * @param heuristikaStavu
     */
    public void setHeuristikaStavu(int heuristikaStavu) {
        this.heuristikaStavu = heuristikaStavu;
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
     * Kontrola shodnoti s jinym stavem
     * @param testovany
     * @return boolean - je shodny, nebo ne
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
     * @return boolean - je cilovy, nebo ne
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
     * @return String vypis aktualnich obsahu kybliku
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
     * @return String vypis cilovych obsahu kybliku
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

    /**
     * Vratime cilove obsahy jako pole int
     * @return int[] cilove obsahy
     */
    public int[] getCiloveObsahy() {
        int[] ret = new int[this.kybliky.size()];
        Iterator it = kybliky.iterator();
        Kyblik act;
        int i = 0;
        while( it.hasNext() ) {
            act = (Kyblik) it.next();
            ret[i] = act.getCilovyStav();
            i++;
        }
        return ret;
    }

    /**
     * Vratime cilove obsahy jako pole int
     * @return int[] cilove obsahy
     */
    public int[] getAktualniObsahy() {
        int[] ret = new int[this.kybliky.size()];
        Iterator it = kybliky.iterator();
        Kyblik act;
        int i = 0;
        while( it.hasNext() ) {
            act = (Kyblik) it.next();
            ret[i] = act.getAktualneVody();
            i++;
        }
        return ret;
    }

    /**
     * Naklonuje stav, abychom ho mohli jako novy vlozit na zasobnik
     * @return
     */
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

    /**
     * Vypise stavy jako string
     * @return
     */
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

    /**
     * Overi ze se prvek nachazi v poli, nebo ne
     * @param haystack
     * @param needle
     * @return
     */
    public static boolean in_array(int[] haystack, int needle) {
        for(int i=0 ; i < haystack.length ; i++) {
            if( haystack[i] == needle) return true;
        }
        return false;
    }

}
