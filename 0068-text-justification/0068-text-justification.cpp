#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> result;
        int n = words.size();
        int i = 0;
        while (i < n) {
            int j = i;
            int lineLen = 0;
            // Greedy: pack as many words as possible
            while (j < n && lineLen + words[j].size() + (j - i) <= maxWidth) {
                lineLen += words[j].size();
                ++j;
            }
            int wordCount = j - i;
            int totalSpaces = maxWidth - lineLen;
            string line;
            if (j == n) {
                // Last line: left justified
                for (int k = i; k < j; ++k) {
                    line += words[k];
                    if (k < j - 1) line += ' ';
                }
                line += string(maxWidth - line.size(), ' ');
            } else if (wordCount == 1) {
                // Single word: left justified
                line = words[i] + string(totalSpaces, ' ');
            } else {
                // Fully justified
                int spacesBetween = totalSpaces / (wordCount - 1);
                int extra = totalSpaces % (wordCount - 1);
                for (int k = i; k < j; ++k) {
                    line += words[k];
                    if (k < j - 1) {
                        int spaces = spacesBetween + (extra > 0 ? 1 : 0);
                        line += string(spaces, ' ');
                        if (extra > 0) --extra;
                    }
                }
            }
            result.push_back(line);
            i = j;
        }
        return result;
    }
};