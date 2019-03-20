import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(Parameterized.class)
public class Rezpartesto {
Rezerwacje rezerwacje;
Restauracja restauracja;
    @Before
    public void setUp() throws IOException {
        rezerwacje = new Rezerwacje();
        Stolik s1 = new Stolik(1,4);
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacje.dodajRezerwacje(new Osoba("a","a"),new Rezerwacja("Czwartek",12,s1),restauracja);
    }
        @Parameterized.Parameters
        public  static Iterable<Object[]> data() {
            Stolik s1 = new Stolik(1,4);
            Stolik s2 = new Stolik(2,3);
            return Arrays.asList(new Object[][] {
                    { new Rezerwacja("Czwartek",12,s1), false },
                    { new Rezerwacja("Czwartek",12,s2), true },
                     {  new Rezerwacja("Czwartek", 13, s1),true},
                    {  new Rezerwacja("Czwartek", 13, s2),true},
            });
        }

        @Parameterized.Parameter(0)
        public Rezerwacja fInput;

        @Parameterized.Parameter(1)
        public boolean fExpected;

        @Test
        public void test() {
            assertEquals(fExpected, rezerwacje.dodajRezerwacje(new Osoba("a","a"),fInput,restauracja));
        }
    }


