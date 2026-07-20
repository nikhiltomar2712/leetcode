class Solution {
    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;
        
        while (i < n) {
            // Skip spaces
            while (i < n && chars[i] == ' ') {
                i++;
            }
            
            // Find end of current word
            int j = i;
            while (j < n && chars[j] != ' ') {
                j++;
            }
            
            // Reverse the word from i to j-1
            reverse(chars, i, j - 1);
            
            // Move to next word
            i = j + 1;
        }
        
        return new String(chars);
    }
    
    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }
}