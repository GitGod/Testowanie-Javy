import org.junit.jupiter.api.BeforeEach;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestBazowy {
    public Rezerwacje rezerwacje;
    public Rezerwacja rezerwacja1;
    public Rezerwacja rezerwacja2;
    public Rezerwacja rezerwacja3;
    public Rezerwacja rezerwacjaNowa;
    public Osoba osoba1;
    public Osoba osoba2;
    public ArrayList list;
    public Restauracja restauracja;

    @BeforeEach
    public void setup() throws IOException, MessagingException {
        rezerwacje = new Rezerwacje();
        rezerwacje.setPlik(false);
        osoba1 = new Osoba("Damian", "test89013@gmail.com");
        osoba2 = new Osoba("Agnieszka", "agag@o2.pl");
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacja1 = new Rezerwacja(LocalDate.of(2019, 1, 17), "12", "13", restauracja.stoliki.get(0));
        rezerwacja2 = new Rezerwacja(LocalDate.of(2019, 1, 17), "13", "14", restauracja.stoliki.get(0));
        rezerwacja3 = new Rezerwacja(LocalDate.of(2019, 1, 17), "13", "14", restauracja.stoliki.get(1));
        rezerwacjaNowa = new Rezerwacja(LocalDate.of(2019, 1, 17), "14", "15", restauracja.stoliki.get(0));
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja1, restauracja);
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja2, restauracja);
        rezerwacje.dodajRezerwacje(osoba2, rezerwacja3, restauracja);
        list = new ArrayList();
        list.add(rezerwacja1);
        list.add(rezerwacja2);


    }
}
