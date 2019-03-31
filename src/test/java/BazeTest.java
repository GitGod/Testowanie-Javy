import org.junit.jupiter.api.BeforeEach;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class BazeTest {
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
        rezerwacje.setOdczyt(true);
        rezerwacje.odczytzPliku("plik.txt");
        rezerwacje.setOdczyt(false);
        rezerwacje.generujDane("plik.txt");
        osoba1 = new Osoba("Damian", "test89013@gmail.com");
        osoba2 = new Osoba("Agnieszka", "agag@o2.pl");
        restauracja = new Restauracja();
        restauracja.readFile("Restauracja.txt");
        rezerwacja1 = new Rezerwacja(LocalDate.of(2022, 1, 17), "12", "13", restauracja.stoliki.get(0));
        rezerwacja2 = new Rezerwacja(LocalDate.of(2022, 1, 17), "13", "14", restauracja.stoliki.get(0));
        rezerwacja3 = new Rezerwacja(LocalDate.of(2022, 1, 17), "13", "14", restauracja.stoliki.get(1));
        rezerwacjaNowa = new Rezerwacja(LocalDate.of(2022, 1, 17), "14", "15", restauracja.stoliki.get(0));
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja1, restauracja);
        rezerwacje.dodajRezerwacje(osoba1, rezerwacja2, restauracja);
        rezerwacje.dodajRezerwacje(osoba2, rezerwacja3, restauracja);
        list = new ArrayList();
        list.add(rezerwacja1);
        list.add(rezerwacja2);


    }
}
