class Solution {
    public String reorganizeString(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Max-heap: [frequency, character]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(new int[]{freq[i], i});
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] prev = null;   // previously used character

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            sb.append((char) (curr[1] + 'a'));
            curr[0]--;

            // Put the previous character back if it still has remaining count
            if (prev != null && prev[0] > 0) {
                pq.offer(prev);
            }

            prev = curr;
        }

        // If we couldn't place all characters, it was impossible
        return sb.length() == s.length() ? sb.toString() : "";
    }
}