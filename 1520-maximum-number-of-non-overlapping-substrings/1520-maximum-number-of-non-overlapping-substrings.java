class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != first[s.charAt(i) - 'a']) continue;

            int start = i;
            int end = last[s.charAt(i) - 'a'];
            boolean valid = true;

            for (int j = start; j <= end; j++) {
                int c = s.charAt(j) - 'a';
                if (first[c] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, last[c]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Greedy: sort by end ascending, pick non-overlapping
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }

        return result;
    }
}