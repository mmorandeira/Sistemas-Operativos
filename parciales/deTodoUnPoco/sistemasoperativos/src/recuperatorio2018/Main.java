package recuperatorio2018;

public class Main {

    public static void main(String[] args) {
        Sala sala = new Sala();
        ManagerJuego managerJuego = new ManagerJuego(sala);
        int cant = 150;
        Thread[] threads = new Thread[cant];
        for (int i = 0; i < cant; i++) {
            threads[i] = new Thread(new Jugador(sala, managerJuego, i));
        }
        Thread thManager = new Thread(managerJuego);
        thManager.start();
        for (int i = 0; i < cant; i++) {
            threads[i].start();
        }
    }
}
