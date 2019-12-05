import java.util.ArrayList;

public class Ronda {

    private ArrayList<Persona> personas;
    private int facturas;

    public Ronda(ArrayList<Persona> personas, int facturas) {
        this.personas = personas;
        this.facturas = facturas;
    }

    public Persona siguiente() {
        //System.out.println("EN RONDA EN METODO SIGUIENTE");
        Persona siguiente = personas.remove(0);
        //System.out.println("EL SIUIENTE ES: " + siguiente.getId());
        personas.add(siguiente);
        return siguiente;
    }

    public synchronized boolean hayFacturas() {
        return facturas > 0;
    }

    public synchronized void agarrarFactura() {
        facturas--;
    }

    public synchronized Persona getPersonaRandom() {
        int index=(int) (Math.random()*personas.size());
        return personas.get(index);
    }

}
