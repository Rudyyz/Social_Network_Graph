import java.util.*;

public class SocialNetwork {

    private HashMap<String, ArrayList<String>> graph;

    public SocialNetwork() {
        graph = new HashMap<>();
    }

    public void addUser(String name) {

        if (graph.containsKey(name)) {
            System.out.println("User sudah ada!");
            return;
        }

        graph.put(name, new ArrayList<>());
        System.out.println("User berhasil ditambahkan.");
    }

    public void addFriendship(String userA, String userB) {

        if (!graph.containsKey(userA) || !graph.containsKey(userB)) {
            System.out.println("User tidak ditemukan!");
            return;
        }

        graph.get(userA).add(userB);
        graph.get(userB).add(userA);

        System.out.println("Friendship berhasil ditambahkan.");
    }

    public void findMutualFriends(String userA, String userB) {

        if (!graph.containsKey(userA) || !graph.containsKey(userB)) {
            System.out.println("User tidak ditemukan!");
            return;
        }

        ArrayList<String> mutual = new ArrayList<>();

        for (String friend : graph.get(userA)) {
            if (graph.get(userB).contains(friend)) {
                mutual.add(friend);
            }
        }

        System.out.println("Mutual Friends: " + mutual);
    }

    public void recommendFriends(String user) {

        if (!graph.containsKey(user)) {
            System.out.println("User tidak ditemukan!");
            return;
        }

        HashSet<String> recommendations = new HashSet<>();

        for (String friend : graph.get(user)) {

            for (String friendOfFriend : graph.get(friend)) {

                if (!friendOfFriend.equals(user)
                        && !graph.get(user).contains(friendOfFriend)) {

                    recommendations.add(friendOfFriend);
                }
            }
        }

        System.out.println("Friend Recommendation:");
        System.out.println(recommendations);
    }

    public HashMap<String, ArrayList<String>> getGraph() {
        return graph;
    }

    public void displayGraph() {

        System.out.println("\n=== SOCIAL NETWORK ===");

        for (String user : graph.keySet()) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }
}