#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> result;
        vector<string> segments;
        backtrack(s, 0, segments, result);
        return result;
    }
    
private:
    void backtrack(const string& s, int start, vector<string>& segments, vector<string>& result) {
        if (segments.size() == 4) {
            if (start == s.length()) {
                result.push_back(segments[0] + "." + segments[1] + "." + segments[2] + "." + segments[3]);
            }
            return;
        }
        // Try to take 1, 2, or 3 digits for the next segment
        for (int len = 1; len <= 3; ++len) {
            if (start + len > s.length()) break;
            string segment = s.substr(start, len);
            // Check for leading zero
            if (len > 1 && segment[0] == '0') continue;
            // Check value range
            int val = stoi(segment);
            if (val > 255) continue;
            segments.push_back(segment);
            backtrack(s, start + len, segments, result);
            segments.pop_back();
        }
    }
};