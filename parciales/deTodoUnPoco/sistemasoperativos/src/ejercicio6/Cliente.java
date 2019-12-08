package ejercicio6;

public class Cliente implements Runnable {
    public Cliente(Barberia b) {
        this.barberia = b;
    }

    private Barberia barberia;

    public void run() {
        if (barberia.getCantidadEsperando() < barberia.getCapacidad()) {
            synchronized (barberia) {
                if(barberia.getCantidadEsperando()==0) {
                    barberia.notify();
                }
                barberia.ingresarBarberia(this);
            }
        }

    }
}