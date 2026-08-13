import java.util.*;

class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> result = new ArrayList<>();
        backtrack(num, result, 0);
        return result;
    }
    
    private boolean backtrack(String num, List<Integer> result, int start) {
        // If we've used all digits and have at least 3 numbers, we found a valid sequence
        if (start == num.length() && result.size() >= 3) {
            return true;
        }
        
        // Try to form the next number starting from 'start'
        for (int i = start; i < num.length(); i++) {
            // Skip numbers with leading zero (except the number 0 itself)
            if (num.charAt(start) == '0' && i > start) {
                break;
            }
            
            // Extract the current number as a long to avoid overflow
            long current = Long.parseLong(num.substring(start, i + 1));
            
            // Prune: if current exceeds 32-bit signed integer range, stop trying larger numbers
            if (current > Integer.MAX_VALUE) {
                break;
            }
            
            int size = result.size();
            
            // If we already have at least 2 numbers, check if current = last + second_last
            if (size >= 2) {
                long sum = (long) result.get(size - 1) + (long) result.get(size - 2);
                if (current > sum) {
                    // If current is already larger than the required sum, break (numbers only get larger)
                    break;
                } else if (current < sum) {
                    // If current is smaller, try adding more digits to this number
                    continue;
                }
            }
            
            // current is a valid next number
            result.add((int) current);
            
            // Recurse to build the rest of the sequence
            if (backtrack(num, result, i + 1)) {
                return true;
            }
            
            // Backtrack: remove the last number and try a different split
            result.remove(result.size() - 1);
        }
        
        return false;
    }
}