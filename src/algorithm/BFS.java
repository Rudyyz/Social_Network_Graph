package algorithm;

import java.util.*;

public class BFS {

    public static class BFSResult {

        private List<String> path;
        private List<String> visitedOrder;

        public BFSResult(List<String> path, List<String> visitedOrder) {
            this.path = path;
            this.visitedOrder = visitedOrder;
        }

        public List<String> getPath() {
            return path;
        }

        public List<String> getVisitedOrder() {
            return visitedOrder;
        }
    }

    public static BFSResult findPath(
            HashMap<String, ArrayList<String>> graph,
            String start,
            String end) {

        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        HashMap<String, String> parent = new HashMap<>();
        List<String> visitedOrder = new ArrayList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();
            visitedOrder.add(current);

            if (current.equals(end))
                break;

            for (String neighbor : graph.get(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!visited.contains(end))
            return new BFSResult(null, visitedOrder);

        List<String> path = new ArrayList<>();

        String current = end;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        return new BFSResult(path, visitedOrder);
    }
}