class Solution {
public:
    long long maximumSum(vector<int>& nums, int m, int l, int r) {
        int n = nums.size();
        vector<long long> pref(n + 1, 0);
        for (int i = 0; i < n; ++i) pref[i + 1] = pref[i] + nums[i];

        const long long NEG = LLONG_MIN / 4;

        // Better of two (value, count) states:
        //  - higher value wins
        //  - on tie, fewer count wins (keeps binary-search monotone)
        auto better = [](pair<long long,int> a, pair<long long,int> b) {
            if (a.first != b.first) return a.first > b.first ? a : b;
            return a.second < b.second ? a : b;
        };

        // check(lambda):
        //   dp0[i] = best value using first i elements with 0 subarrays chosen  (only dp0[?] = 0 if we skip all — but we forbid this at the end)
        //   dp1[i] = best value using first i elements with >= 1 subarray chosen
        // We only need dp1[n] as the final state, but transitions to dp1 require dp0 (to start the first subarray).
        auto check = [&](long long lambda) -> pair<long long,int> {
            vector<pair<long long,int>> dp0(n + 1, {NEG, 0});
            vector<pair<long long,int>> dp1(n + 1, {NEG, 0});
            dp0[0] = {0, 0};

            // Deque over dp0[j] for starting a FIRST subarray
            deque<int> dq0;
            // Deque over dp1[j] for extending a chain of subarrays
            deque<int> dq1;

            auto key0 = [&](int j) { return make_pair(dp0[j].first - pref[j], dp0[j].second); };
            auto key1 = [&](int j) { return make_pair(dp1[j].first - pref[j], dp1[j].second); };

            auto pushClean = [&](deque<int>& dq, int j, auto keyFn) {
                auto v = keyFn(j);
                while (!dq.empty()) {
                    auto b = keyFn(dq.back());
                    if (v.first > b.first || (v.first == b.first && v.second <= b.second))
                        dq.pop_back();
                    else break;
                }
                dq.push_back(j);
            };

            for (int i = 1; i <= n; ++i) {
                int j = i - l;
                if (j >= 0) {
                    if (dp0[j].first > NEG) pushClean(dq0, j, key0);
                    if (dp1[j].first > NEG) pushClean(dq1, j, key1);
                }
                while (!dq0.empty() && dq0.front() < i - r) dq0.pop_front();
                while (!dq1.empty() && dq1.front() < i - r) dq1.pop_front();

                // dp0: skipping everything (must remain 0 only for the prefix-skip state)
                dp0[i] = dp0[i - 1];

                // dp1: propagate skip of i
                dp1[i] = dp1[i - 1];

                // Start first subarray ending at i (from dp0[j])
                if (!dq0.empty()) {
                    int k = dq0.front();
                    long long val = dp0[k].first + (pref[i] - pref[k]) - lambda;
                    int cnt = dp0[k].second + 1;
                    dp1[i] = better(dp1[i], { val, cnt });
                }
                // Extend / start another subarray ending at i (from dp1[j])
                if (!dq1.empty()) {
                    int k = dq1.front();
                    long long val = dp1[k].first + (pref[i] - pref[k]) - lambda;
                    int cnt = dp1[k].second + 1;
                    dp1[i] = better(dp1[i], { val, cnt });
                }
            }
            return dp1[n];   // must have >= 1 subarray
        };

        // Base case: no penalty
        auto base = check(0);
        if (base.second <= m) return base.first;

        // Binary search penalty so that count <= m
        long long lo = 0, hi = (long long)1e15;
        while (lo < hi) {
            long long mid = lo + (hi - lo) / 2;
            auto res = check(mid);
            if (res.second <= m) hi = mid;
            else lo = mid + 1;
        }

        auto res = check(lo);
        return res.first + (long long)m * lo;
    }
};