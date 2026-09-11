class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            // Found a zero
            if (arr[curr] == 0) {
                return true;
            }
            
            // Try both jumps
            int forward = curr + arr[curr];
            int backward = curr - arr[curr];
            
            if (forward < n && !visited[forward]) {
                visited[forward] = true;
                queue.offer(forward);
            }
            
            if (backward >= 0 && !visited[backward]) {
                visited[backward] = true;
                queue.offer(backward);
            }
        }
        
        return false;
    }
}