import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int cantSillas = 4;
        Comedor comedor = new Comedor(cantSillas);
        Blancanieves blancanieves = new Blancanieves(comedor);
        Thread thblancanieves = new Thread(blancanieves);
        int cantEnanos = 10;
        ArrayList<Thread> thEn = new ArrayList<Thread>();
        for (int i = 0; i < cantEnanos; i++) {
            thEn.add(new Thread(new Enano(comedor)));
        }
        thblancanieves.start();
        for (int i = 0; i < cantEnanos; i++) {
            thEn.get(i).start();
        }
    }
}
