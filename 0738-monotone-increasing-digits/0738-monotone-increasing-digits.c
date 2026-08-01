int monotoneIncreasingDigits(int n) {
    if (n < 10) return n;
    
    // Convert n to digit array (most significant digit at index 0)
    char s[12];
    sprintf(s, "%d", n);
    int len = strlen(s);
    
    // Find the first position where digits decrease
    int i = 1;
    while (i < len && s[i - 1] <= s[i]) {
        i++;
    }
    
    if (i < len) {
        // Move left while we have a decreasing pair (handles consecutive equal digits)
        while (i > 0 && s[i - 1] > s[i]) {
            s[i - 1]--;
            i--;
        }
        // Set all digits after the decreased position to '9'
        for (int j = i + 1; j < len; j++) {
            s[j] = '9';
        }
    }
    
    return atoi(s);
}