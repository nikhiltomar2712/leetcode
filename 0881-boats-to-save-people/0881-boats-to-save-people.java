class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int boats = 0;
        
        while (left <= right) {
            // Try to put the lightest and heaviest together
            if (people[left] + people[right] <= limit) {
                left++;   // lightest also boards
            }
            // Heaviest always boards
            right--;
            boats++;
        }
        
        return boats;
    }
}