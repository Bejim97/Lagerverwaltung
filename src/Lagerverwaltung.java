/**
 * @author Bekim Hoti
 * @version 1.0
 */

import java.util.Scanner;

class Lager {

    String artikel;
    int anzahl;

    /**
     *
     * @param artikel
     * @param anzahl
     */
    // Schlüsselwort "this" wird auf die Instanzvariablen der Klasse Lager verwiesen,
    // anstatt auf die lokalen Variablen des Konstruktors
    public Lager(String artikel, int anzahl) {
        this.artikel = artikel;
        this.anzahl = anzahl;
    }

    /**
     *
     * @return
     */
    public String getArtikel() {
        return artikel;
    }

    /**
     *
     * @return
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     *
     * @param anzahl
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}

public class Lagerverwaltung {
    static final int ANZAHL_EINTRAEGE = 20;
    static Lager[] lager = new Lager[ANZAHL_EINTRAEGE];
    static int anzahlEintraegeImLager = 0;

    public static void main(String[] args) {
        // erstellen Instanz der Klasse Scanner,  Eingaben von der konsole
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLagerverwaltung");
            System.out.println("1. Artikel hinzufügen");
            System.out.println("2. Artikel entnehmen");
            System.out.println("3. Eintrag suchen");
            System.out.println("4. Lager ausgeben");
            System.out.println("5. Beenden");
            System.out.print("Auswahl: ");
            int auswahl = scanner.nextInt();

            switch (auswahl) {
                case 1:
                    System.out.print("Artikel: ");
                    String artikel = scanner.next();
                    System.out.print("Anzahl: ");
                    int anzahl = scanner.nextInt();
                    artikelHinzufuegen(artikel, anzahl);
                    break;
                case 2:
                    System.out.print("Artikel: ");
                    artikel = scanner.next();
                    System.out.print("Anzahl: ");
                    anzahl = scanner.nextInt();
                    artikelEntnehmen(artikel, anzahl);
                    break;
                case 3:
                    System.out.print("Artikel: ");
                    artikel = scanner.next();
                    eintragSuchen(artikel);
                    break;
                case 4:
                    lagerAusgeben();
                    break;
                case 5:
                    return;
            }
        }
    }

    /**
     *fügt artikel hinzu
     * @param artikel
     * @param anzahl
     *
     */
    private static void artikelHinzufuegen(String artikel, int anzahl) {
        int index = -1;
        for (int i = 0; i < anzahlEintraegeImLager; i++) {
            if (lager[i].getArtikel().equals(artikel)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            if (anzahlEintraegeImLager < ANZAHL_EINTRAEGE) {
                lager[anzahlEintraegeImLager] = new Lager(artikel, anzahl);
                anzahlEintraegeImLager++;
                System.out.println("Artikel " + artikel + " wurde erfolgreich hinzugefügt.");
            } else {
                System.out.println("Lager ist voll. Kann Artikel " + artikel + " nicht hinzufügen.");
            }
        } else {
            // aktuelle Anzahl + "anzahl" mit der Methode "setAnzahl()
            lager[index].setAnzahl(lager[index].getAnzahl() + anzahl);
            System.out.println("Anzahl von Artikel " + artikel + " wurde erfolgreich erhöht.");
        }
    }


    /**
     * entnimmt artikel
     * @param artikel
     * @param anzahl
     */
    private static void artikelEntnehmen(String artikel, int anzahl) {
        int index = -1;
        for (int i = 0; i < anzahlEintraegeImLager; i++) {
            if (lager[i].getArtikel().equals(artikel)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Artikel " + artikel + " nicht gefunden.");
        } else {
            if (lager[index].getAnzahl() < anzahl) {
                System.out.println("Nicht genug Exemplare von Artikel " + artikel + " auf Lager.");
            } else {
                lager[index].setAnzahl(lager[index].getAnzahl() - anzahl);
                System.out.println( anzahl + " Exemplare von Artikel " + artikel + " erfolgreich entnommen.");
            }
        }
    }

    /**
     * sucht eintrag
     * @param artikel
     */
    private static void eintragSuchen(String artikel) {
        int index = -1;
        for (int i = 0; i < anzahlEintraegeImLager; i++) {
            if (lager[i].getArtikel().equals(artikel)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Artikel " + artikel + " nicht gefunden.");
        } else {
            System.out.println("Anzahl " + artikel + ": " + lager[index].getAnzahl());
        }
    }

    private static void lagerAusgeben() {
        int summe = 0;
        for (int i = 0; i < anzahlEintraegeImLager; i++) {
            System.out.println("Artikel: " + lager[i].getArtikel() + " Anzahl: " + lager[i].getAnzahl());
            summe += lager[i].getAnzahl();
        }
        System.out.println("Insgesamt im Lager: " + summe);
    }
}

/* Unterschied zu C
-   Statt `struct`  Klasse `Lager` verwendet, um die Datenstruktur zu definieren.
-   Statt `strcmp` `.equals()`-Methode verwendet, um Strings zu vergleichen.
-   Konstruktor für die Klasse `Lager` definieren, um neue Instanzen der Klasse zu erstellen.
-   keine `#define` Konstanten als `static final` in einer Klasse definiert werden.
-   keine `printf`-Funktion, sondern die `System.out.println()
-   keine `scanf`-Funktion, sondern ein `Scanner`-Objekt wird verwendet, um Eingaben von der Konsole zu lesen.
 */
