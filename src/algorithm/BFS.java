package algorithm;

import java.util.*;

public class BFS {

    public static List<String> findPath(
            HashMap<String, ArrayList<String>> graph,
            String start,
            String end){

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String,String> parent = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){

            String current = queue.poll();

            if(current.equals(end))
                break;

            for(String neighbor : graph.get(current)){

                if(!visited.contains(neighbor)){

                    visited.add(neighbor);
                    parent.put(neighbor,current);
                    queue.add(neighbor);
                }
            }
        }

        if(!visited.contains(end))
            return null;

        List<String> path = new ArrayList<>();

        String current = end;

        while(current != null){
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        return path;
    }
}