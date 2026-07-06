#include <unordered_map>
#include <algorithm>
#include <string>

class Solution {
public:
    int lengthOfLongestSubstring(std::string s) {
        std::unordered_map<char, int> char_index;
        int left = 0;
        int max_length = 0;
        
        for (int right = 0; right < s.length(); ++right) {
            auto it = char_index.find(s[right]);
            if (it != char_index.end() && it->second >= left) {
                left = it->second + 1;
            }
            char_index[s[right]] = right;
            max_length = std::max(max_length, right - left + 1);
        }
        
        return max_length;
    }
};