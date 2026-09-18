class Solution {
    public boolean isPathCrossing(String path) {
        Set<String> visited = new HashSet<>();
        int x = 0, y = 0;
        visited.add(x + "," + y);

        for (char c : path.toCharArray()) {
            switch (c) {
                case 'N': y++; break;
                case 'S': y--; break;
                case 'E': x++; break;
                case 'W': x--; break;
            }
            String key = x + "," + y;
            if (visited.contains(key)) return true;
            visited.add(key);
        }
        return false;
    }
}