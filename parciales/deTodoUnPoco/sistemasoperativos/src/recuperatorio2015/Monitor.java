package recuperatorio2015;

import java.util.ArrayList;

public class Monitor implements Runnable {

    private ArrayList<Aplicacion> apps;
    private int nroMediciones = 0;
    private double[][] matrix;
    private int quantum;
    private BaterySaver baterySaver;

    public Monitor(ArrayList<Aplicacion> apps, int nroMediciones, BaterySaver baterySaver, int quantum) {
        this.apps = apps;
        this.nroMediciones = nroMediciones;
        this.baterySaver = baterySaver;
        this.quantum = quantum;
        matrix = new double[apps.size()][nroMediciones];
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(quantum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            medir();
            baterySaver.notify();
        }
    }

    public void medir() {
        for (int j = 0; j < nroMediciones; j++) {
            for (int i = 0; i < apps.size(); i++) {
                matrix[i][j] = apps.get(i).getMedicion();
            }

        }
    }

    public double getMedicion(int app, int nroMedicion){
        return matrix[app][nroMedicion];
    }

    public int getNroMediciones(){
        return nroMediciones;
    }
}
