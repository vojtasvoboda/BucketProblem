package kyble;

import java.util.Comparator;

/**
 * Komparator pro porovnavani dvou stavu kybliku
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
class StavyKyblikuComparator implements Comparator<StavyKybliku> {

    private static boolean DEBUG = false;

    /**
     * Returns a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the second.
     * @param o1
     * @param o2
     * @return int flag
     */
    public int compare(StavyKybliku o1, StavyKybliku o2) {
        if ( DEBUG ) System.out.print("Volame compare na stavy " + o1.getAktualniObsahyString() + " a " + o2.getAktualniObsahyString());
        int prvni = o1.getHeuristikaStavu();
        if ( DEBUG ) System.out.print(", prvni ma hodnotu " + prvni + " ");
        int druhy = o2.getHeuristikaStavu();
        if ( DEBUG ) System.out.print("druhy ma hodnotu " + druhy);
        if ( prvni < druhy ) {
            if ( DEBUG ) System.out.println(" - prvni je mensi");
            return 1;
        }
        if ( prvni > druhy ) {
            if ( DEBUG ) System.out.println(" - druhy je mensi");
            return -1;
        }
        if ( DEBUG ) System.out.println(" - jsou stejne");
        return 0;
    }

}
