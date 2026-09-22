import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncoming = new boolean[n];

        for (List<Integer> edge : edges) {
            int to = edge.get(1);
            hasIncoming[to] = true;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncoming[i]) {
                result.add(i);
            }
        }

        return result;
    }
}