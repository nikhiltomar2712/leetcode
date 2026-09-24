import java.util.*;

class Solution {
    private List<List<int[]>> graph;
    private Map<Integer, Integer> maskCount;
    private long answer;

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Build adjacency list: edge from parent to child with character bitmask
        for (int i = 1; i < n; i++) {
            int p = parent.get(i);
            int charMask = 1 << (s.charAt(i) - 'a');
            graph.get(p).add(new int[]{i, charMask});
        }

        maskCount = new HashMap<>();
        maskCount.put(0, 1);  // empty path from root
        answer = 0;

        dfs(0, 0);
        return answer;
    }

    private void dfs(int node, int xor) {
        for (int[] edge : graph.get(node)) {
            int child = edge[0];
            int charMask = edge[1];
            int newXor = xor ^ charMask;

            // Count valid partners already seen
            // Case 1: Same mask (even-length palindrome)
            answer += maskCount.getOrDefault(newXor, 0);

            // Case 2: One bit different (odd-length palindrome)
            for (int bit = 0; bit < 26; bit++) {
                int target = newXor ^ (1 << bit);
                answer += maskCount.getOrDefault(target, 0);
            }

            // Add current mask and recurse
            maskCount.merge(newXor, 1, Integer::sum);
            dfs(child, newXor);
        }
    }
}