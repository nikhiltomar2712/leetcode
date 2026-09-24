import java.util.*;

class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }
        int[] freq = new int[freqMap.size()];
        int idx = 0;
        for (int f : freqMap.values()) {
            freq[idx++] = f;
        }
        Arrays.sort(quantity);
        return backtrack(freq, quantity, quantity.length - 1);
    }

    private boolean backtrack(int[] freq, int[] quantity, int qIdx) {
        if (qIdx < 0) return true;

        for (int i = 0; i < freq.length; i++) {
            // Skip duplicate frequency values to avoid redundant work
            if (i > 0 && freq[i] == freq[i - 1]) continue;

            if (freq[i] >= quantity[qIdx]) {
                freq[i] -= quantity[qIdx];
                if (backtrack(freq, quantity, qIdx - 1)) {
                    return true;
                }
                freq[i] += quantity[qIdx];
            }
        }
        return false;
    }
}