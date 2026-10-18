class Solution {
    public int countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                idx = i;
                break;
            }
        }
        Map<Integer, Integer> leftMap = new HashMap<>();
        int balance = 0;
        leftMap.put(0, 1);
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[i] < k) balance--;
            else balance++;
            leftMap.put(balance, leftMap.getOrDefault(balance, 0) + 1);
        }
        long res = 0;
        balance = 0;
        res += leftMap.getOrDefault(0, 0) + leftMap.getOrDefault(1, 0);
        for (int i = idx + 1; i < n; i++) {
            if (nums[i] < k) balance--;
            else balance++;
            res += leftMap.getOrDefault(-balance, 0) + leftMap.getOrDefault(-balance + 1, 0);
        }
        return (int) res;
    }
}