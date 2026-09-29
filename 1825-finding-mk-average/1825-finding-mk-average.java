class MKAverage {
    private int m, k;
    private Queue<Integer> queue;
    private TreeMap<Integer, Integer> low, mid, high;
    private long sumMid;
    private int sizeLow, sizeMid, sizeHigh;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.queue = new LinkedList<>();
        this.low = new TreeMap<>();
        this.mid = new TreeMap<>();
        this.high = new TreeMap<>();
        this.sumMid = 0;
        this.sizeLow = 0;
        this.sizeMid = 0;
        this.sizeHigh = 0;
    }

    public void addElement(int num) {
        queue.offer(num);
        if (queue.size() <= m) {
            addToMid(num);
            if (queue.size() == m) {
                balance();
            }
        } else {
            int removed = queue.poll();
            removeElement(removed);
            addToMid(num);
            balance();
        }
    }

    private void addToMid(int num) {
        mid.merge(num, 1, Integer::sum);
        sumMid += num;
        sizeMid++;
    }

    private void removeElement(int num) {
        if (low.containsKey(num)) {
            removeFrom(low, num);
            sizeLow--;
        } else if (mid.containsKey(num)) {
            removeFrom(mid, num);
            sumMid -= num;
            sizeMid--;
        } else {
            removeFrom(high, num);
            sizeHigh--;
        }
    }

    private void removeFrom(TreeMap<Integer, Integer> map, int num) {
        map.merge(num, -1, Integer::sum);
        if (map.get(num) == 0) map.remove(num);
    }

    private void balance() {
        while (sizeLow < k) {
            int num = mid.firstKey();
            removeFrom(mid, num);
            sumMid -= num;
            sizeMid--;
            low.merge(num, 1, Integer::sum);
            sizeLow++;
        }
        while (sizeHigh < k) {
            int num = mid.lastKey();
            removeFrom(mid, num);
            sumMid -= num;
            sizeMid--;
            high.merge(num, 1, Integer::sum);
            sizeHigh++;
        }
        while (sizeLow > k) {
            int num = low.lastKey();
            removeFrom(low, num);
            sizeLow--;
            mid.merge(num, 1, Integer::sum);
            sumMid += num;
            sizeMid++;
        }
        while (sizeHigh > k) {
            int num = high.firstKey();
            removeFrom(high, num);
            sizeHigh--;
            mid.merge(num, 1, Integer::sum);
            sumMid += num;
            sizeMid++;
        }
        while (!low.isEmpty() && !mid.isEmpty() && low.lastKey() > mid.firstKey()) {
            int l = low.lastKey(), mi = mid.firstKey();
            removeFrom(low, l);
            removeFrom(mid, mi);
            sizeLow--;
            sizeMid--;
            sumMid -= mi;
            low.merge(mi, 1, Integer::sum);
            sizeLow++;
            mid.merge(l, 1, Integer::sum);
            sumMid += l;
            sizeMid++;
        }
        while (!mid.isEmpty() && !high.isEmpty() && mid.lastKey() > high.firstKey()) {
            int mi = mid.lastKey(), h = high.firstKey();
            removeFrom(mid, mi);
            removeFrom(high, h);
            sizeMid--;
            sizeHigh--;
            sumMid -= mi;
            mid.merge(h, 1, Integer::sum);
            sumMid += h;
            sizeMid++;
            high.merge(mi, 1, Integer::sum);
            sizeHigh++;
        }
    }

    public int calculateMKAverage() {
        if (queue.size() < m) return -1;
        return (int) (sumMid / sizeMid);
    }
}