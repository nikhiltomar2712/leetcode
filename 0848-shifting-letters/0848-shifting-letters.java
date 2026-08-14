class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        // Calculate suffix sums (total shifts for each position)
        long totalShift = 0;
        for (int i = n - 1; i >= 0; i--) {
            totalShift = (totalShift + shifts[i]) % 26;
            // Apply shift to current character
            int newChar = (chars[i] - 'a' + (int)totalShift) % 26;
            chars[i] = (char)(newChar + 'a');
        }
        
        return new String(chars);
    }
}