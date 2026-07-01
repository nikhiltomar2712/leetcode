class Solution {
public:
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> result(n, -1);
        stack<int> st; // stores indices
        
        // Traverse the array twice to handle circular nature
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            
            // While stack is not empty and current element is greater than
            // the element at the index stored at stack top
            while (!st.empty() && nums[st.top()] < nums[idx]) {
                result[st.top()] = nums[idx];
                st.pop();
            }
            
            // Only push indices from the first traversal
            if (i < n) {
                st.push(idx);
            }
        }
        
        return result;
    }
};