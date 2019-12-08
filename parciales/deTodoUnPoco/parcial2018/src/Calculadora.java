import java.util.concurrent.atomic.AtomicInteger;

public class Calculadora implements Runnable {

    private Resultado resultado;
    private SocialNetwork socialNetwork;
    private String[] usuarios;
    private int begin, end;
    private static AtomicInteger NEXT_ID = new AtomicInteger();
    private int id;

    public Calculadora(Resultado resultado, int begin, int end, SocialNetwork socialNetwork, String[] usuarios) {
        this.resultado = resultado;
        this.begin = begin;
        this.end = end;
        this.socialNetwork = socialNetwork;
        this.usuarios = usuarios;
        this.id = NEXT_ID.incrementAndGet();
    }


    @Override
    public void run() {
        String mostFollowers = null, mostFollowees = null;
        int followers = 0, followees = 0;
        int index = begin;
        while (index < end) {
            System.out.println("Calculadora " + this.id + ": " + index);
            String actual = usuarios[index];
            int actualFollowers = socialNetwork.getFollowersOf(actual).length;
            int actualFollowees = socialNetwork.getFolloweesOf(actual).length;
            if (followers < actualFollowers) {
                mostFollowers = actual;
                followers = actualFollowers;
            }
            if (followees < actualFollowees) {
                mostFollowees = actual;
                followees = actualFollowees;
            }
            index++;
        }
        resultado.update(mostFollowers, followers, mostFollowees, followees);
    }

}
