class Solution {
public:
    string smallestGoodBase(string n) {
        long long num = stoll(n);
        
        // Try bases from largest possible m (number of 1s) to smallest
        for (long long m = log2(num + 1); m >= 2; m--) {
            long long left = 2;
            long long right = pow(num, 1.0 / (m - 1)) + 1;
            
            while (left <= right) {
                long long k = left + (right - left) / 2;
                
                // Calculate sum = 1 + k + k^2 + ... + k^(m-1)
                long long sum = 0, cur = 1;
                bool valid = true;
                
                for (long long i = 0; i < m; i++) {
                    sum += cur;
                    
                    // If sum exceeds num, we can stop
                    if (sum > num) {
                        valid = false;
                        break;
                    }
                    
                    // Multiply cur by k for next iteration, but check overflow
                    if (i < m - 1) {
                        if (cur > LLONG_MAX / k) {
                            // Overflow would occur, sum will definitely be > num
                            valid = false;
                            break;
                        }
                        cur *= k;
                    }
                }
                
                if (!valid) {
                    // Sum is too large (or overflow), search left
                    right = k - 1;
                } else if (sum == num) {
                    return to_string(k);
                } else if (sum < num) {
                    left = k + 1;
                } else {
                    right = k - 1;
                }
            }
        }
        
        // Base n-1: num represented as "11"
        return to_string(num - 1);
    }
};