class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;
        for (int i = 0; i < n; i += 2) {
            int partner = row[i] ^ 1;   // partner of row[i]
            if (row[i + 1] != partner) {
                // Swap the person at i+1 with the partner
                int partnerIdx = pos[partner];
                
                // Update positions
                pos[row[i + 1]] = partnerIdx;
                pos[partner] = i + 1;
                
                // Perform the swap
                row[partnerIdx] = row[i + 1];
                row[i + 1] = partner;
                
                swaps++;
            }
        }
        return swaps;
    }
}