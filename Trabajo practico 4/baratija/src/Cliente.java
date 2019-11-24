import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Cliente implements Runnable {

    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;
    private Supermercado supermercado;
    private boolean pago = false;
    private ArrayList<Producto> carrito;

    public Cliente(Supermercado supermercado, ArrayList<Producto> carrito) {
        this.id = NEXT_ID.incrementAndGet();
        this.supermercado = supermercado;
        this.carrito = carrito;
    }

    @Override
    public void run() {
        supermercado.encolar(this);
        while (!pago) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public synchronized void cobrar(double plata) {
        pago = true;
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notify();
    }
}
