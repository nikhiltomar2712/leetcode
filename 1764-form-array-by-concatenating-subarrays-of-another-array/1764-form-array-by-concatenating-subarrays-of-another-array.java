class Solution {
    public boolean canChoose(int[][] groups, int[] nums) {
        int i = 0;
        for (int[] g : groups) {
            boolean found = false;
            while (i + g.length <= nums.length) {
                boolean match = true;
                for (int j = 0; j < g.length; j++) {
                    if (nums[i + j] != g[j]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    i += g.length;
                    found = true;
                    break;
                }
                i++;
            }
            if (!found) return false;
        }
        return true;
    }
}