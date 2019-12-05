import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Persona implements Runnable {

    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;
    Ronda ronda;
    Cebador cebador;
    Mate mate;
    boolean comiendo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Persona() {
        this.id = NEXT_ID.incrementAndGet();
        this.comiendo = false;
        this.cebador = null;
        this.ronda = null;
        this.mate = null;
    }

    public abstract void setMate(Persona persona, Mate mate);

    public int getId() {
        return id;
    }

    public void comerFactura() {
        this.comiendo = true;
        try {
            Thread.sleep((long) (Math.random() * 1000 + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.comiendo = false;
    }

    public boolean getComiendo() {
        return comiendo;
    }

}
