import java.util.*;

class Solution {
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        // Ensure n is the smaller dimension for the profile
        if (n > m) {
            int temp = m; m = n; n = temp;
        }

        int rows = n;  // profile size (rows in a column)
        int cols = m;  // number of columns

        // Precompute powers of 3 for profile encoding
        int[] pow3 = new int[rows + 1];
        pow3[0] = 1;
        for (int i = 1; i <= rows; i++) pow3[i] = pow3[i - 1] * 3;

        int profileSize = pow3[rows];  // 3^rows possible profiles

        // Precompute for each profile:
        // - count of introverts, extroverts
        // - internal happiness (within the column)
        int[] introCount = new int[profileSize];
        int[] extroCount = new int[profileSize];
        int[] internalHappiness = new int[profileSize];

        for (int p = 0; p < profileSize; p++) {
            int x = p;
            int[] cells = new int[rows];
            for (int r = 0; r < rows; r++) {
                cells[r] = x % 3;
                x /= 3;
                if (cells[r] == 1) introCount[p]++;
                else if (cells[r] == 2) extroCount[p]++;
            }

            // Compute within-column happiness (vertical neighbors)
            int happiness = 0;
            for (int r = 0; r < rows; r++) {
                if (cells[r] == 1) happiness += 120;
                else if (cells[r] == 2) happiness += 40;

                if (r > 0) {
                    happiness += pairHappiness(cells[r - 1], cells[r]);
                }
            }
            internalHappiness[p] = happiness;
        }

        // Precompute cross-column happiness between two profiles
        // (horizontal neighbors between adjacent columns)
        int[][] crossHappiness = new int[profileSize][profileSize];
        for (int p1 = 0; p1 < profileSize; p1++) {
            int x1 = p1;
            int[] cells1 = new int[rows];
            for (int r = 0; r < rows; r++) {
                cells1[r] = x1 % 3;
                x1 /= 3;
            }

            for (int p2 = 0; p2 < profileSize; p2++) {
                int x2 = p2;
                int[] cells2 = new int[rows];
                for (int r = 0; r < rows; r++) {
                    cells2[r] = x2 % 3;
                    x2 /= 3;
                }

                int happiness = 0;
                for (int r = 0; r < rows; r++) {
                    happiness += pairHappiness(cells1[r], cells2[r]);
                }
                crossHappiness[p1][p2] = happiness;
            }
        }

        // DP: dp[profile][introUsed][extroUsed] = max happiness
        // Initialize with -infinity (use a very negative number)
        int NEG = Integer.MIN_VALUE / 2;

        // dp[cur] for current column
        int[][][] dp = new int[profileSize][introvertsCount + 1][extrovertsCount + 1];
        for (int[][] arr2 : dp) {
            for (int[] arr1 : arr2) {
                Arrays.fill(arr1, NEG);
            }
        }
        dp[0][0][0] = 0;  // empty profile, no one used

        for (int col = 0; col < cols; col++) {
            int[][][] newDp = new int[profileSize][introvertsCount + 1][extrovertsCount + 1];
            for (int[][] arr2 : newDp) {
                for (int[] arr1 : arr2) {
                    Arrays.fill(arr1, NEG);
                }
            }

            for (int prevProfile = 0; prevProfile < profileSize; prevProfile++) {
                for (int iu = 0; iu <= introvertsCount; iu++) {
                    for (int eu = 0; eu <= extrovertsCount; eu++) {
                        if (dp[prevProfile][iu][eu] == NEG) continue;

                        int baseHappiness = dp[prevProfile][iu][eu];

                        // Try every possible profile for this column
                        for (int newProfile = 0; newProfile < profileSize; newProfile++) {
                            int newIu = iu + introCount[newProfile];
                            int newEu = eu + extroCount[newProfile];
                            if (newIu > introvertsCount || newEu > extrovertsCount) continue;

                            int happiness = baseHappiness
                                          + internalHappiness[newProfile]
                                          + crossHappiness[prevProfile][newProfile];

                            newDp[newProfile][newIu][newEu] = Math.max(
                                newDp[newProfile][newIu][newEu], happiness);
                        }
                    }
                }
            }

            dp = newDp;
        }

        // Find max over all final states
        int result = 0;
        for (int p = 0; p < profileSize; p++) {
            for (int iu = 0; iu <= introvertsCount; iu++) {
                for (int eu = 0; eu <= extrovertsCount; eu++) {
                    result = Math.max(result, dp[p][iu][eu]);
                }
            }
        }
        return result;
    }

    // Happiness contribution of a pair of adjacent cells
    // a, b ∈ {0, 1, 2} where 0=empty, 1=introvert, 2=extrovert
    private int pairHappiness(int a, int b) {
        if (a == 0 || b == 0) return 0;
        if (a == 1 && b == 1) return -60;   // -30 each
        if (a == 1 && b == 2) return -10;   // -30 + 20
        if (a == 2 && b == 1) return -10;
        if (a == 2 && b == 2) return 40;    // +20 each
        return 0;
    }
}