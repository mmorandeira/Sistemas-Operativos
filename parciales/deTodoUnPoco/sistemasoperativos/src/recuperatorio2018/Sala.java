package recuperatorio2018;

import java.util.ArrayList;

public class Sala {

    ArrayList<Jugador> esperando = new ArrayList<Jugador>();
    ManagerJuego partida;

    public Sala() {

    }

    public synchronized void sentarse(Jugador jugador) {
        esperando.add(jugador);
        if (esperando.size() == 4)
            this.notify();
    }

    public synchronized ArrayList<Jugador> siguientes() {
        while (esperando.size() < 4) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Jugador> aux = new ArrayList<Jugador>();
        for (int i = 0; i < 4; i++)
            aux.add(esperando.remove(0));
        return aux;
    }

}
