class Solution {
    // 8 directions: E, SE, S, SW, W, NW, N, NE
    private static final int[][] DIRS = {
        {0, 1}, {1, 1}, {1, 0}, {1, -1},
        {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
    };
    
    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<Integer, Integer> freq = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : DIRS) {
                    int num = mat[i][j];
                    int r = i + dir[0];
                    int c = j + dir[1];
                    
                    while (r >= 0 && r < m && c >= 0 && c < n) {
                        num = num * 10 + mat[r][c];
                        // Only consider numbers > 10
                        if (num > 10 && isPrime(num)) {
                            freq.put(num, freq.getOrDefault(num, 0) + 1);
                        }
                        r += dir[0];
                        c += dir[1];
                    }
                }
            }
        }
        
        int maxFreq = 0;
        int answer = -1;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int prime = entry.getKey();
            int count = entry.getValue();
            if (count > maxFreq || (count == maxFreq && prime > answer)) {
                maxFreq = count;
                answer = prime;
            }
        }
        
        return answer;
    }
    
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; (long) i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}