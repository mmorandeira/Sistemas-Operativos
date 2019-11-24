import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork(5);
        sn.setUser(3, "John");
        sn.setUser(1, "Alice");
        sn.setUser(2, "Bob");
        sn.setUser(0, "Jana");
        sn.setUser(4, "Ralph");
        sn.follow(1, 3);
        sn.follow(0, 3);
        sn.follow(3, 4);
        sn.follow(2, 4);
        sn.follow(0, 4);
        sn.follow(1, 2);
        sn.follow(0, 2);
        String[] followers = sn.getFollowersOf("Bob");
        String[] followees = sn.getFolloweesOf("Bob");
        for (String follower : followers) {
            System.out.println(follower);
        }
        for (String followee : followees) {
            System.out.println(followee);
        }
        String[] usuarios = sn.getNetworkUserNames();
        int threadAmount = (int) ((Math.random() * usuarios.length + 1) % usuarios.length);
        int range = usuarios.length / threadAmount;
        int i = 0;
        Resultado resultado=new Resultado();
        Impresora impresora=new Impresora(resultado);
        resultado.setImpresora(impresora);
        Thread thImpresora=new Thread(impresora);
        thImpresora.start();
        ArrayList<Thread> threads=new ArrayList<Thread>();
        while (i < threadAmount) {
            int begin = i * range;
            int end;
            if (i != threadAmount - 1)
                end = (i + 1) * range;
            else
                end = usuarios.length;
            System.out.println("begin: " + begin);
            System.out.println("end: " + end);
            threads.add(new Thread(new Calculadora(resultado,begin,end,sn,usuarios)));
            i++;
        }
        for(int j=0;j<threads.size();j++)
            threads.get(j).start();

    }

}
