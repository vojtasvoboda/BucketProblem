package kyble;

/**
 * Implementace reseni problemu prelevani vody pomoci A* algoritmem
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class AstarAlgorithm implements IAlgorithm {

    Nalevna nalevna = null;

    /**
     * Konstruktor vytvori novou instanci nalevny
     */
    public AstarAlgorithm(Nalevna nalevna) {
        this.nalevna = nalevna;
    }

    /**
     * Implementace vlastniho algoritmu
     */
    public int computeBuckets() {
        return 0;
    }

}
