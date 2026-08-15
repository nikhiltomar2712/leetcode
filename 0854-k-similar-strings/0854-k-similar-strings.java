class Solution {
    public int kSimilarity(String s1, String s2) {
        if (s1.equals(s2)) return 0;
        
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(s1);
        visited.add(s1);
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(s2)) {
                    return steps;
                }
                
                // Generate only useful next states
                for (String next : getNextStates(curr, s2)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }
        
        return -1; // should never reach here because s2 is an anagram of s1
    }
    
    private List<String> getNextStates(String curr, String target) {
        List<String> res = new ArrayList<>();
        char[] arr = curr.toCharArray();
        int n = arr.length;
        
        // Find the first position where curr differs from target
        int i = 0;
        while (i < n && arr[i] == target.charAt(i)) {
            i++;
        }
        
        // Try swapping arr[i] with later positions that have the needed character
        // and are themselves incorrect
        for (int j = i + 1; j < n; j++) {
            if (arr[j] == target.charAt(i) && arr[j] != target.charAt(j)) {
                // Swap
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                
                res.add(new String(arr));
                
                // Swap back
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        
        return res;
    }
}