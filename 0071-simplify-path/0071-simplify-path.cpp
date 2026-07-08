#include <string>
#include <vector>
#include <sstream>
using namespace std;

class Solution {
public:
    string simplifyPath(string path) {
        vector<string> stack;
        stringstream ss(path);
        string token;
        while (getline(ss, token, '/')) {
            if (token.empty() || token == ".") {
                continue;
            } else if (token == "..") {
                if (!stack.empty()) {
                    stack.pop_back();
                }
            } else {
                stack.push_back(token);
            }
        }
        string result = "/";
        for (int i = 0; i < stack.size(); ++i) {
            result += stack[i];
            if (i < stack.size() - 1) {
                result += "/";
            }
        }
        return result;
    }
};