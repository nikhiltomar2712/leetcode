class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;  // number of $5 bills
        int ten = 0;   // number of $10 bills
        
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                // Need to give back one $5
                if (five == 0) return false;
                five--;
                ten++;
            } else { // bill == 20
                // Prefer to give one $10 + one $5 (greedy choice)
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } 
                // Otherwise try three $5 bills
                else if (five >= 3) {
                    five -= 3;
                } 
                // Cannot make change
                else {
                    return false;
                }
            }
        }
        
        return true;
    }
}