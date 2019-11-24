import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Almacen almacen = new Almacen(2, 6, 7, 15, 20);
        Gerente gerente = new Gerente(almacen);
        System.out.println("Gerente: " + gerente);
        almacen.setGerente(gerente);
        Thread thGerente=new Thread(gerente);
        int cantMiembros = 8;
        ArrayList<Thread> clientes = new ArrayList<Thread>();
        ArrayList<Miembro> miembros = new ArrayList<>();
        for (int i = 0; i < cantMiembros; i++) {
            miembros.add(new Miembro(almacen));
            clientes.add(new Thread(miembros.get(miembros.size()-1)));
        }
        almacen.setMiembros(miembros);
        miembros.forEach(m -> m.setTodos(miembros));
        thGerente.start();
        for (int i = 0; i < cantMiembros; i++) {
            clientes.get(i).start();
        }


    }
}
