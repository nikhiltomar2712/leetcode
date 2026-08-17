class Solution {
    public int numSpecialEquivGroups(String[] words) {
        Set<String> groupSignatures = new HashSet<>();
        
        for (String word : words) {
            // Create signature for this word
            String signature = getSignature(word);
            groupSignatures.add(signature);
        }
        
        return groupSignatures.size();
    }
    
    private String getSignature(String word) {
        // Count characters at even and odd positions separately
        int[] evenCount = new int[26];
        int[] oddCount = new int[26];
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (i % 2 == 0) {
                evenCount[c - 'a']++;
            } else {
                oddCount[c - 'a']++;
            }
        }
        
        // Build signature as concatenation of both counts
        StringBuilder signature = new StringBuilder();
        for (int count : evenCount) {
            signature.append(count).append('#');
        }
        for (int count : oddCount) {
            signature.append(count).append('#');
        }
        
        return signature.toString();
    }
}