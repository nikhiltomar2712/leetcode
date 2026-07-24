class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        
        int maxFreq = 0;
        int maxCount = 0;
        
        for (int f : freq) {
            if (f > maxFreq) {
                maxFreq = f;
                maxCount = 1;
            } else if (f == maxFreq) {
                maxCount++;
            }
        }
        
        // Formula: (maxFreq - 1) * (n + 1) + maxCount
        int part1 = (maxFreq - 1) * (n + 1);
        int part2 = maxCount;
        
        return Math.max(tasks.length, part1 + part2);
    }
}