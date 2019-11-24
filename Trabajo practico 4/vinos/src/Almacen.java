import java.util.ArrayList;

public class Almacen {

    int estaciones;
    int jarras;
    int unidadesParaFermentacion;
    int envases;
    int paquetes;

    Gerente gerente;

    ArrayList<Miembro> miembros;

    public Almacen(int estaciones, int jarras, int unidadesParaFermentacion, int envases, int paquetes) {
        this.estaciones = estaciones;
        this.jarras = jarras;
        this.unidadesParaFermentacion = unidadesParaFermentacion;
        this.envases = envases;
        this.paquetes = paquetes;
    }

    public void setMiembros(ArrayList<Miembro> miembros) {
        this.miembros = miembros;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public synchronized void getElementosMezcla() {
        while (envases == 0 || paquetes == 0 || estaciones == 0 || jarras == 1 || unidadesParaFermentacion == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        envases--;
        paquetes--;
        jarras--;
        estaciones--;
        unidadesParaFermentacion--;
        //si me quedo sin envases o paquetes
        //notify gerente
        System.out.println("Envases: " + envases + " paquetes: " + paquetes + " unidades para fermentacion: " + unidadesParaFermentacion);
        if (envases == 0 || paquetes == 0 || unidadesParaFermentacion==0) {
            synchronized (gerente) {
                System.out.println("Notify del gerente: " + gerente);
                gerente.notify();
            }
        }
    }

    public synchronized int getPaquetes() {
        return paquetes;
    }

    public synchronized int getEnvases() {
        return envases;
    }

    public synchronized int getUnidadesParaFermentacion(){
        return unidadesParaFermentacion;
    }

    public synchronized void devolverEstacion() {
        estaciones++;
        this.notifyAll();
    }

    public synchronized void pedirJarra() {
        while (jarras == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        jarras--;
    }

    public synchronized void devolverJarras() {
        jarras += 2;
        System.out.println("Se devolvieron dos jarras");
        this.notifyAll();
    }

    public synchronized void addEnvases(int cantidad) {
        envases += cantidad;
        this.notifyAll();
    }

    public synchronized void addPaquetes(int cantidad) {
        paquetes += cantidad;
        this.notifyAll();
    }

    public synchronized void addUnidadesParaFermentacion(int cantidad) {
        unidadesParaFermentacion+=cantidad;
        this.notifyAll();
    }

    //public synchronized int get

}
