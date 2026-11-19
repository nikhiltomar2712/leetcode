class Solution {
    public int valueAfterKSeconds(int n, int k) {
        int mod = 1000000007;
        int[] a = new int[n];
        Arrays.fill(a, 1);
        for (int t = 0; t < k; t++) {
            for (int i = 1; i < n; i++) {
                a[i] = (a[i] + a[i - 1]) % mod;
            }
        }
        return a[n - 1];
    }
}