class Solution {
    public int minFlipsMonoIncr(String s) {
        int ones = 0;   // number of 1s seen so far
        int flips = 0;  // min flips for current prefix
        
        for (char c : s.toCharArray()) {
            if (c == '1') {
                ones++;
            } else {
                // Option 1: flip this 0 → 1
                // Option 2: keep this 0 and flip all previous 1s
                flips = Math.min(flips + 1, ones);
            }
        }
        return flips;
    }
}