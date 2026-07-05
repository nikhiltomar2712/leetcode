class Solution {
public:
    int magicalString(int n) {
        if (n == 0) return 0;
        if (n <= 3) return 1;
        
        string s = "122";
        int i = 2; // Pointer for frequency
        
        while (s.length() < n) {
            // Append next character based on frequency
            char next = s.back() == '1' ? '2' : '1';
            int freq = s[i] - '0';
            s.append(freq, next);
            i++;
        }
        
        // Count '1's in first n characters
        return count(s.begin(), s.begin() + n, '1');
    }
};