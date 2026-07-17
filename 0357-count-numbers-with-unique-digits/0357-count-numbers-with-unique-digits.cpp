#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n > 10) n = 10; // Can't have more than 10 unique digits
        
        int count = 10; // For 1-digit numbers (0-9)
        int unique = 9; // Start with 9 choices for the first non-zero digit
        
        for (int i = 2; i <= n; ++i) {
            unique *= (11 - i); // Available remaining digits: 9,8,7,...
            count += unique;
        }
        
        return count;
    }
};