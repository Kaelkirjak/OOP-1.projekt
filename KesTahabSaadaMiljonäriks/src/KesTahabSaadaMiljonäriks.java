import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class KesTahabSaadaMiljonäriks {

    // Isendiväljad taimeri jaoks.
    static int interval;
    static Timer timer;

    static Andmed küsimusteAndmed(String failiteekond) throws Exception{

        // Luuakse massiivid ja maatriks, kuhu salvestatakse info iga küsimuse kohta.
        int[] võidetavSumma = new int[15];
        String[] küsimused = new String[15];
        String[][] vastused = new String[15][4];
        String[] vihjed = new String[15];
        String[] õigedVastused = new String[15];

        // Loetakse failist informatsioon ja salvestatakse igasse massiivi/maatriksi.
        java.io.File fail = new java.io.File(failiteekond);
        try (java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
            int indeks = 0;
            while (sc.hasNextLine()) { // Tsükkel, kus salvestatakse igasse massiivi/maatriksi õige informatsioon.
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

    // meetod taimeri jaoks.
    private static int setInterval() {
        if (interval == 1)
            timer.cancel(); // Lõpetab taimeri.
        return --interval; // Tagastab ühe võrra väiksema arvu.
    }

    // meetod, kus toimub reaalne mängu mängimine.
    static void väljastamine(Mängija mängija) throws Exception {
        Andmed informatsioon = küsimusteAndmed("küsimused.txt"); // Luuakse andmed, kasutades meetodit küsimusteAndmed(failiteekond).

        // Massiivid/maatriks oma vastava sisuga.
        int[] summa = informatsioon.getSumma();
        String[] küsimused = informatsioon.getKüsimused();
        String[][] vastused = informatsioon.getVastused();
        String[] vihjed = informatsioon.getVihjed();
        String[] õiged = informatsioon.getÕigedVastused();

        int vihjeteArv = 3; // Tähistab mitu korda saab mängija vihjet küsida.
        for (int i = 0; i < summa.length; i++) {

            //Uus voor algab

            System.out.println("\nKui vastate järgmise küsimuse õigesti, siis võidate: "+summa[i]+" eurot!"); // Väljastatakse teave küsimuse kohta.
            Scanner üks =new Scanner(System.in);
            System.out.println("Sisestage midagi, kui olete valmis küsimuseks ");
            String valmisolek =üks.nextLine();

            System.out.println(küsimused[i]); // Väljastatakse küsimus.

            // Luuakse massiiv, kus on küsimuse vastusevariandid.
            String[] vastuseTükid = new String[4];
            vastuseTükid[0] = vastused[i][0];
            vastuseTükid[1] = vastused[i][1];
            vastuseTükid[2] = vastused[i][2];
            vastuseTükid[3] = vastused[i][3];

            vastuseTükid= randomVastused(vastuseTükid); // Vastusevariandid "segatakse" ära nii, et need on suvalises järjekorras.

            for (int j=0; j<4;j++) { // Tsükkel, kus väljastatakse vastusevariandid.
                System.out.println((j + 1) + ") " + vastuseTükid[j]);
            }


            int viivitus = 1000; // Aeg millisekundites (1000ms = 1s).
            int periood = 1000; // Samuti aeg millisekundites.
            timer = new Timer(); // Luuakse uus taimer.
            String sekundid = "20"; // Aeg, mis on mängijal vastamiseks.
            interval = Integer.parseInt(sekundid);
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    int sekundid = setInterval(); // Saadakse sekund.
                    if (sekundid == 10) { // Kui aega on veel järgi 10 sekundit väljastab hoiatuse.
                        System.out.println("Teil on aega jäänud vastamiseks 10 sekundit!");
                    }
                    if (sekundid == 0) { // Kui aeg on läbi väljastab vastava teate ja lõpetab mängu.
                        System.out.println("\nAeg läbi! Teie jaoks on seekord kõik.");
                        mängija.setVõidusumma(0); // Väärtustatakse mängija võidetav summa nulliga.
                        System.out.println("Mängija " + mängija.getNimi() + " lahkub saatest " + mängija.getVõidusumma()+ " euroga.");
                        System.exit(0); // Lõpetatakse programmi töö.
                    }
                }
            }, viivitus, periood);

            if (vihjeteArv != 0) { // Kontrollitakse, kas mängija saab veel vihjeid kasutada.
                Scanner kaks = new Scanner(System.in);
                System.out.print("Kui soovite VIHJET, vajutage \"V\", kui ei soovi, vajutage midagi muud.");
                System.out.println(" Teil on järgi " + vihjeteArv + " vihjet."); // Väljastab mitu vihjet saab veel mängija kasutada.
                String vihje = kaks.nextLine();
                if (vihje.equals("V")) {// Väljastatakse vihje, kui mängija seda soovib.
                    System.out.println("VIHJE: " + vihjed[i]);
                    vihjeteArv--;
                }
            }

            // Toimub vastuse sisestamine.
            Scanner kolm= new Scanner(System.in);
            System.out.println("Nüüd peate sisestama õige vastuse: ");
            String vastus=kolm.nextLine();
            Scanner neli=new Scanner(System.in);
            System.out.println("Sisestasite: "+vastus);
            timer.cancel();

            if (vastus.startsWith("Ei")) { // Kui mängija tahab mängu lõpetada.
                System.out.println("Teie jaoks on mäng läbi.");
                break;
            }

            //Õige vastuse kontroll
            if (vastus.equals(õiged[i])){
                System.out.println("ÕIGE, õige vastus on: "+õiged[i]);
                mängija.setVõidusumma(summa[i]);
            }
            else{ // Kui mängija sisestas vale vastuse lõpetatakse tema mäng.
                System.out.println("Vale vastus, õige oli: "+õiged[i]);
                mängija.setVõidusumma(0); // Mängija võidetav summa väärtustatakse nulliga.
                System.out.println();
                System.out.println("Teie jaoks on kahjuks mäng läbi.");
                break;
            }
            System.out.println("");
        }
        // Väljastatakse mängija nimi ja tema võidusumma.
        System.out.print("Mängija " + mängija.getNimi() + " lahkub saatest " + mängija.getVõidusumma() + " euroga.");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Tere tulemast mängu \"kes tahab saada miljonäriks\".");
        System.out.println("Iga küsimuse jaoks on 1 minut aega, et sellele vastata. Kui ei jõua selle aja sees vastata on Teie jaoks mäng läbi.");
        System.out.println("Samuti on mäng läbi, kui sisestate vale vastuse.");
        System.out.println("Vastuseks sisestage üks valiku vastustest MITTE NUMBER.");
        System.out.print("Palun sisestage oma nimi: ");
        Scanner mängijaNimeke = new Scanner(System.in);
        String mängijaNimi = mängijaNimeke.nextLine();
        Mängija mängija = new Mängija(mängijaNimi, 0); // Luuakse mängija.
        väljastamine(mängija);;
    }
}