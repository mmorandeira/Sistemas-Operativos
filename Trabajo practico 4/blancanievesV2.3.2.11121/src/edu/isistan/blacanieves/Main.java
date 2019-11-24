package edu.isistan.blacanieves;

public class Main {

    public static void main(String[] args) {
        Casa casa = new Casa();
        BlancaNieves blancanieves = new BlancaNieves(casa);
        for(int i=0; i<7; i++){
            Enano e = new Enano(casa);
            new Thread(e).start();
        }
        new Thread(blancanieves).start();
    }

}
