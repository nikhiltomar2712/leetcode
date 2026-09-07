class Solution {
    public int maxEqualFreq(int[] nums) {
        int n = nums.length;
        // freq[value] = frequency of value
        int[] freq = new int[100001]; // Max value is 10^5
        // countFreq[f] = number of values with frequency f
        int[] countFreq = new int[100001];
        
        int maxFreq = 0;
        int longest = 0;
        
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // Update frequencies
            if (freq[num] > 0) {
                countFreq[freq[num]]--;
            }
            freq[num]++;
            maxFreq = Math.max(maxFreq, freq[num]);
            countFreq[freq[num]]++;
            
            int currentLength = i + 1;
            
            // Check if current prefix is valid
            // Case 1: All numbers appear once
            if (maxFreq == 1) {
                longest = currentLength;
                continue;
            }
            
            // Case 2: One number appears once, rest appear maxFreq times
            if (countFreq[1] == 1 && countFreq[maxFreq] * maxFreq + 1 == currentLength) {
                longest = currentLength;
                continue;
            }
            
            // Case 3: One number appears maxFreq times, rest appear maxFreq-1 times
            if (countFreq[maxFreq] == 1 && countFreq[maxFreq - 1] * (maxFreq - 1) + maxFreq == currentLength) {
                longest = currentLength;
                // continue;
            }
        }
        
        return longest;
    }
}