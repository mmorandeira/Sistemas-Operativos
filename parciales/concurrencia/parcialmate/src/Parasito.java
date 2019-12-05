public class Parasito extends Persona {

    public Parasito() {
        super();
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public void setCebador(Cebador cebador) {
        this.cebador = cebador;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println("EN PARASITO AL PRINCIPIO");
            if (mate != null) {
                mate.tomar(this);
                /*
                if ((int) (Math.random() * 100 + 1) < 10) {
                    //se lo da a cualquiera
                    System.out.println("LE VA A DAR A CUALQUIERA");
                    ronda.getPersonaRandom().setMate(this, mate);
                } else {
                    //devolver mate a cebador
                    cebador.setMate(this, mate);
                }*/
                cebador.setMate(this,mate);
                this.mate=null;
            }

            System.out.println("EN PARASITO ANTES DE COMER FACTURAS");
            //random si tengo hambre
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
            System.out.println("EN PARASITO ANTES DE DORMIRME");
            synchronized (this) {
                if (this.mate == null) {
                    try {
                        System.out.println(this.getId() + " Me dormi");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public synchronized void setMate(Persona persona, Mate mate) {
        if (this.cebador.equals(persona)) {
            System.out.println("EN PARASITO SET MATE");
            this.mate = mate;
            this.notify();
        } else {
            cebador.setMate(this, mate);
        }
    }

}
