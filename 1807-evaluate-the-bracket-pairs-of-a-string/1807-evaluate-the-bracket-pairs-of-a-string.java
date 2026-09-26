import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build a map for O(1) lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = s.length();

        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = s.indexOf(')', i);
                String key = s.substring(i + 1, j);
                sb.append(map.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }
}