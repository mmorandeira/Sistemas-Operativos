public class Monitor implements Runnable {

    private long tiempo;
    private int cantApps;
    private int cantMediciones;
    private double[][] mediciones;
    private BatterySaver batterySaver;
    private Aplicacion[] aplicaciones;

    public Monitor(long tiempo, Aplicacion[] aplicaciones, int cantMediciones, double[][] mediciones,BatterySaver batterySaver) {
        this.tiempo = tiempo;
        this.cantApps = aplicaciones.length;
        this.cantMediciones = cantMediciones;
        this.mediciones = mediciones;
        this.aplicaciones = aplicaciones;
        this.batterySaver = batterySaver;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //medir los tiempos
            this.medir();
            synchronized (batterySaver) {
                batterySaver.notify();
            }
        }
    }

    public void medir() {
        synchronized (aplicaciones) {
            for (int j = 0; j < cantMediciones; j++) {
                for (int i = 0; i < cantApps; i++) {
                    mediciones[i][j] = aplicaciones[i].getMedicion();
                }
            }
        }
    }
}
