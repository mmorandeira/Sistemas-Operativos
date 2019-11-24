public class Gerente implements Runnable {

    Almacen almacen;

    public Gerente(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Wait del gerente" + this);
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //checkear stocks y agregar segun corresponda
            System.out.println("Se despierta el Gerente");
            System.out.println("Gerente checkea envases");
            synchronized (almacen) {
                if (almacen.getEnvases() == 0) {
                    System.out.println("Gerente agrega 15 envases");
                    almacen.addEnvases(15);
                }
            }
            System.out.println("Gerente checkea paquetes");
            synchronized (almacen) {
                if (almacen.getPaquetes() == 0) {
                    System.out.println("Gerente agrega 20 paquetes");
                    almacen.addPaquetes(20);
                }
            }
            System.out.println("Gerente checkea unidades para fermentacion");
            synchronized (almacen){
                if (almacen.getUnidadesParaFermentacion()==0){
                    System.out.println("Gerente agrega 7 unidades para fermentacion");
                    almacen.addUnidadesParaFermentacion(7);
                }
            }
        }
    }

}
