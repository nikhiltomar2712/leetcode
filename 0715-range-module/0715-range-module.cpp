#include <map>
#include <vector>

class RangeModule {
private:
    std::map<int, int> intervals;
    
public:
    RangeModule() {}
    
    void addRange(int left, int right) {
        if (left >= right) return;
        
        auto it = intervals.lower_bound(left);
        
        if (it != intervals.begin()) {
            auto prev = std::prev(it);
            if (prev->second >= left) {
                left = std::min(left, prev->first);
                right = std::max(right, prev->second);
                intervals.erase(prev);
                it = intervals.lower_bound(left);
            }
        }
        
        while (it != intervals.end() && it->first <= right) {
            right = std::max(right, it->second);
            it = intervals.erase(it);
        }
        
        intervals[left] = right;
    }
    
    bool queryRange(int left, int right) {
        if (left >= right) return true;
        
        auto it = intervals.upper_bound(left);
        if (it != intervals.begin()) {
            auto prev = std::prev(it);
            if (prev->first <= left && prev->second >= right) {
                return true;
            }
        }
        return false;
    }
    
    void removeRange(int left, int right) {
        if (left >= right) return;
        
        auto it = intervals.lower_bound(left);
        
        // Handle previous interval if it overlaps
        if (it != intervals.begin()) {
            auto prev = std::prev(it);
            if (prev->second > left) {
                int prevStart = prev->first;
                int prevEnd = prev->second;
                intervals.erase(prev);
                
                // Keep the left part [prevStart, left)
                if (prevStart < left) {
                    intervals[prevStart] = left;
                }
                
                // Keep the right part [right, prevEnd) if it exists
                if (right < prevEnd) {
                    intervals[right] = prevEnd;
                }
                
                // Re-get iterator
                it = intervals.lower_bound(left);
            }
        }
        
        // Remove/trim intervals that overlap [left, right)
        while (it != intervals.end() && it->first < right) {
            if (it->second <= right) {
                // This interval is completely inside removal range
                it = intervals.erase(it);
            } else {
                // This interval extends beyond right, keep the right part
                int rightPartStart = right;
                int rightPartEnd = it->second;
                intervals.erase(it);
                intervals[rightPartStart] = rightPartEnd;
                break; // No more intervals can overlap
            }
        }
    }
};