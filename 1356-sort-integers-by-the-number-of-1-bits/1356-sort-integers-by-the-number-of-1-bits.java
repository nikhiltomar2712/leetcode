import java.util.Arrays;

class Solution {
    public int[] sortByBits(int[] arr) {
        // Box into Integer[] so we can use a custom comparator with Arrays.sort
        Integer[] boxed = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boxed[i] = arr[i];
        }

        Arrays.sort(boxed, (a, b) -> {
            int cmp = Integer.bitCount(a) - Integer.bitCount(b);
            if (cmp != 0) return cmp;
            return a - b; // tie-break by value
        });

        // Unbox back to int[]
        for (int i = 0; i < arr.length; i++) {
            arr[i] = boxed[i];
        }
        return arr;
    }
}