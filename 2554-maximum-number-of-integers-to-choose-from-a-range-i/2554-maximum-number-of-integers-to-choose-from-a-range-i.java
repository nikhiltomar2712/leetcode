class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set = new HashSet<>();
        for (int b : banned) set.add(b);
        int count = 0, sum = 0;
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) continue;
            if (sum + i > maxSum) break;
            sum += i;
            count++;
        }
        return count;
    }
}