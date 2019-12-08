public class Impresora implements Runnable {

    private Resultado resultado;

    public Impresora(Resultado resultado){
        this.resultado=resultado;
    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            resultado.imprimir();
        }
    }
}
