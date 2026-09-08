/*
 * // This is the custom function interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *     // Returns f(x, y) for any given positive integers x and y.
 *     // Note that f(x, y) is increasing with respect to both x and y.
 *     public int f(int x, int y);
 * };
 */

class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        int x = 1;
        int y = 1000; // Start from the maximum possible y (as per constraints)
        
        // Two-pointer approach: x increases, y decreases
        while (x <= 1000 && y >= 1) {
            int value = customfunction.f(x, y);
            
            if (value == z) {
                // Found a valid pair
                result.add(Arrays.asList(x, y));
                // Move both pointers to find other combinations
                x++;
                y--;
            } else if (value < z) {
                // If f(x,y) < z, we need to increase x (since f is increasing with x)
                x++;
            } else {
                // If f(x,y) > z, we need to decrease y (since f is decreasing as y decreases)
                y--;
            }
        }
        
        return result;
    }
}