class Solution {
    public boolean isRobotBounded(String instructions) {
        // Directions: 0 = North, 1 = West, 2 = South, 3 = East
        int[] dist = new int[4];
        int k = 0; // starting facing North

        for (char c : instructions.toCharArray()) {
            if (c == 'L') {
                k = (k + 1) % 4;
            } else if (c == 'R') {
                k = (k + 3) % 4;
            } else { // 'G'
                dist[k]++;
            }
        }

        // After one cycle:
        // 1. Back at origin  → bounded
        // 2. Not facing North → will eventually cycle
        return (dist[0] == dist[2] && dist[1] == dist[3]) || k != 0;
    }
}