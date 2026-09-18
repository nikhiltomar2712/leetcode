class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        Map<Integer, Integer> full = new HashMap<>();   // lake -> day it was filled
        TreeSet<Integer> dryDays = new TreeSet<>();     // sorted available dry days

        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                dryDays.add(i);
            } else {
                int lake = rains[i];
                if (full.containsKey(lake)) {
                    Integer dry = dryDays.ceiling(full.get(lake));
                    if (dry == null || dry > i) {
                        return new int[0];
                    }
                    dryDays.remove(dry);
                    ans[dry] = lake;
                }
                full.put(lake, i);
            }
        }

        // Unused dry days: any valid lake works, use 1
        for (int d : dryDays) {
            ans[d] = 1;
        }

        return ans;
    }
}