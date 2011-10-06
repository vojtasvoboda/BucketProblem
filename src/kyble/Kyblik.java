package kyble;

/**
 * Implementace kybliku
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class Kyblik {

    private int aktualneVody;
    private int kapacita;
    private int cilovyStav;

    /**
     * Vytvoreni kybliku
     * @param aktualneVody kolik je v kybliku aktualne vody
     * @param kapacita kolik se do kybliku vejde maximalne
     * @param cilovyStav kolik chceme aby v kybliku bylo
     */
    public Kyblik(int aktualneVody, int kapacita, int cilovyStav) {
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

    public int getAktualneVody() {
        return aktualneVody;
    }

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

}
