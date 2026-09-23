import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {
        long total = 0;
        for (int num : nums) total += num;
        int target = (int) (total % p);
        if (target == 0) return 0;

        Map<Integer, Integer> lastIndex = new HashMap<>();
        lastIndex.put(0, -1); // prefix mod before index 0

        long prefix = 0;
        int minLen = nums.length;

        for (int i = 0; i < nums.length; i++) {
            prefix = (prefix + nums[i]) % p;
            int need = (int) ((prefix - target + p) % p);

            if (lastIndex.containsKey(need)) {
                minLen = Math.min(minLen, i - lastIndex.get(need));
            }
            lastIndex.put((int) prefix, i);
        }

        return minLen < nums.length ? minLen : -1;
    }
}