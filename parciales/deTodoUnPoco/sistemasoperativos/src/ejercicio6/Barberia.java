package ejercicio6;

import java.util.ArrayList;

public class Barberia {
    int capacidad = 0;
    private ArrayList<Cliente> esperando;

    public Barberia(int capacidad) {
        esperando = new ArrayList<Cliente>();
        this.capacidad = capacidad;
    }

    public synchronized boolean ingresarBarberia(Cliente c) {
        if (esperando.size() < capacidad) {
            esperando.add(c);
            return true;
        }
        return false;
    }

    public synchronized Cliente llamarCliente() {
        if (esperando.size() > 0) {
            return esperando.remove(0);
        }
        return null;
    }

    public synchronized int getCantidadEsperando() {
        return esperando.size();
    }

    public int getCapacidad() {
        return capacidad;
    }
}