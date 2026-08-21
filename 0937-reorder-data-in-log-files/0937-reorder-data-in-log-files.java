class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            // Split into identifier and content
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            
            // Case 1: both are letter-logs
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]); // compare content
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);    // same content → compare id
            }
            
            // Case 2: one letter-log, one digit-log
            // letter-log should come first → return -1 if log1 is letter
            if (!isDigit1 && isDigit2) return -1;
            if (isDigit1 && !isDigit2) return 1;
            
            // Case 3: both digit-logs → keep original order
            return 0;
        });
        
        return logs;
    }
}