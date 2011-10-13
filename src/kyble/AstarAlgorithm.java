package kyble;

/**
 * Implementace reseni problemu prelevani vody pomoci A* algoritmem
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class AstarAlgorithm implements IAlgorithm {

    Nalevna nalevna = null;

    /**
     * Neco jako konstruktor
     * @param nalevna
     */
    public void init(Nalevna nalevna) {
        this.nalevna = nalevna;
    }

    /**
     * Implementace vlastniho algoritmu
     */
    public int computeBuckets() {
        return 0;
    }


}
