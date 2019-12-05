public class BatterySaver implements Runnable {

    private int cantApps;
    private int cantMediciones;
    private double[][] mediciones;
    private double umbral;
    private Aplicacion[] aplicaciones;
    boolean esperandoFinalizacion = false;

    public BatterySaver(int cantMediciones, double[][] mediciones, double umbral) {
        this.cantMediciones = cantMediciones;
        this.mediciones = mediciones;
        this.umbral = umbral;
    }

    public void setAplicaciones(Aplicacion[] aplicaciones) {
        this.aplicaciones = aplicaciones;
        this.cantApps = aplicaciones.length;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.verificarApps();
        }
    }

    public void verificarApps() {
        synchronized (mediciones) {
            for (int i = 0; i < cantApps; i++) {
                if (!aplicaciones[i].terminada()) {
                    double promedio = 0;
                    for (int j = 0; j < cantMediciones; j++) {
                        promedio += mediciones[i][j];
                    }
                    promedio = promedio / cantMediciones;
                    if (promedio > umbral)
                        aplicaciones[i].terminar();
                    synchronized (this) {
                        while (esperandoFinalizacion) {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public synchronized void notificarFinalizacion() {
        esperandoFinalizacion = false;
        this.notify();
    }

}
