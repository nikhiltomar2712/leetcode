class Bitset {
    private int[] bits;
    private int size;
    private int ones;
    private boolean flipped;

    public Bitset(int size) {
        this.size = size;
        this.bits = new int[size];
        this.ones = 0;
        this.flipped = false;
    }

    public void fix(int idx) {
        if (flipped) {
            if (bits[idx] == 1) {
                bits[idx] = 0;
                ones++;
            }
        } else {
            if (bits[idx] == 0) {
                bits[idx] = 1;
                ones++;
            }
        }
    }

    public void unfix(int idx) {
        if (flipped) {
            if (bits[idx] == 0) {
                bits[idx] = 1;
                ones--;
            }
        } else {
            if (bits[idx] == 1) {
                bits[idx] = 0;
                ones--;
            }
        }
    }

    public void flip() {
        flipped = !flipped;
        ones = size - ones;
    }

    public boolean all() {
        return ones == size;
    }

    public boolean one() {
        return ones > 0;
    }

    public int count() {
        return ones;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (flipped) {
                sb.append(bits[i] == 1 ? '0' : '1');
            } else {
                sb.append(bits[i]);
            }
        }
        return sb.toString();
    }
}