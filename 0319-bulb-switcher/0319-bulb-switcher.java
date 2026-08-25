class Solution {
    public int bulbSwitch(int n) {
        // The number of bulbs that remain on is the count of perfect squares ≤ n
        return (int) Math.sqrt(n);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test cases
        System.out.println(solution.bulbSwitch(3));   // Output: 1
        System.out.println(solution.bulbSwitch(0));   // Output: 0
        System.out.println(solution.bulbSwitch(1));   // Output: 1
        System.out.println(solution.bulbSwitch(9));   // Output: 3
        System.out.println(solution.bulbSwitch(10));  // Output: 3
        System.out.println(solution.bulbSwitch(16));  // Output: 4
    }
}