package parcial2018;

import java.util.Vector;

public class SocialNetwork {

    String[] users;
    boolean[][] follows;

    public SocialNetwork(int numberOfUsers) {
        users = new String[numberOfUsers];
        follows = new boolean[numberOfUsers][numberOfUsers];
    }

    String[] getNetworkUserNames() {
        return users;
    }

    String[] getFollowersOf(String username) {
        int user = getIndex(username);
        if(user!=-1){
            Vector<String> aux=new Vector<String> ();
            for(int i = 0; i< users.length; i++){
                if(follows[i][user]){
                    aux.add(users[i]);
                }
            }
            return toArray(aux);
        }
        return null;
    }

    String[] getFolloweesOf(String username) {
        int user = getIndex(username);
        if(user!=-1){
            Vector<String> aux=new Vector<String> ();
            for(int i = 0; i< users.length; i++){
                if(follows[user][i]){
                    aux.add(users[i]);
                }
            }
            return toArray(aux);
        }
        return null;
    }

    void setUser(int index, String name) {
        users[index] = name;
    }

    void follow(int origin, int destination) {
        follows[origin][destination] = true;
    }

    private int getIndex(String username){
        int i=0;
        while (i < users.length && !users[i].equals(username)) {
            i++;
        }
        if (i < users.length) {
            return i;
        }
        return  -1;
    }

    private String[] toArray(Vector<String> vector){
        String[] retorno =new String[vector.size()];
        int index=0;
        for(String seguidor:vector){
            retorno[index]=seguidor;
            index++;
        }
        return retorno;
    }
}
