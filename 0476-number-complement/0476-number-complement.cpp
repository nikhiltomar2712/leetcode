class Solution {
public:
    int findComplement(int num) {
        // Find the bitmask: all 1s with the same length as num's binary representation
        unsigned int mask = ~0; // All 1s
        
        // Shift left until mask has same number of bits as num
        while (num & mask) {
            mask <<= 1;
        }
        
        // XOR with inverted mask to get complement
        return ~num ^ mask; // or return ~mask ^ num;
    }
};