public class Solution {
    public int NumWaterBottles(int numBottles, int numExchange) {
        int total = numBottles;      // drink all full bottles first
        int empties = numBottles;    // each gives one empty

        while (empties >= numExchange) {
            int newFull = empties / numExchange;        // bottles gained
            total += newFull;
            empties = (empties % numExchange) + newFull; // leftover + new empties
        }

        return total;
    }
}