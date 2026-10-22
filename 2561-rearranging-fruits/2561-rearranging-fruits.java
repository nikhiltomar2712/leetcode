class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int b : basket1) count.merge(b, 1, Integer::sum);
        for (int b : basket2) count.merge(b, -1, Integer::sum);
        int minVal = Integer.MAX_VALUE;
        for (int b : basket1) minVal = Math.min(minVal, b);
        for (int b : basket2) minVal = Math.min(minVal, b);
        List<Integer> diffs = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            int c = e.getValue();
            if (c % 2 != 0) return -1;
            for (int i = 0; i < Math.abs(c) / 2; i++) {
                diffs.add(e.getKey());
            }
        }
        Collections.sort(diffs);
        long cost = 0;
        for (int i = 0; i < diffs.size() / 2; i++) {
            cost += Math.min(diffs.get(i), 2 * minVal);
        }
        return cost;
    }
}