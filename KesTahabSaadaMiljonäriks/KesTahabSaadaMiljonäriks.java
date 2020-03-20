import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

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
    //meetod, mis paneb vastusevariandid random järjekorda
    public static String[] randomVastused(String [] variandid) {

        //teen indeksite listi
        int[] vastused= {0,1,2};
        Random random = new Random();

        //panen indeksid listis random järjekorda
        for (int i = 0; i < vastused.length; i++) {
            int suvalineIndeks = random.nextInt(vastused.length);
            int suvaline= vastused[suvalineIndeks];
            vastused[suvalineIndeks] = vastused[i];
            vastused[i] = suvaline;}

        //eelnevalt random indeksite listile panen vastavusse elemendid
        String[] randomVariandid=new String[4];
        for (int i=0;i<3;i++) {
            randomVariandid[i] = variandid[vastused[i]];
        }
        randomVariandid[3]=variandid[3];
        return randomVariandid;
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

            //Uus voor algab

            System.out.println("\nKui vastate järgmise küsimuse õigesti, siis võidate: "+summa[i]+" eurot!");
            Scanner üks =new Scanner(System.in);
            System.out.println("Sisestage midagi, kui olete valmis küsimuseks ");
            String valmisolek =üks.nextLine();

            System.out.println(küsimused[i]);
            String[] vastuseTükid= Arrays.toString(vastused[i]).split(", ");

            vastuseTükid= randomVastused(vastuseTükid);

            // "[" ja "]" peaks kuidagi ära saama.
            for (int j=0; j<4;j++)
                System.out.println((j+1)+") "+vastuseTükid[j]);

            //Küsimine kasutajalt, kas ta soovib vihjet
            Scanner kaks =new Scanner(System.in);
            System.out.println("Kui soovite VIHJET, vajutage \"V\", kui ei soovi, vajutage midagi muud");
            String vihje=kaks.nextLine();
            if(vihje.equals("V"))
                System.out.println("VIHJE: "+vihjed[i]);

            Scanner kolm= new Scanner(System.in);
            System.out.println("Nüüd peate sisestama õige vastuse: ");
            String vastus=kolm.nextLine();
            Scanner neli=new Scanner(System.in);
            System.out.println("Sisestasite: "+vastus);

            //Õige vastuse kontroll
            if (vastus.equals(õiged[i])){
                System.out.println("ÕIGE, õige vastus on: "+õiged[i]);
                //võidusumma lisamine?
            }
            else{
                System.out.println("Vale vastus, õige oli: "+õiged[i]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws Exception {
        Mängija mängija = new Mängija("Toomas Mets", 0);
        väljastamine();
    }
}
