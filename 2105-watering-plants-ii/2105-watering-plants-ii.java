class Solution {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int n = plants.length;
        int i = 0, j = n - 1;
        int a = capacityA, b = capacityB;
        int refills = 0;
        while (i < j) {
            if (a >= plants[i]) {
                a -= plants[i];
            } else {
                refills++;
                a = capacityA - plants[i];
            }
            if (b >= plants[j]) {
                b -= plants[j];
            } else {
                refills++;
                b = capacityB - plants[j];
            }
            i++;
            j--;
        }
        if (i == j) {
            if (Math.max(a, b) < plants[i]) refills++;
        }
        return refills;
    }
}