class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        // Build adjacency list
        vector<vector<pair<int, int>>> graph(n + 1);
        for (auto& road : roads) {
            int u = road[0], v = road[1], dist = road[2];
            graph[u].push_back({v, dist});
            graph[v].push_back({u, dist});
        }
        
        // BFS to find all nodes reachable from node 1
        vector<bool> visited(n + 1, false);
        queue<int> q;
        int minEdge = INT_MAX;
        
        q.push(1);
        visited[1] = true;
        
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            
            for (auto& [neighbor, weight] : graph[node]) {
                // Update minimum edge weight seen
                minEdge = min(minEdge, weight);
                
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.push(neighbor);
                }
            }
        }
        
        return minEdge;
    }
};