import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Miembro implements Runnable {

    public static AtomicInteger NEXT_ID = new AtomicInteger();
    Almacen almacen;
    private List<Miembro> aProbar = new LinkedList<>();
    private int faltanProbar = 0;
    private List<Miembro> todos;
    private int id = 0;

    public Miembro(Almacen almacen) {
        this.almacen = almacen;
        this.id = NEXT_ID.incrementAndGet();
    }

    public void setTodos(List<Miembro> todos) {
        this.todos = new ArrayList<Miembro>();
        for (Miembro m : todos)
            if (!m.equals(this))
                this.todos.add(m);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(id + ": Empieza a hacer vino");
            almacen.getElementosMezcla();
            System.out.println(id + ": Empezo a mezclar");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + ": Termino de mezclar");
            almacen.devolverEstacion();
            System.out.println(id + ": Empezo a fermentar");
            //DUDA DE HACER WAIT O SLEEP
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + ": Termino de fermentar");

            almacen.pedirJarra();
            System.out.println(id + ": Empezo a decantar");
            //DUDA DE HACER WAIT O SLEEP
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(id + ": Termino de decantar");
            almacen.devolverJarras();


            synchronized (this) {
                System.out.println("entre al synchronized");
                this.faltanProbar = todos.size();
                System.out.println(faltanProbar);
            }
            for (Miembro m : todos) {
                m.agregarVinoAProbar(this);
            }
            //todos.forEach(m -> agregarVinoAProbar(this));
            //FOREACH NO ANDA EXPLICAME PORQUE


            while (true) {

                List<Miembro> aux = new ArrayList<>();
                synchronized (this) {
                    System.out.println(id + ": falta probar: " + faltanProbar);
                    System.out.println(id + aProbar.toString());
                    aux.addAll(this.aProbar);
                    this.aProbar.clear();
                }
                while (!aux.isEmpty()) {
                    aux.remove(0).probarVino(this);
                }
                synchronized (this) {
                    if (faltanProbar != 0)
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    else
                        break;
                }
            }

        }
    }

    public synchronized void agregarVinoAProbar(Miembro miembro) {
        //System.out.println("estoy en: " + this.id + " agrego el miembro: " + miembro.id);
        this.aProbar.add(miembro);
        this.notify();
        /*synchronized (miembro){
            miembro.notify();
        }*/
    }

    public synchronized void probarVino(Miembro m) {
        this.faltanProbar--;
        System.out.println(m.id + " Prueba el vino de " + this.id + " falta que lo prueben " + this.faltanProbar);
        if (this.faltanProbar == 0)
            this.notify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Miembro miembro = (Miembro) o;
        return id == miembro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
