import java.util.*;

class Solution {
    public long beautifulSubstrings(String s, int k) {
        int n = s.length();
        // 1. Find the minimum vowel count x that satisfies x*x % k == 0
        // Actually looking for the smallest l such that l*l % (4*k) == 0, because the length is 2*l
        int l = 1;
        while (l * l % (k * 4) != 0) {
            l++;
        }
        
        // 2. Use hash map to record (index % l, diff) occurrences
        // diff = number of vowels - number of consonants
        Map<Integer, Map<Integer, Integer>> seen = new HashMap<>();
        for (int i = 0; i < l; i++) {
            seen.put(i, new HashMap<>());
        }
        // Initial state: before index -1, diff is 0
        // Note: To handle the first substring of length l, we put the initial state at (l-1) % l
        seen.get(l - 1).put(0, 1);
        
        long ans = 0;
        int diff = 0;
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        
        for (int i = 0; i < n; i++) {
            // Update diff: +1 for vowels, -1 for consonants
            if (vowels.contains(s.charAt(i))) {
                diff++;
            } else {
                diff--;
            }
            
            // Current index modulo l
            int mod = i % l;
            Map<Integer, Integer> map = seen.get(mod);
            
            // If the same diff has appeared before, it means a beautiful substring is found
            int count = map.getOrDefault(diff, 0);
            ans += count;
            
            // Record current state
            map.put(diff, count + 1);
        }
        
        return ans;
    }
}