package edu.isistan.blacanieves;

import java.util.LinkedList;
import java.util.List;

public class Casa {
    public static int MAX = 4;
    private int sillasOcupadas = 0;
    private List<Enano> esperando = new LinkedList<>();

    public synchronized Enano siguiente(){
        while (this.esperando.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.esperando.remove(0);
    }

    public synchronized boolean sentarse(Enano e){
        if(this.sillasOcupadas == MAX){
            return false;
        }
        this.sillasOcupadas++;
        this.esperando.add(e);
        this.notify();
        return true;
    }

    public synchronized void irse(){
        this.sillasOcupadas--;
    }
}
