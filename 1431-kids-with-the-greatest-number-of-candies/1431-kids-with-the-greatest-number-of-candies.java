import java.util.*;

class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        for (int c : candies) {
            maxCandies = Math.max(maxCandies, c);
        }

        List<Boolean> result = new ArrayList<>(candies.length);
        for (int c : candies) {
            result.add(c + extraCandies >= maxCandies);
        }
        return result;
    }
}