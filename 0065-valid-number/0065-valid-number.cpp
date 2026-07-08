#include <cctype>
#include <string>
using namespace std;

class Solution {
public:
    bool isNumber(string s) {
        int i = 0, n = s.size();
        // Optional sign
        if (i < n && (s[i] == '+' || s[i] == '-')) i++;
        bool hasDigit = false;
        // Digits before dot
        while (i < n && isdigit(s[i])) {
            hasDigit = true;
            i++;
        }
        // Dot and digits after dot
        if (i < n && s[i] == '.') {
            i++;
            while (i < n && isdigit(s[i])) {
                hasDigit = true;
                i++;
            }
        }
        // Must have at least one digit
        if (!hasDigit) return false;
        // Exponent part
        if (i < n && (s[i] == 'e' || s[i] == 'E')) {
            i++;
            // Optional sign after e
            if (i < n && (s[i] == '+' || s[i] == '-')) i++;
            bool hasExpDigit = false;
            while (i < n && isdigit(s[i])) {
                hasExpDigit = true;
                i++;
            }
            if (!hasExpDigit) return false;
        }
        return i == n;
    }
};