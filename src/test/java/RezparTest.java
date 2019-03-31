import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class RezparTest extends TestBazowy{
    @Parameterized.Parameter(0)
    public Rezerwacja fInput;
    @Parameterized.Parameter(1)
    public boolean fExpected;

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        Stolik s1 = new Stolik(1, 4);
        Stolik s2 = new Stolik(2, 3);
        return Arrays.asList(new Object[][]{
                {new Rezerwacja(LocalDate.of(2019, 1, 17), "12.30", "14", s1), false},
                {new Rezerwacja(LocalDate.of(2019, 1, 17), "12", "14", s2), true},
                {new Rezerwacja(LocalDate.of(2019, 1, 17), "15", "16", s1), true},
                {new Rezerwacja(LocalDate.of(2019, 1, 17), "15", "16", s2), true},
        });
    }

    @Before
    public void setUp() throws IOException, MessagingException {
        rezerwacje = new Rezerwacje();
        rezerwacje.setPlik(false);
        Stolik s1 = new Stolik(1, 4);
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacje.dodajRezerwacje(new Osoba("Kamil", "a@o2.pl"), new Rezerwacja(LocalDate.of(2019, 1, 17), "12", "14", s1), restauracja);
    }

    @Test
    public void test() throws IOException, MessagingException {
        assertEquals(fExpected, rezerwacje.dodajRezerwacje(new Osoba("Kamil", "a@o2.pl"), fInput, restauracja));
    }

    @AfterEach
    public void tearDown() {
        rezerwacje.setPlik(true);
        rezerwacje = null;
        restauracja = null;
    }
}




