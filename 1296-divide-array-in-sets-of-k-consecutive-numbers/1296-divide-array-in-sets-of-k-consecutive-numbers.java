class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;
        
        // Frequency map (TreeMap keeps keys sorted)
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        while (!freq.isEmpty()) {
            int start = freq.firstKey();  // smallest remaining number
            
            // Try to form a consecutive group of size k starting from 'start'
            for (int i = 0; i < k; i++) {
                int curr = start + i;
                if (!freq.containsKey(curr)) {
                    return false;
                }
                int count = freq.get(curr);
                if (count == 1) {
                    freq.remove(curr);
                } else {
                    freq.put(curr, count - 1);
                }
            }
        }
        
        return true;
    }
}