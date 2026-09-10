class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);  // Sort lexicographically
        
        List<List<String>> result = new ArrayList<>();
        StringBuilder prefix = new StringBuilder();
        
        for (char c : searchWord.toCharArray()) {
            prefix.append(c);
            String currentPrefix = prefix.toString();
            
            // Binary search for the first product that starts with currentPrefix
            int start = lowerBound(products, currentPrefix);
            
            List<String> suggestions = new ArrayList<>();
            for (int i = start; i < Math.min(start + 3, products.length); i++) {
                if (products[i].startsWith(currentPrefix)) {
                    suggestions.add(products[i]);
                } else {
                    break;
                }
            }
            result.add(suggestions);
        }
        
        return result;
    }
    
    // Returns the smallest index i such that products[i] >= target
    private int lowerBound(String[] products, String target) {
        int left = 0, right = products.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (products[mid].compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}