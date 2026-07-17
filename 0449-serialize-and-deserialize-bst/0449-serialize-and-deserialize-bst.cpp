/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
public:
    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        string s;
        serializeHelper(root, s);
        return s;
    }
    
    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if (data.empty()) return nullptr;
        vector<int> nodes;
        stringstream ss(data);
        string token;
        while (ss >> token) {
            nodes.push_back(stoi(token));
        }
        int index = 0;
        return deserializeHelper(nodes, index, INT_MIN, INT_MAX);
    }
    
private:
    void serializeHelper(TreeNode* root, string& s) {
        if (!root) return;
        s += to_string(root->val) + " ";
        serializeHelper(root->left, s);
        serializeHelper(root->right, s);
    }
    
    TreeNode* deserializeHelper(vector<int>& nodes, int& index, int minVal, int maxVal) {
        if (index >= nodes.size()) return nullptr;
        int val = nodes[index];
        if (val < minVal || val > maxVal) return nullptr;
        
        index++;
        TreeNode* root = new TreeNode(val);
        root->left = deserializeHelper(nodes, index, minVal, val);
        root->right = deserializeHelper(nodes, index, val, maxVal);
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec* ser = new Codec();
// Codec* deser = new Codec();
// string tree = ser->serialize(root);
// TreeNode* ans = deser->deserialize(tree);
// return ans;