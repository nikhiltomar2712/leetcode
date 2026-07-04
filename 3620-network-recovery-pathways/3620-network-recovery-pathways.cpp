#include <bits/stdc++.h>
using namespace std;

using ll = long long;

class Solution {
public:
    int findMaxPathScore(vector<vector<int>>& edges, vector<bool>& online, long long k) {
        int n = online.size();
        if (n == 1) return 0; // edge case if n==1
        
        // Build graph: only consider online nodes (except source/sink already guaranteed)
        vector<vector<pair<int, int>>> graph(n);
        int minCost = INT_MAX, maxCost = 0;
        
        for (auto& e : edges) {
            int u = e[0], v = e[1], c = e[2];
            if ((u == 0 || online[u]) && (v == n-1 || online[v])) {
                graph[u].emplace_back(v, c);
                minCost = min(minCost, c);
                maxCost = max(maxCost, c);
            }
        }
        
        // Binary search on the minimum edge weight (bottleneck)
        int left = 0, right = maxCost + 1;
        int answer = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Check if there exists a path where min-edge >= mid and total cost <= k
            if (canReach(graph, n, mid, k)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
private:
    // Check function: Dijkstra for shortest path with edges >= threshold
    bool canReach(const vector<vector<pair<int,int>>>& graph, int n, int threshold, long long k) {
        vector<ll> dist(n, LLONG_MAX / 2);
        dist[0] = 0;
        
        priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> pq;
        pq.emplace(0, 0);
        
        while (!pq.empty()) {
            auto [cost, u] = pq.top(); 
            pq.pop();
            
            if (cost > dist[u]) continue;
            if (u == n - 1) return cost <= k;
            
            for (auto& [v, w] : graph[u]) {
                if (w < threshold) continue; // only edges >= threshold
                
                ll newCost = cost + w;
                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    pq.emplace(newCost, v);
                }
            }
        }
        
        return dist[n-1] <= k;
    }
};