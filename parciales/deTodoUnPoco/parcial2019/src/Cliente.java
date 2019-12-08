import java.util.concurrent.atomic.AtomicInteger;

public class Cliente implements Runnable {

    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;

    public Cliente(){
        this.id=NEXT_ID.incrementAndGet();
    }

    @Override
    public void run() {

    }
}
