import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CorrectorOrtografico {

    private Hashtable<String, String> dicc;
    private int escritores = 0;
    private int lectores = 0;

    public CorrectorOrtografico() {
        this.dicc = new Hashtable<String, String>();
    }

    public synchronized void empezarEscribir() {
        while (escritores != 0 || lectores != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.escritores++;
    }

    public synchronized void terminarEscribir() {
        this.escritores--;
        this.notifyAll();
    }

    public synchronized void empezarLectura() {
        while (this.escritores != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.lectores++;
    }

    public synchronized void terminarLectura() {
        this.lectores--;
        if (lectores == 0)
            this.notifyAll();
    }

    public boolean addPalabra(String palabra, String definicion) {
        if (dicc.containsKey(palabra))
            return false;
        dicc.put(palabra, definicion);
        return true;
    }

    public boolean removePalabra(String palabra) {
        if (dicc.containsKey(palabra)) {
            dicc.remove(palabra);
            return true;
        }
        return false;
    }

    public List<String> existenPalabras(List<String> palabras) {
        ArrayList<String> aux = new ArrayList<String>();
        for (String palabra : palabras) {
            if (!dicc.containsKey(palabra))
                aux.add(palabra);
        }
        return aux;
    }
}
