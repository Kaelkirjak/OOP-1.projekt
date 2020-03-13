import java.util.Arrays;

public class KesTahabSaadaMiljonäriks {

    static Andmed küsimusteAndmed(String failiteekond) throws Exception{

        // Luuakse massiivid ja maatriks, kus salvestatakse info iga küsimuse kohta.
        int[] võidetavSumma = new int[15];
        String[] küsimused = new String[15];
        String[][] vastused = new String[15][4];
        String[] vihjed = new String[15];
        String[] õigedVastused = new String[15];

        // Loetakse failist informatsioon ja salvestatakse igasse massiivi/maatriksi.
        java.io.File fail = new java.io.File(failiteekond);
        try (java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
            int indeks = 0;
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                if (rida.startsWith("Summa: ")) {
                    String[] sõnad = rida.split(" ");
                    võidetavSumma[indeks] = Integer.parseInt(sõnad[1]);
                }
                else if (rida.startsWith("a) ")) {
                    String[] sõnad = rida.split(" ", 2);
                    vastused[indeks][0] = sõnad[1];
                }
                else if (rida.startsWith("b) ")) {
                    String[] sõnad = rida.split(" ", 2);
                    vastused[indeks][1] = sõnad[1];
                }
                else if (rida.startsWith("c) ")) {
                    String[] sõnad = rida.split(" ", 2);
                    vastused[indeks][2] = sõnad[1];
                }
                else if (rida.startsWith("d) ")) {
                    String[] sõnad = rida.split(" ", 2);
                    vastused[indeks][3] = sõnad[1];
                }
                else if (rida.startsWith("VIHJE: ")) {
                    String[] sõnad = rida.split(" ", 2);
                    vihjed[indeks] = sõnad[1];
                }
                else if (rida.startsWith("õige: ")) {
                    String[] sõnad = rida.split(" ", 2);
                    õigedVastused[indeks] = sõnad[1];
                    indeks++;
                }
                else {
                    küsimused[indeks] = rida;
                }

            }
        }

        return new Andmed(võidetavSumma, küsimused, vastused, vihjed, õigedVastused);
    }
    // test meetod, et näha, kas massiividesse salvestati õige info õigesse kohta.
    static void väljastamine() throws Exception {
        Andmed informatsioon = küsimusteAndmed("küsimused.txt");
        int[] summa = informatsioon.getSumma();
        String[] küsimused = informatsioon.getKüsimused();
        String[][] vastused = informatsioon.getVastused();
        String[] vihjed = informatsioon.getVihjed();
        String[] õiged = informatsioon.getÕigedVastused();
        for (int i = 0; i < summa.length; i++) {
            System.out.println(summa[i]);
            System.out.println(küsimused[i]);
            System.out.println(Arrays.toString(vastused[i]));
            System.out.println(vihjed[i]);
            System.out.println(õiged[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Mängija mängija = new Mängija("Toomas Mets", 0);
        //String failitee = "küsimused.txt";
        //Andmed informatsioon = küsimusteAndmed(failitee);
        //int[] võidetavSumma = Andmed.getSumma();
        väljastamine();
    }
}
