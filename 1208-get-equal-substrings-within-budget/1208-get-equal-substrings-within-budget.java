class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int currentCost = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add cost of converting current character
            currentCost += Math.abs(s.charAt(right) - t.charAt(right));

            // Shrink window from left if cost exceeds budget
            while (currentCost > maxCost) {
                currentCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Update maximum valid length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}