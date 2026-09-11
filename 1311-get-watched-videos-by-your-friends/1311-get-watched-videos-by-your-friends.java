class Solution {
    public List<String> watchedVideosByFriends(
            List<List<String>> watchedVideos,
            int[][] friends,
            int id,
            int level) {
        
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(id);
        visited[id] = true;
        int currentLevel = 0;
        
        // BFS to find people at exactly `level` distance
        List<Integer> targetPeople = new ArrayList<>();
        
        while (!queue.isEmpty() && currentLevel <= level) {
            int size = queue.size();
            
            if (currentLevel == level) {
                // Collect everyone at this level
                while (!queue.isEmpty()) {
                    targetPeople.add(queue.poll());
                }
                break;
            }
            
            for (int i = 0; i < size; i++) {
                int person = queue.poll();
                for (int friend : friends[person]) {
                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }
            currentLevel++;
        }
        
        // Count video frequencies
        Map<String, Integer> freq = new HashMap<>();
        for (int person : targetPeople) {
            for (String video : watchedVideos.get(person)) {
                freq.put(video, freq.getOrDefault(video, 0) + 1);
            }
        }
        
        // Sort: by frequency ascending, then alphabetically
        List<String> result = new ArrayList<>(freq.keySet());
        result.sort((a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);
            if (fa != fb) return fa - fb;
            return a.compareTo(b);
        });
        
        return result;
    }
}