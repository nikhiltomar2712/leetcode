import java.util.*;

class Solution {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Integer> forbiddenSet = new HashSet<>();
        int maxForbidden = 0;
        for (int f : forbidden) {
            forbiddenSet.add(f);
            maxForbidden = Math.max(maxForbidden, f);
        }

        // Upper bound: beyond this, we can never return to x
        int limit = Math.max(maxForbidden, x) + a + b;

        // BFS state: {position, direction} where direction = 1 (forward) or 0 (backward)
        // Visited: [position][direction]
        boolean[][] visited = new boolean[limit + 1][2];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1});  // start at 0, last move considered "forward" (so backward is allowed)
        visited[0][1] = true;

        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int pos = curr[0], lastForward = curr[1];

                if (pos == x) return jumps;

                // Forward jump
                int nextF = pos + a;
                if (nextF <= limit && !forbiddenSet.contains(nextF) && !visited[nextF][1]) {
                    visited[nextF][1] = true;
                    queue.offer(new int[]{nextF, 1});
                }

                // Backward jump (only if last move was forward)
                if (lastForward == 1) {
                    int nextB = pos - b;
                    if (nextB >= 0 && !forbiddenSet.contains(nextB) && !visited[nextB][0]) {
                        visited[nextB][0] = true;
                        queue.offer(new int[]{nextB, 0});
                    }
                }
            }
            jumps++;
        }

        return -1;
    }
}