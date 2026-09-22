class Solution {
    public int longestAwesome(String s) {
        int n = s.length();
        int[] first = new int[1 << 10];
        Arrays.fill(first, n);
        first[0] = -1;

        int mask = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            mask ^= (1 << d);

            ans = Math.max(ans, i - first[mask]);

            for (int b = 0; b < 10; b++) {
                ans = Math.max(ans, i - first[mask ^ (1 << b)]);
            }

            first[mask] = Math.min(first[mask], i);
        }
        return ans;
    }
}