public class Cebador extends Persona {

    private Ronda ronda;
    private int capacidadTermo;

    public Cebador(Ronda ronda, int capacidadTermo, Mate mate) {
        super();
        this.ronda = ronda;
        this.capacidadTermo = capacidadTermo;
        this.mate = mate;
    }

    @Override
    public void run() {
        int capacidad = capacidadTermo;
        while (true) {
            if (capacidad > 0) {
                System.out.println("Dentro del if");
                Persona siguiente = ronda.siguiente();
                System.out.println("Soy el cebador " + this.getId() + " y le di el mate a " + siguiente.getId());
                if (!siguiente.equals(this)) {
                    siguiente.setMate(this, this.mate);
                    this.mate = null;
                } else {
                    try {
                        Thread.sleep((long) (Math.random() * 1000 + 1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                capacidad--;
                boolean agarreFactura = false;
                if ((int) (Math.random() * 100 + 1) < 35) {
                    synchronized (ronda) {
                        if (ronda.hayFacturas()) {
                            ronda.agarrarFactura();
                            agarreFactura = true;
                        }
                    }
                }
                if (agarreFactura) {
                    this.comerFactura();
                }
                //System.out.println("Termina de comer facturas");
                if (this.mate == null)
                    System.out.println("EL MATE ES NULL");
                synchronized (this) {
                    while (this.mate == null) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("EL CEBADOR VA A CALENTAR AGUITA");
                try {
                    Thread.sleep((long) (Math.random() * 10000 + 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                capacidad = capacidadTermo;
            }
        }
    }


    @Override
    public synchronized void setMate(Persona persona, Mate mate) {
        this.mate = mate;
        this.notify();
    }

}
