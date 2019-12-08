package ejercicio6;

public class Barbero implements Runnable {
    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        System.out.println("entro al run del barbero");
        while (true) {
            Cliente act = barberia.llamarCliente();
            if (act != null)
                atender(act);
            else {
                synchronized (barberia) {
                    try {
                        barberia.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void atender(Cliente c) {
        System.out.println("que comience el juego");
        try {
            Thread.currentThread().sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("he acabado");
    }
}