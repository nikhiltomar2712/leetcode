class Solution {
public:
    int findLUSlength(vector<string>& strs) {
        unordered_set<string> seen, duplicates;
        
        // Find duplicates
        for (const string& str : strs) {
            if (seen.count(str)) {
                duplicates.insert(str);
            } else {
                seen.insert(str);
            }
        }
        
        // Sort by length descending
        sort(strs.begin(), strs.end(), [](const string& a, const string& b) {
            return a.length() > b.length();
        });
        
        for (int i = 0; i < strs.size(); ++i) {
            if (duplicates.count(strs[i])) continue;
            
            bool isSubseq = false;
            for (int j = 0; j < i; ++j) {
                if (isSubsequence(strs[i], strs[j])) {
                    isSubseq = true;
                    break;
                }
            }
            
            if (!isSubseq) {
                return strs[i].length();
            }
        }
        
        return -1;
    }
    
private:
    // Check if a is subsequence of b
    bool isSubsequence(const string& a, const string& b) {
        int i = 0;
        for (char c : b) {
            if (i < a.length() && c == a[i]) {
                ++i;
            }
        }
        return i == a.length();
    }
};