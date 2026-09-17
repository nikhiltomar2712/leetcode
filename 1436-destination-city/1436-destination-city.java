import java.util.*;

class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> origins = new HashSet<>();

        // Collect all cities that have an outgoing path
        for (List<String> path : paths) {
            origins.add(path.get(0));
        }

        // Find the destination city: appears as cityB but not as cityA
        for (List<String> path : paths) {
            String dest = path.get(1);
            if (!origins.contains(dest)) {
                return dest;
            }
        }

        return ""; // unreachable given problem guarantees
    }
}