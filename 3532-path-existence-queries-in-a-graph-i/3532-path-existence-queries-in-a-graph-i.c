/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
bool* pathExistenceQueries(int n, int* nums, int numsSize, int maxDiff, int** queries, int queriesSize, int* queriesColSize, int* returnSize) {
    // Allocate memory for the component ID of each node
    int* comp = (int*)malloc(n * sizeof(int));
    comp[0] = 0; // First node starts component 0

    // Assign component IDs based on adjacent differences
    for (int i = 1; i < n; i++) {
        if (nums[i] - nums[i - 1] <= maxDiff) {
            comp[i] = comp[i - 1]; // Same component
        } else {
            comp[i] = comp[i - 1] + 1; // New component
        }
    }

    // Allocate result array
    bool* answer = (bool*)malloc(queriesSize * sizeof(bool));
    *returnSize = queriesSize;

    // Answer each query by comparing component IDs
    for (int i = 0; i < queriesSize; i++) {
        int u = queries[i][0];
        int v = queries[i][1];
        answer[i] = (comp[u] == comp[v]);
    }

    free(comp); // Free temporary array
    return answer;
}