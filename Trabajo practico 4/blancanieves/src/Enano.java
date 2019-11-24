public class Enano implements Runnable {

    Comedor comedor;
    boolean esperando = true;

    public Enano(Comedor comedor) {
        this.comedor = comedor;
        this.esperando = true;
    }

    @Override
    public void run() {
        System.out.println("El enano trata de sentarse");
        comedor.sentarse(this);
        System.out.println("El enano espera que le sirvan la comida");
        //ACA EL ENANO YA SE SENTO

        synchronized (this) {
            if (esperando) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //SIMULO QUE ESTA COMIENDO ALGO RICO
        System.out.println("El enano empieza a comer");
        try {
            Thread.currentThread().sleep((long) (Math.random() * 500.0 + 1.0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //EL ENANO TERMINO DE COMER Y SE PIRA VAMPIRA
        System.out.println("El enano termina de comer");
        comedor.termino(this);
        System.out.println("El enano se va a trabajar");
    }

    public synchronized void comer() {
        esperando = false;
        this.notify();
    }

    public boolean getEsperando() {
        return esperando;
    }

}
