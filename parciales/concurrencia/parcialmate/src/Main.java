import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int cantParasitos = 7;
        int capacidadTermo = 10;
        int cantFacturas = 15;
        ArrayList<Persona> personas = new ArrayList<Persona>();
        for (int i = 0; i < cantParasitos; i++) {
            personas.add(new Parasito());
        }
        Mate mate = new Mate();
        Ronda ronda = new Ronda(personas, cantFacturas);
        Cebador cebador = new Cebador(ronda, capacidadTermo, mate);
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (Persona p : personas) {
            Parasito aux = (Parasito) (p);
            aux.setRonda(ronda);
            aux.setCebador(cebador);
            threads.add(new Thread(p));
        }
        personas.add(cebador);
        Thread thCebador = new Thread(cebador);
        for (Thread thread : threads) {
            thread.start();
        }
        thCebador.start();
    }
}
