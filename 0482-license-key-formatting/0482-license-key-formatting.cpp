class Solution {
public:
    string licenseKeyFormatting(string s, int k) {
        string result;
        
        // Process from right to left for easier grouping
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s[i] != '-') {
                // Add a dash when we've collected k characters
                if (result.length() % (k + 1) == k) {
                    result += '-';
                }
                // Add the character (converted to uppercase)
                result += toupper(s[i]);
            }
        }
        
        // Reverse to get the correct order
        reverse(result.begin(), result.end());
        
        return result;
    }
};