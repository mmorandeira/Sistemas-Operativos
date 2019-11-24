import java.util.ArrayList;

public class Comedor {

    ArrayList<Enano> sillas;
    int cantSillas = 0;

    public Comedor(int cantSillas) {
        this.cantSillas = cantSillas;
        this.sillas = new ArrayList<Enano>();
    }

    public synchronized void sentarse(Enano enano) {
        System.out.println("entra a sentarse");
        while (sillas.size() >= cantSillas) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Un enano se sienta");
        sillas.add(enano);
        this.notifyAll();
    }

    public synchronized void termino(Enano enano) {
        sillas.remove(enano);
        this.notifyAll();
    }

    public synchronized Enano siguiente() {
        Enano siguiente = enanoEsperando();
        while (siguiente == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            siguiente=enanoEsperando();
        }
        this.notifyAll();
        return siguiente;
    }

    public synchronized Enano enanoEsperando(){
        for (Enano enano : sillas) {
            if (enano.getEsperando())
                return enano;
        }
        return null;
    }


}
