char* maskPII(char* s) {
    int len = strlen(s);
    char* result = (char*)malloc(50);  // sufficient size
    
    // Check if email (contains '@')
    char* at = strchr(s, '@');
    if (at != NULL) {
        // Email case
        int name_end = at - s - 1;  // last char of name
        
        // First letter (lowercase)
        result[0] = tolower(s[0]);
        // 5 asterisks
        strcpy(result + 1, "*****");
        // Last letter of name + rest (all lowercase)
        int pos = 6;
        for (int i = name_end; i < len; i++) {
            result[pos++] = tolower(s[i]);
        }
        result[pos] = '\0';
        return result;
    }
    
    // Phone number case: extract digits
    char digits[20];
    int dlen = 0;
    for (int i = 0; i < len; i++) {
        if (isdigit(s[i])) {
            digits[dlen++] = s[i];
        }
    }
    digits[dlen] = '\0';
    
    int country = dlen - 10;
    char* last4 = digits + (dlen - 4);
    
    if (country == 0) {
        sprintf(result, "***-***-%s", last4);
    } else {
        // Build "+***-***-***-XXXX" style
        int pos = 0;
        result[pos++] = '+';
        for (int i = 0; i < country; i++) {
            result[pos++] = '*';
        }
        result[pos++] = '-';
        strcpy(result + pos, "***-***-");
        pos += 8;
        strcpy(result + pos, last4);
    }
    
    return result;
}