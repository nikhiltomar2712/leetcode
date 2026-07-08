#include <string>
#include <unordered_map>
#include <algorithm>
using namespace std;

class Solution {
public:
    bool isScramble(string s1, string s2) {
        if (s1 == s2) return true;
        int n = s1.length();
        // Check if they have same characters
        string t1 = s1, t2 = s2;
        sort(t1.begin(), t1.end());
        sort(t2.begin(), t2.end());
        if (t1 != t2) return false;
        
        // Memoization key
        string key = s1 + "#" + s2;
        if (memo.find(key) != memo.end()) return memo[key];
        
        for (int i = 1; i < n; ++i) {
            // No swap
            if (isScramble(s1.substr(0, i), s2.substr(0, i)) &&
                isScramble(s1.substr(i), s2.substr(i))) {
                memo[key] = true;
                return true;
            }
            // Swap
            if (isScramble(s1.substr(0, i), s2.substr(n - i)) &&
                isScramble(s1.substr(i), s2.substr(0, n - i))) {
                memo[key] = true;
                return true;
            }
        }
        memo[key] = false;
        return false;
    }
    
private:
    unordered_map<string, bool> memo;
};