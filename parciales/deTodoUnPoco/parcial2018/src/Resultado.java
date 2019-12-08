public class Resultado {
    private String mostFollowers,mostFollowees;
    private int followers,followees;
    private Impresora impresora;
    boolean imprimiendo=false;

    public Resultado(){
        mostFollowees=null;
        followees=0;
        mostFollowers=null;
        followers=0;
        this.imprimiendo=false;
    }

    public void setImpresora(Impresora impresora){
        this.impresora=impresora;
    }

    public synchronized void update(String mostFollowers, int followers, String mostFollowees, int followees){
        System.out.println("Entro alguien imiprimiendo--->" + imprimiendo);
        while (imprimiendo) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(this.followers<followers) {
            System.out.println("Alguien updatea");
            System.out.println(mostFollowers);
            imprimiendo=true;
            this.mostFollowers = mostFollowers;
            this.followers=followers;
            synchronized (impresora) {
                impresora.notify();
            }
        }
        if(this.followees<followees){
            System.out.println("Alguien updatea");
            System.out.println(mostFollowees);
            imprimiendo=true;
            this.mostFollowees=mostFollowees;
            this.followees=followees;
            synchronized (impresora) {
                impresora.notify();
            }
        }
    }

    public synchronized void imprimir() {
        imprimiendo = false;
        System.out.println("El usuario mas seguido es: " + mostFollowers + " con " + followers + " seguidores");
        System.out.println("El usuario mas seguidor es: " + mostFollowees + " siguiendo a " + followees + " usuarios");
        this.notifyAll();
    }
}
