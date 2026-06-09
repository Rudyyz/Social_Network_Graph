package algorithm;

import java.util.*;

public class DFS {

    public static class DFSResult {

        private List<String> path;
        private List<String> visitedOrder;

        public DFSResult(List<String> path, List<String> visitedOrder) {
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

    public static DFSResult findPath(
            HashMap<String, ArrayList<String>> graph,
            String start,
            String end) {

        HashSet<String> visited = new HashSet<>();
        List<String> visitedOrder = new ArrayList<>();
        List<String> path = new ArrayList<>();

        boolean found = dfsRecursive(
                graph,
                start,
                end,
                visited,
                visitedOrder,
                path
        );

        if (!found) {
            return new DFSResult(null, visitedOrder);
        }

        return new DFSResult(path, visitedOrder);
    }

    private static boolean dfsRecursive(
            HashMap<String, ArrayList<String>> graph,
            String current,
            String end,
            HashSet<String> visited,
            List<String> visitedOrder,
            List<String> path) {

        visited.add(current);
        visitedOrder.add(current);
        path.add(current);

        if (current.equals(end)) {
            return true;
        }

        for (String neighbor : graph.get(current)) {

            if (!visited.contains(neighbor)) {

                boolean found = dfsRecursive(
                        graph,
                        neighbor,
                        end,
                        visited,
                        visitedOrder,
                        path
                );

                if (found) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);

        return false;
    }
}