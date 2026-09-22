import java.util.Arrays;

class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);

        int low = 1;
        int high = position[position.length - 1] - position[0];
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(position, m, mid)) {
                result = mid;      // feasible, try a larger gap
                low = mid + 1;
            } else {
                high = mid - 1;    // not feasible, try a smaller gap
            }
        }

        return result;
    }

    // Greedily check if we can place m balls with at least `minDist` between them
    private boolean canPlace(int[] position, int m, int minDist) {
        int count = 1;                     // place the first ball
        int lastPos = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= minDist) {
                count++;
                lastPos = position[i];
                if (count == m) return true;
            }
        }

        return count >= m;
    }
}