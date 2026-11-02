class Solution {
    public int minChanges(int[] nums, int k) {
        final int MAX = 1 << 10;          // nums[i] < 2^10
        final int INF = 1 << 30;

        // cnt[i] = frequency map of values that appear at positions ≡ i (mod k)
        Map<Integer, Integer>[] cnt = new Map[k];
        int[] size = new int[k];          // size of each residue class
        Arrays.setAll(cnt, i -> new HashMap<>());

        for (int i = 0; i < nums.length; i++) {
            int r = i % k;
            cnt[r].merge(nums[i], 1, Integer::sum);
            size[r]++;
        }

        // f[x] = minimum changes so that the XOR of the values chosen
        //        for the processed residue classes equals x
        int[] f = new int[MAX];
        Arrays.fill(f, INF);
        f[0] = 0;

        for (int i = 0; i < k; i++) {
            int[] g = new int[MAX];
            int minF = Arrays.stream(f).min().getAsInt();
            Arrays.fill(g, minF + size[i]);   // worst case: change everything in this class

            // try every value that actually appears in this residue class
            for (int x = 0; x < MAX; x++) {
                for (var e : cnt[i].entrySet()) {
                    int v = e.getKey();
                    int c = e.getValue();
                    // keep the c occurrences of v, change the rest
                    g[x] = Math.min(g[x], f[x ^ v] + size[i] - c);
                }
            }
            f = g;
        }
        return f[0];
    }
}