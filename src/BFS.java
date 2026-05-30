import java.util.*;

public class BFS {

    public static void findPath(
            HashMap<String, ArrayList<String>> graph,
            String start,
            String target) {

        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            System.out.println("User tidak ditemukan!");
            return;
        }

        Queue<String> queue = new LinkedList<>();
        HashMap<String, String> parent = new HashMap<>();
        HashSet<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(target)) {
                break;
            }

            for (String neighbor : graph.get(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!visited.contains(target)) {
            System.out.println("Tidak ada jalur.");
            return;
        }

        ArrayList<String> path = new ArrayList<>();

        String current = target;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        System.out.println("Jalur Koneksi:");
        System.out.println(path);
    }
}