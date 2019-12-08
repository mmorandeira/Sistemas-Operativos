package recuperatorio2018;

import java.util.ArrayList;

public class ManagerJuego implements Runnable {

    private Sala sala;
    private int listos = 0;

    public ManagerJuego(Sala sala) {
        this.sala = sala;
    }

    public ManagerJuego() {

    }

    @Override
    public void run() {
        while (true) {
            ArrayList<Jugador> jugadores = sala.siguientes();
            for (Jugador jugador : jugadores)
                jugador.avisarTurno();
            synchronized (this) {
                while (listos < 4) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (this) {
                while (listos > 0) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public synchronized void jugar() {
        listos++;
        if (listos == 4)
            this.notifyAll();
        while (listos < 4) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void abandonar() {
        listos--;
        if (listos == 0)
            this.notify();
    }
}
