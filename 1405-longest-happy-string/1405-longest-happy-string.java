import java.util.*;

class Solution {
    public String longestDiverseString(int a, int b, int c) {
        // Max-heap by count
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        if (a > 0) pq.offer(new int[]{a, 'a'});
        if (b > 0) pq.offer(new int[]{b, 'b'});
        if (c > 0) pq.offer(new int[]{c, 'c'});
        
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int count = top[0];
            char ch = (char) top[1];
            
            int len = sb.length();
            // Check if appending ch would create 3 in a row
            if (len >= 2 && sb.charAt(len - 1) == ch && sb.charAt(len - 2) == ch) {
                // Can't use ch — try the next best
                if (pq.isEmpty()) break;  // no alternative, stop
                int[] second = pq.poll();
                sb.append((char) second[1]);
                second[0]--;
                if (second[0] > 0) pq.offer(second);
                // Push top back for later
                pq.offer(top);
            } else {
                sb.append(ch);
                count--;
                if (count > 0) pq.offer(new int[]{count, ch});
            }
        }
        
        return sb.toString();
    }
}