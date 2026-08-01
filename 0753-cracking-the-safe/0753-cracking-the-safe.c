char* crackSafe(int n, int k) {
    // Special case n == 1
    if (n == 1) {
        char* res = (char*)malloc(k + 1);
        for (int i = 0; i < k; i++) {
            res[i] = '0' + i;
        }
        res[k] = '\0';
        return res;
    }
    
    int nodes = 1;
    for (int i = 1; i < n; i++) nodes *= k;   // k^(n-1)
    
    // used[node][digit] – whether we have used the edge digit from this node
    bool** used = (bool**)malloc(nodes * sizeof(bool*));
    for (int i = 0; i < nodes; i++) {
        used[i] = (bool*)calloc(k, sizeof(bool));
    }
    
    // Maximum length of the answer
    int maxLen = 1;
    for (int i = 0; i < n; i++) maxLen *= k;   // k^n
    maxLen += n - 1;
    
    char* ans = (char*)malloc(maxLen + 1);
    int idx = 0;
    
    // Hierholzer’s algorithm (iterative)
    int* stack = (int*)malloc((maxLen + 5) * sizeof(int));
    int top = 0;
    stack[top++] = 0;          // start from node 0
    
    while (top > 0) {
        int u = stack[top - 1];
        int d;
        for (d = 0; d < k; d++) {
            if (!used[u][d]) break;
        }
        if (d < k) {
            // take the edge
            used[u][d] = true;
            int v = (u * k + d) % nodes;
            stack[top++] = v;
        } else {
            // no more edges – record the digit that led into this node
            // (the last digit of the current node)
            if (top > 1) {
                ans[idx++] = '0' + (u % k);
            }
            top--;
        }
    }
    
    // Prepend the initial (n-1) zeros (the starting node)
    for (int i = 0; i < n - 1; i++) {
        ans[idx++] = '0';
    }
    ans[idx] = '\0';
    
    // Reverse because we built the path backwards
    for (int i = 0, j = idx - 1; i < j; i++, j--) {
        char t = ans[i];
        ans[i] = ans[j];
        ans[j] = t;
    }
    
    // Cleanup
    for (int i = 0; i < nodes; i++) free(used[i]);
    free(used);
    free(stack);
    
    return ans;
}