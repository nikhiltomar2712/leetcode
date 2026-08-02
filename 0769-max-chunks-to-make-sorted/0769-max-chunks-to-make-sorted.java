class Solution {
    public int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        int currentSum = 0;
        int expectedSum = 0;
        
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            expectedSum += i; // Sum of numbers from 0 to i
            
            if (currentSum == expectedSum) {
                chunks++;
            }
        }
        
        return chunks;
    }
}