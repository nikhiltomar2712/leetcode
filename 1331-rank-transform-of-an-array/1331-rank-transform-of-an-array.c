#include <stdlib.h>

typedef struct {
    int value;
    int index;
} Pair;

int compare(const void* a, const void* b) {
    return ((Pair*)a)->value - ((Pair*)b)->value;
}

int* arrayRankTransform(int* arr, int arrSize, int* returnSize) {
    *returnSize = arrSize;
    
    if (arrSize == 0) {
        return NULL;
    }
    
    // Create and initialize pairs
    Pair* pairs = (Pair*)malloc(arrSize * sizeof(Pair));
    for (int i = 0; i < arrSize; i++) {
        pairs[i].value = arr[i];
        pairs[i].index = i;
    }
    
    // Sort by value
    qsort(pairs, arrSize, sizeof(Pair), compare);
    
    // Assign ranks
    int* result = (int*)malloc(arrSize * sizeof(int));
    int rank = 1;
    result[pairs[0].index] = rank;
    
    for (int i = 1; i < arrSize; i++) {
        if (pairs[i].value != pairs[i - 1].value) {
            rank++;
        }
        result[pairs[i].index] = rank;
    }
    
    free(pairs);
    return result;
}