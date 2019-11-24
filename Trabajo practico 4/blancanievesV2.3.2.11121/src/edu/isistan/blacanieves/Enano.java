package edu.isistan.blacanieves;

import java.util.concurrent.atomic.AtomicInteger;

public class Enano implements Runnable{

    public static AtomicInteger NEXT_ID = new AtomicInteger();
    private boolean esperandoComida;
    private Casa casa;
    private int id;

    public Enano(Casa casa) {
        this.casa = casa;
        this.id = NEXT_ID.incrementAndGet();
    }

    @Override
    public synchronized void run(){
        while (true) {
            System.out.println(this.id + " Trabajo duro, como un esclavo!");
            try {
                Thread.sleep((long)(5000 * Math.random() + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.casa.sentarse(this)) {
                System.out.println(this.id + " Esperando Comida");
                this.esperandoComida = true;
                while (this.esperandoComida) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(this.id + " Comiendo");
                try {
                    Thread.sleep((long)(2000 * Math.random() + 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.id + " Libero silla");
                this.casa.irse();
            }
        }
    }

    public synchronized void darComida() {
        this.esperandoComida = false;
        this.notify();
    }
}
