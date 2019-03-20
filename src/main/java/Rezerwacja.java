import java.util.Date;

public class Rezerwacja {
    public String dzien;
    public int godzina;
    public Stolik stolik;

    public Rezerwacja(String dzien, int godzina, Stolik stolik) {

        this.dzien = dzien;
        this.godzina = godzina;
        this.stolik = stolik;
    }

    @Override
    public String toString() {
        return "Stolik numer " + stolik.numer + ":" + stolik.liczbaMiejsc + "osobowy: " +  dzien + " godzina " + godzina;
    }
}
