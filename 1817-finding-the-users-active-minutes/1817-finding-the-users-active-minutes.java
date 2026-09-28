class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            map.computeIfAbsent(log[0], x -> new HashSet<>()).add(log[1]);
        }
        int[] res = new int[k];
        for (Set<Integer> set : map.values()) {
            int uam = set.size();
            if (uam <= k) res[uam - 1]++;
        }
        return res;
    }
}