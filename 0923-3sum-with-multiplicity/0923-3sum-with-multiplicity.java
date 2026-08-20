class Solution {
    public int threeSumMulti(int[] arr, int target) {
        final int MOD = 1_000_000_007;
        long[] count = new long[101];
        
        for (int num : arr) {
            count[num]++;
        }
        
        long ans = 0;
        
        for (int i = 0; i <= 100; i++) {
            for (int j = i; j <= 100; j++) {
                int k = target - i - j;
                if (k < 0 || k > 100) continue;
                if (k < j) continue;   // enforce i ≤ j ≤ k to avoid duplicates
                
                if (i == j && j == k) {
                    // C(count[i], 3)
                    ans += count[i] * (count[i] - 1) * (count[i] - 2) / 6;
                } else if (i == j && j != k) {
                    // C(count[i], 2) * count[k]
                    ans += count[i] * (count[i] - 1) / 2 * count[k];
                } else if (i != j && j == k) {
                    // count[i] * C(count[j], 2)
                    ans += count[i] * count[j] * (count[j] - 1) / 2;
                } else {
                    // all distinct: count[i] * count[j] * count[k]
                    ans += count[i] * count[j] * count[k];
                }
            }
        }
        
        return (int) (ans % MOD);
    }
}