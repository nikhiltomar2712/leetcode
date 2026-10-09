class CountIntervals {
    TreeMap<Integer, Integer> map;
    int count;

    public CountIntervals() {
        map = new TreeMap<>();
        count = 0;
    }

    public void add(int left, int right) {
        Map.Entry<Integer, Integer> entry = map.floorEntry(left);
        if (entry != null && entry.getValue() >= left) {
            left = entry.getKey();
            right = Math.max(right, entry.getValue());
            count -= entry.getValue() - entry.getKey() + 1;
            map.remove(entry.getKey());
        }
        while (true) {
            Map.Entry<Integer, Integer> next = map.ceilingEntry(left);
            if (next != null && next.getKey() <= right + 1) {
                left = Math.min(left, next.getKey());
                right = Math.max(right, next.getValue());
                count -= next.getValue() - next.getKey() + 1;
                map.remove(next.getKey());
            } else {
                break;
            }
        }
        map.put(left, right);
        count += right - left + 1;
    }

    public int count() {
        return count;
    }
}