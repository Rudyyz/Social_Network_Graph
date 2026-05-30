package model;

import java.util.*;

public class SocialNetwork {

    private final HashMap<String, ArrayList<String>> graph;

    public SocialNetwork() {
        graph = new HashMap<>();
    }

    public boolean addUser(String name){

        if(graph.containsKey(name))
            return false;

        graph.put(name,new ArrayList<>());
        return true;
    }

    public boolean addFriendship(String a,String b){

        if(!graph.containsKey(a) || !graph.containsKey(b))
            return false;

        if(!graph.get(a).contains(b))
            graph.get(a).add(b);

        if(!graph.get(b).contains(a))
            graph.get(b).add(a);

        return true;
    }

    public Set<String> getUsers(){
        return graph.keySet();
    }

    public HashMap<String, ArrayList<String>> getGraph(){
        return graph;
    }

    public int getTotalUsers(){
        return graph.size();
    }

    public int getTotalFriendships(){

        int count = 0;

        for(String user : graph.keySet()){
            count += graph.get(user).size();
        }

        return count / 2;
    }
    public java.util.List<String> getMutualFriends(String user1, String user2){

        java.util.List<String> mutual = new java.util.ArrayList<>();

        if(!graph.containsKey(user1) || !graph.containsKey(user2))
            return mutual;

        for(String friend : graph.get(user1)){

            if(graph.get(user2).contains(friend)){
                mutual.add(friend);
            }
        }

        return mutual;
    }
    public java.util.Set<String> getRecommendations(String user){

        java.util.Set<String> recommendations =
                new java.util.HashSet<>();

        if(!graph.containsKey(user))
            return recommendations;

        for(String friend : graph.get(user)){

            for(String friendOfFriend : graph.get(friend)){

                if(!friendOfFriend.equals(user)
                        && !graph.get(user).contains(friendOfFriend)){

                    recommendations.add(friendOfFriend);
                }
            }
        }

        return recommendations;
    }
}