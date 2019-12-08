package recuperatorio2015;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int nroApss=150;
        ArrayList<Aplicacion> apps=new ArrayList<Aplicacion>();
        for(int i=0;i<nroApss;i++){
            apps.add(new Aplicacion());
        }
        /*Aplicacion a = new Aplicacion();
        Thread th1 = new Thread(a);
        th1.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.kill();*/
        BaterySaver baterySaver=new BaterySaver(apps,);
        Monitor monitor=new Monitor(apps,10,baterySaver,100);
        Thread th2=new Thread(monitor);
        th2.start();
    }

}
