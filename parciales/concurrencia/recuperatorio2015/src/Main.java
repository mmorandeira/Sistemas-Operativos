public class Main {

    public static void main(String[] args) {
        int cantMediciones=12;
        int cantApps = 50;
        double[][] mediciones = new double[cantApps][cantMediciones];
        double umbral = 60.0;
        BatterySaver batterySaver = new BatterySaver(cantMediciones,mediciones,umbral);
        Aplicacion[] apps = new Aplicacion[cantApps];
        for (int i = 0; i < cantApps; i++) {
            apps[i] = new Aplicacion(batterySaver);
        }
        batterySaver.setAplicaciones(apps);
        long tiempo=2000;
        Monitor monitor= new Monitor(tiempo,apps,cantMediciones,mediciones,batterySaver);
        Thread[] thApps= new Thread[cantApps];
        for(int i=0;i<cantApps;i++)
            thApps[i]=new Thread(apps[i]);
        Thread thMonitor=new Thread(monitor);
        Thread thBatterySaver=new Thread(batterySaver);
        thMonitor.setDaemon(true);
        thBatterySaver.setDaemon(true);
        thMonitor.start();
        thBatterySaver.start();
        for(int i=0;i<cantApps;i++)
            thApps[i].start();
    }
}
