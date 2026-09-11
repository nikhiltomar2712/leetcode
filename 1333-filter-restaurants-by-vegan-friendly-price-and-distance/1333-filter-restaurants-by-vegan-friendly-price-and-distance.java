class Solution {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> filtered = new ArrayList<>();
        
        for (int[] r : restaurants) {
            int id = r[0], rating = r[1], vegan = r[2], price = r[3], distance = r[4];
            
            // Filter by veganFriendly (only if veganFriendly == 1)
            if (veganFriendly == 1 && vegan == 0) continue;
            // Filter by price and distance
            if (price > maxPrice || distance > maxDistance) continue;
            
            filtered.add(r);
        }
        
        // Sort by rating desc, then id desc
        filtered.sort((a, b) -> {
            if (a[1] != b[1]) return b[1] - a[1]; // rating descending
            return b[0] - a[0];                    // id descending
        });
        
        List<Integer> result = new ArrayList<>();
        for (int[] r : filtered) {
            result.add(r[0]);
        }
        return result;
    }
}