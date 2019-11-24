public class Blancanieves implements Runnable {

    Comedor comedor;

    public Blancanieves(Comedor comedor) {
        this.comedor = comedor;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Blanca nieves pide un enano");
            Enano en = comedor.siguiente();
            System.out.println("Blanca nieves empieza a cocinarle");
            try {
                Thread.currentThread().sleep((long) (Math.random() * 500.0 + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Blanca nieves le avisa que coma");
            en.comer();
        }
    }
}
