/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *   public:
 *     int guess(string word);
 * };
 */

class Solution {
public:
    void findSecretWord(vector<string>& words, Master& master) {
        auto match = [](const string& a, const string& b) {
            int cnt = 0;
            for (int i = 0; i < 6; ++i)
                if (a[i] == b[i]) ++cnt;
            return cnt;
        };

        vector<string> candidates = words;

        while (!candidates.empty()) {
            // Build frequency table: position × character
            int freq[6][26] = {};
            for (const string& w : candidates) {
                for (int i = 0; i < 6; ++i) {
                    freq[i][w[i] - 'a']++;
                }
            }

            // Choose the word with the highest total frequency score
            string best;
            int bestScore = -1;
            for (const string& w : candidates) {
                int score = 0;
                for (int i = 0; i < 6; ++i) {
                    score += freq[i][w[i] - 'a'];
                }
                if (score > bestScore) {
                    bestScore = score;
                    best = w;
                }
            }

            int matches = master.guess(best);
            if (matches == 6) return;

            // Filter candidates
            vector<string> next;
            for (const string& w : candidates) {
                if (match(best, w) == matches) {
                    next.push_back(w);
                }
            }
            candidates = move(next);
        }
    }
};