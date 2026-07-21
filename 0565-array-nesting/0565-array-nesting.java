class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int len = 0;
                int x = i;
                while (!visited[x]) {
                    visited[x] = true;
                    x = nums[x];
                    len++;
                }
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}