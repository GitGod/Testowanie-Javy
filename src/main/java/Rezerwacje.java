import java.util.*;

public class Rezerwacje {
    HashMap<String, ArrayList> rezerwacje = new HashMap();
    private static final String EMAIL_REGEX = ".+\\@.+\\..+";
    private static final String IMIE_REGEX = "[A-Z][a-z]*";


    public boolean dodajRezerwacje(Osoba osoba, Rezerwacja rezerwacja, Restauracja restauracja) {
        if (osoba == null || rezerwacja == null) {
            throw new NullPointerException();
        }
        if (!sprawdzCzyotwarte(rezerwacja, restauracja)) {
            throw new IllegalArgumentException();
        }
        if (!validOsoba(osoba)) {
            throw new IllegalArgumentException();
        }


        if (czyWolny(rezerwacja)) {
            if (rezerwacje.containsKey(osoba.getEmail())) {
                ArrayList lista = rezerwacje.get(osoba.getEmail());
                lista.add(rezerwacja);
                rezerwacje.put(osoba.getEmail(), lista);
                return true;
            } else {
                ArrayList lista = new ArrayList();
                lista.add(rezerwacja);
                rezerwacje.put(osoba.getEmail(), lista);
                return true;
            }
        }
        return false;
    }

    public boolean czyWolny(Rezerwacja rezerwacja) {
        for (Map.Entry<String, ArrayList> entry : rezerwacje.entrySet()) {
            Object value = entry.getValue();
            for (int i = 0; i < ((ArrayList) value).size(); i++) {
                if (compare(rezerwacja, ((ArrayList) value).get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean compare(Object o1, Object o2) {
        Rezerwacja p1 = (Rezerwacja) o1;
        Rezerwacja p2 = (Rezerwacja) o2;
        // if last names are the same compare first names
        if (p1.dzien.equals(p2.dzien) && p1.godzina == p2.godzina && p1.stolik.numer == p2.stolik.numer) {
            return true;
        }
        return false;
    }

    public ArrayList WypiszDlaKonkretnego(Osoba osoba) {
        ArrayList<Rezerwacja> rezerwacjeOsoby = rezerwacje.get(osoba.getEmail());
        ArrayList<String> rzeczy = new ArrayList();
        for (int i = 0; i < rezerwacjeOsoby.size(); i++) {
            rzeczy.add(rezerwacjeOsoby.get(i).toString());
        }
        return rzeczy;
    }

    public boolean sprawdzCzyotwarte(Rezerwacja rezerwacja, Restauracja restauracja) {
        String godzinki = restauracja.godziny_otwarcia.get(rezerwacja.dzien);
        String podzielone[] = godzinki.split("-");
        if (rezerwacja.godzina < Integer.parseInt(podzielone[0]) || rezerwacja.godzina >= Integer.parseInt(podzielone[1])) {
            return false;
        }
        return true;

    }

    public boolean validOsoba(Osoba osoba) {
        if (!osoba.getEmail().matches(EMAIL_REGEX) || !osoba.getImie().matches(IMIE_REGEX)) {
            return false;
        }
        return true;
    }

}
