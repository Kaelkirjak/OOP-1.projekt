import java.util.Arrays;

public class Andmed {
    // Isendiväljad
    private int[] summa;
    private String[] küsimused;
    private String[][] vastused;
    private String[] vihjed;
    private String[] õigedVastused;

    // Konstruktor
    public Andmed(int[] summa, String[] küsimused, String[][] vastused, String[] vihjed, String[] õigedVastused) {
        this.summa = summa;
        this.küsimused = küsimused;
        this.vastused = vastused;
        this.vihjed = vihjed;
        this.õigedVastused = õigedVastused;
    }

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

    // test to string nägemaks, kas väljastab õiged asjad
    public String toString() {
        return "Summad: " + Arrays.toString(summa) + "\n" + "Küsimused:  " + Arrays.toString(küsimused) + "\n" +
                "Vihjed: " + Arrays.toString(vihjed) + "\n" + "Õiged vastused: " + Arrays.toString(õigedVastused) + "\n" +
                "Vastused: " + Arrays.deepToString(vastused);
    }
}
