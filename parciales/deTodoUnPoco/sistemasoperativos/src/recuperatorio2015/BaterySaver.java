package recuperatorio2015;

import java.util.ArrayList;

public class BaterySaver implements Runnable {

    private ArrayList<Aplicacion> apps;
    private Monitor monitor;
    private double umbral;

    public BaterySaver(ArrayList<Aplicacion> apps,Monitor monitor,double umbral){
        this.apps=apps;
        this.monitor=monitor;
        this.umbral=umbral;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.checkearApps();
        }
    }

    private void checkearApps() {
        int nroMediciones=monitor.getNroMediciones();
        for(int i=0;i<apps.size();i++){
            double prom=0;
            for(int j=0;j<nroMediciones;j++){
                prom+=monitor.getMedicion(i,j)/nroMediciones;
            }
            if(prom>umbral){
                apps.get(i).kill();
            }
        }

    }

}
