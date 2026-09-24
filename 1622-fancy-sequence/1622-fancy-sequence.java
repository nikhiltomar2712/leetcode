class Fancy {
    private static final int MOD = 1_000_000_007;
    private List<Long> seq;      // stores raw/normalized values
    private long a;              // global multiplier
    private long b;              // global addend

    public Fancy() {
        seq = new ArrayList<>();
        a = 1;
        b = 0;
    }

    public void append(int val) {
        // Store (val - b) * a^(-1) mod MOD
        long normalized = (val - b + MOD) % MOD;
        normalized = normalized * modPow(a, MOD - 2) % MOD;
        seq.add(normalized);
    }

    public void addAll(int inc) {
        b = (b + inc) % MOD;
    }

    public void multAll(int m) {
        a = (a * m) % MOD;
        b = (b * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= seq.size()) return -1;
        return (int) ((a * seq.get(idx) + b) % MOD);
    }

    // Fast modular exponentiation
    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}