import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        osoba1 = new Osoba("Damian", "damian@o2.pl");
        osoba2 = new Osoba("Agnieszka", "agag@o2.pl");
        stolik1 = new Stolik(1, 4);
        stolik2 = new Stolik(2, 3);
        rezerwacja1 = new Rezerwacja("Czwartek", 12, stolik1);
        rezerwacja2 = new Rezerwacja("Czwartek", 13, stolik1);
        rezerwacja3 = new Rezerwacja("Czwartek", 13, stolik2);
        rezerwacjaNowa = new Rezerwacja("Czwartek", 14, stolik1);
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja1,restauracja);
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja2,restauracja);
        rezerwacje.dodajRezerwacje(osoba2, rezerwacja3,restauracja);
        list = new ArrayList();
        list.add(rezerwacja1);
        list.add(rezerwacja2);
    }

  @Test
    public void TestGetImie() {
        assertThat(osoba1.getImie()).isEqualTo("Damian");
    }

   
    @Test
    public void TestRegexImieFalse() {
        Osoba osoba=new Osoba("kamil2","oo@pl");
        assertFalse(rezerwacje.validOsoba(osoba));
    }
     @Test
    public void TestRegexImieTrue() {
        assertTrue(rezerwacje.validOsoba(osoba1));
    }
   @Test
    public void TestRegexEmailFalse() {
        Osoba osoba=new Osoba("Kamil","oo@pl");
        assertFalse(rezerwacje.validOsoba(osoba));
    }
    @Test
    public void TestRegexEmailTrue() {
        assertTrue(rezerwacje.validOsoba(osoba1));
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
    public void toStringTest() {
        assertEquals("Stolik numer 1:4osobowy: Czwartek godzina 12", rezerwacja1.toString());
    }
    @Test
    public void TestWypisz() {
        assertEquals(list.size(), rezerwacje.WypiszDlaKonkretnego(osoba1).size());
    }
  
  
  
  



  

@AfterEach
    public void tearDown() {

    }
}
