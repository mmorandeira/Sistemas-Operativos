import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        String[] nombres = {"Agua", "Lapicera", "Notebook", "Criollitas", "Pepas", "Salchichas",
                "Coca-Cola", "Yerba", "Cafe", "Azucar", "Lapiz", "Cuaderno", "Crema dove"};
        ArrayList<Producto> productos = new ArrayList<Producto>();
        for (int i = 0; i < nombres.length; i++) {
            productos.add(new Producto(nombres[i], "P" + toString(i + 1), round(Math.random() * 1000)));
        }
        for (Producto p : productos) {
            System.out.println(p);
        }
        ArrayList<Producto> canasta = new ArrayList<Producto>();
        int amount = (int) (Math.random() * productos.size());
        for (int i = 0; i < amount; i++) {
            Producto p = productos.get((int) (Math.random() * productos.size()));
            if (canasta.contains(p)) {
                canasta.add(p);
            }
        }
        ArrayList aux = carritoRandom(productos);
        System.out.println(aux);
    }

    private static String toString(int number) {
        if (number <= 9)
            return "0" + number;
        return "" + number;
    }

    private static double round(double amount) {
        return ((double) (long) (amount * 20 + 0.5)) / 20;
    }

    private static ArrayList<Producto> carritoRandom(ArrayList<Producto> productos) {
        ArrayList<Producto> carrito = new ArrayList<>();
        int cant = (int) (Math.random() * 100 + 1);
        int i = 0;
        while (i < cant) {
            Producto producto = productos.get((int) (Math.random() * productos.size()));
            carrito.add(producto);
            i++;
        }
        return carrito;
    }
}
