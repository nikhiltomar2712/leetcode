char* shortestCompletingWord(char* licensePlate, char** words, int wordsSize) {
    int need[26] = {0};
    
    // Count required letters (ignore non-letters, case-insensitive)
    for (int i = 0; licensePlate[i]; i++) {
        char c = licensePlate[i];
        if (c >= 'A' && c <= 'Z') c += 32;   // to lowercase
        if (c >= 'a' && c <= 'z') {
            need[c - 'a']++;
        }
    }
    
    char* ans = NULL;
    int minLen = 16;   // words[i].length <= 15
    
    for (int i = 0; i < wordsSize; i++) {
        int cnt[26] = {0};
        char* w = words[i];
        int len = 0;
        
        for (int j = 0; w[j]; j++) {
            cnt[w[j] - 'a']++;
            len++;
        }
        
        // Check if this word covers all required letters
        int ok = 1;
        for (int j = 0; j < 26; j++) {
            if (cnt[j] < need[j]) {
                ok = 0;
                break;
            }
        }
        
        if (ok && len < minLen) {
            minLen = len;
            ans = w;
        }
    }
    
    return ans;
}