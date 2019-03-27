import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;




public class RezerwacjeTest {
  
  

    private Rezerwacje rezerwacje;
    private boolean expected;
    private Rezerwacja rezerwacja1;
    private Rezerwacja rezerwacja2;
    private Rezerwacja rezerwacja3;
    private Rezerwacja rezerwacjaNowa;
    private Osoba osoba1;
    private Osoba osoba2;
    private Stolik stolik1;
    private Stolik stolik2;
    private ArrayList list;
    private Restauracja restauracja;

    @BeforeEach
    public void setup() throws IOException {
        rezerwacje = new Rezerwacje();
        rezerwacje.setPlik(false);
        osoba1 = new Osoba("Damian", "damian@o2.pl");
        osoba2 = new Osoba("Agnieszka", "agag@o2.pl");
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacja1 = new Rezerwacja(LocalDate.of(2019, 1, 17), 12, 13, restauracja.stoliki.get(0));
        rezerwacja2 = new Rezerwacja(LocalDate.of(2019, 1, 17), 13, 14, restauracja.stoliki.get(0));
        rezerwacja3 = new Rezerwacja(LocalDate.of(2019, 1, 17), 13, 14, restauracja.stoliki.get(1));
        rezerwacjaNowa = new Rezerwacja(LocalDate.of(2019, 1, 17), 14, 15, restauracja.stoliki.get(0));
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja1, restauracja);
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja2, restauracja);
        rezerwacje.dodajRezerwacje(osoba2, rezerwacja3, restauracja);
        rezerwacje.generujDane("plik.txt");
        rezerwacje.odczytzPliku("plik.txt");
        list = new ArrayList();
        list.add(rezerwacja1);
        list.add(rezerwacja2);
    }

 @Test
    public void toStringTest() {
        assertEquals("Stolik numer 1 4 osobowy 2019-01-17 godzina 12-13", rezerwacja1.toString());
    }

    @Test
    public void TestGetImie() {
        assertThat(osoba1.getImie()).isEqualTo("Damian");
    }

    @Test
    public void TestGetEmail() {
        assertThat(osoba1.getEmail(), is("damian@o2.pl"));
    }

    @Test
    public void TestGetEmailNot() {
        assertThat(osoba1.getEmail(), not("damia@o2.pl"));
    }


    @Test
    public void TestRegexEmailFalse() {
        Osoba osoba = new Osoba("Kamil", "oo@pl");
        assertFalse(rezerwacje.validOsoba(osoba));
    }

    @Test
    public void TestRegexImieFalse() {
        Osoba osoba = new Osoba("kamil2", "oo@o2.pl");
        assertFalse(rezerwacje.validOsoba(osoba));
    }

    @Test
    public void TestRegexRmailTrue() {
        assertTrue(rezerwacje.validOsoba(osoba1));
    }

    @Test
    public void TestRegexImieTrue() {
        assertTrue(rezerwacje.validOsoba(osoba1));
    }


    @Test
    public void TestWypisz() {
        assertEquals(list.size(), rezerwacje.WypiszDlaKonkretnego(osoba1).size());
    }


    @Test
    public void testCzyWolnyFalse() {
        assertFalse(rezerwacje.czyWolny(rezerwacja1));
    }

    @Test
    public void testCzyWolnyTrue() {
        assertTrue(rezerwacje.czyWolny(rezerwacjaNowa));
    }

    @Test
    public void TestCompareTrue() {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 12, 13, restauracja.stoliki.get(0));
        assertTrue(rezerwacje.compare(rezerwacja1, rezerwacja4));
    }

    @Test
    public void TestCompareFalse() {
        assertFalse(rezerwacje.compare(rezerwacja1, rezerwacjaNowa));
    }


    @Test
    public void czyOtwartaTestTrue() {
        assertTrue(rezerwacje.sprawdzCzyotwarte(rezerwacja1, restauracja));
    }

    @Test
    public void czyOtwartaPoCzasieTestFalse() {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 18, 19, restauracja.stoliki.get(0));
        assertFalse(rezerwacje.sprawdzCzyotwarte(rezerwacja4, restauracja));
    }

    @Test
    public void czyOtwartaPrzedCzasieTestFalse() {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 9, 10, restauracja.stoliki.get(0));
        assertFalse(rezerwacje.sprawdzCzyotwarte(rezerwacja4, restauracja));
    }

    @Test
    public void dodajRezerwacjeFalse() throws IOException {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 12, 13, restauracja.stoliki.get(0));
        assertFalse(rezerwacje.dodajRezerwacje(osoba1, rezerwacja4, restauracja));
    }

    @Test
    void nullAddReservationTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> {
                    rezerwacje.dodajRezerwacje(null, null, null);
                });
    }

    @Test
    void nullAddReservationTest2() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> {
                    rezerwacje.dodajRezerwacje(osoba1, null, null);
                });
    }

    @Test
    void TestAddReservationWithBadTimeExeprion() {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 9, 10, restauracja.stoliki.get(0));
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    rezerwacje.dodajRezerwacje(osoba1, rezerwacja4, restauracja);
                });
    }

    @Test
    void TestExpeptionBadOsoba() {
        Osoba osoba = new Osoba("Kamil", "oo@pl");
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    rezerwacje.dodajRezerwacje(osoba, rezerwacja1, restauracja);
                });
    }

    @Test
    void TestAddReservationWithBadTimeStartEndExeprion() {
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 1, 17), 11, 10, restauracja.stoliki.get(0));
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    rezerwacje.dodajRezerwacje(osoba1, rezerwacja4, restauracja);
                });
    }

    @Test
    void dodajRezerwacjeTrueIsniejacaOsoba() throws IOException {
        rezerwacje.setPlik(true);
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 4, 17), 11, 13, restauracja.stoliki.get(0));
        assertTrue(rezerwacje.dodajRezerwacje(osoba1, rezerwacja4, restauracja));
        RandomAccessFile f = new RandomAccessFile("plik.txt", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while (b != 10);
        f.setLength(length + 1);
        f.close();
        rezerwacje.setPlik(false);
    }

    @Test
    void dodajRezerwacjeTrueNowaOsoba() throws IOException {
        rezerwacje.setPlik(true);
        Osoba osoba421 = new Osoba("Kacper", "Dragon@o2.pl");
        Rezerwacja rezerwacja4 = new Rezerwacja(LocalDate.of(2019, 4, 18), 11, 13, restauracja.stoliki.get(0));
        assertTrue(rezerwacje.dodajRezerwacje(osoba421, rezerwacja4, restauracja));
        RandomAccessFile f = new RandomAccessFile("plik.txt", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while (b != 10);
        f.setLength(length + 1);
        f.close();
        rezerwacje.setPlik(false);
    }
    @Test
    void WypiszPotwierdzenie() throws IOException {
        assertEquals("Brawo udalo ci sie zarezerwowac stolik w naszej restauracji!\n" +
                " Rezerwacja na :\n" +
                "imie: Damian email: damian@o2.pl\n" +
                "Informacje o rezerwacji: 2019-01-17 12-13 Stolik numer: 1 Liczba miejsc: 4", rezerwacje.generujPotwierdzenie2(osoba1,rezerwacja1));
    }




    @AfterEach
    public void tearDown() {

        rezerwacje.setPlik(true);
        rezerwacje = null;
        rezerwacja1 = null;
        rezerwacja2 = null;
        rezerwacja3 = null;
        rezerwacjaNowa = null;
        osoba1 = null;
        osoba2 = null;
        list = null;
        restauracja = null;
    }

}
