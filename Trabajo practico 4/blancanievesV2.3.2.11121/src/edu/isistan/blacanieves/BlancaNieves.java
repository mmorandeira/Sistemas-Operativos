package edu.isistan.blacanieves;

public class BlancaNieves implements Runnable {

    private Casa casa;

    public BlancaNieves(Casa casa) {
        this.casa = casa;
    }

    @Override
    public void run() {
        while (true) {
            Enano e = this.casa.siguiente();
            e.darComida();
        }
    }
}
