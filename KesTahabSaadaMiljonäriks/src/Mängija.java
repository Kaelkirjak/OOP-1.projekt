public class Mängija {
    // Isendiväljad.
    private String nimi;
    private int võidusumma;

    // Konstruktor.
    public Mängija(String nimi, int võidusumma) {
        this.nimi = nimi;
        this.võidusumma = võidusumma;
    }

    public String getNimi() {
        return nimi;
    }

    public int getVõidusumma() {
        return võidusumma;
    }

    public void setVõidusumma(int võidusumma) {
        this.võidusumma = võidusumma;
    }

    // Väljastab mängija nime ja võidusumma.
    public String toString() {
        return "Mängija: " + nimi + "; Võidusumma: " + võidusumma;
    }
}
