class Solution {
public:
    bool isValid(string code) {
        stack<string> stk;
        int n = code.size();
        
        for (int i = 0; i < n; ++i) {
            // After the first character, stack must never be empty
            // (everything must stay inside the outer tag)
            if (i > 0 && stk.empty()) return false;
            
            // CDATA section: <![CDATA[ ... ]]>
            if (i + 9 <= n && code.substr(i, 9) == "<![CDATA[") {
                size_t j = code.find("]]>", i + 9);
                if (j == string::npos) return false;
                i = j + 2;          // move past "]]>"
            }
            // End tag: </TAG_NAME>
            else if (i + 2 <= n && code.substr(i, 2) == "</") {
                size_t j = code.find('>', i + 2);
                if (j == string::npos) return false;
                string tag = code.substr(i + 2, j - (i + 2));
                if (!isValidTag(tag) || stk.empty() || stk.top() != tag)
                    return false;
                stk.pop();
                i = j;
            }
            // Start tag: <TAG_NAME>
            else if (code[i] == '<') {
                size_t j = code.find('>', i + 1);
                if (j == string::npos) return false;
                string tag = code.substr(i + 1, j - (i + 1));
                if (!isValidTag(tag)) return false;
                stk.push(tag);
                i = j;
            }
            // else: ordinary character → just continue
        }
        return stk.empty();
    }
    
private:
    bool isValidTag(const string& tag) {
        int len = tag.size();
        if (len < 1 || len > 9) return false;
        for (char c : tag)
            if (!isupper(c)) return false;
        return true;
    }
};