class DetectSquares {
    private Map<Integer, Map<Integer, Integer>> pts;

    public DetectSquares() {
        pts = new HashMap<>();
    }
    
    public void add(int[] point) {
        int x = point[0], y = point[1];
        pts.computeIfAbsent(x, k -> new HashMap<>()).merge(y, 1, Integer::sum);
    }
    
    public int count(int[] point) {
        int x = point[0], y = point[1];
        int res = 0;
        if (!pts.containsKey(x)) return 0;
        Map<Integer, Integer> yCnt = pts.get(x);
        for (Map.Entry<Integer, Map<Integer, Integer>> e : pts.entrySet()) {
            int x2 = e.getKey();
            if (x2 == x) continue;
            int d = x2 - x;
            Map<Integer, Integer> yCnt2 = e.getValue();
            res += yCnt2.getOrDefault(y, 0) * yCnt.getOrDefault(y + d, 0) * yCnt2.getOrDefault(y + d, 0);
            res += yCnt2.getOrDefault(y, 0) * yCnt.getOrDefault(y - d, 0) * yCnt2.getOrDefault(y - d, 0);
        }
        return res;
    }
}