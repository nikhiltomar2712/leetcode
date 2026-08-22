class Solution {
public:
    int tallestBillboard(vector<int>& rods) {
        // dp[diff] = max sum of the smaller side achievable for this diff
        // diff = taller_side - shorter_side
        unordered_map<int, int> dp;
        dp[0] = 0;
        
        for (int rod : rods) {
            unordered_map<int, int> newDp = dp; // carry forward "skip this rod"
            
            for (auto& [diff, shorter] : dp) {
                int taller = diff + shorter;
                
                // Add rod to the taller side -> diff increases
                int newDiff1 = diff + rod;
                int newShorter1 = shorter; // shorter side unchanged
                if (newDp.find(newDiff1) == newDp.end() || newDp[newDiff1] < newShorter1)
                    newDp[newDiff1] = newShorter1;
                
                // Add rod to the shorter side
                int newTaller2 = taller;
                int newShorter2 = shorter + rod;
                int newDiff2 = abs(newTaller2 - newShorter2);
                int newShorterVal2 = min(newTaller2, newShorter2);
                if (newDp.find(newDiff2) == newDp.end() || newDp[newDiff2] < newShorterVal2)
                    newDp[newDiff2] = newShorterVal2;
            }
            
            dp = newDp;
        }
        
        return dp.count(0) ? dp[0] : 0;
    }
};