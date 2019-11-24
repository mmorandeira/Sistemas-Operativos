import java.util.ArrayList;

public class Supermercado {

    private ArrayList<Cliente> cola;
    private Canasta canasta;


    public Supermercado(Canasta canasta) {
        cola = new ArrayList<Cliente>();
        this.canasta = canasta;
    }

    public synchronized Cliente proximo() {
        while (cola.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cola.remove(0);
    }

    public synchronized void encolar(Cliente cliente) {
        this.cola.add(cliente);
        this.notifyAll();
    }

    public synchronized double getPrecio(Producto producto) {
        if (canasta.contiene(producto))
            return producto.getPrecio() * 0.9;
        return producto.getPrecio();
    }


}
