package ejercicio6;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int cant_clientes = 500;
        Barberia barberia = new Barberia(10);
        Barbero barbero = new Barbero(barberia);
        ArrayList<Thread> arreglodethreadsclientes = new ArrayList<Thread>();
        for (int i = 0; i < cant_clientes; i++) {
            arreglodethreadsclientes.add(new Thread(new Cliente(barberia)));
        }
        Thread thread_del_barbero = new Thread(barbero);
        thread_del_barbero.start();
        for (int i = 0; i < cant_clientes; i++) {
            arreglodethreadsclientes.get(i).start();
        }
    }
}
