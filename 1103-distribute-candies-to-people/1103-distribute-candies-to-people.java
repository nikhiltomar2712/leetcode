class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int give = 1;          // number of candies to give in the current turn
        int i = 0;             // current person index
        
        while (candies > 0) {
            // Give min(give, remaining candies) to the current person
            int actual = Math.min(give, candies);
            result[i] += actual;
            candies -= actual;
            
            // Move to next person and next amount
            give++;
            i = (i + 1) % num_people;
        }
        
        return result;
    }
}