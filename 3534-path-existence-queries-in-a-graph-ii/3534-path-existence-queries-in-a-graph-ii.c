#include <stdlib.h>
#include <string.h>
#include <limits.h>

typedef struct {
    int num;
    int idx;
} Pair;

int comparePairs(const void* a, const void* b) {
    return ((Pair*)a)->num - ((Pair*)b)->num;
}

int minJumps(int** jump, int start, int end, int level) {
    if (start == end) return 0;
    if (jump[start][0] >= end) return 1;
    if (jump[start][level] < end) return INT_MAX;
    int j = level;
    for (; j >= 0; --j) {
        if (jump[start][j] < end) break;
    }
    int next = jump[start][j];
    int res = minJumps(jump, next, end, j);
    if (res == INT_MAX) return INT_MAX;
    return (1 << j) + res;
}

int* pathExistenceQueries(int n, int* nums, int numsSize, int maxDiff, int** queries, int queriesSize, int* queriesColSize, int* returnSize) {
    *returnSize = queriesSize;
    int* ans = (int*)malloc(queriesSize * sizeof(int));
    if (n == 0) return ans;

    Pair* sortedPairs = (Pair*)malloc(n * sizeof(Pair));
    for (int i = 0; i < n; ++i) {
        sortedPairs[i].num = nums[i];
        sortedPairs[i].idx = i;
    }
    qsort(sortedPairs, n, sizeof(Pair), comparePairs);

    int* sortedNums = (int*)malloc(n * sizeof(int));
    int* indexMap = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; ++i) {
        sortedNums[i] = sortedPairs[i].num;
        indexMap[sortedPairs[i].idx] = i;
    }
    free(sortedPairs);

    // Compute maxLevel (log2(n) + 1 approx)
    int maxLevel = 0;
    int temp = n;
    while (temp) {
        maxLevel++;
        temp >>= 1;
    }
    maxLevel += 2;  // Safe upper bound

    int** jump = (int**)malloc(n * sizeof(int*));
    for (int i = 0; i < n; ++i) {
        jump[i] = (int*)malloc(maxLevel * sizeof(int));
        memset(jump[i], 0, maxLevel * sizeof(int));
    }

    // Build jump[0]: farthest reachable in sorted order
    int right = 0;
    for (int i = 0; i < n; ++i) {
        while (right + 1 < n && sortedNums[right + 1] - sortedNums[i] <= maxDiff) {
            ++right;
        }
        jump[i][0] = right;
    }

    // Binary lifting table
    for (int level = 1; level < maxLevel; ++level) {
        for (int i = 0; i < n; ++i) {
            int prev = jump[i][level - 1];
            jump[i][level] = jump[prev][level - 1];
        }
    }

    for (int q = 0; q < queriesSize; ++q) {
        int u = queries[q][0];
        int v = queries[q][1];
        if (u == v) {
            ans[q] = 0;
            continue;
        }
        int uIndex = indexMap[u];
        int vIndex = indexMap[v];
        int startIdx = uIndex < vIndex ? uIndex : vIndex;
        int endIdx = uIndex > vIndex ? uIndex : vIndex;
        int res = minJumps(jump, startIdx, endIdx, maxLevel - 1);
        ans[q] = (res == INT_MAX ? -1 : res);
    }

    // Free memory
    for (int i = 0; i < n; ++i) free(jump[i]);
    free(jump);
    free(sortedNums);
    free(indexMap);

    return ans;
}