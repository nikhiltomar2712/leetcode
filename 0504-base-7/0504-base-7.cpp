class Solution {
public:
    string convertToBase7(int num) {
        // Handle special case
        if (num == 0) return "0";
        
        bool isNegative = num < 0;
        num = abs(num);
        
        string result = "";
        
        // Convert to base 7 by repeated division
        while (num > 0) {
            int remainder = num % 7;
            result = char('0' + remainder) + result;
            num /= 7;
        }
        
        // Add negative sign if needed
        if (isNegative) {
            result = "-" + result;
        }
        
        return result;
    }
};