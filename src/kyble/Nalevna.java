package kyble;

import java.util.List;

/**
 * Implementace mista, kde lze prelit dva kybliky
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class Nalevna {

    private List<Kyblik> kybliky;

    /**
     * Konstruktor
     */
    public Nalevna(List<Kyblik> kybliky) {
        this.kybliky = kybliky;
    }

    /**
     * Provede preliti jednoho kybliku do druheho
     * @param zdrojovy
     * @param cilovy
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

}
