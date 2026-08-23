class Solution {
public:
    vector<int> prisonAfterNDays(vector<int>& cells, int n) {
        unordered_map<string, int> seen;
        
        while (n > 0) {
            string state;
            for (int c : cells) state += to_string(c);
            
            if (seen.count(state)) {
                // Cycle detected
                int cycleLength = seen[state] - n;
                n %= cycleLength;
            } else {
                seen[state] = n;
            }
            
            if (n > 0) {
                n--;
                cells = nextDay(cells);
            }
        }
        
        return cells;
    }
    
private:
    vector<int> nextDay(const vector<int>& cells) {
        vector<int> next(8, 0);
        for (int i = 1; i < 7; ++i) {
            next[i] = (cells[i - 1] == cells[i + 1]) ? 1 : 0;
        }
        return next;
    }
};