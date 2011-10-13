package kyble;

/**
 * Interface pro implementaci design patternu Strategy
 * Implementace strategii: BruteForceAlgorithm, GreedyAlgorithm (obecne *Algorithm.java)
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public interface IAlgorithm {

    /**
     * Algoritmus musi vratit pocet kroku k reseni
     * @return int - pocet kroku
     */
    public int computeBuckets();

    /**
     * Neco jako konstruktor
     */
    public void init(Nalevna nalevna);

}
