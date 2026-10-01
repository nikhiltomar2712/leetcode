class Solution {
    public int minSwaps(String s) {
        int balance = 0, maxImbalance = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') balance++;
            else balance--;
            maxImbalance = Math.min(maxImbalance, balance);
        }
        return (-maxImbalance + 1) / 2;
    }
}