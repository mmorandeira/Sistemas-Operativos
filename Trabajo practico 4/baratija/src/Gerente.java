public class Gerente implements Runnable {

    @Override
    public void run() {
        while (true){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //chekeo
        }
    }
}
