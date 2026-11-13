class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int drunk = numBottles;   // Drink all initial bottles
        int empty = numBottles;   // They become empty

        while (empty >= numExchange) {
            // Exchange empty bottles for one full bottle
            empty -= numExchange;
            numExchange++;        // Cost increases for next exchange
            drunk++;              // Drink the new bottle
            empty++;              // It becomes empty after drinking
        }

        return drunk;
    }
}