class Solution {
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int n = nums.size();
        int res = 0;
        if (nums.get(0) > 0) res++;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < i + 1 && nums.get(i + 1) > i + 1) {
                res++;
            }
        }
        if (nums.get(n - 1) < n) res++;
        return res;
    }
}