class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int total = 0;
        
        // For each character, find its contribution
        // For each index i, find:
        // - left: distance to previous same character (or start)
        // - right: distance to next same character (or end)
        // Contribution = left * right
        
        // Store positions of each character (0-25 for A-Z)
        int[][] positions = new int[26][2]; // [first occurrence, last occurrence]
        // We need previous and next occurrence for each index
        // Using arrays to store for each character the positions
        
        // Approach 1: Using previous and next arrays
        int[] prev = new int[n];
        int[] next = new int[n];
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        
        // Find previous occurrence for each index
        for (int i = 0; i < n; i++) {
            int charIndex = s.charAt(i) - 'A';
            prev[i] = lastSeen[charIndex];
            lastSeen[charIndex] = i;
        }
        
        // Find next occurrence for each index
        Arrays.fill(lastSeen, n);
        for (int i = n - 1; i >= 0; i--) {
            int charIndex = s.charAt(i) - 'A';
            next[i] = lastSeen[charIndex];
            lastSeen[charIndex] = i;
        }
        
        // Calculate contribution of each character
        for (int i = 0; i < n; i++) {
            // Number of possible start positions = i - prev[i]
            // Number of possible end positions = next[i] - i
            total += (i - prev[i]) * (next[i] - i);
        }
        
        return total;
    }
}