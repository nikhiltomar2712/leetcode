class SORTracker {
    private TreeSet<String[]> set;
    private String[] last;

    public SORTracker() {
        set = new TreeSet<>((a, b) -> {
            int sa = Integer.parseInt(a[0]), sb = Integer.parseInt(b[0]);
            if (sa != sb) return sb - sa;
            return a[1].compareTo(b[1]);
        });
        last = null;
    }

    public void add(String name, int score) {
        String[] entry = new String[]{String.valueOf(score), name};
        set.add(entry);
        if (last != null && set.comparator().compare(entry, last) < 0) {
            last = set.lower(last);
        }
    }

    public String get() {
        if (last == null) {
            last = set.first();
        } else {
            last = set.higher(last);
        }
        return last[1];
    }
}