package recuperatorio2018;

import static java.lang.Math.random;

public class Jugador implements Runnable {

    private Sala sala;
    private ManagerJuego managerJuego;
    private boolean espera = true;
    private int numero;


    public Jugador(Sala feria, ManagerJuego managerJuego, int numero) {
        this.sala = feria;
        this.managerJuego = managerJuego;
        this.numero = numero;
    }


    @Override
    public void run() {
        System.out.println("Jugador" + numero + " run.");
        this.sala.sentarse(this);
        System.out.println("Jugador" + numero + " se sento y se puso a esperar.");
        this.esperar();
        System.out.println("Jugador" + numero + " termino de esperar y se pone a jugar.");
        managerJuego.jugar();
        try {
            Thread.currentThread().sleep(((int) (Math.random() * 500) + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Jugador" + numero + " termino de jugar y se pira vampira.");
        managerJuego.abandonar();
    }

    private synchronized void esperar() {
        while (espera) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void avisarTurno() {
        this.espera = false;
        this.notify();
    }
}
