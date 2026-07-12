class Solution {
public:
    int countDigitOne(int n) {
        int count = 0;
        
        // Process each digit place (units, tens, hundreds, ...)
        for (long long place = 1; place <= n; place *= 10) {
            long long divider = place * 10;
            
            // Complete cycles + partial cycle
            count += (n / divider) * place;
            count += min(max(n % divider - place + 1, 0LL), place);
        }
        
        return count;
    }
};