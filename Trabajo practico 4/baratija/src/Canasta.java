import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class Canasta {

    private ArrayList<Producto> canasta;

    private Hashtable<String, LocalDate> fechas;

    public Canasta() {
        canasta = new ArrayList<Producto>();
    }

    public synchronized void addProducto(Producto producto) {
        canasta.add(producto);
        fechas.put(producto.getId(),LocalDate.now());
    }

    public synchronized boolean removeProducto(Producto producto) {
        if (canasta.contains(producto)) {
            canasta.remove(producto);
            fechas.remove(producto.getId());
            return true;
        }
        return false;
    }

    public synchronized boolean contiene(Producto producto) {
        if (canasta.contains(producto))
            return true;
        return false;
    }


}
