class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        int m = name.length(), n = typed.length();
        
        while (i < m && j < n) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            
            // Count consecutive same characters in name
            int countName = 0;
            char c = name.charAt(i);
            while (i < m && name.charAt(i) == c) {
                countName++;
                i++;
            }
            
            // Count consecutive same characters in typed
            int countTyped = 0;
            while (j < n && typed.charAt(j) == c) {
                countTyped++;
                j++;
            }
            
            if (countTyped < countName) {
                return false;
            }
        }
        
        return i == m && j == n;
    }
}