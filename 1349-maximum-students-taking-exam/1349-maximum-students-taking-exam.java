import java.util.*;

class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int fullMask = 1 << n;

        // Precompute valid masks for each row (no adjacent bits + only good seats)
        List<Integer>[] validMasks = new List[m];
        for (int i = 0; i < m; i++) {
            validMasks[i] = new ArrayList<>();
            for (int mask = 0; mask < fullMask; mask++) {
                if (isValidRow(mask, seats[i], n)) {
                    validMasks[i].add(mask);
                }
            }
        }

        // dp[mask] = max students using this mask for the current row
        int[] dp = new int[fullMask];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < m; i++) {
            int[] nextDp = new int[fullMask];
            Arrays.fill(nextDp, -1);

            for (int prevMask : validMasks[i > 0 ? i - 1 : 0]) {
                if (i > 0 && dp[prevMask] == -1) continue;
                int prevCount = (i == 0) ? 0 : dp[prevMask];

                for (int mask : validMasks[i]) {
                    // Check diagonal conflicts with previous row
                    if (i > 0 && ((mask & (prevMask << 1)) != 0 || (mask & (prevMask >> 1)) != 0)) {
                        continue;
                    }
                    int count = prevCount + Integer.bitCount(mask);
                    nextDp[mask] = Math.max(nextDp[mask], count);
                }
            }
            dp = nextDp;
        }

        int result = 0;
        for (int val : dp) {
            result = Math.max(result, val);
        }
        return result;
    }

    // Check if a mask is valid for a given row:
    // 1. No two adjacent bits (horizontal conflict)
    // 2. Only seats that are '.'
    private boolean isValidRow(int mask, char[] row, int n) {
        // No two adjacent bits
        if ((mask & (mask << 1)) != 0) return false;

        // All seated positions must be good seats
        for (int j = 0; j < n; j++) {
            if ((mask & (1 << j)) != 0 && row[j] == '#') {
                return false;
            }
        }
        return true;
    }
}