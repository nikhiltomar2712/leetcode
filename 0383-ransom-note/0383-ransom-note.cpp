#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool canConstruct(string ransomNote, string magazine) {
        vector<int> count(26, 0);
        
        // Count frequencies in magazine
        for (char c : magazine) {
            count[c - 'a']++;
        }
        
        // Check if ransomNote can be constructed
        for (char c : ransomNote) {
            if (count[c - 'a'] == 0) {
                return false;
            }
            count[c - 'a']--;
        }
        
        return true;
    }
};