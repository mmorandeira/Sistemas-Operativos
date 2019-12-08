package parcial2018;

public class Main {
    public static void main(String[] args) {
        SocialNetwork sn=new SocialNetwork(5);
        sn.setUser(0,"John");
        sn.setUser(1,"Alice");
        sn.setUser(2,"Bob");
        sn.setUser(3,"Jana");
        sn.setUser(4,"Ralph");
        sn.follow(1,0);
        sn.follow(3,0);
        sn.follow(0,4);
        sn.follow(2,4);
        sn.follow(3,4);
        sn.follow(1,2);
        sn.follow(3,2);
        String[] followers=sn.getFollowersOf("Bob");
        String[] followees=sn.getFolloweesOf("Bob");
        for(String follower:followers){
            System.out.println(follower);
        }
        for(String followee:followees){
            System.out.println(followee);
        }
    }

}
