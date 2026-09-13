import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    // prefix[i] = product of all numbers added since the last zero (up to index i-1)
    private List<Integer> prefix;
    private int lastZeroIndex; // index in prefix list where the last zero reset occurred

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);       // sentinel so prefix.get(0) = 1
        lastZeroIndex = 0;
    }

    public void add(int num) {
        if (num == 0) {
            // Reset: clear everything and start fresh
            prefix = new ArrayList<>();
            prefix.add(1);
            lastZeroIndex = 0;
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        int size = prefix.size() - 1; // number of elements since last zero
        if (k > size) {
            // The last k elements include a zero
            return 0;
        }
        // prefix[size] / prefix[size - k]
        return prefix.get(size) / prefix.get(size - k);
    }
}