import java.util.Arrays;

public class Andmed {
    // Isendiväljad.
    private int[] summa;
    private String[] küsimused;
    private String[][] vastused;
    private String[] vihjed;
    private String[] õigedVastused;

    // Konstruktor.
    public Andmed(int[] summa, String[] küsimused, String[][] vastused, String[] vihjed, String[] õigedVastused) {
        this.summa = summa;
        this.küsimused = küsimused;
        this.vastused = vastused;
        this.vihjed = vihjed;
        this.õigedVastused = õigedVastused;
    }

    // Get meetodid.
    public int[] getSumma() {
        return summa;
    }

    public String[] getKüsimused() {
        return küsimused;
    }

    public String[][] getVastused() {
        return vastused;
    }

    public String[] getVihjed() {
        return vihjed;
    }

    public String[] getÕigedVastused() {
        return õigedVastused;
    }
}
