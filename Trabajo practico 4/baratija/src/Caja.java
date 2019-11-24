import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Caja implements Runnable {

    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;
    private Supermercado supermercado;

    public Caja(Supermercado supermercado) {
        this.id = NEXT_ID.incrementAndGet();
        this.supermercado = supermercado;
    }

    @Override
    public void run() {
        while (true) {
            Cliente prox = supermercado.proximo();
            ArrayList<Producto> carrito = prox.getCarrito();
            double aCobrar = 0;
            for (Producto producto : carrito) {
                aCobrar += supermercado.getPrecio(producto);
            }
            prox.cobrar(aCobrar);
        }

    }
}
