#include <map>
#include <vector>
using namespace std;

class SummaryRanges {
private:
    map<int, int> intervals; // start -> end

public:
    SummaryRanges() {}
    
    void addNum(int value) {
        auto it = intervals.upper_bound(value);
        // Check if value is already inside an interval
        if (it != intervals.begin()) {
            auto prev_it = prev(it);
            if (prev_it->second >= value) return; // already covered
            // Try to extend previous interval
            if (prev_it->second == value - 1) {
                prev_it->second = value;
                // Merge with next if possible
                if (it != intervals.end() && it->first == value + 1) {
                    prev_it->second = it->second;
                    intervals.erase(it);
                }
                return;
            }
        }
        // Try to merge with next interval (extend backwards)
        if (it != intervals.end() && it->first == value + 1) {
            int new_end = it->second;
            intervals.erase(it);
            intervals[value] = new_end;
            return;
        }
        // Create new single-element interval
        intervals[value] = value;
    }
    
    vector<vector<int>> getIntervals() {
        vector<vector<int>> res;
        for (const auto& p : intervals) {
            res.push_back({p.first, p.second});
        }
        return res;
    }
};