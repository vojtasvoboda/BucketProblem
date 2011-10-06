package kyble;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reseni problemu prelevani vody
 * @author Bc. Vojtěch Svoboda <svobovo3@fit.cvut.cz>
 */
public class Main {

    /* vstupni soubor */
    final static String FILE_NAME = "bu.inst.dat";
    /* pocet radku (pro testovani) */
    final static int LINES_NO = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* nacteme instance ze souboru do pole */
        String[][] instanceProblemu = loadFile(FILE_NAME);

        /* init */
        Nalevna nalevna = null;
        List<Kyblik> kybliky = new ArrayList<Kyblik>();

        /* projdeme vsechny instance */
        for (int i = 0; i < LINES_NO; i++) {
            /* nacteme si kybliky */
            kybliky = loadItemFromFile(instanceProblemu[i]);
            /* vytvorime nalevnu - misto kde se manipuluje s kybliky, vodou atd. */
            nalevna = new Nalevna(kybliky);
            /* spustime algoritmus */
            BfsAlgorithm bfs = new BfsAlgorithm(nalevna);
            bfs.computeBuckets();
        }

    }

    /**
     * Nacteme polozky ze souboru
     * @param i - cislo souboru
     * @return Strin[][] - rozparsovany polozky v baraku
     */
    private static String[][] loadFile(String i) {
        String soubor = System.getProperty("user.dir") + "\\data\\" + i;
        System.out.println("Nacitam soubor: " + soubor + ", existuje: " + new File(soubor).exists());
        FileInputStream fstream = null;
        DataInputStream in = null;
        BufferedReader br = null;
        String[][] instance = new String[20][30];
        try {
            /* Zkopirovano z http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml */
            fstream = new FileInputStream(soubor);
            // Get the object of DataInputStream
            in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            String strLine = "";
            int idecko = 0;
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // System.out.println("Ctu radek " + strLine + ", ktery bude na indexu " + idecko);
                String[] radek = strLine.split("\\s+");
                instance[idecko] = radek;
                idecko++;
            }
            // Close the input stream
            in.close();

        } catch (Exception e) {
            System.err.println("Chyba v nacitani souboru, error: " + e);
            return null;
        }
        return instance;
    }

    /**
     * Z nactene radky souboru naplni barak polozkama
     * @param barak
     * @param parametry
     */
    private static List<Kyblik> loadItemFromFile(String[] parametry) {
        /* pocet zaznamu ke cteni */
        int pocetZaznamu = Integer.parseInt(parametry[1]);
        System.out.println("Pocet zaznamu k nacteni je " + pocetZaznamu + " z radku instance " + parametry[0]);

        /* init */
        int kapacita = 0;
        int obsah = 0;
        int koncovyObsah = 0;
        List<Kyblik> kybliky = new ArrayList<Kyblik>();

        /* ID # v1 v2 v3 v4 v5 s1 s2 s3 s4 s5 t1 t2 t3 t4 t5 */
        /* 11 5 14 10  6  2  8  0  0  1  0  0 12  6  4  1  8 */
        /* v = kapacity, s = pocatecti obsahy, t = koncove obsahy */
        /* projdeme vsechny zaznamy */
        for( int i = 2; i < (pocetZaznamu + 2); i++ ) {
            kapacita = Integer.parseInt(parametry[i]);
            obsah = Integer.parseInt(parametry[i+pocetZaznamu]);
            koncovyObsah = Integer.parseInt(parametry[i+pocetZaznamu+pocetZaznamu]);
            // System.out.println("Pridavam kyblik {" + kapacita + "," + obsah + "," + koncovyObsah + "}");
            // pridame polozky
            kybliky.add(new Kyblik(obsah, kapacita, koncovyObsah));
        }
        return kybliky;
    }

}
