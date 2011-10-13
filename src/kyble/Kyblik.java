package kyble;

/**
 * Implementace kybliku
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class Kyblik {

    private int cislo;
    private int aktualneVody;
    private int kapacita;
    private int cilovyStav;

    /**
     * Vytvoreni kybliku
     * @param aktualneVody kolik je v kybliku aktualne vody
     * @param kapacita kolik se do kybliku vejde maximalne
     * @param cilovyStav kolik chceme aby v kybliku bylo
     */
    public Kyblik(int cislo, int aktualneVody, int kapacita, int cilovyStav) {
        this.cislo = cislo;
        this.aktualneVody = aktualneVody;
        this.kapacita = kapacita;
        this.cilovyStav = cilovyStav;
    }

    /**
     * Vrati jestli je kyblik spravne naplnen, nebo ne
     * @return boolean - kyblik je spravne naplnen
     */
    public boolean spravneNaplnen() {
        return (this.aktualneVody == this.cilovyStav);
    }

    /**
     * Naplnime kyblik
     */
    public void naplnKyblik() {
        this.aktualneVody = this.kapacita;
    }

    /**
     * Vylejeme kyblik
     */
    public void vylejKyblik() {
       this.aktualneVody = 0;
    }

    /**
     * Provede preliti jednoho kybliku do druheho
     * @param cilovy
     */
    public void prelejKyblik(Kyblik cilovy) {

        /* init cilovy */
        int cilovyKapacita = cilovy.getKapacita();
        int cilovyAktualne = cilovy.getAktualneVody();

        /* init zdrojovy */
        int kolikMuzemeZdrojovy = this.getAktualneVody();

        /* vypocitame novy stav cilovyho */
        int novyStavCilovy = cilovyAktualne + kolikMuzemeZdrojovy;
        if ( novyStavCilovy > cilovyKapacita ) novyStavCilovy = cilovyKapacita;

        /* vypocitame novy stav zdrojoveho */
        int kolikSeUlilo = novyStavCilovy - cilovyAktualne;
        int novyStavZdrojovy = kolikMuzemeZdrojovy - kolikSeUlilo;

        /* nastavime novy obsahy */
        this.setAktualneVody(novyStavZdrojovy);
        cilovy.setAktualneVody(novyStavCilovy);

    }

    /**
     * Vypis obsahu kybliku
     * @return
     */
    @Override
    public String toString() {
        return "{" + getCislo() + "," + getKapacita() + "," + getAktualneVody() + "," + getCilovyStav() + "}";
    }

    /**
     * Porovnani dvou kybliku
     * @param kyb
     * @return
     */
    public boolean equals(Kyblik kyb) {
        if ( kyb.getCislo() != this.getCislo() ) return false;
        if ( kyb.getAktualneVody() != this.getAktualneVody() ) return false;
        if ( kyb.getKapacita() != this.getKapacita() ) return false;
        if ( kyb.getCilovyStav() != this.getCilovyStav() ) return false;
        return true;
    }

    /**
     * Vrati aktualni stav vody
     * @return
     */
    public int getAktualneVody() {
        return aktualneVody;
    }

    /**
     * Nastavi aktualni stav vody
     * @param aktualneVody
     */
    public void setAktualneVody(int aktualneVody) {
        this.aktualneVody = aktualneVody;
    }

    public int getCilovyStav() {
        return cilovyStav;
    }

    public void setCilovyStav(int cilovyStav) {
        this.cilovyStav = cilovyStav;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public int getCislo() {
        return cislo;
    }

    public void setCislo(int cislo) {
        this.cislo = cislo;
    }

}
