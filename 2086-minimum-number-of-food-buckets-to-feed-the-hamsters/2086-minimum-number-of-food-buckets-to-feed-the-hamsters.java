class Solution {
    public int minimumBuckets(String hamsters) {
        int n = hamsters.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (hamsters.charAt(i) == 'H') {
                if (i + 1 < n && hamsters.charAt(i + 1) == '.') {
                    count++;
                    i += 2;
                } else if (i - 1 >= 0 && hamsters.charAt(i - 1) == '.') {
                    count++;
                } else {
                    return -1;
                }
            }
        }
        return count;
    }
}