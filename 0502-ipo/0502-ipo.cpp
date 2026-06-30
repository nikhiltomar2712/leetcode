class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        int n = profits.size();
        
        // Create pairs of (capital, profit) for sorting
        vector<pair<int, int>> projects;
        for (int i = 0; i < n; i++) {
            projects.push_back({capital[i], profits[i]});
        }
        
        // Sort by capital requirement (ascending)
        sort(projects.begin(), projects.end());
        
        // Max heap to store profits of affordable projects
        priority_queue<int> maxHeap;
        
        int index = 0;  // Pointer to iterate through sorted projects
        
        // Complete at most k projects
        for (int i = 0; i < k; i++) {
            // Add all projects we can now afford to the max heap
            while (index < n && projects[index].first <= w) {
                maxHeap.push(projects[index].second);
                index++;
            }
            
            // If no projects are available, break
            if (maxHeap.empty()) {
                break;
            }
            
            // Take the most profitable project
            w += maxHeap.top();
            maxHeap.pop();
        }
        
        return w;
    }
};