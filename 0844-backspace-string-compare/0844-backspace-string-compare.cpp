#include <string>
#include <stack>
using namespace std;

class Solution {
public:
    bool backspaceCompare(string s, string t) {
        return buildString(s) == buildString(t);
    }
    
private:
    string buildString(const string& str) {
        stack<char> st;
        for (char c : str) {
            if (c != '#') {
                st.push(c);
            } else if (!st.empty()) {
                st.pop();
            }
        }
        // Convert stack to string
        string result;
        while (!st.empty()) {
            result += st.top();
            st.pop();
        }
        reverse(result.begin(), result.end()); // To maintain original order
        return result;
    }
};