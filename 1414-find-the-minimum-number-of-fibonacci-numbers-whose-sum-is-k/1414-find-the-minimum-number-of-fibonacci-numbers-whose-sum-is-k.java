class Solution {
    public int findMinFibonacciNumbers(int k) {
        // Generate Fibonacci numbers up to k
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);
        while (true) {
            int next = fib.get(fib.size() - 1) + fib.get(fib.size() - 2);
            if (next > k) break;
            fib.add(next);
        }

        int count = 0;
        int i = fib.size() - 1;

        while (k > 0) {
            while (fib.get(i) > k) i--;
            k -= fib.get(i);
            count++;
        }

        return count;
    }
}