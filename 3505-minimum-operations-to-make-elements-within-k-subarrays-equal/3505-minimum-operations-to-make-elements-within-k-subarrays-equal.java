import java.util.*;

class Solution {
    public long minOperations(int[] nums, int x, int k) {
        List<Long> minOps = getMinOps(nums, x);
        Long[][] mem = new Long[nums.length + 1][k + 1];
        return dfs(nums, x, 0, k, minOps, mem);
    }

    private static final long INF = Long.MAX_VALUE / 2;

    // min cost starting from index i, still need to pick `remain` windows
    private long dfs(int[] nums, int x, int i, int remain, List<Long> minOps, Long[][] mem) {
        if (remain == 0) return 0;
        if (i == nums.length) return INF;
        if (mem[i][remain] != null) return mem[i][remain];

        // Option 1: skip current position
        long skip = dfs(nums, x, i + 1, remain, minOps, mem);

        // Option 2: take the window that starts at i
        long pick = INF;
        if (i + x <= nums.length) {
            pick = minOps.get(i) + dfs(nums, x, i + x, remain - 1, minOps, mem);
        }

        return mem[i][remain] = Math.min(skip, pick);
    }

    // cost[i] = min ops to make nums[i .. i+x-1] equal (to its median)
    private List<Long> getMinOps(int[] nums, int x) {
        List<Long> minOps = new ArrayList<>();
        MyMap lower = new MyMap(); // smaller half (contains median)
        MyMap upper = new MyMap(); // larger half

        for (int i = 0; i < nums.length; i++) {
            // add nums[i]
            if (lower.map.isEmpty() || nums[i] <= lower.map.lastKey()) {
                lower.add(nums[i]);
            } else {
                upper.add(nums[i]);
            }

            // remove nums[i-x]
            if (i >= x) {
                int out = nums[i - x];
                if (lower.map.containsKey(out)) {
                    lower.remove(out);
                } else {
                    upper.remove(out);
                }
            }

            // rebalance so that lower.size == upper.size or lower.size == upper.size + 1
            if (lower.size < upper.size) {
                int val = upper.map.firstKey();
                upper.remove(val);
                lower.add(val);
            } else if (lower.size > upper.size + 1) {
                int val = lower.map.lastKey();
                lower.remove(val);
                upper.add(val);
            }

            if (i >= x - 1) {
                int median = lower.map.lastKey();
                long ops = median * (long) lower.size - lower.sum
                         + upper.sum - median * (long) upper.size;
                minOps.add(ops);
            }
        }
        return minOps;
    }

    // helper multiset with sum & size
    static class MyMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.merge(val, 1, Integer::sum);
            sum += val;
            size++;
        }

        void remove(int val) {
            int cnt = map.get(val);
            if (cnt == 1) map.remove(val);
            else map.put(val, cnt - 1);
            sum -= val;
            size--;
        }
    }
}