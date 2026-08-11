char* findReplaceString(char* s, int* indices, int indicesSize, char** sources, int sourcesSize, char** targets, int targetsSize) {
    int n = strlen(s);
    int k = indicesSize;
    
    // Map: original index -> replacement index in the parallel arrays (-1 if none)
    int* replace_at = (int*)malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        replace_at[i] = -1;
    }
    
    // Check each possible replacement against the original string
    for (int i = 0; i < k; i++) {
        int idx = indices[i];
        char* src = sources[i];
        int src_len = strlen(src);
        
        // Verify the source matches at the given index
        if (idx + src_len <= n && strncmp(s + idx, src, src_len) == 0) {
            replace_at[idx] = i;
        }
    }
    
    // Build the result string
    // Worst case: every char replaced by a 50-char target
    char* result = (char*)malloc(n * 50 + 1);
    int pos = 0;
    
    int i = 0;
    while (i < n) {
        if (replace_at[i] != -1) {
            int r = replace_at[i];
            char* tgt = targets[r];
            int tgt_len = strlen(tgt);
            memcpy(result + pos, tgt, tgt_len);
            pos += tgt_len;
            i += strlen(sources[r]);  // skip the matched source length
        } else {
            result[pos++] = s[i];
            i++;
        }
    }
    result[pos] = '\0';
    
    free(replace_at);
    return result;
}