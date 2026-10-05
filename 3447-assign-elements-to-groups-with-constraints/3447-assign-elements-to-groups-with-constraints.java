class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < elements.length; i++) {
            map.putIfAbsent(elements[i], i);
        }
        int[] res = new int[groups.length];
        for (int i = 0; i < groups.length; i++) {
            int g = groups[i];
            int best = Integer.MAX_VALUE;
            for (int d = 1; d * d <= g; d++) {
                if (g % d == 0) {
                    if (map.containsKey(d)) best = Math.min(best, map.get(d));
                    if (map.containsKey(g / d)) best = Math.min(best, map.get(g / d));
                }
            }
            res[i] = best == Integer.MAX_VALUE ? -1 : best;
        }
        return res;
    }
}