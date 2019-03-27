import java.time.LocalDate;
import java.util.Date;

public class Rezerwacja {
    public String dzien;
    public LocalDate data;
    public int godzina;
    public int godzinaDo;
    public Stolik stolik;

    public Rezerwacja(LocalDate data, int godzina, int godzinaDo, Stolik stolik) {
        this.data = data;
        this.dzien = dzien;
        this.godzina = godzina;
        this.godzinaDo = godzinaDo;
        this.stolik = stolik;
    }

    @Override
    public String toString() {
        return "Stolik numer " + stolik.numer + " " + stolik.liczbaMiejsc + " osobowy " + data + " godzina " + godzina + "-" + godzinaDo;
    }
}

