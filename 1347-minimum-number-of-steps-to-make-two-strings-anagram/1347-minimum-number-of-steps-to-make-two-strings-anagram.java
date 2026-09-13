class Solution {
    public int minSteps(String s, String t) {
        int[] count = new int[26];
        
        // Count frequency difference: +1 for s, -1 for t
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        
        // Sum up the positive differences (excess in s = deficit in t)
        int steps = 0;
        for (int diff : count) {
            if (diff > 0) {
                steps += diff;
            }
        }
        
        return steps;
    }
}