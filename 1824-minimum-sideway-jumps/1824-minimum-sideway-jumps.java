class Solution {
    public int minSideJumps(int[] obstacles) {
        final int INF = 1 << 30;
        // f[0], f[1], f[2] = min side jumps to be on lanes 1, 2, 3 at current point
        // Start at lane 2 (index 1) with 0 jumps; other lanes need 1 jump
        int[] f = {1, 0, 1};

        for (int i = 1; i < obstacles.length; i++) {
            // Mark the lane with obstacle as unreachable
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] == j + 1) {
                    f[j] = INF;
                    break;
                }
            }

            // Minimum jumps among all lanes + 1 (for a side jump)
            int x = Math.min(f[0], Math.min(f[1], f[2])) + 1;

            // Update each free lane: stay or side-jump from the best lane
            for (int j = 0; j < 3; j++) {
                if (obstacles[i] != j + 1) {
                    f[j] = Math.min(f[j], x);
                }
            }
        }

        return Math.min(f[0], Math.min(f[1], f[2]));
    }
}