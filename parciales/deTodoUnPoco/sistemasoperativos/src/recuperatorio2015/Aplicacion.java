package recuperatorio2015;

public class Aplicacion implements Runnable {

    boolean running=true;

    public Aplicacion(){

    }

    @Override
    public void run() {
        while(running){
            System.out.println("Trabajo muy duro, como un esclavo");
        }
    }

    public double getMedicion(){
        return (Math.random() * 100);
    }

    public void kill(){
        this.running=false;
    }
}
