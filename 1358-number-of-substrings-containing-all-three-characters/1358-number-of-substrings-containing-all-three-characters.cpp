class Solution {
public:
    int numberOfSubstrings(string s) {
        int count[3] = {0, 0, 0}; // Frequency of 'a', 'b', 'c'
        int left = 0;
        int total = 0;
        int n = s.length();
        
        for (int right = 0; right < n; right++) {
            // Expand window by including s[right]
            count[s[right] - 'a']++;
            
            // Shrink window from the left while it contains all three characters
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                // All substrings starting from 'left' up to 'right' are valid
                // Number of such substrings = n - right
                total += n - right;
                
                // Remove s[left] from window and move left forward
                count[s[left] - 'a']--;
                left++;
            }
        }
        
        return total;
    }
};