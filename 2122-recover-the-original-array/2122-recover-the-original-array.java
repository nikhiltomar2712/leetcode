class Solution {
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[0];
            if (diff == 0 || diff % 2 != 0) continue;
            int k = diff / 2;
            int[] res = new int[n / 2];
            boolean[] used = new boolean[n];
            int idx = 0;
            boolean ok = true;
            for (int j = 0; j < n && ok; j++) {
                if (used[j]) continue;
                int target = nums[j] + 2 * k;
                int pos = -1;
                for (int p = j + 1; p < n; p++) {
                    if (!used[p] && nums[p] == target) {
                        pos = p;
                        break;
                    }
                }
                if (pos == -1) {
                    ok = false;
                    break;
                }
                used[j] = true;
                used[pos] = true;
                res[idx++] = nums[j] + k;
            }
            if (ok && idx == n / 2) return res;
        }
        return new int[0];
    }
}