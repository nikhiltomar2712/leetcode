class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] wordFreqs = new int[n];
        
        // Compute f() for all words
        for (int i = 0; i < n; i++) {
            wordFreqs[i] = f(words[i]);
        }
        
        // Sort the frequencies
        Arrays.sort(wordFreqs);
        
        int m = queries.length;
        int[] answer = new int[m];
        
        for (int i = 0; i < m; i++) {
            int qFreq = f(queries[i]);
            // Find the first index where wordFreqs[index] > qFreq
            int idx = upperBound(wordFreqs, qFreq);
            answer[i] = n - idx;
        }
        
        return answer;
    }
    
    // Returns frequency of the smallest character
    private int f(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int c : count) {
            if (c > 0) return c;   // first non-zero is the smallest char
        }
        return 0;
    }
    
    // Binary search: first index with value > target
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}