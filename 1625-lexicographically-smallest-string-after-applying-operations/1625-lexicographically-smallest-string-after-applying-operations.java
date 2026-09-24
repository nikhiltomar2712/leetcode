import java.util.*;

class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        String smallest = s;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            // Update smallest
            if (curr.compareTo(smallest) < 0) {
                smallest = curr;
            }

            // Operation 1: Add a to all odd indices
            char[] arr = curr.toCharArray();
            for (int i = 1; i < arr.length; i += 2) {
                arr[i] = (char) ('0' + (arr[i] - '0' + a) % 10);
            }
            String added = new String(arr);
            if (visited.add(added)) {
                queue.offer(added);
            }

            // Operation 2: Rotate right by b positions
            int n = curr.length();
            String rotated = curr.substring(n - b) + curr.substring(0, n - b);
            if (visited.add(rotated)) {
                queue.offer(rotated);
            }
        }

        return smallest;
    }
}