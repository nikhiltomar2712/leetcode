#include <vector>
#include <string>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> result;
        if (words.empty() || s.empty()) return result;
        
        int wordLen = words[0].length();
        int wordCount = words.size();
        int totalLen = wordLen * wordCount;
        if (s.length() < totalLen) return result;
        
        unordered_map<string, int> wordFreq;
        for (const string& w : words) {
            wordFreq[w]++;
        }
        
        // Slide over each possible starting offset modulo wordLen
        for (int offset = 0; offset < wordLen; ++offset) {
            unordered_map<string, int> seen;
            int left = offset;
            int matched = 0;
            
            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                string curr = s.substr(right, wordLen);
                
                if (wordFreq.count(curr)) {
                    seen[curr]++;
                    if (seen[curr] == wordFreq[curr]) {
                        matched++;
                    }
                    
                    // Shrink window if we have more than needed
                    while (seen[curr] > wordFreq[curr]) {
                        string leftWord = s.substr(left, wordLen);
                        if (seen[leftWord] == wordFreq[leftWord]) {
                            matched--;
                        }
                        seen[leftWord]--;
                        left += wordLen;
                    }
                    
                    // If we have matched all words, record starting index
                    if (matched == wordFreq.size()) {
                        result.push_back(left);
                        // Move left one word to continue searching
                        string leftWord = s.substr(left, wordLen);
                        if (seen[leftWord] == wordFreq[leftWord]) {
                            matched--;
                        }
                        seen[leftWord]--;
                        left += wordLen;
                    }
                } else {
                    // Reset window because current word is not in words
                    seen.clear();
                    matched = 0;
                    left = right + wordLen;
                }
            }
        }
        return result;
    }
};