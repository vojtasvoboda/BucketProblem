package kyble;

/**
 * Jedna konfigurace vsech kybliku = jeden stav stavoveho prostoru
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class StavyKybliku {

    private int[] obsahy;

    /**
     * Konstruktor
     * @param stavy
     */
    public StavyKybliku(int[] stavy) {
        this.obsahy = stavy;
    }

    /**
     * Prazdny konstruktor vytvori stav, kde ma kazdy kyblik nulu
     */
    public StavyKybliku() {
        this.obsahy = new int[100];
    }

    /**
     * Vrati stav jednoho kybliku
     * @param i
     */
    public int getObsah(int i) {
        return this.obsahy[i];
    }

    /**
     * Vrati cele pole
     * @return
     */
    public int[] getObsahy() {
        return obsahy;
    }

    /**
     * Vrati obsahy jako string
     * @return
     */
    public String getObsahyString() {
        String ret = "{";
        for (int i = 0; i < obsahy.length; i++) {
            ret += obsahy[i] + " ";
        }
        ret += "}";
        return ret;
    }

    public void setObsahy(int[] obsahy) {
        this.obsahy = obsahy;
    }

}
