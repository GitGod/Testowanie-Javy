public class Osoba {
    private String imie;
    private String email;
    private static final String EMAIL_REGEX = ".+\\@.+\\..+";
    private static final String IMIE_REGEX = "[A-Z][a-z]*";

    public Osoba(String imie, String email) {
        this.imie = imie;
        this.email = email;
    }





    public String getImie() {
        return imie;
    }

    public String getEmail() {
        return email;
    }


}
