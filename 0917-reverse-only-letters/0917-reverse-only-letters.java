class Solution {
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        
        while (left < right) {
            // Skip non-letters from the left
            if (!Character.isLetter(chars[left])) {
                left++;
                continue;
            }
            // Skip non-letters from the right
            if (!Character.isLetter(chars[right])) {
                right--;
                continue;
            }
            // Both are letters - swap them
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            
            left++;
            right--;
        }
        
        return new String(chars);
    }
}