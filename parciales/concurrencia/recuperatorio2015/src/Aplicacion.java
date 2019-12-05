import java.util.concurrent.atomic.AtomicInteger;

public class Aplicacion implements Runnable {

    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;
    private boolean terminado = false;
    private BatterySaver batterySaver;

    public Aplicacion(BatterySaver batterySaver) {
        this.id = NEXT_ID.incrementAndGet();
        this.batterySaver = batterySaver;
    }

    @Override
    public void run() {
        while (!terminado) {
            //algo
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        batterySaver.notificarFinalizacion();
    }

    public synchronized void terminar() {
        this.terminado = true;
        System.out.println(this.id + " Me suicidaron");
    }

    public synchronized double getMedicion() {
        return Math.random() * 100 + 1;
    }

    public synchronized boolean terminada() {
        return terminado;
    }

}
